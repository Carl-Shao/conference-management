package com.ruoyi.huiyi.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会议信息对象 meeting
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
public class Meeting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会议 ID（主键） */
    private Long id;

    /** 会议主题 */
    @Excel(name = "会议主题")
    private String title;

    /** 会议室 ID（关联 meeting_room 表） */
    @Excel(name = "会议室 ID", readConverterExp = "关=联,m=eeting_room,表=")
    private Long roomId;

    /** 会议日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "会议日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date meetingDate;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "HH:mm:ss")
    private String startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "HH:mm:ss")
    private String endTime;

    /** 组织者工号（关联 meeting_employee 表） */
    @Excel(name = "组织者工号", readConverterExp = "关=联,m=eeting_employee,表=")
    private String organizerNo;

    /** 参会人员工号数组 (JSON 格式，如：["E001","E002","E003"]) */
    @Excel(name = "参会人员工号数组", readConverterExp = "J=SON,格=式，如：=[\"E001\",\"E002\",\"E003\"]")
    private String participantNos;

    /** 会议描述 */
    @Excel(name = "会议描述")
    private String description;

    /** 会议状态（scheduled:预定，active:进行中，completed:已结束，cancelled:已取消） */
    @Excel(name = "会议状态", readConverterExp = "s=cheduled:预定，active:进行中，completed:已结束，cancelled:已取消")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }

    public void setMeetingDate(Date meetingDate) 
    {
        this.meetingDate = meetingDate;
    }

    public Date getMeetingDate() 
    {
        return meetingDate;
    }

    public void setStartTime(String startTime) 
    {
        this.startTime = startTime;
    }

    public String getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(String endTime) 
    {
        this.endTime = endTime;
    }

    public String getEndTime() 
    {
        return endTime;
    }

    public void setOrganizerNo(String organizerNo) 
    {
        this.organizerNo = organizerNo;
    }

    public String getOrganizerNo() 
    {
        return organizerNo;
    }

    public void setParticipantNos(String participantNos) 
    {
        this.participantNos = participantNos;
    }

    public String getParticipantNos() 
    {
        return participantNos;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("roomId", getRoomId())
            .append("meetingDate", getMeetingDate())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("organizerNo", getOrganizerNo())
            .append("participantNos", getParticipantNos())
            .append("description", getDescription())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
