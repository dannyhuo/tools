package com.scrum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scrum.model.ScrumSprint;
import com.scrum.service.ScrumSprintService;

@Controller
@RequestMapping(value = "/scrumSprint")
public class ScrumSpintController {
	
	@Autowired
	private ScrumSprintService scrumSprintService;

	@RequestMapping(value = "/toCreateSprint")
	public ModelAndView toCreateSprint(){
		ModelAndView mav = new ModelAndView("/webpage/sprintTask/addSprint");

		mav.addObject("", null);//查询team 列表放入mav
		return mav;
	}
	
	@RequestMapping(value = "/createSprint")
	public ModelAndView createSprint(ScrumSprint scrumSprint){
		ModelAndView mav = new ModelAndView("/webpage/sprintTask/sprintList");
		scrumSprintService.insertSelective(scrumSprint);
		
		List<ScrumSprint> sprints = scrumSprintService.queryScrums(scrumSprint);
		
		mav.addObject("sprints", sprints);
		
		return mav;
	}
}
