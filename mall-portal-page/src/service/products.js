import { get, post, put, del } from "../utils/request";

/**
 * 获取列表
 * @param {*} data
 * @returns
 */
export function listApi(page = 1) {
  return get("/api/v1/admin/products", { page, per: 2 });
}

/**
 * 创建数据
 * @param {*} data
 * @returns
 */
export function createApi(data) {
  return post("/api/v1/admin/products", data);
}

/**
 * 根据id获取数据
 * @param {*} id
 * @returns
 */
export function getOneById(id) {
  return get(`/api/v1/admin/products/${id}`);
}
/**
 * 修改数据
 * @param {*} id
 * @param {*} data
 * @returns
 */
export function modifyOne(id, data) {
  return put(`/api/v1/admin/products/${id}`, data);
}

/**
 * 删除数据
 * @param {*} id
 * @returns
 */
export function delOne(id) {
  return del(`/api/v1/admin/products/${id}`);
}
