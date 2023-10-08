<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div class="search" v-show="showSearch">
        <el-form :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">
          <el-form-item label="文章id" prop="postId">
            <el-input v-model="queryParams.postId" placeholder="请输入文章id" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="通知id" prop="noticeId">
            <el-input v-model="queryParams.noticeId" placeholder="请输入通知id" clearable style="width: 240px" @keyup.enter="handleQuery" />
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
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['blog:postNotice:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['blog:postNotice:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['blog:postNotice:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['blog:postNotice:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" :data="postNoticeList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="" align="center" prop="id" v-if="true" />
        <el-table-column label="文章id" align="center" prop="postId" />
        <el-table-column label="通知类型" align="center" prop="noticeType" />
        <el-table-column label="通知id" align="center" prop="noticeId" />
        <el-table-column label="已读标志" align="center" prop="readFlag" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['blog:postNotice:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['blog:postNotice:remove']"></el-button>
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
    <!-- 添加或修改文章通知对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="postNoticeFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文章id" prop="postId">
          <el-input v-model="form.postId" placeholder="请输入文章id" />
        </el-form-item>
        <el-form-item label="通知id" prop="noticeId">
          <el-input v-model="form.noticeId" placeholder="请输入通知id" />
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

<script setup name="PostNotice" lang="ts">
import { listPostNotice, getPostNotice, delPostNotice, addPostNotice, updatePostNotice } from '@/api/blog/postNotice';
import { PostNoticeVO, PostNoticeQuery, PostNoticeForm } from '@/api/blog/postNotice/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const postNoticeList = ref<PostNoticeVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const postNoticeFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: PostNoticeForm = {
  id: undefined,
  postId: undefined,
  noticeType: undefined,
  noticeId: undefined,
  readFlag: undefined,
}
const data = reactive<PageData<PostNoticeForm, PostNoticeQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    postId: undefined,
    noticeType: undefined,
    noticeId: undefined,
    readFlag: undefined,
    params: {
    }
  },
  rules: {
    id: [
      { required: true, message: "不能为空", trigger: "blur" }
    ],
    postId: [
      { required: true, message: "文章id不能为空", trigger: "blur" }
    ],
    noticeType: [
      { required: true, message: "通知类型不能为空", trigger: "change" }
    ],
    noticeId: [
      { required: true, message: "通知id不能为空", trigger: "blur" }
    ],
    readFlag: [
      { required: true, message: "已读标志不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询文章通知列表 */
const getList = async () => {
  loading.value = true;
  const res = await listPostNotice(queryParams.value);
  postNoticeList.value = res.rows;
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
  postNoticeFormRef.value?.resetFields();
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
const handleSelectionChange = (selection: PostNoticeVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加文章通知";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: PostNoticeVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getPostNotice(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改文章通知";
}

/** 提交按钮 */
const submitForm = () => {
  postNoticeFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updatePostNotice(form.value).finally(() =>  buttonLoading.value = false);
      } else {
        await addPostNotice(form.value).finally(() =>  buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("修改成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: PostNoticeVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除文章通知编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delPostNotice(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('blog/postNotice/export', {
    ...queryParams.value
  }, `postNotice_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
