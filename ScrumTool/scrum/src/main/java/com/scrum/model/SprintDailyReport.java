package com.scrum.model;

public class SprintDailyReport {
    private Long reportId;

    private String reportNo;

    private Long sprintId;

    private Long userId;

    private String yesterdayReport;

    private String todayPlan;

    private String remark;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo == null ? null : reportNo.trim();
    }

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getYesterdayReport() {
        return yesterdayReport;
    }

    public void setYesterdayReport(String yesterdayReport) {
        this.yesterdayReport = yesterdayReport == null ? null : yesterdayReport.trim();
    }

    public String getTodayPlan() {
        return todayPlan;
    }

    public void setTodayPlan(String todayPlan) {
        this.todayPlan = todayPlan == null ? null : todayPlan.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}