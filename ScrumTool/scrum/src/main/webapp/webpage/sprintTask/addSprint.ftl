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
				<form action="/scrum/scrumSprint/createSprint.do" method="post">
					<table cellspacing="0" cellpadding="0" border="0" width="80%">
						<tr><td colspan="3" height="30"></td></tr>
						<tr>
							<td align="right">小&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;组&nbsp;&nbsp;</td>
							<td width="30"></td>
							<td>
								<select name="teamName">
									<option value="">请选择所属小组</option>
									<option value="会员与CRM部">会员与CRM部</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">迭代名称&nbsp;&nbsp;</td>
							<td width="30"></td>
							<td><input type="text" name="sprintName"/></td>
						</tr>
						<tr>
							<td align="right">收件人</td>
							<td width="30"></td>
							<td><input type="text" name="mailTo"/></td>
						</tr>
						<tr>
							<td align="right">抄送人</td>
							<td width="30"></td>
							<td><input type="text" name="ccTo"/></td>
						</tr>
						<tr>
							<td align="right">自动发邮件</td>
							<td width="30"></td>
							<td>
								<select name="autoSendEmail">
									<option value="N">否</option>
									<option value="Y">是</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">迭代计划周期</td>
							<td width="30"></td>
							<td><input type="text" name="planCycle"/></td>
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
		</div>
	</body>
</html>