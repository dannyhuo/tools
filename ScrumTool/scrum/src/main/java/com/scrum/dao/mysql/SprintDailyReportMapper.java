package com.scrum.dao.mysql;

import com.scrum.model.SprintDailyReport;

public interface SprintDailyReportMapper {
    int deleteByPrimaryKey(Long reportId);

    int insert(SprintDailyReport record);

    int insertSelective(SprintDailyReport record);

    SprintDailyReport selectByPrimaryKey(Long reportId);

    int updateByPrimaryKeySelective(SprintDailyReport record);

    int updateByPrimaryKey(SprintDailyReport record);
}