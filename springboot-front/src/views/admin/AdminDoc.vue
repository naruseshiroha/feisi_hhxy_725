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
      <a-row :gutter="24">
        <a-col :span="12">
          <p>
            <a-form layout="inline" :model="param">
              <a-form-item>
                <a-button type="primary" @click="handleQuery()">
                  刷新
                </a-button>
              </a-form-item>
              <!-- <a-form-item>
                <a-button type="primary" @click="add()"> 新增 </a-button>
              </a-form-item> -->
            </a-form>
          </p>
          <a-table
            v-if="level1.length > 0"
            :columns="columns"
            :row-key="'record=>record.id'"
            :data-source="level1"
            :pagination="false"
            :loading="loading"
            size="small"
            :defaultExpandAllRows="false"
          >
            <template #name="{ text: record }">
              {{ record.sort }} {{ text }}
            </template>
            <template v-slot:action="{ record }">
              <a-space size="small">
                <!-- <router-link :to="'/admin/doc?docId=' + record.id">
                  <a-button type="primary" size="small"> 文档管理 </a-button>
                </router-link> -->
                <a-button type="primary" @click="edit(record)" size="small">
                  编辑
                </a-button>
                <a-popconfirm
                  title="删除后不可恢复，确认删除?"
                  ok-text="是"
                  cancel-text="否"
                  @confirm="handleDelete(record.id)"
                >
                  <a-button type="danger" size="small"> 删除 </a-button>
                </a-popconfirm>
              </a-space>
            </template>
          </a-table>
        </a-col>
        <a-col :span="12">
          <p>
            <a-form layout="inline" :model="param">
              <a-form-item>
                <a-button type="primary" @click="handleSave()"> 保存 </a-button>
              </a-form-item>
            </a-form>
          </p>
          <a-form :model="doc" layout="vertical">
            <a-form-item>
              <a-input v-model:value="doc.name" placeholder="名字" />
            </a-form-item>
            <a-form-item>
              <a-tree-select
                v-model:value="doc.parent"
                style="width: 100%"
                :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                :tree-data="treeSelectData"
                placeholder="请选择父文档"
                tree-default-expand-all
                :replaceFields="{ title: 'name', key: id, value: 'id' }"
              >
              </a-tree-select>
            </a-form-item>
            <a-form-item>
              <a-input v-model:value="doc.sort" placeholder="顺序" />
            </a-form-item>
            <a-form-item>
              <!-- <div style="border: 1px solid #ccc" v-if="isShowEditor">
                <Toolbar
                  :editorId="editorId"
                  :defaultConfig="toolbarConfig"
                  :mode="mode"
                  style="border-bottom: 1px solid #ccc"
                />
                <Editor
                  :editorId="editorId"
                  :defaultConfig="editorConfig"
                  :defaultHtml="defaultHtml"
                  :mode="mode"
                  style="height: 500px; overflow-y: hidden"
                />
              </div>
              <div v-else></div> -->
              <div id="content"></div>
            </a-form-item>
          </a-form>
        </a-col>
      </a-row>
    </a-layout-content>
  </a-layout>
</template>
<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";
import axios from "axios";
import { message } from "ant-design-vue";
import { Tool } from "@/utils/tool";
import { useRoute } from "vue-router";
import E from "wangeditor";

