package com.scrum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrum.dao.mysql.ScrumUserMapper;
import com.scrum.model.ScrumUser;

@Service("scrumUserService")
public class ScrumUserService {
	
	@Autowired
	private ScrumUserMapper scUserMapper;
	
	public List<ScrumUser> queryUserSelective(ScrumUser user){
		return this.scUserMapper.selectSelective(user);
	}
	
	

}
