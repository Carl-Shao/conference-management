package com.ruoyi.huiyi.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.huiyi.service.IMeetingAIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 会议AI服务接口实现
 * 调用Python后端的ai_services_api.py提供的功能
 */
@Service
public class MeetingAIServiceImpl implements IMeetingAIService {

    @Value("${ai.services.api.url:http://localhost:5001}")
    private String aiServicesApiUrl;

    /**
     * 构建完整的API URL
     */
    private String buildApiUrl(String endpoint) {
        return aiServicesApiUrl + endpoint;
    }

    @Override
    public Map<String, Object> healthCheck() {
        String url = buildApiUrl("/api/v1/health");
        try {
            String response = HttpUtils.sendGet(url);
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "Error calling health check API: " + e.getMessage());
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> startAsrRecording(String rtspUrl, String roomId) {
        String url = buildApiUrl("/api/v1/asr/start-recording");
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("rtsp_url", rtspUrl);
            requestBody.put("room_id", roomId);

            String jsonData = JSON.toJSONString(requestBody);
            String response = HttpUtils.sendPost(url, jsonData, "application/json");
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "Error starting ASR recording: " + e.getMessage());
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> startFullRecording(String rtspUrl, String roomId) {
        String url = buildApiUrl("/api/v1/recording/start-full");
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("rtsp_url", rtspUrl);
            requestBody.put("room_id", roomId);

            String jsonData = JSON.toJSONString(requestBody);
            String response = HttpUtils.sendPost(url, jsonData, "application/json");
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "Error starting full recording: " + e.getMessage());
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> stopAsrRecording(String roomId) {
        String url = buildApiUrl("/api/v1/asr/stop-recording");
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("room_id", roomId);

            String jsonData = JSON.toJSONString(requestBody);
            String response = HttpUtils.sendPost(url, jsonData, "application/json");
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "Error stopping ASR recording: " + e.getMessage());
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> stopFullRecording() {
        String url = buildApiUrl("/api/v1/recording/stop-full");
        try {
            String response = HttpUtils.sendPost(url, "{}", "application/json");
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "Error stopping full recording: " + e.getMessage());
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> getRecordingPath(String roomId) {
        String url = buildApiUrl("/api/v1/recording/path/" + roomId);
        try {
            String response = HttpUtils.sendGet(url);
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "Error getting recording path: " + e.getMessage());
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> getTranscript(String roomId) {
        String url = buildApiUrl("/api/v1/asr/get-transcript/" + roomId);
        try {
            String response = HttpUtils.sendGet(url);
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "Error getting transcript: " + e.getMessage());
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> generateMinutes(String roomId, String transcript) {
        String url = buildApiUrl("/api/v1/minutes/generate");
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("room_id", roomId);
            if (transcript != null) {
                requestBody.put("transcript", transcript);
            }

            String jsonData = JSON.toJSONString(requestBody);
            String response = HttpUtils.sendPost(url, jsonData, "application/json");
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "Error generating minutes: " + e.getMessage());
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> getMinutes(String roomId) {
        String url = buildApiUrl("/api/v1/minutes/get/" + roomId);
        try {
            String response = HttpUtils.sendGet(url);
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "Error getting minutes: " + e.getMessage());
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> getStatus() {
        String url = buildApiUrl("/api/v1/status");
        try {
            String response = HttpUtils.sendGet(url);
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("success", false);
            errorResult.put("message", "Error getting status: " + e.getMessage());
            return errorResult;
        }
    }
}