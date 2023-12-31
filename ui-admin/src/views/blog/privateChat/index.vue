<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div class="search" v-show="showSearch">
        <el-form :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">
          <el-form-item label="用户Aid" prop="userAId">
            <el-input v-model="queryParams.userAId" placeholder="请输入用户Aid" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="用户Bid" prop="userBId">
            <el-input v-model="queryParams.userBId" placeholder="请输入用户Bid" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="用户A未读消息数" prop="userAUnreadCount">
            <el-input v-model="queryParams.userAUnreadCount" placeholder="请输入用户A未读消息数" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="用户B未读消息数" prop="userBUnreadCount">
            <el-input v-model="queryParams.userBUnreadCount" placeholder="请输入用户B未读消息数" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
            <el-button icon="Refresh" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </transition>

    <el-card shadow="never">
      <template #header>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['blog:privateChat:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['blog:privateChat:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['blog:privateChat:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['blog:privateChat:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" :data="privateChatList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="" align="center" prop="id" v-if="true" />
        <el-table-column label="用户Aid" align="center" prop="userAId" />
        <el-table-column label="用户Bid" align="center" prop="userBId" />
        <el-table-column label="用户A未读消息数" align="center" prop="userAUnreadCount" />
        <el-table-column label="用户B未读消息数" align="center" prop="userBUnreadCount" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['blog:privateChat:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['blog:privateChat:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination
          v-show="total>0"
          :total="total"
          v-model:page="queryParams.pageNum"
          v-model:limit="queryParams.pageSize"
          @pagination="getList"
      />
    </el-card>
    <!-- 添加或修改私聊对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="privateChatFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户Aid" prop="userAId">
          <el-input v-model="form.userAId" placeholder="请输入用户Aid" />
        </el-form-item>
        <el-form-item label="用户Bid" prop="userBId">
          <el-input v-model="form.userBId" placeholder="请输入用户Bid" />
        </el-form-item>
        <el-form-item label="用户A未读消息数" prop="userAUnreadCount">
          <el-input v-model="form.userAUnreadCount" placeholder="请输入用户A未读消息数" />
        </el-form-item>
        <el-form-item label="用户B未读消息数" prop="userBUnreadCount">
          <el-input v-model="form.userBUnreadCount" placeholder="请输入用户B未读消息数" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="PrivateChat" lang="ts">
import { listPrivateChat, getPrivateChat, delPrivateChat, addPrivateChat, updatePrivateChat } from '@/api/blog/privateChat';
import { PrivateChatVO, PrivateChatQuery, PrivateChatForm } from '@/api/blog/privateChat/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const privateChatList = ref<PrivateChatVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const privateChatFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: PrivateChatForm = {
  id: undefined,
  userAId: undefined,
  userBId: undefined,
  userAUnreadCount: undefined,
  userBUnreadCount: undefined
}
const data = reactive<PageData<PrivateChatForm, PrivateChatQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userAId: undefined,
    userBId: undefined,
    userAUnreadCount: undefined,
    userBUnreadCount: undefined,
    params: {
    }
  },
  rules: {
    id: [
      { required: true, message: "不能为空", trigger: "blur" }
    ],
    userAId: [
      { required: true, message: "用户Aid不能为空", trigger: "blur" }
    ],
    userBId: [
      { required: true, message: "用户Bid不能为空", trigger: "blur" }
    ],
    userAUnreadCount: [
      { required: true, message: "用户A未读消息数不能为空", trigger: "blur" }
    ],
    userBUnreadCount: [
      { required: true, message: "用户B未读消息数不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询私聊列表 */
const getList = async () => {
  loading.value = true;
  const res = await listPrivateChat(queryParams.value);
  privateChatList.value = res.rows;
  total.value = res.total;
  loading.value = false;
}

/** 取消按钮 */
const cancel = () => {
  reset();
  dialog.visible = false;
}

/** 表单重置 */
const reset = () => {
  form.value = {...initFormData};
  privateChatFormRef.value?.resetFields();
}

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
const resetQuery = () => {
  queryFormRef.value?.resetFields();
  handleQuery();
}

/** 多选框选中数据 */
const handleSelectionChange = (selection: PrivateChatVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加私聊";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: PrivateChatVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getPrivateChat(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改私聊";
}

/** 提交按钮 */
const submitForm = () => {
  privateChatFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updatePrivateChat(form.value).finally(() =>  buttonLoading.value = false);
      } else {
        await addPrivateChat(form.value).finally(() =>  buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("修改成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: PrivateChatVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除私聊编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delPrivateChat(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('blog/privateChat/export', {
    ...queryParams.value
  }, `privateChat_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
