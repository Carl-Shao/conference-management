package com.ruoyi.huiyi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会议资源（完整转录、会议概要、录音）对象 meeting_resource
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
public class MeetingResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资源 ID（主键） */
    private Long id;

    /** 会议 ID（关联 meeting 表） */
    @Excel(name = "会议 ID", readConverterExp = "关=联,m=eeting,表=")
    private Long meetingId;

    /** 会议主题 */
    @Excel(name = "会议主题")
    private String meetingTitle;

    /** 完整会议转录（逐字稿） */
    @Excel(name = "完整会议转录", readConverterExp = "逐=字稿")
    private String fullTranscript;

    /** 会议概要内容（简要总结） */
    @Excel(name = "会议概要内容", readConverterExp = "简=要总结")
    private String summaryContent;

    /** 录音文件路径 */
    @Excel(name = "录音文件路径")
    private String recordingPath;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setMeetingId(Long meetingId) 
    {
        this.meetingId = meetingId;
    }

    public Long getMeetingId() 
    {
        return meetingId;
    }

    public void setMeetingTitle(String meetingTitle) 
    {
        this.meetingTitle = meetingTitle;
    }

    public String getMeetingTitle() 
    {
        return meetingTitle;
    }

    public void setFullTranscript(String fullTranscript) 
    {
        this.fullTranscript = fullTranscript;
    }

    public String getFullTranscript() 
    {
        return fullTranscript;
    }

    public void setSummaryContent(String summaryContent) 
    {
        this.summaryContent = summaryContent;
    }

    public String getSummaryContent() 
    {
        return summaryContent;
    }

    public void setRecordingPath(String recordingPath) 
    {
        this.recordingPath = recordingPath;
    }

    public String getRecordingPath() 
    {
        return recordingPath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("meetingId", getMeetingId())
            .append("meetingTitle", getMeetingTitle())
            .append("fullTranscript", getFullTranscript())
            .append("summaryContent", getSummaryContent())
            .append("recordingPath", getRecordingPath())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
