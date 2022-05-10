import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AdminEbook from "../views/admin/AdminEbook.vue";
import AdminCategory from "../views/admin/AdminCategory.vue";
import AdminDoc from "../views/admin/AdminDoc.vue";
import DocView from "../views/DocView.vue";
import store from "@/store";
import { Tool } from "@/utils/tool";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },
  {
    path: "/admin/ebook",
    name: "adminEbook",
    component: AdminEbook,
    meta: {
      loginRequire: true,
    },
  },
  {
    path: "/admin/category",
    name: "AdminCategory",
    component: AdminCategory,
    meta: {
      loginRequire: true,
    },
  },
  {
    path: "/admin/doc",
    name: "AdminDoc",
    component: AdminDoc,
    meta: {
      loginRequire: true,
    },
  },
  {
    path: "/admin/user",
    name: "AdminUser",
    component: import("@/views/admin/AdminUser.vue"),
    meta: {
      loginRequire: true,
    },
  },
  {
    path: "/doc",
    name: "Doc",
    component: DocView,
    // meta: {
    //   loginRequire: true,
    // },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  // 要不要对meta.loginRequire属性做监控拦截
  if (
    to.matched.some(function (item) {
      console.log(item, "是否需要登录校验：", item.meta.loginRequire);
      return item.meta.loginRequire;
    })
  ) {
    const loginUser = store.state.user;
    if (Tool.isEmpty(loginUser)) {
      console.log("用户未登录！");
      next("/");
    } else {
      next();
    }
  } else {
    next();
  }
});

export default router;
