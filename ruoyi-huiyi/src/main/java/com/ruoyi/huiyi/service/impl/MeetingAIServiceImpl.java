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

    @Value("${ai.services.api.url:http://localhost:5000}")
    private String aiServicesApiUrl;

    /**
     * 构建完整的API URL
     */
    private String buildApiUrl(String endpoint) {
        return aiServicesApiUrl + endpoint;
    }

    @Override
    public Map<String, Object> healthCheck() {
        String url = buildApiUrl("/api/health");
        try {
            String response = HttpUtils.sendGet(url);
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("code", 500);
            errorResult.put("msg", "Error calling health check API: " + e.getMessage());
            errorResult.put("data", null);
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> startMeeting(String rtspUrl, String roomId) {
        String url = buildApiUrl("/api/meeting/start");
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("rtspUrl", rtspUrl);
            requestBody.put("roomId", roomId);

            String jsonData = JSON.toJSONString(requestBody);
            String response = HttpUtils.sendPost(url, jsonData, "application/json");
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("code", 500);
            errorResult.put("msg", "Error starting meeting recording: " + e.getMessage());
            errorResult.put("data", null);
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> stopMeeting(String roomId) {
        String url = buildApiUrl("/api/meeting/stop");
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("roomId", roomId);

            String jsonData = JSON.toJSONString(requestBody);
            String response = HttpUtils.sendPost(url, jsonData, "application/json");
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("code", 500);
            errorResult.put("msg", "Error stopping meeting recording: " + e.getMessage());
            errorResult.put("data", null);
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> getTranscript(String roomId) {
        String url = buildApiUrl("/api/meeting/" + roomId + "/transcript");
        try {
            String response = HttpUtils.sendGet(url);
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("code", 500);
            errorResult.put("msg", "Error getting transcript: " + e.getMessage());
            errorResult.put("data", null);
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> generateMinutes(String roomId) {
        String url = buildApiUrl("/api/meeting/" + roomId + "/minutes/generate");
        try {
            String response = HttpUtils.sendPost(url, "{}", "application/json");
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("code", 500);
            errorResult.put("msg", "Error generating minutes: " + e.getMessage());
            errorResult.put("data", null);
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> getMinutesStatus(String roomId) {
        String url = buildApiUrl("/api/meeting/" + roomId + "/minutes/status");
        try {
            String response = HttpUtils.sendGet(url);
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("code", 500);
            errorResult.put("msg", "Error getting minutes status: " + e.getMessage());
            errorResult.put("data", null);
            return errorResult;
        }
    }

    @Override
    public Map<String, Object> getMinutes(String roomId) {
        String url = buildApiUrl("/api/meeting/" + roomId + "/minutes");
        try {
            String response = HttpUtils.sendGet(url);
            return JSON.parseObject(response, Map.class);
        } catch (Exception e) {
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("code", 500);
            errorResult.put("msg", "Error getting minutes: " + e.getMessage());
            errorResult.put("data", null);
            return errorResult;
        }
    }
}