function showTaskItemDialog(taskId){
	var dialog = document.getElementById("my_add_task_item_dialog");
	if(dialog){
		document.getElementById("taskId").value = taskId;
		dialog.style.display = "block";
		dialog.style.position = "absolute";
	}
	return false;
}

function closeTaskItemDialog(){
	var dialog = document.getElementById("my_add_task_item_dialog");
	if(dialog){
		document.getElementById("taskId").value = taskId;
		dialog.style.display = "none";
	}
	clearMyAddTaskItemForm();
}

/**
 * 清空from表单中的数据
 */
function clearMyAddTaskItemForm(){
	var myAddTaskItemForm = document.getElementById("myAddTaskItemForm");
	if(myAddTaskItemForm){
		myAddTaskItemForm.reset();
	}
}

var curentItem = null;
function showTaskItem(taskId){
	if(curentItem){
		curentItem.innerHTML = "";
	}
	$.ajax({
		type:"GET",
		url:"/scrum/sprintTaskItem/getTaskItem.do",
		data:{taskId:taskId},
		dataType:"json",
		success:function(json,status,dom){
			if(status && json && json.length > 0){
				curentItem = document.getElementById(taskId);
				var innerHtml = [];
				innerHtml.push("<table class=\"my_task_item_table\">");
				innerHtml.push("<tr>");
				innerHtml.push("	<th>序号</th><th>拆分名称</th><th>估时</th><th>开发者</th><th>开发完成百分比</th><th>是否送测</th>");
				innerHtml.push("	<th>测试人员</th><th>测试号</th><th>测试完成度</th><th>是否上线</th><th>上线时间</th><th>备注</th>");
				innerHtml.push("</tr>");
				for(var i = 0; i < json.length; i++){
					innerHtml.push("<tr>");
					innerHtml.push("	<td>");
					innerHtml.push(		i+1);
					innerHtml.push("	</td>");
					innerHtml.push("	<td>");
					innerHtml.push(		json[i]["taskItemName"]);
					innerHtml.push("	</td>");
					innerHtml.push("	<td>");
					innerHtml.push(		json[i]["elapsedTime"]);
					innerHtml.push("小时	</td>");
					innerHtml.push("	<td>");
					innerHtml.push(		json[i]["developer"]);
					innerHtml.push("	</td>");
					innerHtml.push("	<td>");
					innerHtml.push(		json[i]["developerPercent"]);
					innerHtml.push("%	</td>");
					innerHtml.push("	<td>");
					innerHtml.push(		json[i]["isTest"]);
					innerHtml.push("	</td>");
					innerHtml.push("	<td>");
					innerHtml.push(		json[i]["testor"]);
					innerHtml.push("	</td>");
					innerHtml.push("	<td>");
					innerHtml.push(		json[i]["testNo"]);
					innerHtml.push("	</td>");
					innerHtml.push("	<td>");
					innerHtml.push(		json[i]["testPercent"]);
					innerHtml.push("%	</td>");
					innerHtml.push("	<td>");
					innerHtml.push(		json[i]["isOnLine"]);
					innerHtml.push("	</td>");
					innerHtml.push("	<td>");
					innerHtml.push(		json[i]["onLineDate"]);
					innerHtml.push("	</td>");
					innerHtml.push("	<td>");
					innerHtml.push(		json[i]["remark"]);
					innerHtml.push("	</td>");
					/*innerHtml.push("	<td>");
					innerHtml.push(		new Date(json[i]["createTime"].time).toString());
					innerHtml.push("	</td>");*/
					innerHtml.push("</tr>");
				}
				innerHtml.push("</table>");
				curentItem.innerHTML = innerHtml.join("");
			}
		},
		error:function(){
			alert("error");
		}
	});
	clearMyAddTaskItemForm();
}