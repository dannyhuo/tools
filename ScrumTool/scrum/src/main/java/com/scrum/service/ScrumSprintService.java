package com.scrum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrum.dao.mysql.ScrumSprintMapper;
import com.scrum.model.ScrumSprint;

@Service("scrumSprintService")
public class ScrumSprintService {
	
	@Autowired
	private ScrumSprintMapper scrumSprintMapper;
	
	public List<ScrumSprint> queryScrums(ScrumSprint scrumSprint){
		return scrumSprintMapper.queryScrums(scrumSprint);
	}
	
	public int insertSelective(ScrumSprint record){
		return scrumSprintMapper.insertSelective(record);
	}

}