export default defineComponent({
  name: "AdminDoc",
  setup() {
    // 通过路由内置函数得到一个路由的变量route，而这个变量里面就有路由的信息，包括路径参数
    const route = useRoute();
    const param = ref();
    param.value = {};
    const docs = ref();
    docs.value = {};

    const loading = ref(false);
    const treeSelectData = ref();
    treeSelectData.value = [];
    const columns = [
      {
        title: "名称",
        dataIndex: "name",
        // slots: { customRender: "name" },
      },
      {
        title: "父文档",
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
     * 一级文档树的数据层级说明，children属性就是二级文档
     * [{
     *   id: "",
     *   name: "",
     *   children: [{
     *     id: "",
     *     name: "",
     *   }]
     * }]
     */
    const level1 = ref(); // 一级文档树，children属性就是二级文档
    level1.value = [];

    // -------- 表单 ---------
    const doc = ref();
    doc.value = {};
    const modalVisible = ref(false);
    const modalLoading = ref(false);

    // Editor
    let editor: any;
    const createEditor = () => {
      editor = new E("#content");
      editor.create();
      editor.config.zIndex = 0;

      console.log("config", editor.config);
    };

    /**
     * 数据查询
     **/
    const handleQuery = () => {
      loading.value = true;
      doc.value = {};
      level1.value = []; // 编辑之前清空下之前记录的数据
      axios.get("/doc/all/" + route.query.ebookId).then((response) => {
        loading.value = false;
        editor.txt.html("");
        const { data } = response;
        if (data.code === 200) {
          docs.value = data.data;
          level1.value = [];
          level1.value = Tool.array2Tree(docs.value, 0);
          treeSelectData.value = Tool.copy(level1.value);
          treeSelectData.value.unshift({ id: 0, name: "无" });
        } else {
          message.error(data.message);
        }
      });
    };

    const handleSave = () => {
      modalLoading.value = true;
      doc.value.ebookId = route.query.ebookId
      doc.value.content = editor.txt.html();
      console.log("doc", doc.value);
      axios.post("/doc/save", doc.value).then((response) => {
        modalLoading.value = false;
        const { data } = response; // data = commonResp
        if (data.code === 200) {
          modalVisible.value = false;
          message.success("保存成功！");
          // 重新加载列表
          handleQuery();
        } else {
          message.error(data.message);
        }
      });
    };

    /**
     * 将某节点及其子孙节点全部置为disabled
     */
    const setDisable = (treeSelectData: any, id: any) => {
      // console.log(treeSelectData, id);
      // 遍历数组，即遍历某一层节点
      for (let i = 0; i < treeSelectData.length; i++) {
        const node = treeSelectData[i];
        if (node.id === id) {
          // 如果当前节点就是目标节点
          console.log("disabled", node);
          // 将目标节点设置为disabled
          node.disabled = true;

          // 遍历所有子节点，将所有子节点全部都加上disabled
          const children = node.children;
          if (Tool.isNotEmpty(children)) {
            for (let j = 0; j < children.length; j++) {
              setDisable(children, children[j].id);
            }
          }
        } else {
          // 如果当前节点不是目标节点，则到其子节点再找找看。
          const children = node.children;
          if (Tool.isNotEmpty(children)) {
            setDisable(children, id);
          }
        }
      }
    };

    /**
     * 内容查询
     */
    const handleQueryContent = () => {
      // isShowEditor.value = false;
      axios.get("/doc/find-content/" + doc.value.id).then((response) => {
        const { data } = response;
        if (data.code == 200) {
          editor.txt.html(data.data);
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
      doc.value = Tool.copy(record);
      handleQueryContent();

      // 不能选择当前节点及其所有子孙节点，作为父节点，会使树断开
      treeSelectData.value = Tool.copy(level1.value);
      setDisable(treeSelectData.value, record.id);

      // 为选择树添加一个"无"
      treeSelectData.value.unshift({ id: 0, name: "无" }); // unshift({id: 0, name: '无'})的作用就是在数组的前面加一个指定节点
    };

    /**
     * 新增
     */
    // const add = () => {
    //   isShowEditor.value = false;
    //   // modalVisible.value = true;
    //   doc.value = {
    //     ebookId: route.query.ebookId,
    //   };
    //   treeSelectData.value = Tool.copy(level1.value);
    //   treeSelectData.value.unshift({ id: 0, name: "无" });
    // };

    /**
     * 获取某节点及其子孙节点全部id
     */
    const ids: Array<string> = [];
    const getDeleteIds = (treeSelectData: any, id: any) => {
      // 遍历数组，即遍历某一层节点
      for (let i = 0; i < treeSelectData.length; i++) {
        const node = treeSelectData[i];
        if (node.id === id) {
          // 把目标id放入结果集
          ids.push(id);
          // 遍历所有子节点
          const children = node.children;
          if (Tool.isNotEmpty(children)) {
            for (let j = 0; j < children.length; j++) {
              getDeleteIds(children, children[j].id);
            }
          }
        } else {
          // 如果当前节点不是目标节点，则到其子节点再找找看。
          const children = node.children;
          if (Tool.isNotEmpty(children)) {
            getDeleteIds(children, id);
          }
        }
      }
    };

    /**删除 */
    const handleDelete = (id: number) => {
      getDeleteIds(level1.value, id);
      axios.delete("/doc/delete/" + ids.join(",")).then((response) => {
        const { data } = response; // data = commonResp
        if (data.code === 200) {
          // 重新加载列表
          handleQuery();
        }
      });
    };

    onMounted(() => {
      handleQuery();
      createEditor();
    });
    return {
      // docs,
      level1,
      columns,
      loading,

      edit,
      modalVisible,
      modalLoading,
      handleSave,
      doc,
      // add,
      handleDelete,
      param,
      handleQuery,
      treeSelectData,
    };
  },
});
</script>
<style>
.w-e-menu {
  z-index: 2 !important;
}
.w-e-text-container {
  z-index: 1 !important;
}
.w-e-toolbar {
  z-index: 2 !important;
}
</style>
