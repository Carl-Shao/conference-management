package com.ruoyi.huiyi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.huiyi.mapper.MeetingRoomMapper;
import com.ruoyi.huiyi.domain.MeetingRoom;
import com.ruoyi.huiyi.service.IMeetingRoomService;

/**
 * 会议室信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
@Service
public class MeetingRoomServiceImpl implements IMeetingRoomService 
{
    @Autowired
    private MeetingRoomMapper meetingRoomMapper;

    /**
     * 查询会议室信息
     * 
     * @param id 会议室信息主键
     * @return 会议室信息
     */
    @Override
    public MeetingRoom selectMeetingRoomById(Long id)
    {
        return meetingRoomMapper.selectMeetingRoomById(id);
    }

    /**
     * 查询会议室信息列表
     * 
     * @param meetingRoom 会议室信息
     * @return 会议室信息
     */
    @Override
    public List<MeetingRoom> selectMeetingRoomList(MeetingRoom meetingRoom)
    {
        return meetingRoomMapper.selectMeetingRoomList(meetingRoom);
    }

    /**
     * 新增会议室信息
     * 
     * @param meetingRoom 会议室信息
     * @return 结果
     */
    @Override
    public int insertMeetingRoom(MeetingRoom meetingRoom)
    {
        meetingRoom.setCreateTime(DateUtils.getNowDate());
        return meetingRoomMapper.insertMeetingRoom(meetingRoom);
    }

    /**
     * 修改会议室信息
     * 
     * @param meetingRoom 会议室信息
     * @return 结果
     */
    @Override
    public int updateMeetingRoom(MeetingRoom meetingRoom)
    {
        meetingRoom.setUpdateTime(DateUtils.getNowDate());
        return meetingRoomMapper.updateMeetingRoom(meetingRoom);
    }

    /**
     * 批量删除会议室信息
     * 
     * @param ids 需要删除的会议室信息主键
     * @return 结果
     */
    @Override
    public int deleteMeetingRoomByIds(Long[] ids)
    {
        return meetingRoomMapper.deleteMeetingRoomByIds(ids);
    }

    /**
     * 删除会议室信息信息
     * 
     * @param id 会议室信息主键
     * @return 结果
     */
    @Override
    public int deleteMeetingRoomById(Long id)
    {
        return meetingRoomMapper.deleteMeetingRoomById(id);
    }
}
