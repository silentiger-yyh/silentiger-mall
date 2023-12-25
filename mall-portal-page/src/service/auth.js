import { post } from "../utils/request";
import {AUTH_BASE_URL} from "../utils/config";

/**
 * 用户登录
 * @param {*} user 用户登录信息
 * @returns
 * username
 * password
 */
export function loginApi(user) {
  if(!user['grant_type']) {
    user['grant_type'] = 'password'
  }
  if (!user['scopes']) {
    user['scopes'] = 'all'
  }
  return post(AUTH_BASE_URL + '/oauth/token', user);
}
