<template>
  <a-layout>
    <a-layout-content
      :style="{
        background: '#fff',
        padding: '24px',
        margin: 0,
        minHeight: '280px',
      }"
    >
      <!-- <div class="about">
              <h1>管理分类页面</h1>
            </div> -->
      <p>
        <a-form layout="inline" :model="param">
          <a-form-item>
            <a-button type="primary" @click="handleQuery()"> 刷新 </a-button>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="add()"> 新增 </a-button>
          </a-form-item>
        </a-form>
      </p>
      <a-table
        :columns="columns"
        :row-key="'record=>record.id'"
        :data-source="level1"
        :pagination="false"
        :loading="loading"
        @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:category="{ record }">
          <span
            >{{ getCategoryName(record.category1Id) }} /
            {{ getCategoryName(record.category2Id) }}</span
          >
        </template>
        <template v-slot:action="{ record }">
          <a-space size="small">
            <!-- <router-link :to="'/admin/doc?categoryId=' + record.id">
              <a-button type="primary"> 文档管理 </a-button>
            </router-link> -->
            <a-button type="primary" @click="edit(record)"> 编辑 </a-button>
            <a-popconfirm
              title="删除后不可恢复，确认删除?"
              ok-text="是"
              cancel-text="否"
              @confirm="handleDelete(record.id)"
            >
              <a-button type="danger"> 删除 </a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </a-table>

      <!--点击编辑弹出的modal框-->
      <div>
        <a-modal
          title="Title"
          v-model:visible="modalVisible"
          :confirm-loading="modalLoading"
          @ok="handleModalOk"
        >
          <a-form
            :model="category"
            :label-col="{ span: 6 }"
            :wrapper-col="{ span: 18 }"
          >
            <a-form-item label="名称">
              <a-input v-model:value="category.name" />
            </a-form-item>
            <a-form-item label="父分类">
              <a-select v-model:value="category.parent" ref="select">
                <a-select-option :value="0">无</a-select-option>
                <a-select-option
                  v-for="c in level1"
                  :key="c.id"
                  :value="c.id"
                  :disabled="category.id === c.id"
                  >{{ c.name }}</a-select-option
                >
              </a-select>
            </a-form-item>
            <a-form-item label="顺序">
              <a-input v-model:value="category.sort" />
            </a-form-item>
          </a-form>
        </a-modal>
      </div>
    </a-layout-content>
  </a-layout>
</template>
<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";
import axios from "axios";
import { message } from "ant-design-vue";
import { Tool } from "@/utils/tool";
export default defineComponent({
  name: "AdminCategory",
  setup() {
    const param = ref();
    param.value = {};
    const categorys = ref();

    const loading = ref(false);

    const columns = [
      {
        title: "名称",
        dataIndex: "name",
      },
      {
        title: "父分类",
        Key: "parent",
        dataIndex: "parent",
      },
      {
        title: "排序",
        dataIndex: "sort",
      },

      {
        title: "操作",
        key: "action",
        slots: { customRender: "action" },
      },
    ];
    /**
     * 一级分类树的数据层级说明，children属性就是二级分类
     * [{
     *   id: "",
     *   name: "",
     *   children: [{
     *     id: "",
     *     name: "",
     *   }]
     * }]
     */
    const level1 = ref(); // 一级分类树，children属性就是二级分类
    /**
     * 数据查询
     **/
    const handleQuery = () => {
      loading.value = true;
      axios.get("/category/all").then((response) => {
        console.log("查询11111111111");
        loading.value = false;
        const { data } = response;
        if (data.code === 200) {
          categorys.value = data.data;
          console.log("原始数组：", categorys.value);

          level1.value = [];
          level1.value = Tool.array2Tree(categorys.value, 0);
          console.log("树形结构的数组：", level1);
        } else {
          message.error(data.message);
        }
      });
    };

    // -------- 表单 ---------
    const category = ref({});
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;
      axios.post("/category/save", category.value).then((response) => {
        modalLoading.value = false;
        const { data } = response; // data = commonResp
        if (data.code === 200) {
          modalVisible.value = false;
          // 重新加载列表
          handleQuery();
        } else {
          message.error(data.message);
        }
      });
    };

    /**
     * 编辑
     */
    const edit = (record: any) => {
      modalVisible.value = true;
      category.value = Tool.copy(record);
    };

    /**
     * 新增
     */
    const add = () => {
      modalVisible.value = true;
      category.value = {};
    };
    /**删除 */
    const handleDelete = (id: number) => {
      axios.delete("/category/delete/" + id).then((response) => {
        const { data } = response; // data = commonResp
        if (data.code === 200) {
          // 重新加载列表
          handleQuery();
        }
      });
    };

    onMounted(() => {
      handleQuery();
    });
    return {
      // categorys,
      level1,
      columns,
      loading,

      edit,
      modalVisible,
      modalLoading,
      handleModalOk,
      category,
      add,
      handleDelete,
      param,
      handleQuery,
    };
  },
});
</script>
