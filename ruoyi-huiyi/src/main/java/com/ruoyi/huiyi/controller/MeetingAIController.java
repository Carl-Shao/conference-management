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
     * 开始会议录制
     */
    @PostMapping("/meeting/start")
    public AjaxResult startMeeting(@RequestBody AsrRequestDto request) {
        try {
            Map<String, Object> result = meetingAIService.startMeeting(
                    request.getRtspUrl(), request.getRoomId());
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Start meeting recording failed: " + e.getMessage());
        }
    }

    /**
     * 结束会议录制
     */
    @PostMapping("/meeting/stop")
    public AjaxResult stopMeeting(@RequestParam String roomId) {
        try {
            Map<String, Object> result = meetingAIService.stopMeeting(roomId);
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Stop meeting recording failed: " + e.getMessage());
        }
    }

    /**
     * 获取会议转录文本
     */
    @GetMapping("/transcript/{roomId}")
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
    @PostMapping("/minutes/generate/{roomId}")
    public AjaxResult generateMinutes(@PathVariable String roomId) {
        try {
            Map<String, Object> result = meetingAIService.generateMinutes(roomId);
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Generate minutes failed: " + e.getMessage());
        }
    }

    /**
     * 获取会议纪要生成状态
     */
    @GetMapping("/minutes/status/{roomId}")
    public AjaxResult getMinutesStatus(@PathVariable String roomId) {
        try {
            Map<String, Object> result = meetingAIService.getMinutesStatus(roomId);
            return AjaxResult.success(result);
        } catch (Exception e) {
            return AjaxResult.error("Get minutes status failed: " + e.getMessage());
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
}