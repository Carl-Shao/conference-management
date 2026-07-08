package com.ruoyi.huiyi.domain.dto;

/**
 * ASR请求数据传输对象
 */
public class AsrRequestDto {

    /**
     * RTSP流地址
     */
    private String rtspUrl;

    /**
     * 房间ID
     */
    private String roomId;

    public String getRtspUrl() {
        return rtspUrl;
    }

    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}