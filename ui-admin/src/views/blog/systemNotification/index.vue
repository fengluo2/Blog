<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div class="search" v-show="showSearch">
        <el-form :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">
          <el-form-item label="标题" prop="title">
            <el-input v-model="queryParams.title" placeholder="请输入标题" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="可点击标志" prop="click">
            <el-input v-model="queryParams.click" placeholder="请输入可点击标志" clearable style="width: 240px" @keyup.enter="handleQuery" />
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
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['blog:systemNotification:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['blog:systemNotification:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['blog:systemNotification:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['blog:systemNotification:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" :data="systemNotificationList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="" align="center" prop="id" v-if="true" />
        <el-table-column label="标题" align="center" prop="title" />
        <el-table-column label="内容" align="center" prop="content" />
        <el-table-column label="可点击标志" align="center" prop="click" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['blog:systemNotification:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['blog:systemNotification:remove']"></el-button>
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
    <!-- 添加或修改系统通知对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="systemNotificationFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="可点击标志" prop="click">
          <el-input v-model="form.click" placeholder="请输入可点击标志" />
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

<script setup name="SystemNotification" lang="ts">
import { listSystemNotification, getSystemNotification, delSystemNotification, addSystemNotification, updateSystemNotification } from '@/api/blog/systemNotification';
import { SystemNotificationVO, SystemNotificationQuery, SystemNotificationForm } from '@/api/blog/systemNotification/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const systemNotificationList = ref<SystemNotificationVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const systemNotificationFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: SystemNotificationForm = {
  id: undefined,
  title: undefined,
  content: undefined,
  click: undefined,
}
const data = reactive<PageData<SystemNotificationForm, SystemNotificationQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    title: undefined,
    content: undefined,
    click: undefined,
    params: {
    }
  },
  rules: {
    id: [
      { required: true, message: "不能为空", trigger: "blur" }
    ],
    title: [
      { required: true, message: "标题不能为空", trigger: "blur" }
    ],
    content: [
      { required: true, message: "内容不能为空", trigger: "blur" }
    ],
    click: [
      { required: true, message: "可点击标志不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询系统通知列表 */
const getList = async () => {
  loading.value = true;
  const res = await listSystemNotification(queryParams.value);
  systemNotificationList.value = res.rows;
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
  systemNotificationFormRef.value?.resetFields();
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
const handleSelectionChange = (selection: SystemNotificationVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加系统通知";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: SystemNotificationVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getSystemNotification(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改系统通知";
}

/** 提交按钮 */
const submitForm = () => {
  systemNotificationFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateSystemNotification(form.value).finally(() =>  buttonLoading.value = false);
      } else {
        await addSystemNotification(form.value).finally(() =>  buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("修改成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: SystemNotificationVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除系统通知编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delSystemNotification(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('blog/systemNotification/export', {
    ...queryParams.value
  }, `systemNotification_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
