// import logo from "./logo.svg";
import "./App.css";
import { Switch, Route, Redirect } from "react-router-dom";
import { adminRoutes } from "./routes";
import Frame from "./components/frame/Index";
import { isExpired, isLogined } from "./utils/auth";
import "./App.css";
/* 
导入带花括号和不带花括号的区别是什么？？？？？
不带花括号{}，引用js文件中默认导出（export default）的模块,import模块时的可随意命名。
带花括号{}，引用js文件中export导出的同名模块,import模块时的命名必须一致。
*/
function App() {
  // 使用三目运算判断是否登录，登录了就跳转到管理页面，否则跳转到登录页面
  return isLogined() && !isExpired() ? ( // 已经登录且没有过期
    <Frame>
      <Switch>
        {/* 遍历adminRoute下面的所有路由 */}
        {adminRoutes.map((route) => {
          return (
            <Route
              key={route.path}
              path={route.path}
              exact={route.exact}
              render={(routeProps) => {
                //routeProps是干嘛的？？？
                return <route.component {...routeProps} />;
              }}
            />
          );
        })}
        {/* 如果上面没匹配到，但是能匹配到以admin开头的话就跳转到to指定的路由 */}
        <Redirect
          to={adminRoutes[0].path}
          from="/admin/"
          key={adminRoutes[0].path}
        />{" "}
        <Redirect to="/404" key="/404" />
      </Switch>
    </Frame>
  ) : (
    <Redirect to="/login" key="/login" />
  );
}

export default App;
