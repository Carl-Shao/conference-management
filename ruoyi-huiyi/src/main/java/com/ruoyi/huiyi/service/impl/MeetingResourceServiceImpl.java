package com.ruoyi.huiyi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.huiyi.mapper.MeetingResourceMapper;
import com.ruoyi.huiyi.domain.MeetingResource;
import com.ruoyi.huiyi.service.IMeetingResourceService;

/**
 * 会议资源（完整转录、会议概要、录音）Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
@Service
public class MeetingResourceServiceImpl implements IMeetingResourceService 
{
    @Autowired
    private MeetingResourceMapper meetingResourceMapper;

    /**
     * 查询会议资源（完整转录、会议概要、录音）
     * 
     * @param id 会议资源（完整转录、会议概要、录音）主键
     * @return 会议资源（完整转录、会议概要、录音）
     */
    @Override
    public MeetingResource selectMeetingResourceById(Long id)
    {
        return meetingResourceMapper.selectMeetingResourceById(id);
    }

    /**
     * 查询会议资源（完整转录、会议概要、录音）列表
     * 
     * @param meetingResource 会议资源（完整转录、会议概要、录音）
     * @return 会议资源（完整转录、会议概要、录音）
     */
    @Override
    public List<MeetingResource> selectMeetingResourceList(MeetingResource meetingResource)
    {
        return meetingResourceMapper.selectMeetingResourceList(meetingResource);
    }

    /**
     * 新增会议资源（完整转录、会议概要、录音）
     * 
     * @param meetingResource 会议资源（完整转录、会议概要、录音）
     * @return 结果
     */
    @Override
    public int insertMeetingResource(MeetingResource meetingResource)
    {
        meetingResource.setCreateTime(DateUtils.getNowDate());
        return meetingResourceMapper.insertMeetingResource(meetingResource);
    }

    /**
     * 修改会议资源（完整转录、会议概要、录音）
     * 
     * @param meetingResource 会议资源（完整转录、会议概要、录音）
     * @return 结果
     */
    @Override
    public int updateMeetingResource(MeetingResource meetingResource)
    {
        meetingResource.setUpdateTime(DateUtils.getNowDate());
        return meetingResourceMapper.updateMeetingResource(meetingResource);
    }

    /**
     * 批量删除会议资源（完整转录、会议概要、录音）
     * 
     * @param ids 需要删除的会议资源（完整转录、会议概要、录音）主键
     * @return 结果
     */
    @Override
    public int deleteMeetingResourceByIds(Long[] ids)
    {
        return meetingResourceMapper.deleteMeetingResourceByIds(ids);
    }

    /**
     * 删除会议资源（完整转录、会议概要、录音）信息
     * 
     * @param id 会议资源（完整转录、会议概要、录音）主键
     * @return 结果
     */
    @Override
    public int deleteMeetingResourceById(Long id)
    {
        return meetingResourceMapper.deleteMeetingResourceById(id);
    }
}
