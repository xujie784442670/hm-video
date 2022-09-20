<template>
  <div class="formTable">
    <el-row v-if="modelValue" clss="form">
      <el-col :span="24">
        <el-form v-model="modelValue"
                 :inline="true"
                 :rules="formRules"
                 label-suffix=":"
                 @update:modelValue="$emit('update:modelValue',$event)"
        >
          <slot name="form"></slot>
          <el-form-item>
            <slot name="formBtns">
              <el-button native-type="reset" type="danger">重置</el-button>
              <el-button type="primary" @click="$emit('query',modelValue)">查询</el-button>
            </slot>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-row class="table">
      <el-col :span="24">
        <el-table v-loading="loading" :data="data" v-bind="tableAttrs">
          <slot name="column"></slot>
        </el-table>
      </el-col>
    </el-row>
    <el-row class="page">
      <el-col :span="24">
        <slot name="food">
          <el-pagination v-if="pageInfo"
                         v-model:currentPage="pageInfo.page"
                         v-model:page-size="pageInfo.pageSize"
                         :layout="pageInfo.layout||'total, sizes, prev, pager, next, jumper'"
                         :page-sizes="pageInfo.pageSizes||[10,20,50,100]"
                         :total="pageInfo.total"
                         @size-change="pageSizeHandler"
                         @current-change="currentPageHandler"
          />
        </slot>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "FormTable"
}
</script>
<script setup>

import {computed, useAttrs} from "vue";

const emit = defineEmits(['query', 'update:pageInfo']);
const props = defineProps({
  modelValue: Object,
  data: Array,
  formRules: {
    type: Array,
    default() {
      return [];
    }
  },
  pageInfo: Object,
  loading: {
    type: Boolean,
    default: false
  },
})
const attrs = useAttrs();
const tableAttrs = computed(() => {
  const tabAttrs = {}
  for (let attrsKey in attrs) {
    if (attrsKey.startsWith("table-")) {
      tabAttrs[attrsKey.replace("table-", "")] = attrs[attrsKey];
    }
  }
  return tabAttrs;
})

function pageSizeHandler(pageSize) {
  emit('update:pageInfo', {pageSize: parseInt(pageSize), ...props.pageInfo})
  emit('query', props.modelValue);
}

function currentPageHandler(currentPage) {
  emit('update:pageInfo', {page: parseInt(currentPage), ...props.pageInfo})
  emit('query', props.modelValue);
}
</script>
<style lang="less" scoped>
.formTable {
  height: 100%;

  .form, .page {
    height: 10%;
  }

  .table {
    height: 80%;
  }
}
</style>
