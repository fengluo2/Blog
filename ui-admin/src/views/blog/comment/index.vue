<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div class="search" v-show="showSearch">
        <el-form :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">
          <el-form-item label="回复id" prop="replyId">
            <el-input v-model="queryParams.replyId" placeholder="请输入回复id" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="评论级别" prop="level">
            <el-input v-model="queryParams.level" placeholder="请输入评论级别" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="文章id" prop="postId">
            <el-input v-model="queryParams.postId" placeholder="请输入文章id" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="主评论标志" prop="masterFlag">
            <el-input v-model="queryParams.masterFlag" placeholder="请输入主评论标志" clearable style="width: 240px" @keyup.enter="handleQuery" />
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
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['blog:comment:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['blog:comment:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['blog:comment:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['blog:comment:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" :data="commentList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="" align="center" prop="id" v-if="true" />
        <el-table-column label="回复id" align="center" prop="replyId" />
        <el-table-column label="评论级别" align="center" prop="level" />
        <el-table-column label="文章id" align="center" prop="postId" />
        <el-table-column label="评论内容" align="center" prop="content" />
        <el-table-column label="主评论标志" align="center" prop="masterFlag" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['blog:comment:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['blog:comment:remove']"></el-button>
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
    <!-- 添加或修改评论对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="commentFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="回复id" prop="replyId">
          <el-input v-model="form.replyId" placeholder="请输入回复id" />
        </el-form-item>
        <el-form-item label="评论级别" prop="level">
          <el-input v-model="form.level" placeholder="请输入评论级别" />
        </el-form-item>
        <el-form-item label="文章id" prop="postId">
          <el-input v-model="form.postId" placeholder="请输入文章id" />
        </el-form-item>
        <el-form-item label="评论内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="主评论标志" prop="masterFlag">
          <el-input v-model="form.masterFlag" placeholder="请输入主评论标志" />
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

<script setup name="Comment" lang="ts">
import { listComment, getComment, delComment, addComment, updateComment } from '@/api/blog/comment';
import { CommentVO, CommentQuery, CommentForm } from '@/api/blog/comment/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const commentList = ref<CommentVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const commentFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: CommentForm = {
  id: undefined,
  replyId: undefined,
  level: undefined,
  postId: undefined,
  content: undefined,
  masterFlag: undefined,
}
const data = reactive<PageData<CommentForm, CommentQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    replyId: undefined,
    level: undefined,
    postId: undefined,
    content: undefined,
    masterFlag: undefined,
    params: {
    }
  },
  rules: {
    id: [
      { required: true, message: "不能为空", trigger: "blur" }
    ],
    replyId: [
      { required: true, message: "回复id不能为空", trigger: "blur" }
    ],
    level: [
      { required: true, message: "评论级别不能为空", trigger: "blur" }
    ],
    postId: [
      { required: true, message: "文章id不能为空", trigger: "blur" }
    ],
    content: [
      { required: true, message: "评论内容不能为空", trigger: "blur" }
    ],
    masterFlag: [
      { required: true, message: "主评论标志不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询评论列表 */
const getList = async () => {
  loading.value = true;
  const res = await listComment(queryParams.value);
  commentList.value = res.rows;
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
  commentFormRef.value?.resetFields();
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
const handleSelectionChange = (selection: CommentVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加评论";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: CommentVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getComment(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改评论";
}

/** 提交按钮 */
const submitForm = () => {
  commentFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateComment(form.value).finally(() =>  buttonLoading.value = false);
      } else {
        await addComment(form.value).finally(() =>  buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("修改成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: CommentVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除评论编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delComment(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('blog/comment/export', {
    ...queryParams.value
  }, `comment_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
