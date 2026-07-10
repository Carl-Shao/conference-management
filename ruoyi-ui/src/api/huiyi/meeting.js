import request from '@/utils/request'

// 查询会议信息列表
export function listMeeting(query) {
  return request({
    url: '/huiyi/meeting/list',
    method: 'get',
    params: query
  })
}

// 查询会议信息详细
export function getMeeting(id) {
  return request({
    url: '/huiyi/meeting/' + id,
    method: 'get'
  })
}

// 新增会议信息
export function addMeeting(data) {
  return request({
    url: '/huiyi/meeting',
    method: 'post',
    data: data
  })
}

// 修改会议信息
export function updateMeeting(data) {
  return request({
    url: '/huiyi/meeting',
    method: 'put',
    data: data
  })
}

// 删除会议信息
export function delMeeting(id) {
  return request({
    url: '/huiyi/meeting/' + id,
    method: 'delete'
  })
}
