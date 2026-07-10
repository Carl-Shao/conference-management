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
import com.ruoyi.huiyi.domain.MeetingResource;
import com.ruoyi.huiyi.service.IMeetingResourceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会议资源（完整转录、会议概要、录音）Controller
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
@RestController
@RequestMapping("/huiyi/resource")
public class MeetingResourceController extends BaseController
{
    @Autowired
    private IMeetingResourceService meetingResourceService;

    /**
     * 查询会议资源（完整转录、会议概要、录音）列表
     */
    @PreAuthorize("@ss.hasPermi('huiyi:resource:list')")
    @GetMapping("/list")
    public TableDataInfo list(MeetingResource meetingResource)
    {
        startPage();
        List<MeetingResource> list = meetingResourceService.selectMeetingResourceList(meetingResource);
        return getDataTable(list);
    }

    /**
     * 导出会议资源（完整转录、会议概要、录音）列表
     */
    @PreAuthorize("@ss.hasPermi('huiyi:resource:export')")
    @Log(title = "会议资源（完整转录、会议概要、录音）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MeetingResource meetingResource)
    {
        List<MeetingResource> list = meetingResourceService.selectMeetingResourceList(meetingResource);
        ExcelUtil<MeetingResource> util = new ExcelUtil<MeetingResource>(MeetingResource.class);
        util.exportExcel(response, list, "会议资源（完整转录、会议概要、录音）数据");
    }

    /**
     * 获取会议资源（完整转录、会议概要、录音）详细信息
     */
    @PreAuthorize("@ss.hasPermi('huiyi:resource:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(meetingResourceService.selectMeetingResourceById(id));
    }

    /**
     * 新增会议资源（完整转录、会议概要、录音）
     */
    @PreAuthorize("@ss.hasPermi('huiyi:resource:add')")
    @Log(title = "会议资源（完整转录、会议概要、录音）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MeetingResource meetingResource)
    {
        return toAjax(meetingResourceService.insertMeetingResource(meetingResource));
    }

    /**
     * 修改会议资源（完整转录、会议概要、录音）
     */
    @PreAuthorize("@ss.hasPermi('huiyi:resource:edit')")
    @Log(title = "会议资源（完整转录、会议概要、录音）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MeetingResource meetingResource)
    {
        return toAjax(meetingResourceService.updateMeetingResource(meetingResource));
    }

    /**
     * 删除会议资源（完整转录、会议概要、录音）
     */
    @PreAuthorize("@ss.hasPermi('huiyi:resource:remove')")
    @Log(title = "会议资源（完整转录、会议概要、录音）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(meetingResourceService.deleteMeetingResourceByIds(ids));
    }
}
