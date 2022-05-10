<template>
  <a-layout>
    <TheHead></TheHead>
    <router-view />
    <TheFooter></TheFooter>
  </a-layout>
</template>
<script>
import {
  UserOutlined,
  LaptopOutlined,
  NotificationOutlined,
} from "@ant-design/icons-vue";
import { defineComponent, ref } from "vue";
import TheHead from "@/components/TheHead";
import TheFooter from "@/components/TheFooter";
export default defineComponent({
  components: {
    UserOutlined,
    LaptopOutlined,
    NotificationOutlined,
    TheHead,
    TheFooter,
  },

  setup() {
    return {
      selectedKeys1: ref(["2"]),
      selectedKeys2: ref(["1"]),
      collapsed: ref(false),
      openKeys: ref(["sub1"]),
    };
  },
});
</script>
<style>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>
