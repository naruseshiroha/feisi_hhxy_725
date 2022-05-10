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
            <a-input v-model:value="param.loginName" placeholder="登陆名">
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-button
              type="primary"
              @click="handleQuery({ page: 1, size: pagination.pageSize })"
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
        :row-key="'record=>record.id'"
        :data-source="users"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
      >
        <template v-slot:action="{ record }">
          <a-space size="small">
            <a-button type="primary" @click="resetPassword(record)">
              重置密码
            </a-button>
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
          title="用户表单"
          v-model:visible="modalVisible"
          :confirm-loading="modalLoading"
          @ok="handleModalOk"
        >
          <a-form
            :model="user"
            :label-col="{ span: 6 }"
            :wrapper-col="{ span: 18 }"
          >
            <a-form-item label="登陆名">
              <a-input v-model:value="user.loginName" :disabled="!!user.id" />
            </a-form-item>
            <a-form-item label="昵称">
              <a-input v-model:value="user.name" />
            </a-form-item>
            <a-form-item label="密码" v-show="!user.id">
              <a-input v-model:value="user.password" type="password" />
            </a-form-item>
          </a-form>
        </a-modal>

        <a-modal
          title="重置密码"
          v-model:visible="resetModalVisible"
          :confirm-loading="resetModalLoading"
          @ok="handleResetModalOk"
        >
          <a-form
            :model="user"
            :label-col="{ span: 6 }"
            :wrapper-col="{ span: 18 }"
          >
            <a-form-item label="请输入新密码">
              <a-input v-model:value="user.password" type="password" />
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

declare let hexMd5: any;
declare let KEY: any;

export default defineComponent({
  name: "AdminUser",
  setup() {
    const param = ref();
    param.value = {};
    const users = ref();
    const pagination = ref({
      current: 1,
      pageSize: 5,
      total: 0,
    });
    const loading = ref(false);

    const columns = [
      {
        title: "登陆名",
        dataIndex: "loginName",
      },
      {
        title: "名称",
        dataIndex: "name",
      },
      {
        title: "密码",
        dataIndex: "password",
      },
      {
        title: "Action",
        key: "action",
        slots: { customRender: "action" },
      },
    ];
    /**
     * 数据查询
     **/
    const handleQuery = (params: any) => {
      loading.value = true;
      // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
      users.value = [];
      axios
        .get("/user/findAllUser", {
          params: {
            pageNum: 1,
            pageSize: 1000,
            loginName: param.value.loginName,
          },
        })
        .then((response) => {
          loading.value = false;
          const { data } = response;
          console.log("data = ", data.data);

          if (data.code === 200) {
            users.value = data.data.list;

            // 重置分页按钮
            pagination.value.current = params.pageNum;
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
      handleQuery({
        pageNum: pagination.current,
        pageSize: pagination.pageSize,
      });
    };

    // -------- 表单 ---------
    const user = ref();
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;
      // eslint-disable-next-line
      user.value.password = hexMd5(user.value.password + KEY);
      axios.post("/user/save", user.value).then((response) => {
        modalLoading.value = false;
        const { data } = response;
        console.log("save data", data);

        if (data.code === 200) {
          modalVisible.value = false;
          // 重新加载列表
          handleQuery({
            pageNum: pagination.value.current,
            pageSize: pagination.value.pageSize,
          });
        } else {
          message.error(data.msg);
        }
      });
    };

    // reset password
    const resetModalVisible = ref(false);
    const resetModalLoading = ref(false);
    const handleResetModalOk = () => {
      resetModalLoading.value = true;
      user.value.password = hexMd5(user.value.password + KEY);
      axios.post("/user/reset-password", user.value).then((response) => {
        resetModalLoading.value = false;
        const { data } = response;
        if (data.data) {
          resetModalVisible.value = false;
          //重新加载列表
          handleQuery({
            pageNum: 1,
            pageSize: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
        }
      });
    };
    // reset button
    const resetPassword = (record: any) => {
      resetModalVisible.value = true;
      user.value = Tool.copy(record);
      user.value.password = null;
    };

    /**
     * 编辑
     */
    const edit = (record: any) => {
      modalVisible.value = true;
      user.value = Tool.copy(record);
    };

    /**
     * 新增
     */
    const add = () => {
      modalVisible.value = true;
      user.value = {};
    };
    /**删除 */
    const handleDelete = (id: number) => {
      axios.delete("/user/delete/" + id).then((response) => {
        const { data } = response;
        if (data.code === 200) {
          // 重新加载列表
          handleQuery({ 
            pageNum: pagination.value.current,
            pageSize: pagination.value.pageSize,
          });
          message.success("删除成功")
        } else {
          message.error(data.msg);
        }
      });
    };
    onMounted(() => {
      handleQuery({
        pageNum: 1,
        pageSize: pagination.value.pageSize,
      });
    });
    return {
      users,
      pagination,
      columns,
      loading,
      handleTableChange,

      edit,
      modalVisible,
      modalLoading,
      handleModalOk,
      user,
      add,
      handleDelete,
      param,
      handleQuery,

      resetModalVisible,
      resetModalLoading,
      handleResetModalOk,
      resetPassword,
    };
  },
});
</script>
