package com.ruoyi.huiyi.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.huiyi.domain.MeetingEmployee;
import com.ruoyi.huiyi.service.IMeetingEmployeeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工信息Controller
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
@RestController
@RequestMapping("/huiyi/employee")
public class MeetingEmployeeController extends BaseController
{
    @Autowired
    private IMeetingEmployeeService meetingEmployeeService;

    /**
     * 查询员工信息列表
     */
    @PreAuthorize("@ss.hasPermi('huiyi:employee:list')")
    @GetMapping("/list")
    public TableDataInfo list(MeetingEmployee meetingEmployee)
    {
        startPage();
        List<MeetingEmployee> list = meetingEmployeeService.selectMeetingEmployeeList(meetingEmployee);
        return getDataTable(list);
    }

    /**
     * 导出员工信息列表
     */
    @PreAuthorize("@ss.hasPermi('huiyi:employee:export')")
    @Log(title = "员工信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MeetingEmployee meetingEmployee)
    {
        List<MeetingEmployee> list = meetingEmployeeService.selectMeetingEmployeeList(meetingEmployee);
        ExcelUtil<MeetingEmployee> util = new ExcelUtil<MeetingEmployee>(MeetingEmployee.class);
        util.exportExcel(response, list, "员工信息数据");
    }

    /**
     * 获取员工信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('huiyi:employee:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(meetingEmployeeService.selectMeetingEmployeeById(id));
    }

    /**
     * 新增员工信息
     */
    @PreAuthorize("@ss.hasPermi('huiyi:employee:add')")
    @Log(title = "员工信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MeetingEmployee meetingEmployee)
    {
        return toAjax(meetingEmployeeService.insertMeetingEmployee(meetingEmployee));
    }

    /**
     * 修改员工信息
     */
    @PreAuthorize("@ss.hasPermi('huiyi:employee:edit')")
    @Log(title = "员工信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MeetingEmployee meetingEmployee)
    {
        return toAjax(meetingEmployeeService.updateMeetingEmployee(meetingEmployee));
    }

    /**
     * 删除员工信息
     */
    @PreAuthorize("@ss.hasPermi('huiyi:employee:remove')")
    @Log(title = "员工信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(meetingEmployeeService.deleteMeetingEmployeeByIds(ids));
    }
}
