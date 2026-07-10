import request from '@/utils/request'

// 健康检查
export function healthCheck() {
  return request({
    url: '/huiyi/ai/health',
    method: 'get'
  })
}

// 开始会议录制
export function startMeeting(data) {
  return request({
    url: '/huiyi/ai/meeting/start',
    method: 'post',
    data: data
  })
}

// 结束会议录制
export function stopMeeting(roomId) {
  return request({
    url: '/huiyi/ai/meeting/stop',
    method: 'post',
    params: { roomId: roomId }
  })
}

// 获取转录文本
export function getTranscript(roomId) {
  return request({
    url: '/huiyi/ai/transcript/' + roomId,
    method: 'get'
  })
}

// 生成会议纪要
export function generateMinutes(roomId) {
  return request({
    url: '/huiyi/ai/minutes/generate/' + roomId,
    method: 'post'
  })
}

// 获取会议纪要状态
export function getMinutesStatus(roomId) {
  return request({
    url: '/huiyi/ai/minutes/status/' + roomId,
    method: 'get'
  })
}

// 获取会议纪要
export function getMinutes(roomId) {
  return request({
    url: '/huiyi/ai/minutes/' + roomId,
    method: 'get'
  })
}