package com.ruoyi.huiyi.service;

import java.util.List;
import com.ruoyi.huiyi.domain.MeetingResource;

/**
 * 会议资源（完整转录、会议概要、录音）Service接口
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
public interface IMeetingResourceService 
{
    /**
     * 查询会议资源（完整转录、会议概要、录音）
     * 
     * @param id 会议资源（完整转录、会议概要、录音）主键
     * @return 会议资源（完整转录、会议概要、录音）
     */
    public MeetingResource selectMeetingResourceById(Long id);

    /**
     * 查询会议资源（完整转录、会议概要、录音）列表
     * 
     * @param meetingResource 会议资源（完整转录、会议概要、录音）
     * @return 会议资源（完整转录、会议概要、录音）集合
     */
    public List<MeetingResource> selectMeetingResourceList(MeetingResource meetingResource);

    /**
     * 新增会议资源（完整转录、会议概要、录音）
     * 
     * @param meetingResource 会议资源（完整转录、会议概要、录音）
     * @return 结果
     */
    public int insertMeetingResource(MeetingResource meetingResource);

    /**
     * 修改会议资源（完整转录、会议概要、录音）
     * 
     * @param meetingResource 会议资源（完整转录、会议概要、录音）
     * @return 结果
     */
    public int updateMeetingResource(MeetingResource meetingResource);

    /**
     * 批量删除会议资源（完整转录、会议概要、录音）
     * 
     * @param ids 需要删除的会议资源（完整转录、会议概要、录音）主键集合
     * @return 结果
     */
    public int deleteMeetingResourceByIds(Long[] ids);

    /**
     * 删除会议资源（完整转录、会议概要、录音）信息
     * 
     * @param id 会议资源（完整转录、会议概要、录音）主键
     * @return 结果
     */
    public int deleteMeetingResourceById(Long id);
}
