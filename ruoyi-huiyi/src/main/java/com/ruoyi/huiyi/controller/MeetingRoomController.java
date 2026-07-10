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
import com.ruoyi.huiyi.domain.MeetingRoom;
import com.ruoyi.huiyi.service.IMeetingRoomService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会议室信息Controller
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
@RestController
@RequestMapping("/huiyi/room")
public class MeetingRoomController extends BaseController
{
    @Autowired
    private IMeetingRoomService meetingRoomService;

    /**
     * 查询会议室信息列表
     */
    @PreAuthorize("@ss.hasPermi('huiyi:room:list')")
    @GetMapping("/list")
    public TableDataInfo list(MeetingRoom meetingRoom)
    {
        startPage();
        List<MeetingRoom> list = meetingRoomService.selectMeetingRoomList(meetingRoom);
        return getDataTable(list);
    }

    /**
     * 导出会议室信息列表
     */
    @PreAuthorize("@ss.hasPermi('huiyi:room:export')")
    @Log(title = "会议室信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MeetingRoom meetingRoom)
    {
        List<MeetingRoom> list = meetingRoomService.selectMeetingRoomList(meetingRoom);
        ExcelUtil<MeetingRoom> util = new ExcelUtil<MeetingRoom>(MeetingRoom.class);
        util.exportExcel(response, list, "会议室信息数据");
    }

    /**
     * 获取会议室信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('huiyi:room:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(meetingRoomService.selectMeetingRoomById(id));
    }

    /**
     * 新增会议室信息
     */
    @PreAuthorize("@ss.hasPermi('huiyi:room:add')")
    @Log(title = "会议室信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MeetingRoom meetingRoom)
    {
        return toAjax(meetingRoomService.insertMeetingRoom(meetingRoom));
    }

    /**
     * 修改会议室信息
     */
    @PreAuthorize("@ss.hasPermi('huiyi:room:edit')")
    @Log(title = "会议室信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MeetingRoom meetingRoom)
    {
        return toAjax(meetingRoomService.updateMeetingRoom(meetingRoom));
    }

    /**
     * 删除会议室信息
     */
    @PreAuthorize("@ss.hasPermi('huiyi:room:remove')")
    @Log(title = "会议室信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(meetingRoomService.deleteMeetingRoomByIds(ids));
    }
}
