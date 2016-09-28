package com.scrum.model;

import java.util.Date;

public class DailyLineProblem {
    private Long lineProblemId;

    private String lineProblemNo;

    private String problemDescribe;

    private String problemAnalysis;

    private String waysOfResolution;

    private String processor;

    private String watch;

    private String isSolved;

    private Date solvedTime;

    private String isRepeat;

    private String needFollowUp;

    private Date reportTime;

    private Date createTime;

    private Date updateTime;

    private String remark;

    public Long getLineProblemId() {
        return lineProblemId;
    }

    public void setLineProblemId(Long lineProblemId) {
        this.lineProblemId = lineProblemId;
    }

    public String getLineProblemNo() {
        return lineProblemNo;
    }

    public void setLineProblemNo(String lineProblemNo) {
        this.lineProblemNo = lineProblemNo == null ? null : lineProblemNo.trim();
    }

    public String getProblemDescribe() {
        return problemDescribe;
    }

    public void setProblemDescribe(String problemDescribe) {
        this.problemDescribe = problemDescribe == null ? null : problemDescribe.trim();
    }

    public String getProblemAnalysis() {
        return problemAnalysis;
    }

    public void setProblemAnalysis(String problemAnalysis) {
        this.problemAnalysis = problemAnalysis == null ? null : problemAnalysis.trim();
    }

    public String getWaysOfResolution() {
        return waysOfResolution;
    }

    public void setWaysOfResolution(String waysOfResolution) {
        this.waysOfResolution = waysOfResolution == null ? null : waysOfResolution.trim();
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor == null ? null : processor.trim();
    }

    public String getWatch() {
        return watch;
    }

    public void setWatch(String watch) {
        this.watch = watch == null ? null : watch.trim();
    }

    public String getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(String isSolved) {
        this.isSolved = isSolved == null ? null : isSolved.trim();
    }

    public Date getSolvedTime() {
        return solvedTime;
    }

    public void setSolvedTime(Date solvedTime) {
        this.solvedTime = solvedTime;
    }

    public String getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(String isRepeat) {
        this.isRepeat = isRepeat == null ? null : isRepeat.trim();
    }

    public String getNeedFollowUp() {
        return needFollowUp;
    }

    public void setNeedFollowUp(String needFollowUp) {
        this.needFollowUp = needFollowUp == null ? null : needFollowUp.trim();
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}