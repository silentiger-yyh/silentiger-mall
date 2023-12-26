/**
 * 获取token
 * @returns
 */
export function getToken() {
  return localStorage.getItem("token");
}

/**
 * 设置token
 * @param {*} token
 */
export function setToken(token) {
  localStorage.setItem("token", token);
}

/**
 * 是否已经登陆过了
 * @returns
 */
export function isLogined() {
  if (localStorage.getItem("token")) {
    return true;
  }
  return false;
}

/**
 * 验证token身份信息是否过期
 * @returns false：未过期
 */
export function isExpired() {
  const token = localStorage.getItem("token");
  // 验证token是否过期
  return false;
}

/**
 * 清空token
 */
export function clearToken() {
  localStorage.removeItem("token");
}
