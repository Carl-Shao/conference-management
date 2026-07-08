package com.ruoyi.huiyi.domain.dto;

/**
 * 会议纪要请求数据传输对象
 */
public class MinutesRequestDto {

    /**
     * 房间ID
     */
    private String roomId;

    /**
     * 转录文本
     */
    private String transcript;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }
}