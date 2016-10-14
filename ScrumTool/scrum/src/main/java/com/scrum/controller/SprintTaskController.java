package com.scrum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scrum.model.ScrumSprint;
import com.scrum.model.ScrumUser;
import com.scrum.model.SprintTask;
import com.scrum.service.ScrumSprintService;
import com.scrum.service.ScrumUserService;
import com.scrum.service.SprintTaskService;
import com.scrum.util.IDManager;

@Controller
@RequestMapping(value = "/sprintTask")
public class SprintTaskController {
	
	@Autowired
	private SprintTaskService sprintTaskService;
	
	@Autowired 
	private ScrumSprintService scrumSprintService;
	
	@Autowired 
	private ScrumUserService scrumUserService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(ScrumSprint sprint){
		ModelAndView mav = new ModelAndView("/webpage/index");
		mav.addObject("sprintId", sprint.getSprintId());
		mav.addObject("tasks",sprintTaskService.queryBySprintId(sprint.getSprintId()));
		mav.addObject("sprints", scrumSprintService.queryScrums(null));
		ScrumUser user = new ScrumUser();
		mav.addObject("developers", scrumUserService.queryUserSelective(user));
		return mav;
	}

	@RequestMapping(value = "/toAddTask")
	public ModelAndView toAddTask(ScrumSprint scrumSprint){
		ModelAndView mav = new ModelAndView("/webpage/sprintTask/addSprintTask");
		mav.addObject("sprints",scrumSprintService.queryScrums(scrumSprint));
		return mav;
	}
	
	@RequestMapping(value = "/doAddTask")
	public ModelAndView doAddTask(SprintTask sprintTask){
		ModelAndView mav = new ModelAndView("/webpage/index");
		
		sprintTask.setTaskNo(IDManager.generHaxi32());
		
		sprintTaskService.insertSelective(sprintTask);
		
		mav.addObject("sprintId", sprintTask.getSprintId());
		mav.addObject("sprints", scrumSprintService.queryScrums(null));
		mav.addObject("tasks",sprintTaskService.queryBySprintId(sprintTask.getSprintId()));
		return mav;
	}
}
