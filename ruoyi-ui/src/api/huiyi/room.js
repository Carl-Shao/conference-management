import request from '@/utils/request'

// 查询会议室信息列表
export function listRoom(query) {
  return request({
    url: '/huiyi/room/list',
    method: 'get',
    params: query
  })
}

// 查询会议室信息详细
export function getRoom(id) {
  return request({
    url: '/huiyi/room/' + id,
    method: 'get'
  })
}

// 新增会议室信息
export function addRoom(data) {
  return request({
    url: '/huiyi/room',
    method: 'post',
    data: data
  })
}

// 修改会议室信息
export function updateRoom(data) {
  return request({
    url: '/huiyi/room',
    method: 'put',
    data: data
  })
}

// 删除会议室信息
export function delRoom(id) {
  return request({
    url: '/huiyi/room/' + id,
    method: 'delete'
  })
}
