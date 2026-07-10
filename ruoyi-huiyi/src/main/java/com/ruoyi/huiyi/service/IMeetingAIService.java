package com.ruoyi.huiyi.service;

import java.util.Map;

/**
 * 会议AI服务接口
 * 对应Python后端的ai_services_api.py提供的功能
 */
public interface IMeetingAIService {

    /**
     * 健康检查
     */
    Map<String, Object> healthCheck();

    /**
     * 开始会议录制（音频+视频）
     */
    Map<String, Object> startMeeting(String rtspUrl, String roomId);

    /**
     * 结束会议录制（音频+视频）
     */
    Map<String, Object> stopMeeting(String roomId);

    /**
     * 获取会议转录文本
     */
    Map<String, Object> getTranscript(String roomId);

    /**
     * 提交会议纪要生成任务
     */
    Map<String, Object> generateMinutes(String roomId);

    /**
     * 获取会议纪要生成状态
     */
    Map<String, Object> getMinutesStatus(String roomId);

    /**
     * 获取已生成的会议纪要
     */
    Map<String, Object> getMinutes(String roomId);
}