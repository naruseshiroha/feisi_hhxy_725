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
      <p>
        <a-form layout="inline" :model="param">
          <a-form-item>
            <a-input v-model:value="param.name" placeholder="名称"> </a-input>
          </a-form-item>
          <a-form-item>
            <a-button
              type="primary"
              @click="
                handleQuery({
                  pageNum: 1,
                  pageSize: pagination.pageSize,
                })
              "
            >
              查询
            </a-button>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="add()"> 新增 </a-button>
          </a-form-item>
        </a-form>
      </p>

      <a-table
        :columns="columns"
        :row-key="(record) => record.id"
        :data-source="ebooks"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <!-- <template v-slot:category="{ text, record }"> -->
        <template v-slot:category="{ record }">
          <span
            >{{ getCategoryName(record.category1Id) }} /
            {{ getCategoryName(record.category2Id) }}</span
          >
        </template>
        <!-- <template v-slot:action="{ text, record }"> -->
        <template v-slot:action="{ record }">
          <a-space size="small">
            <router-link :to="'/admin/doc?ebookId=' + record.id">
              <a-button type="primary"> 文档管理 </a-button>
            </router-link>
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
            :model="ebook"
            :label-col="{ span: 6 }"
            :wrapper-col="{ span: 18 }"
          >
            <a-form-item label="封面">
              <a-input v-model:value="ebook.cover" />
            </a-form-item>
            <a-form-item label="名称">
              <a-input v-model:value="ebook.name" />
            </a-form-item>
            <a-form-item label="分类">
              <a-cascader
                v-model:value="categoryIds"
                :field-names="{
                  label: 'name',
                  value: 'id',
                  children: 'children',
                }"
                :options="level1"
              />
            </a-form-item>
            <a-form-item label="描述">
              <a-input v-model:value="ebook.description" type="textarea" />
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
  name: "AdminEbook",
  setup() {
    const param = ref({
      name: "",
    });
    const ebooks = ref();
    const pagination = ref({
      current: 1,
      pageSize: 5,
      total: 0,
    });
    const loading = ref(false);

    const columns = [
      {
        title: "封面",
        dataIndex: "cover",
        slots: { customRender: "cover" },
      },
      {
        title: "名称",
        dataIndex: "name",
      },
      // {
      //   title: "分类一",
      //   Key: "category1Id",
      //   dataIndex: "category1Id",
      // },
      // {
      //   title: "分类二",
      //   dataIndex: "category2Id",
      // },
      {
        title: "分类",
        slots: { customRender: "category" },
      },
      {
        title: "文档数",
        dataIndex: "docCount",
      },
      {
        title: "阅读数",
        dataIndex: "viewCount",
      },
      {
        title: "点赞数",
        dataIndex: "voteCount",
      },
      {
        title: "操作",
        key: "action",
        slots: { customRender: "action" },
      },
    ];

    /**
     * 数据查询
     **/
    const handleQuery = (params: any) => {
      loading.value = true;
      params["name"] = param.value.name;
      axios.get("/ebook/findAllEbook", { params }).then((response) => {
        loading.value = false;
        const { data } = response;
        console.log(data);
        if (data.code === 200) {
          ebooks.value = data.data.list;
          // 重置分页按钮
          pagination.value.current = data.data.pageNum;
          pagination.value.total = data.data.total;
        } else {
          message.error(data.message);
        }
      });
    };

    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination: any) => {
      console.log("看看自带的分页参数都有啥：", pagination);
      handleQuery({
        pageNum: pagination.current,
        pageSize: pagination.pageSize,
      });
    };

    // -------- 表单 ---------
    const categoryIds = ref();
    const ebook = ref();
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;
      ebook.value.category1Id = categoryIds.value[0];
      ebook.value.category2Id = categoryIds.value[1];
      axios.post("/ebook/save", ebook.value).then((response) => {
        modalLoading.value = false;
        const { data } = response; // data = commonResp
        if (data.data) {
          modalVisible.value = false;
          // 重新加载列表
          handleQuery({
            pageNum: pagination.value.current,
            pageSize: pagination.value.pageSize,
          });
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
      // ebook.value=record
      ebook.value = Tool.copy(record);
      categoryIds.value = [ebook.value.category1Id, ebook.value.category2Id];
    };

    /**
     * 新增
     */
    const add = () => {
      modalVisible.value = true;
      ebook.value = {};
    };

    /**删除 */
    const handleDelete = (id: number) => {
      axios.delete("/ebook/delete/" + id).then((response) => {
        const { data } = response; // data = commonResp
        console.log("data", data);
        if (data.data) {
          // 重新加载列表
          handleQuery({
            pageNum: pagination.value.current,
            pageSize: pagination.value.pageSize,
          });
        }
      });
    };

    const level1 = ref();
    let categorys: any;
    /**
     * 查询所有分类
     **/
    const handleQueryCategory = () => {
      loading.value = true;
      // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
      ebooks.value = [];
      axios.get("/category/all").then((response) => {
        loading.value = false;
        const { data } = response;
        if (data.code === 200) {
          categorys = data.data;
          console.log("原始数组：", categorys);

          level1.value = [];
          level1.value = Tool.array2Tree(categorys, 0);
          console.log("树形结构：", level1.value);
          // 加载完分类后，再加载电子书，否则会报forEach undefined的错误
          // 列表渲染的时候，如果还没取到分类数据就会报错；
          handleQuery({
            pageNum: 1,
            pageSize: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
        }
      });
    };

    const getCategoryName = (cid: number) => {
      let result = "";
      categorys.forEach((item: any) => {
        if (item != null) {
          if (item.id === cid) {
            // return item.name; // 注意，这里直接return不起作用
            result = item.name;
          }
        }
      });
      return result;
    };

    onMounted(() => {
      handleQueryCategory();
    });

    return {
      ebooks,
      pagination,
      columns,
      loading,
      handleTableChange,
      param,
      handleQuery,

      edit,
      add,
      handleDelete,
      ebook,

      modalVisible,
      modalLoading,
      handleModalOk,

      categoryIds,
      level1,
      getCategoryName,
    };
  },
});
</script>
