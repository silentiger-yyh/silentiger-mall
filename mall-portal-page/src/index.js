import React from "react";
import ReactDOM from "react-dom/client";
import {
  HashRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import "./index.css";
import App from "./App";
import "antd/dist/antd";
import { mainRoutes } from "./routes/route";
import reportWebVitals from "./reportWebVitals";
import { isLogined } from "./utils/authUtils";
import { Provider } from "react-redux";
import store from "./store/store";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <Router>
        {/* 注册路由 */}
        {/* 注册路由时，使用 Switch 组件包裹，可以实现单一匹配，一个路由对应一个组件 */}
        {/* 渲染与该地址匹配的第一个子节点 <Route> 或者 <Redirect>。 */}
        {/* 如果不使用 Switch 组件进行包裹，相同 path 的就会被匹配到，然后一起展示*/}
        <Switch>
          {/* routeProps 是干嘛的 */}
          <Route
            path="/home"
            key="/home"
            render={(routeProps) => <App {...routeProps} />}
          />
          {mainRoutes.map((route) => {
            // 如果已经登陆过，不能重复登录，需要跳转到管理页面
            if (route.path === "/login" && isLogined()) {
              return <Redirect from="/" to="/home" key="/home" />;
            }
            return <Route key={route.path} {...route}></Route>;
          })}
          {/*Redirect：一般写在所有路由注册的最下方，当所有路由都无法匹配时，跳转到Redirect指定的路由 */}
          {/* 没有匹配到任何路由，访问根路由跳转到admin管理页 */}
          <Redirect from="/" to="/home" />
          <Redirect to="/404" />
        </Switch>
      </Router>
    </Provider>
  </React.StrictMode>
);
reportWebVitals();
