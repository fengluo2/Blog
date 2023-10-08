<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div class="search" v-show="showSearch">
        <el-form :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">
          <el-form-item label="合集名称" prop="name">
            <el-input v-model="queryParams.name" placeholder="请输入合集名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="合集描述" prop="describe">
            <el-input v-model="queryParams.describe" placeholder="请输入合集描述" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="订阅标志" prop="payFlag">
            <el-input v-model="queryParams.payFlag" placeholder="请输入订阅标志" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="从第节开始付费" prop="payIndex">
            <el-input v-model="queryParams.payIndex" placeholder="请输入从第节开始付费" clearable style="width: 240px" @keyup.enter="handleQuery" />
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
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['blog:collection:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['blog:collection:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['blog:collection:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['blog:collection:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" :data="collectionList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="" align="center" prop="id" v-if="true" />
        <el-table-column label="合集名称" align="center" prop="name" />
        <el-table-column label="合集描述" align="center" prop="describe" />
        <el-table-column label="订阅标志" align="center" prop="payFlag" />
        <el-table-column label="从第节开始付费" align="center" prop="payIndex" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['blog:collection:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['blog:collection:remove']"></el-button>
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
    <!-- 添加或修改合集对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="collectionFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="合集名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入合集名称" />
        </el-form-item>
        <el-form-item label="合集描述" prop="describe">
            <el-input v-model="form.describe" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="订阅标志" prop="payFlag">
          <el-input v-model="form.payFlag" placeholder="请输入订阅标志" />
        </el-form-item>
        <el-form-item label="从第节开始付费" prop="payIndex">
          <el-input v-model="form.payIndex" placeholder="请输入从第节开始付费" />
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

<script setup name="Collection" lang="ts">
import { listCollection, getCollection, delCollection, addCollection, updateCollection } from '@/api/blog/collection';
import { CollectionVO, CollectionQuery, CollectionForm } from '@/api/blog/collection/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const collectionList = ref<CollectionVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const collectionFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: CollectionForm = {
  id: undefined,
  name: undefined,
  describe: undefined,
  payFlag: undefined,
  payIndex: undefined,
}
const data = reactive<PageData<CollectionForm, CollectionQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: undefined,
    describe: undefined,
    payFlag: undefined,
    payIndex: undefined,
    params: {
    }
  },
  rules: {
    id: [
      { required: true, message: "不能为空", trigger: "blur" }
    ],
    name: [
      { required: true, message: "合集名称不能为空", trigger: "blur" }
    ],
    describe: [
      { required: true, message: "合集描述不能为空", trigger: "blur" }
    ],
    payFlag: [
      { required: true, message: "订阅标志不能为空", trigger: "blur" }
    ],
    payIndex: [
      { required: true, message: "从第节开始付费不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询合集列表 */
const getList = async () => {
  loading.value = true;
  const res = await listCollection(queryParams.value);
  collectionList.value = res.rows;
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
  collectionFormRef.value?.resetFields();
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
const handleSelectionChange = (selection: CollectionVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加合集";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: CollectionVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getCollection(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改合集";
}

/** 提交按钮 */
const submitForm = () => {
  collectionFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateCollection(form.value).finally(() =>  buttonLoading.value = false);
      } else {
        await addCollection(form.value).finally(() =>  buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("修改成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: CollectionVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除合集编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delCollection(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('blog/collection/export', {
    ...queryParams.value
  }, `collection_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
