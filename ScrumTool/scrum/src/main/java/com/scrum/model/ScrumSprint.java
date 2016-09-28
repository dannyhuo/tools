package com.scrum.model;

import java.util.Date;

public class ScrumSprint {
    private Long sprintId;

    private String teamName;

    private String sprintName;

    private String mailTo;

    private String ccTo;

    private String autoSendEmail;

    private String planCycle;

    private String realCycle;

    private Date createTime;

    private String remark;

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName == null ? null : sprintName.trim();
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo == null ? null : mailTo.trim();
    }

    public String getCcTo() {
        return ccTo;
    }

    public void setCcTo(String ccTo) {
        this.ccTo = ccTo == null ? null : ccTo.trim();
    }

    public String getAutoSendEmail() {
        return autoSendEmail;
    }

    public void setAutoSendEmail(String autoSendEmail) {
        this.autoSendEmail = autoSendEmail == null ? null : autoSendEmail.trim();
    }

    public String getPlanCycle() {
        return planCycle;
    }

    public void setPlanCycle(String planCycle) {
        this.planCycle = planCycle == null ? null : planCycle.trim();
    }

    public String getRealCycle() {
        return realCycle;
    }

    public void setRealCycle(String realCycle) {
        this.realCycle = realCycle == null ? null : realCycle.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}