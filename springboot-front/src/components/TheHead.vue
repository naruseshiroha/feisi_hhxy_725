<template>
  <a-layout-header class="header">
    <div class="logo" />
    <div style="float: right">
      <a style="float: left" v-show="user.id" class="login-mennu">
        <span>您好：{{ user.name }}</span>
      </a>
      <a-popconfirm
        title="确认退出登录?"
        ok-text="是"
        cancel-text="否"
        @confirm="logout()"
      >
        <a style="float: right" class="login-menu" v-show="user.id">
          <span>退出登录</span>
        </a>
      </a-popconfirm>
    </div>
    <a v-show="!user.id" @click="showLoginModal" class="login-mennu">
      <span>登录</span>
    </a>

    <a-menu
      theme="dark"
      mode="horizontal"
      v-model:selectedKeys="selectedKeys1"
      :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/">
        <router-link to="/">首页</router-link>
      </a-menu-item>
      <a-menu-item key="AdminUser">
        <router-link to="/admin/user">用户管理</router-link>
      </a-menu-item>
      <a-menu-item key="AdminEbook" :style="user.id ? {} : { display: 'none' }">
        <router-link to="/admin/ebook">内容管理</router-link>
      </a-menu-item>
      <a-menu-item
        key="AdminCategory"
        :style="user.id ? {} : { display: 'none' }"
      >
        <router-link to="/admin/category">分类管理</router-link>
      </a-menu-item>
      <a-menu-item key="about">
        <router-link to="/about">关于我们</router-link>
      </a-menu-item>
    </a-menu>

    <a-modal
      title="登录"
      v-model:visible="loginModalVisible"
      :confirm-loading="loginModalLoading"
      @ok="login"
    >
      <a-form
        :model="loginUser"
        :label-col="{ span: 6 }"
        :wrapper-col="{ span: 18 }"
      >
        <a-form-item label="登录名">
          <a-input v-model:value="loginUser.loginName" />
        </a-form-item>
        <a-form-item label="密码">
          <a-input v-model:value="loginUser.password" type="password" />
        </a-form-item>
      </a-form>
    </a-modal>
  </a-layout-header>
</template>

<script lang="ts">
import { message } from "ant-design-vue";
import axios from "axios";
import { computed, defineComponent, ref } from "vue";
import store from "@/store";
import router from "@/router";
declare let hexMd5: any;
declare let KEY: any;
export default defineComponent({
  name: "the-head",
  setup() {
    const user = computed(() => store.state.user);

    // 用来登录
    const loginUser = ref({
      loginName: "test",
      password: "test",
    });
    const loginModalVisible = ref(false);
    const loginModalLoading = ref(false);
    const showLoginModal = () => {
      loginModalVisible.value = true;
    };

    // 登录
    const login = () => {
      console.log("开始登录");
      loginModalLoading.value = true;
      loginUser.value.password = hexMd5(loginUser.value.password + KEY);
      axios.post("/user/login", loginUser.value).then((response) => {
        loginModalLoading.value = false;
        const { data } = response;
        console.log("login", data);

        if (data.data) {
          loginModalVisible.value = false;
          message.success("登录成功！");
          store.commit("setUser", data.data);
        } else {
          message.error(data.msg);
        }
      });
    };

    // 退出登录
    const logout = () => {
      axios.get("/user/logout/" + user.value.token).then((response) => {
        const { data } = response;
        if (data.code === 200) {
          message.success("退出登录成功！");
          store.commit("setUser", {});
          router.push("/");
        } else {
          message.error(data.message);
        }
      });
    };

    return {
      loginModalVisible,
      loginModalLoading,
      showLoginModal,
      loginUser,
      login,

      user,
      logout,
    };
  },
});
</script>
<style>
.logo {
  width: 120px;
  height: 31px;
  float: left;
  color: white;
  font-size: 18px;
}
.login-menu {
  float: right;
  color: white;
  padding-left: 10px;
}
</style>
