package com.scrum.dao.mysql;

import java.util.List;

import com.scrum.model.ScrumSprint;

public interface ScrumSprintMapper {
    int deleteByPrimaryKey(Long sprintId);

    int insert(ScrumSprint record);

    int insertSelective(ScrumSprint record);

    ScrumSprint selectByPrimaryKey(Long sprintId);

    int updateByPrimaryKeySelective(ScrumSprint record);

    int updateByPrimaryKey(ScrumSprint record);
    
    List<ScrumSprint> queryScrums(ScrumSprint scrumSprint);
}