package com.scrum.dao.mysql;

import com.scrum.model.DailyLineProblem;

public interface DailyLineProblemMapper {
    int deleteByPrimaryKey(Long lineProblemId);

    int insert(DailyLineProblem record);

    int insertSelective(DailyLineProblem record);

    DailyLineProblem selectByPrimaryKey(Long lineProblemId);

    int updateByPrimaryKeySelective(DailyLineProblem record);

    int updateByPrimaryKey(DailyLineProblem record);
}