import request from '@/utils/request'

// 查询会议资源（完整转录、会议概要、录音）列表
export function listResource(query) {
  return request({
    url: '/huiyi/resource/list',
    method: 'get',
    params: query
  })
}

// 查询会议资源（完整转录、会议概要、录音）详细
export function getResource(id) {
  return request({
    url: '/huiyi/resource/' + id,
    method: 'get'
  })
}

// 新增会议资源（完整转录、会议概要、录音）
export function addResource(data) {
  return request({
    url: '/huiyi/resource',
    method: 'post',
    data: data
  })
}

// 修改会议资源（完整转录、会议概要、录音）
export function updateResource(data) {
  return request({
    url: '/huiyi/resource',
    method: 'put',
    data: data
  })
}

// 删除会议资源（完整转录、会议概要、录音）
export function delResource(id) {
  return request({
    url: '/huiyi/resource/' + id,
    method: 'delete'
  })
}
