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
     * 启动RTSP音频录制和实时ASR转录
     */
    Map<String, Object> startAsrRecording(String rtspUrl, String roomId);

    /**
     * 启动完整RTSP音频录制
     */
    Map<String, Object> startFullRecording(String rtspUrl, String roomId);

    /**
     * 停止ASR录音
     */
    Map<String, Object> stopAsrRecording(String roomId);

    /**
     * 停止完整录音
     */
    Map<String, Object> stopFullRecording();

    /**
     * 获取完整录音文件路径
     */
    Map<String, Object> getRecordingPath(String roomId);

    /**
     * 获取实时转录文本
     */
    Map<String, Object> getTranscript(String roomId);

    /**
     * 从转录文本生成会议纪要
     */
    Map<String, Object> generateMinutes(String roomId, String transcript);

    /**
     * 获取已生成的会议纪要
     */
    Map<String, Object> getMinutes(String roomId);

    /**
     * 获取服务状态
     */
    Map<String, Object> getStatus();
}