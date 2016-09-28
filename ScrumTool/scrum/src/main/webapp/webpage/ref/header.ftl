<div class="status_bar">
	<table cellspacing="0" cellpadding="0" border="0" width="100%">
		<tr>
			<td align="center" valign="middle">
				<div>
					<#if user?has_content>
						欢迎使用,${user.userName}
						<a href="">退出</a>
					<#else>
						您未<a href="">登录</a>
					</#if>
				</div>
			</td>
		</tr>
	</table>
</div>
<div class="newmember_bg">
    <div class="newmember_nav">
    	<a href="http://www.lvmama.com/myspace/index.do" class="newmember_logo fl"></a>
        <ul class="nav_box">
            <li class="newmember_list" id="lv_newhome"><a href="" class="a_list">任务管理</a></li>
            <li class="newmember_list"  id="lv_newaccount"><a href="" class="a_list">每日晨会</a></li>
            <li class="newmember_list"><a href="" class="a_list">线上投诉</a></li>
        </ul>
    </div>
</div>
