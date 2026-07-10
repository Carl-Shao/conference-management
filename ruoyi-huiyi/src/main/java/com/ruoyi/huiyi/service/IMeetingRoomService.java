package com.ruoyi.huiyi.service;

import java.util.List;
import com.ruoyi.huiyi.domain.MeetingRoom;

/**
 * 会议室信息Service接口
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
public interface IMeetingRoomService 
{
    /**
     * 查询会议室信息
     * 
     * @param id 会议室信息主键
     * @return 会议室信息
     */
    public MeetingRoom selectMeetingRoomById(Long id);

    /**
     * 查询会议室信息列表
     * 
     * @param meetingRoom 会议室信息
     * @return 会议室信息集合
     */
    public List<MeetingRoom> selectMeetingRoomList(MeetingRoom meetingRoom);

    /**
     * 新增会议室信息
     * 
     * @param meetingRoom 会议室信息
     * @return 结果
     */
    public int insertMeetingRoom(MeetingRoom meetingRoom);

    /**
     * 修改会议室信息
     * 
     * @param meetingRoom 会议室信息
     * @return 结果
     */
    public int updateMeetingRoom(MeetingRoom meetingRoom);

    /**
     * 批量删除会议室信息
     * 
     * @param ids 需要删除的会议室信息主键集合
     * @return 结果
     */
    public int deleteMeetingRoomByIds(Long[] ids);

    /**
     * 删除会议室信息信息
     * 
     * @param id 会议室信息主键
     * @return 结果
     */
    public int deleteMeetingRoomById(Long id);
}
