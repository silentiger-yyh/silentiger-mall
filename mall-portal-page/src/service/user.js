import { post, get } from "../config/requestConfig";
import {AUTH_BASE_URL} from "../config/globalConfig";
import {clearToken, setToken} from "../utils/authUtils";

/**
 * 用户登录
 * @param props 组件参数
 * @param user 用户登录信息
 * @returns
 * username
 * password
 */
export function loginApi(props, user) {
  if(!user['grant_type']) {
    user['grant_type'] = 'password'
  }
  if (!user['scopes']) {
    user['scopes'] = 'all'
  }
  post(AUTH_BASE_URL + '/oauth/token', user)
    .then((res) => {
      if (res.status === 200) {
        setToken(res.data.data.token);
        props.history.push("/home");
        console.log("登录成功");
      } else {
        console.log("登录失败");
      }
    })
    .catch((err) => {});
}

export function logoutApi() {
  get(AUTH_BASE_URL + '/oauth/logout', {})
    .then(res => {
      if (res.data.code === 200) {
        clearToken();  // 清除token
      }
    }).catch(err => {
      console.log("退出异常",err.message)
    })
}