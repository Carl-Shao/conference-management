package com.ruoyi.huiyi.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.huiyi.domain.dto.AsrRequestDto;
import com.ruoyi.huiyi.domain.dto.MinutesRequestDto;
import com.ruoyi.huiyi.service.IMeetingAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * AI服务控制器
 * 提供对Python AI服务API的访问接口
 */
@RestController
@RequestMapping("/huiyi/ai")
public class MeetingAIController {

    @Autowired
    private IMeetingAIService meetingAIService;

    /**
     * 健康检查
     */
    @GetMapping("/health")
    public AjaxResult healthCheck() {
        try {
            Map<String, Object> result = meetingAIService.healthCheck();
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Health check failed: " + e.getMessage());
        }
    }

    /**
     * 启动ASR录音
     */
    @PostMapping("/asr/start")
    public AjaxResult startAsrRecording(@RequestBody AsrRequestDto request) {
        try {
            Map<String, Object> result = meetingAIService.startAsrRecording(
                    request.getRtspUrl(), request.getRoomId());
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Start ASR recording failed: " + e.getMessage());
        }
    }

    /**
     * 启动完整录音
     */
    @PostMapping("/recording/start")
    public AjaxResult startFullRecording(@RequestBody AsrRequestDto request) {
        try {
            Map<String, Object> result = meetingAIService.startFullRecording(
                    request.getRtspUrl(), request.getRoomId());
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Start full recording failed: " + e.getMessage());
        }
    }

    /**
     * 停止ASR录音
     */
    @PostMapping("/asr/stop")
    public AjaxResult stopAsrRecording(@RequestParam String roomId) {
        try {
            Map<String, Object> result = meetingAIService.stopAsrRecording(roomId);
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Stop ASR recording failed: " + e.getMessage());
        }
    }

    /**
     * 停止完整录音
     */
    @PostMapping("/recording/stop")
    public AjaxResult stopFullRecording() {
        try {
            Map<String, Object> result = meetingAIService.stopFullRecording();
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Stop full recording failed: " + e.getMessage());
        }
    }

    /**
     * 获取录音路径
     */
    @GetMapping("/recording/path/{roomId}")
    public AjaxResult getRecordingPath(@PathVariable String roomId) {
        try {
            Map<String, Object> result = meetingAIService.getRecordingPath(roomId);
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Get recording path failed: " + e.getMessage());
        }
    }

    /**
     * 获取转录文本
     */
    @GetMapping("/asr/transcript/{roomId}")
    public AjaxResult getTranscript(@PathVariable String roomId) {
        try {
            Map<String, Object> result = meetingAIService.getTranscript(roomId);
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Get transcript failed: " + e.getMessage());
        }
    }

    /**
     * 生成会议纪要
     */
    @PostMapping("/minutes/generate")
    public AjaxResult generateMinutes(@RequestBody MinutesRequestDto request) {
        try {
            Map<String, Object> result = meetingAIService.generateMinutes(
                    request.getRoomId(), request.getTranscript());
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Generate minutes failed: " + e.getMessage());
        }
    }

    /**
     * 获取会议纪要
     */
    @GetMapping("/minutes/{roomId}")
    public AjaxResult getMinutes(@PathVariable String roomId) {
        try {
            Map<String, Object> result = meetingAIService.getMinutes(roomId);
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Get minutes failed: " + e.getMessage());
        }
    }

    /**
     * 获取服务状态
     */
    @GetMapping("/status")
    public AjaxResult getStatus() {
        try {
            Map<String, Object> result = meetingAIService.getStatus();
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Get status failed: " + e.getMessage());
        }
    }
}