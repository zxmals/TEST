<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人资料</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<table class="table table-striped table-bordered table-hover" width="200px" style="font-size:16px;">
		<tbody>
			<tr class="info">
				<td colspan="2" align="center">
					<b>${teacherCustom.teacherId }:${teacherCustom.teacherName }</b>的用户信息
				</td>
			</tr>
			<tr>
				<td>姓名</td>
				<td>${teacherCustom.teacherName }</td>
			</tr>
			<tr>
				<td>工号</td>
				<td>${teacherCustom.teacherId }</td>
			</tr>
			<tr>
				<td>所在研究所</td>
				<td>${teacherCustom.researchLab.researchLabName }</td>
			</tr>
			<tr>
				<td>所在系</td>
				<td>${teacherCustom.department.departmentName }</td>
			</tr>
			<tr>
				<td>角色</td>
				<td>${teacherCustom.role }</td>
			</tr>
			<tr>
				<td>职位</td>
				<td>${teacherCustom.teacherPost }</td>
			</tr>
		</tbody>
	</table>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>