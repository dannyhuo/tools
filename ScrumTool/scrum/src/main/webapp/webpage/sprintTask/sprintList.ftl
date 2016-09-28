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
						<option value="1" <#if sprintId == 1>selected="true"</#if> >第24次迭代</option>
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
						</tr>
						<#list tasks as task>
							<tr>
								<td>${task.taskId}</td>
								<td>${task.taskNo}</td>
								<td>${task.taskName}</td>
								<td>${task.sprintId}</td>
								<td>${task.elapsedTime}</td>
								<td>${(task?if_exists.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
								<td>${task.remark}</td>
							</tr>
						</#list>
					</table>
				</#if>
			</div>
		</div>
	</body>
</html>