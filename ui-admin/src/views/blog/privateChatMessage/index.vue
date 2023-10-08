<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div class="search" v-show="showSearch">
        <el-form :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">
          <el-form-item label="私聊id" prop="chatId">
            <el-input v-model="queryParams.chatId" placeholder="请输入私聊id" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="发送者id" prop="senderId">
            <el-input v-model="queryParams.senderId" placeholder="请输入发送者id" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="接收者id" prop="receiverId">
            <el-input v-model="queryParams.receiverId" placeholder="请输入接收者id" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="已读标志" prop="readFlag">
            <el-input v-model="queryParams.readFlag" placeholder="请输入已读标志" clearable style="width: 240px" @keyup.enter="handleQuery" />
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
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['blog:privateChatMessage:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['blog:privateChatMessage:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['blog:privateChatMessage:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['blog:privateChatMessage:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" :data="privateChatMessageList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="" align="center" prop="id" v-if="true" />
        <el-table-column label="私聊id" align="center" prop="chatId" />
        <el-table-column label="发送者id" align="center" prop="senderId" />
        <el-table-column label="接收者id" align="center" prop="receiverId" />
        <el-table-column label="消息内容" align="center" prop="content" />
        <el-table-column label="已读标志" align="center" prop="readFlag" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['blog:privateChatMessage:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['blog:privateChatMessage:remove']"></el-button>
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
    <!-- 添加或修改私聊消息对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="privateChatMessageFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="私聊id" prop="chatId">
          <el-input v-model="form.chatId" placeholder="请输入私聊id" />
        </el-form-item>
        <el-form-item label="发送者id" prop="senderId">
          <el-input v-model="form.senderId" placeholder="请输入发送者id" />
        </el-form-item>
        <el-form-item label="接收者id" prop="receiverId">
          <el-input v-model="form.receiverId" placeholder="请输入接收者id" />
        </el-form-item>
        <el-form-item label="消息内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="已读标志" prop="readFlag">
          <el-input v-model="form.readFlag" placeholder="请输入已读标志" />
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

<script setup name="PrivateChatMessage" lang="ts">
import { listPrivateChatMessage, getPrivateChatMessage, delPrivateChatMessage, addPrivateChatMessage, updatePrivateChatMessage } from '@/api/blog/privateChatMessage';
import { PrivateChatMessageVO, PrivateChatMessageQuery, PrivateChatMessageForm } from '@/api/blog/privateChatMessage/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const privateChatMessageList = ref<PrivateChatMessageVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const privateChatMessageFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: PrivateChatMessageForm = {
  id: undefined,
  chatId: undefined,
  senderId: undefined,
  receiverId: undefined,
  content: undefined,
  readFlag: undefined,
}
const data = reactive<PageData<PrivateChatMessageForm, PrivateChatMessageQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    chatId: undefined,
    senderId: undefined,
    receiverId: undefined,
    content: undefined,
    readFlag: undefined,
    params: {
    }
  },
  rules: {
    id: [
      { required: true, message: "不能为空", trigger: "blur" }
    ],
    chatId: [
      { required: true, message: "私聊id不能为空", trigger: "blur" }
    ],
    senderId: [
      { required: true, message: "发送者id不能为空", trigger: "blur" }
    ],
    receiverId: [
      { required: true, message: "接收者id不能为空", trigger: "blur" }
    ],
    content: [
      { required: true, message: "消息内容不能为空", trigger: "blur" }
    ],
    readFlag: [
      { required: true, message: "已读标志不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询私聊消息列表 */
const getList = async () => {
  loading.value = true;
  const res = await listPrivateChatMessage(queryParams.value);
  privateChatMessageList.value = res.rows;
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
  privateChatMessageFormRef.value?.resetFields();
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
const handleSelectionChange = (selection: PrivateChatMessageVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加私聊消息";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: PrivateChatMessageVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getPrivateChatMessage(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改私聊消息";
}

/** 提交按钮 */
const submitForm = () => {
  privateChatMessageFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updatePrivateChatMessage(form.value).finally(() =>  buttonLoading.value = false);
      } else {
        await addPrivateChatMessage(form.value).finally(() =>  buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("修改成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: PrivateChatMessageVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除私聊消息编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delPrivateChatMessage(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('blog/privateChatMessage/export', {
    ...queryParams.value
  }, `privateChatMessage_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
