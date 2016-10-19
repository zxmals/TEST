<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

<head>
<base target="_self">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>NUAA.EC.TPM</title>


<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min.css?v=4.0.0" rel="stylesheet">
<base target="_blank">
<!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
<script>
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
</script>
<script>
	function checkmess() {
		var message = "${loginstatus}";
		if (message != null && message != "")
			alert(message);
	}
</script>
</head>

<body class="gray-bg" onload="checkmess()">

	<div style="text-align: center;margin-top:30px;">

		<h4 class="logo-nameU" style="margin-left: 500px;">南 &nbsp; 京
			&nbsp;航 &nbsp;空 &nbsp; 航 &nbsp; 天 &nbsp; 大 &nbsp; 学</h4>
		<h4 class="logo-nameD" style="margin-left: 500px">经 &nbsp;&nbsp;济
			&nbsp; &nbsp;与 &nbsp;&nbsp; 管 &nbsp;&nbsp;理 &nbsp;&nbsp;
			学&nbsp;&nbsp; 院</h4>

	</div>
	<div class="middle-box text-center loginscreen  animated fadeInDown">
		<div>
			<h3>测试一</h3>
			<h4></h4>

			<form class="m-t" role="form" action="login!login" method="post">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="工号"
						name="teacherid" required="">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="密码"
						name="password" required="">
				</div>
				<button type="submit" class="btn btn-primary block full-width m-b">登录</button>
			</form>
		</div>
	</div>
	<script src="js/jquery.min.js?v=2.1.4"></script>
	<script src="js/bootstrap.min.js?v=3.3.5"></script>
	<script type="text/javascript"
		src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>

</html>