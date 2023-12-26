import PageNotFound from "../pages/common/PageNotFound";
import Index from "../pages/admin/dashboard/Index";
import Edit from "../pages/admin/products/Edit";
import List from "../pages/admin/products/List";
import Login from "../pages/user/Login";
import Register from "../pages/user/Register";
import Notice from "../pages/admin/notice/Index";

import { ShopOutlined, AreaChartOutlined } from "@ant-design/icons";
export const mainRoutes = [
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/register",
    component: Register,
  },
  {
    path: "/404",
    component: PageNotFound,
  },
];

export const adminRoutes = [
  {
    path: "/home",
    component: Index,
    isShow: true,
    title: "主页",
    icon: <AreaChartOutlined />,
  },
  // {
  //   path: "/admin/products",
  //   component: List,
  //   isShow: true,
  //   exact: true, // 为true是表示严格匹配，为false时为正常匹配。
  //   title: "商品管理",
  //   icon: <ShopOutlined />,
  // },
  // {
  //   path: "/admin/products/edit/:id?", // ? 表示id是可选参数
  //   component: Edit,
  //   isShow: false,
  // },
  // {
  //   path: "/admin/notice", // ? 表示id是可选参数
  //   component: Notice,
  //   isShow: false,
  // },
];
