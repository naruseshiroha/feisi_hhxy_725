import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import Antd from "ant-design-vue";
import "ant-design-vue/dist/antd.css";
import * as Icons from "@ant-design/icons-vue";
import axios from "axios";
import { Tool } from "./utils/tool";

axios.defaults.baseURL = process.env.VUE_APP_API;

const app = createApp(App);
app.use(store).use(Antd).use(router).mount("#app");
// 全局使用图标
const icons: any = Icons;
for (const i in icons) {
  app.component(i, icons[i]);
}

/**
 * axios拦截器
 */
axios.interceptors.request.use(
  function (config) {
    console.log("请求参数：", config);
    const token = store.state.user.token;
    if (Tool.isNotEmpty(token)) {
      if (config.headers) {
        config.headers.token = token;
        console.log("请求headers增加token:", token);
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
// axios.interceptors.response.use(
//   function (response) {
//     console.log("返回结果：", response);
//     return response;
//   },
//   (error) => {
//     console.log("返回错误：", error);
//     return Promise.reject(error);
//   }
// );