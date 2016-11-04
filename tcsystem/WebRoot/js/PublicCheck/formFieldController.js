/**
 *该js文件主要是检测表单填写是否完整
 *增删改全部同ajax异步提交
 *@author：linhuadong 
 */
//////////////1：以下是添加记录表单的控制////////////////////
/**
 * function1：检测表单必要域填写是否完整
 * 如果填写完整，返回true，然后直接提交表单。
 * 不采用js重新封装ajax要传向后台的表单数据，容易出错
 * 直接用ajax提交表单
 */
function fieldCheck() {
	var elements = $(".doCheck_add");
	var yes = true;
	for (var i = 0; i < elements.length; i++) {
		if (elements[i].value.trim() == "") {
			yes = false;
			elements[i].style.backgroundColor = "green";
			elements[i].placeholder = "请填充空白";
			elements[i].value = "";
		}
	}
	return yes;
}
/**
 * function2：点击新增按钮，或者修改按钮的时候
 * 重置表单域的css样式，回复最初的背景为白色
 */
function fieldCSSReset(className){
	var elements = $(className);
	for (var i = 0; i < elements.length; i++) {
		elements[i].style.backgroundColor = "white";
		elements[i].placeholder = "";
		elements[i].value = "";
	}
}
/**
 * function3:当field为空js将该文本框或者其他组件背景颜色变成绿色的时候，
 * 保证单击该组件能够重新恢复成背景为白色
 */
/**
 * 1:鼠标触发
 */
	$('.doCheck_add').click(function() {
		$(this).css("background-color","white");
	});
/**
 * 2:键盘触发
 */
	$('.doCheck_add').keyup(function() {
		$(this).css("background-color","white");
	});
	
	/**
	 * 单击新增按钮的时候 保证表单恢复原样
	 */
	$("#submitNewRecord").click(function(){
		fieldCSSReset(".doCheck_add");
	});
	/**
	 * 封装提交增加绩效的表单的动作
	 * @param postUrl:表单提交的URL
	 * @param refreshUrl:表单提交完毕页面刷新的URL
	 */
	function submitAddedInfo(actionName,postMethod,refreshMethod){
		var illegal=fieldCheck();
		if(illegal){
			$.ajax({
				cache:true,
				type:"POST",
				url:actionName+"!"+postMethod,
				data:$("#addInfoForm").serialize(),
				async:true,
				error:function(){
					alert("连接失败！!!");
				},
				success:function(data){
					if(data=="succ"){
						window.alert("添加成功！");
						window.location.replace(actionName+"!"+refreshMethod);
					}else{
						window.alert("添加成败！");
					}
				}
			});
		}else{
			return;
		}
	}

//////////////2：以下是更新记录表单的控制////////////////////
	/**
	 * 检查修改信息的表单是否符合提交要求
	 */
	function updateFormCheck() {
		var elements = $(".doCheck_update");
		var yes = true;
		for (var i = 0; i < elements.length; i++) {
			if (elements[i].value.trim() == "") {
				yes = false;
				elements[i].style.backgroundColor = "green";
				elements[i].placeholder = "请填充空白";
				elements[i].value = "";
			}
		}
		return yes;
	}
	/**
	 * function3:当field为空js将该文本框或者其他组件背景颜色变成绿色的时候，
	 * 保证单击该组件能够重新恢复成背景为白色
	 */
	/**
	 * 1:鼠标触发
	 */
		$('.doCheck_update').click(function() {
			$(this).css("background-color","white");
		});
	/**
	 * 2:键盘触发
	 */
		$('.doCheck_update').keyup(function() {
			$(this).css("background-color","white");
		});
		
		/**
		 * 单击修改按钮的时候 保证表单恢复原样
		 */
		$("#submitUpdateRecord").click(function(){
			fieldCSSReset(".doCheck_update");
		});
		/**
		 * 封装提交更新的绩效的表单的动作
		 * @param postUrl:表单提交的URL
		 * @param refreshUrl:表单提交完毕页面刷新的URL
		 */
		function submitUpdatedInfo(actionName,postMethod,refreshMethod){
			var illegal=updateFormCheck();
			if(illegal){
//				window.alert("合法");
				$.ajax({
					cache:true,
					type:"POST",
					url:actionName+"!"+postMethod,
					data:$("#updateInfoForm").serialize(),
					async:true,
					error:function(){
						alert("连接失败！!!");
					},
					success:function(data){
						if(data=="succ"){
							window.alert("更新成功！");
							window.location.replace(actionName+"!"+refreshMethod);
						}else{
							window.alert("更新成败！");
						}
					}
				});
			}else{
				return;
			}
		}
	
//////////////3：以下是删除记录的ajax////////////////////	
		function deleteRecord(formData,actionName,postUrl,refreshUrl){
			if(window.confirm("确定删除？")){
				$.post(actionName+"!"+postUrl,formData,
    				function(data,status){
    					if(status=="success"){
    						if(data=="succ"){
    							alert("删除成功");
    							window.location.replace(actionName+"!"+refreshUrl);
    						}else{
    							alert("删除失败");
    						}
    					}else{
    						alert("请求失败");
    					}
    				});
			}
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	