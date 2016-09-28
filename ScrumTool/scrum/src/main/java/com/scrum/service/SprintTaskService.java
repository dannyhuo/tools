package com.scrum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrum.dao.mysql.SprintTaskMapper;
import com.scrum.model.SprintTask;
import com.scrum.model.SprintTaskItem;

@Service("sprintTaskService")
public class SprintTaskService {

	@Autowired
	private SprintTaskMapper sprintTaskMapper;
	
	/**
	 * 根据sprintId查询当前sprint下的所有任务
	 * @param sprintId
	 * @return
	 */
	public List<SprintTask> queryBySprintId(Long sprintId){
		return sprintTaskMapper.queryBySprintId(sprintId);
	}
	
	public int insertSelective(SprintTask record){
		return sprintTaskMapper.insertSelective(record);
	}
}
