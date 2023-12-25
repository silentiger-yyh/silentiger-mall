import axios from "axios";
import { getToken } from "./auth";


const instance = axios.create({
  baseURL: '',
  timeout: 3000,
});

// 全局请求拦截，参考https://github.com/axios/axios?tab=readme-ov-file#interceptors
// 发送请求之前执行
instance.interceptors.request.use(
  function (config) {
    /*
    Bearer 是授权的类型，常见的授权类型有：
      Basic 用于 http-basic 认证；
      Bearer 常见于 OAuth 和 JWT 授权；
      Digest MD5 哈希的 http-basic 认证
      AWS4-HMAC-SHA256 授权
    */
    console.log(process.env)
    config.headers["Authorization"] = "Bearer " + getToken();
    return config;
  },
  function (error) {
    // Do something with request error
    return Promise.reject(error);
  }
);

// 全局响应拦截，参考https://github.com/axios/axios?tab=readme-ov-file#interceptors
// 请求返回之后执行
instance.interceptors.response.use(
  function (response) {
    // 可以对返回的结果进行过滤，比如，响应中又多层嵌套，我们直接取数据层
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response;
  },
  function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    return Promise.reject(error);
  }
);

/**
 * get请求
 * @param {*} url 请求地址
 * @param {*} params url传参
 * @returns
 */
export function get(url, params) {
  return instance.get(url, {
    params,
  });
}

/**
 * post请求
 * @param {*} url 请求地址
 * @param {*} data 参数
 * @returns
 */
export function post(url, data) {
  return instance.post(url, data);
}

/**
 * put请求
 * @param {*} url url地址
 * @param {*} data 数据
 * @returns
 */
export function put(url, data) {
  return instance.put(url, data);
}

/**
 * delete请求
 * @param {*} url 请求地址
 * @returns
 */
export function del(url) {
  return instance.delete(url);
}
