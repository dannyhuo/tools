package com.scrum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrum.dao.mysql.SprintTaskItemMapper;
import com.scrum.model.SprintTaskItem;

@Service("sprintTaskItem")
public class SprintTaskItemService {

	@Autowired
	private SprintTaskItemMapper sprintTaskItemMapper;
	
	public List<SprintTaskItem> querySprintTaskItem(SprintTaskItem sprintTaskItem){
		return sprintTaskItemMapper.querySprintTaskItem(sprintTaskItem);
	}
	
	public int insertSelective(SprintTaskItem record){
		return sprintTaskItemMapper.insertSelective(record);
	}
}
