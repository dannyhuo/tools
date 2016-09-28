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
				<form action="/scrum/sprintTask/doAddTask.do" method="post">
					<table cellspacing="0" cellpadding="0" border="0" width="80%">
						<tr><td colspan="3" height="30"></td></tr>
						<tr>
							<td align="right">小&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;组&nbsp;&nbsp;</td>
							<td width="30"></td>
							<td>
								<select name="task_name">
									<option value="">请选择所属小组</option>
									<option value="会员与CRM">会员与CRM</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">跌&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;代&nbsp;&nbsp;</td>
							<td width="30"></td>
							<td>
								<select name="sprintId">
									<option value="">请选择所属迭代</option>
									<#if sprints?has_content>
										<#list sprints as sprint>
											<option value="${sprint.sprintId}" <#if sprintId == sprint.sprintId>selected="true"</#if> >${sprint.sprintName}</option>
										</#list>
									</#if>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">任务名称&nbsp;&nbsp;</td>
							<td width="30"></td>
							<td><input type="text" name="taskName"/></td>
						</tr>
						<tr>
							<td align="right">估&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时&nbsp;&nbsp;</td>
							<td width="30"></td>
							<td><input type="text" name="elapsedTime"/></td>
						</tr>
						<tr>
							<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;</td>
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
		</div>
	</body>
</html>