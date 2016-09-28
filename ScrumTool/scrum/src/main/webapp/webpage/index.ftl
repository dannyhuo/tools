<html>
	<!--[if lt IE 7 ]><html class="ie ie6"><![endif]-->
	<!--[if IE 7 ]><html class="ie ie7"><![endif]-->
	<!--[if IE 8 ]><html class="ie ie8"><![endif]-->
	<!--[if IE 9 ]><html class="ie ie9"><![endif]-->
	<!--[if (gt IE 9)|!(IE)]><!--><html><!--<![endif]-->
	<head>
		<title>Scrum Index</title>
		<#include "/webpage/ref/lv-meta.ftl"/>
	</head>
	<body>
		<#include "/webpage/ref/header.ftl"/>
		<div class="newmember_main">
			<#include "/webpage/ref/lv-nav.ftl"/>
			
			<div class="my_main">
				<form action="/scrum/sprintTask/index.do" method="post">
					<select name="sprintId" onchange="javascript:this.parentNode.submit();">
						<option value="">请选择迭代</option>
						<#if sprints?has_content>
							<#list sprints as sprint>
								<option value="${sprint.sprintId}" <#if sprintId == sprint.sprintId>selected="true"</#if> >${sprint.sprintName}</option>
							</#list>
						</#if>
					</select>
				</form>
				<#if tasks?has_content>
					<table cellspacing="1px" cellpadding="0" border="1px solid #cccccc;" width="100%">
						<tr>
							<th>任务ID</th>
							<th>任务编号</th>
							<th>任务名称</th>
							<th>迭代ID</th>
							<th>估时</th>
							<th>创建时间</th>
							<th>备注</th>
							<th>操作</th>
						</tr>
						<#list tasks as task>
							<tr onclick="showTaskItem(${task.taskId});">
								<td>${task.taskId}</td>
								<td>${task.taskNo}</td>
								<td>${task.taskName}</td>
								<td>${task.sprintId}</td>
								<td>${task.elapsedTime}</td>
								<td>${(task?if_exists.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
								<td>${task.remark}</td>
								<td onclick="showTaskItemDialog(${task.taskId})" style="cursor:pointer">添加拆分</td>
							</tr>
						</#list>
					</table>
				</#if>
			</div>
		</div>
		
		
		<!--添加task item弹窗-->
		<div id="my_add_task_item_dialog" class="myAddTaskItemDialog" style="display:none;">
			<form action="/scrum/sprintTaskItem/doCreateTaskItem.do" method="post">
				<table cellspacing="0" cellpadding="0" border="0" width="80%">
					<tr><td colspan="3" height="30"></td></tr>
					<tr>
						<td align="right">任务ID</td>
						<td width="30"></td>
						<td><input type="text" id="taskId" readonly="true" name="taskId"/></td>
					</tr>
					<tr>
						<td align="right">任务拆分名称</td>
						<td width="30"></td>
						<td><input type="text" name="taskItemName"/></td>
					</tr>
					<tr>
						<td align="right">估时</td>
						<td width="30"></td>
						<td><input type="text" name="elapsedTime"/></td>
					</tr>
					<tr>
						<td align="right">备注</td>
						<td width="30"></td>
						<td><input type="text" name="remark"/></td>
					</tr>
					<tr>
						<td colspan="2"></td>
						<td><input type="submit" value="添加"/></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>