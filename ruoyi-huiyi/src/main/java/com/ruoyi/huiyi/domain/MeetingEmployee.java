package com.ruoyi.huiyi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工信息对象 meeting_employee
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
public class MeetingEmployee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工 ID（主键） */
    private Long id;

    /** 工号（如：E001） */
    @Excel(name = "工号", readConverterExp = "如=：E001")
    private String employeeNo;

    /** 姓名 */
    @Excel(name = "姓名")
    private String employeeName;

    /** 部门（如：技术部） */
    @Excel(name = "部门", readConverterExp = "如=：技术部")
    private String department;

    /** 职位（如：工程师） */
    @Excel(name = "职位", readConverterExp = "如=：工程师")
    private String position;

    /** 邮箱地址 */
    @Excel(name = "邮箱地址")
    private String email;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phone;

    /** 角色（secretary:秘书，participant:参与者） */
    @Excel(name = "角色", readConverterExp = "s=ecretary:秘书，participant:参与者")
    private String role;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setEmployeeNo(String employeeNo) 
    {
        this.employeeNo = employeeNo;
    }

    public String getEmployeeNo() 
    {
        return employeeNo;
    }

    public void setEmployeeName(String employeeName) 
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() 
    {
        return employeeName;
    }

    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }

    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setRole(String role) 
    {
        this.role = role;
    }

    public String getRole() 
    {
        return role;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("employeeNo", getEmployeeNo())
            .append("employeeName", getEmployeeName())
            .append("department", getDepartment())
            .append("position", getPosition())
            .append("email", getEmail())
            .append("phone", getPhone())
            .append("role", getRole())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
