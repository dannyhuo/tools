package com.scrum.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scrum.model.SprintTask;
import com.scrum.model.SprintTaskItem;
import com.scrum.service.ScrumSprintService;
import com.scrum.service.SprintTaskItemService;
import com.scrum.service.SprintTaskService;
import com.scrum.util.IDManager;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/sprintTaskItem")
public class SprintTaskItemController {
	
	@Autowired
	private SprintTaskItemService sprintTaskItemService;
	
	@Autowired
	private SprintTaskService sprintTaskService;
	
	@Autowired
	private ScrumSprintService scrumSprintService;
	
	@RequestMapping(value = "/toCreateTaskItem")
	public ModelAndView toCreateTaskItem(SprintTaskItem sprintTaskItem){
		ModelAndView mav = new ModelAndView("/webpage/sprintTask/addSprintItem");
		
		
		return mav;
	}
	
	@RequestMapping(value = "/doCreateTaskItem")
	public ModelAndView doCreateTaskItem(SprintTaskItem sprintTaskItem, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/webpage/index");
		sprintTaskItem.setTaskItemNo(IDManager.generHaxi32());
		sprintTaskItem.setDeveloper(1L);//登录后，从session中取
		sprintTaskItemService.insertSelective(sprintTaskItem);
		
		//mav.addObject("sprintId", sprintTaskItem);
		//mav.addObject("tasks",sprintTaskService.queryBySprintId(sprint.getSprintId()));
		mav.addObject("sprints", scrumSprintService.queryScrums(null));
		
		return mav;
	}

	@RequestMapping(value = "/getTaskItem")
	public void getSprintTaskItem(SprintTaskItem sprintTaskItem, HttpServletRequest request, HttpServletResponse response){
		
		List<SprintTaskItem> taskItems = sprintTaskItemService.querySprintTaskItem(sprintTaskItem);
		
		JSONArray jsonArray = JSONArray.fromObject(taskItems);
		
		try {
			response.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
