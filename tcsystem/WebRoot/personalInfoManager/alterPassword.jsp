<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body>
	<div
		style="margin:40px auto;text-align:center;font-size:30px;font-family: 微软雅黑;color:silver;">修改密码</div>
	<div
		style="width:500px;height:400px;font-family: 微软雅黑;margin:40px auto;">
		<form action="" class="" id="form">
			<div class="input-group">
				<div class="input-group-addon input-group-addon-cus"style="letter-spacing:5px;">原密码</div>
				<input type="password" id="oriPassword" name="oriPassword"
					required="required" class="form-control input-lg">
			</div>
			<div id="errInfo" style="width:500px;height:30px;color:orange;text-align: center;">

			</div>
			<div class="input-group">
				<div class="input-group-addon input-group-addon-cus" style="letter-spacing:5px;">新密码</div>
				<input type="password" name="newPassword" id="newPassword"
					class="form-control input-lg">
			</div>
			<div id="errInfo2" style="width:500px;height:30px;color:orange;text-align: center;">

			</div>
			<div class="input-group">
				<div class="input-group-addon input-group-addon-cus">确认密码</div>
				<input type="password" name="confrimPass" id="confrimPass"
					class="form-control input-lg">
			</div>
			<div id="errInfo3" style="width:500px;height:30px;color:orange;text-align: center;">

			</div>
			<div class="input-group">
				<button type="button" id="submitLoginInfo"
					style="width:500px; height: 50px;"
					class="from-control btn btn-success btn-style-set"
					onclick="alterPassword();">确认修改</button>
			</div>
		</form>
	</div>
</body>
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript">
	function alterPassword(){
		var oriPassword = document.getElementById("oriPassword").value;
		var newPassword = document.getElementById("newPassword").value;
		var confrimPass = document.getElementById("confrimPass").value;
		var errorInfo = document.getElementById("errInfo");
		var errorInfo2 = document.getElementById("errInfo2");
		var errorInfo3 = document.getElementById("errInfo3");
		if(oriPassword=='' || oriPassword.length==0){
			errorInfo.innerHTML="原始密码不能为空！";
			return;
		}
		errorInfo.innerHTML="";
		if(newPassword=='' || newPassword.length==0){
			errorInfo2.innerHTML="新密码不能为空！";
			return;
		}
		errorInfo2.innerHTML="";
		if(newPassword!=confrimPass){
			errorInfo3.innerHTML="确认密码与新密码不一致，请重新输入";
			return;
		}
		errorInfo3.innerHTML="";
		if(newPassword==oriPassword){
			errorInfo2.innerHTML="新密码与原密码不能相同！";
			return;
		}
		$.ajax({
			type:'POST',
			url:'AT_GT_AlterPassword!alterPassword',
			data:'oriPassword='+oriPassword+'&newPassword='+newPassword,
			async:true,
			error:function(){
				window.alert("服务器错误！");
			},
			success:function(data){
				if(data=="succ"){
					if(confirm("修改成功！是否现在重新登录？")){
						window.location.href="${pageContext.request.contextPath}";
					}else{
						document.getElementById("form").reset();
						errorInfo.innerHTML="";
						errorInfo2.innerHTML="";
					}
				}else{
					errorInfo.innerHTML="原始密码错误！"
				}
			}
		});
	}
</script>
</html>











