function showTaskItemDialog(taskId){
	alert(taskId);
	var dialog = document.getElementById("my_add_task_item_dialog");
	if(dialog){
		document.getElementById("taskId").value = taskId;
		dialog.style.display = "absolute";
		dialog.style.left = "200px";
		dialog.style.top = "100px";
	}
}

function showTaskItem(taskId){
	alert("请求任务拆分");
	$.ajax({
		url:"/scrum/sprintTaskItem/getTaskItem.do",
		data:{taskId:taskId},
		success:function(arg){
			alert(arg);
		},
		error:function(){
			alert("error");
		}
	});
}