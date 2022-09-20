import request from '../../http/Request.js'

/**
 * 根据条件分页查找举报信息
 * @param queryData
 * @param pageData
 * @returns {Promise<AxiosResponse<any>>}
 */
export function getReportManageVo ({
  queryData,
  pageData,
}) {
  return request.post('/report/getReportManageVo', {
    "nickName": queryData.nickName,
    "title": queryData.title,
    "typeId": queryData.typeId,
    "createTimes": queryData.createTimes,
    "status": queryData.status,
    "page": pageData.page,
    "pageSize": pageData.pageSize,
  })
}

/**
 * 查询所有视频类型
 * @returns {Promise<AxiosResponse<any>>}
 */
export function findVideoTypes () {
  return request.post("/videoTypes/findVideoTypes")
}

/**
 * 通过条件分页查询广告信息
 * @returns {Promise<AxiosResponse<any>>}
 */
export function getAdvertiseManageVo ({ queryData, pageData }) {
  return request.post("/advertise/getAdvertiseManageVo", {
    "nickName": queryData.nickName,
    "posterUrl": queryData.posterUrl,
    "url": queryData.url,
    "status": queryData.status,
    "page": pageData.page,
    "pageSize": pageData.pageSize,
  })
}

/**
 * 根据条件分页查找视频信息
 * @param queryData
 * @param pageData
 * @returns {Promise<AxiosResponse<any>>}
 */
export function getVideoManageVo ({
  queryData,
  pageData,
}) {
  return request.post('/video/getVideoManageVo', {
    "title": queryData.title,
    "typeId": queryData.typeId,
    "nickName": queryData.nickName,
    "createTimes": queryData.createTimes,
    "state": queryData.state,
    "page": pageData.page,
    "pageSize": pageData.pageSize,
  })
}

/**
 * 通过id逻辑删除广告
 * @param id 广告id
 * @returns {Promise<AxiosResponse<any>>}
 */
export function logicDeleteAdvertise (id) {
  return request.post('/advertise/logicDeleteAdvertise/?id=' + id)
}

/**
 * 通过id修改举报状态
 * @param id
 * @param status
 * @returns {Promise<AxiosResponse<any>>}
 */
export function updateReportStatus (id, status) {
  return request.post('/report/updateReportStatus', {
    "id": id,
    "status": status,
  })
}

/**
 * 通过主键逻辑删除账户
 * @param id 主键
 * @returns {Promise<AxiosResponse<any>>}
 */
export function logicDeleteAccount (id) {
  return request.post('/user/logicDeleteAccount?id=' + id)
}

/**
 * 通过主键逻辑删除视频
 * @param id
 * @returns {Promise<AxiosResponse<any>>}
 */
export function logicDeleteVideo (id) {
  return request.post('/video/logicDeleteVideo?id=' + id)
}

/**
 * 通过id修改视频状态
 * @param id 主键
 * @param status 视频状态
 * @returns {Promise<AxiosResponse<any>>}
 */
export function updateVideoStatus (id, status) {
  return request.post('/video/updateVideoStatus', {
    "id": id,
    "status": status,
  })
}

/**
 * 通过表单和用户id新增广告
 * @param addData
 * @param id
 * @returns {Promise<AxiosResponse<any>>}
 */
export function addNewAd (addData, id) {
  return request.post("/advertise/addNewAd?id=" + id, addData, {
    "Content-Type": "multipart/form-data",
  })
}

/**
 * 查询用户统计数据
 * @returns {Promise<AxiosResponse<any>>}
 */
export function getUserView () {
  return request.get("/data/getUserView")
}
