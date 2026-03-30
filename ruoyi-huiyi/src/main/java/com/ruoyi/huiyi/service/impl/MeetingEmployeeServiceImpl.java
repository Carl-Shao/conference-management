package com.ruoyi.huiyi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.huiyi.mapper.MeetingEmployeeMapper;
import com.ruoyi.huiyi.domain.MeetingEmployee;
import com.ruoyi.huiyi.service.IMeetingEmployeeService;

/**
 * 员工信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
@Service
public class MeetingEmployeeServiceImpl implements IMeetingEmployeeService 
{
    @Autowired
    private MeetingEmployeeMapper meetingEmployeeMapper;

    /**
     * 查询员工信息
     * 
     * @param id 员工信息主键
     * @return 员工信息
     */
    @Override
    public MeetingEmployee selectMeetingEmployeeById(Long id)
    {
        return meetingEmployeeMapper.selectMeetingEmployeeById(id);
    }

    /**
     * 查询员工信息列表
     * 
     * @param meetingEmployee 员工信息
     * @return 员工信息
     */
    @Override
    public List<MeetingEmployee> selectMeetingEmployeeList(MeetingEmployee meetingEmployee)
    {
        return meetingEmployeeMapper.selectMeetingEmployeeList(meetingEmployee);
    }

    /**
     * 新增员工信息
     * 
     * @param meetingEmployee 员工信息
     * @return 结果
     */
    @Override
    public int insertMeetingEmployee(MeetingEmployee meetingEmployee)
    {
        meetingEmployee.setCreateTime(DateUtils.getNowDate());
        return meetingEmployeeMapper.insertMeetingEmployee(meetingEmployee);
    }

    /**
     * 修改员工信息
     * 
     * @param meetingEmployee 员工信息
     * @return 结果
     */
    @Override
    public int updateMeetingEmployee(MeetingEmployee meetingEmployee)
    {
        meetingEmployee.setUpdateTime(DateUtils.getNowDate());
        return meetingEmployeeMapper.updateMeetingEmployee(meetingEmployee);
    }

    /**
     * 批量删除员工信息
     * 
     * @param ids 需要删除的员工信息主键
     * @return 结果
     */
    @Override
    public int deleteMeetingEmployeeByIds(Long[] ids)
    {
        return meetingEmployeeMapper.deleteMeetingEmployeeByIds(ids);
    }

    /**
     * 删除员工信息信息
     * 
     * @param id 员工信息主键
     * @return 结果
     */
    @Override
    public int deleteMeetingEmployeeById(Long id)
    {
        return meetingEmployeeMapper.deleteMeetingEmployeeById(id);
    }
}
