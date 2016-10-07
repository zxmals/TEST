<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.nuaa.ec.science.dao.ScientificResearchRewardDAO"/>
<jsp:directive.page import="com.nuaa.ec.science.dao.TeacherANDScientificResearchRewardDAO"/>
<jsp:directive.page import="com.nuaa.ec.science.Permodel.TeacherLoginStatus"/>
<jsp:directive.page import="com.nuaa.ec.science.dao.RewardTypeDAO"/>
<jsp:directive.page import="com.nuaa.ec.science.dao.RewardLevelDAO"/>
<jsp:directive.page import="com.nuaa.ec.science.baseSet.action.AddSRRewardAction"/>
<jsp:directive.page import="javax.servlet.http.HttpSession"/>
<jsp:directive.page import="com.nuaa.ec.science.Permodel.ViewXMJL"/>
<jsp:directive.page import="com.nuaa.ec.science.dao.PersonInfoDAO"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
      TeacherLoginStatus a = (TeacherLoginStatus)session.getAttribute("teacherloginStatus");
    RewardLevelDAO rldao = new RewardLevelDAO();
     RewardTypeDAO rtdao = new RewardTypeDAO();
 List<String> c = rldao.getAllRewardLevelName();
 List<String> d = rtdao.getAllRewardTypeName();
 PersonInfoDAO pidao = new PersonInfoDAO();
    TeacherANDScientificResearchRewardDAO dao = new TeacherANDScientificResearchRewardDAO();
	 List<ViewXMJL> b = new ArrayList<ViewXMJL>();
	  String teacherID = (String)request.getAttribute("teacherID");
      String teacherid = (String)request.getAttribute("teacherid");
      String id = null;
    
      if(teacherid!=null){
      id = teacherid;
      b = dao.getAllXMJL(id);
      System.out.println("teacherid:"+teacherid);
      }
      else b = dao.getAllXMJL(teacherID);
	
	 int g =-1;
	 String ttr = (String)request.getAttribute("flag");
	 if(ttr!=null){
	 		g =Integer.parseInt(ttr);
	}
	 int h =-1;
	 String hhh = (String)request.getAttribute("flag1");
	 if(hhh!=null){
	 		h =Integer.parseInt(hhh);
	 		}
  %>
 
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
   

    <title>管理员界面</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">欢迎你，<%=a.getTeacherName()%></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
           
            <li><a href="#">设置</a></li>
            <li><a href="#">注销</a></li>
            <li><a href="#">帮助</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar" id="accordion">
          <ul class="nav nav-sidebar">
            <li><font size="5">表格管理</a></font></li>
            <li><a class="collapsed" data-toggle="collapse" data-parent="#accordion"  href="#shangchuanbiaoge" aria-expanded="false" aria-controls="shangchuanbiaoge">上传表格</a>
			<div id="shangchuanbiaoge" class="collapse">
			  <div class="well">
				<ul class="nav nav-sidebar">
				<li><a href="">新建表格</a></li>
				<li><a href="">已生成表格</a></li>
				</ul>
			  </div>
			</div>
			</li>
			
            <li><a class="collapsed" data-toggle="collapse" data-parent="#accordion"  href="#shengchengbiaoge" aria-expanded="true" aria-controls="shengchuanbiaoge">生成表格</a></li>
          </ul>
          <ul class="nav nav-sidebar">
           <li><font size="5">任务管理</a></font></li>
            <li><a href="gljixiao.jsp">绩效查询</a></li>
            <li><a href="gladdxm.jsp">添加项目</a></li>
            <li><a href="gldeljx.jsp">删除绩效</a></li>
            <li><a href="glupdatejx.jsp">更改绩效</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><font size="5">查询管理</a></font></li>
           
          </ul>
		  <ul class="nav nav-sidebar">
            <li><font size="5">绩效管理</a></font></li>
            <li><a href="ViewTeacherPerformance">科研绩效管理</a></li>
            <li><a href="">教学绩效管理</a></li>
            <li><a href="">公益绩效管理</a></li>
            <li><a href="teacherjx.jsp">教师绩效查询</a></li>
          </ul>
		  <ul class="nav nav-sidebar">
            <li><font size="5">系统设置</a></font></li>
            <li><a href="viewAllScientificModular">科研设置</a></li>
            <li><a href="">公益设置</a></li>
			<li><a href="">教学设置</a></li>
            <li><a href="baseSet!getBaseSetInfo">基础设置</a></li>
          </ul>
		  <ul class="nav nav-sidebar">
            <li><font size="5">用户管理</a></font></li>
            <li><a href="add_Teacher">增加用户</a></li>
             <li><a href="update_Teacher!viewTeacher">修改用户</a></li>
          </ul>

        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">项目奖励</h1>
        
<center>
      <br><br>
      <form action="" method="post" name="f0">
      <table class="table table-striped"  style="border-collapse:collapse">
		  <input type="submit" value="添加绩效"
				 onclick="document.f0.action='DoSRReward!admintmp?tmp1=0&tid=<%=id %>&teacherID=<%=teacherID%>';document.f0.submit();"></input>
      </form>
      <br>
      <form action="" method="post" name="f1">
     <table class="table table-striped"  style="border-collapse:collapse">
		<tr>
		   
			<td>工号</td>
			<td>姓名</td>
			<td>奖励名称</td>
			<td>获奖时间</td>
			<td>授奖部门</td>
			<td>奖励级别</td>
			<td>奖励类别</td>
			<td>获奖总人数</td>
			<td>本人排名</td>
			<td>得分</td>
			<td>操作</td>
			
		</tr>
		<%if(b!=null)for(int i=0;i<b .size();i++){ %>
			<tr>
				<td><%=b.get(i).getTeacherID() %></td>
				<td><%=pidao.getTeacherName(b.get(i).getTeacherID())%></td>
				<td><%=b.get(i).getSRRewardName()%></td>
				<td><%=b.get(i).getRewardDate() %></td>
				<td><%=b.get(i).getAwardDepartment() %></td>
				<td><%=b.get(i).getRewardLevelName() %></td>
				<td><%=b.get(i).getRewardTypeName() %></td>
				<td><%=b.get(i).getRewardTotalPeople()%></td> 
				<td><%=b.get(i).getSelfRanking() %></td>
				<td><%=b.get(i).getFinalScore() %></td>


				<td><input type="submit" value="修改"
						   onclick="document.f1.action='DoSRReward!admintmp?tmp=<%=i%>&tid=<%=id %>&teacherID=<%=teacherID%>';document.f1.submit();window.location.reload(); "></input>
					<input type="submit" value="删除"
						   onclick="document.f1.action='DoSRReward!admindelSRReward?SRRewardID=<%=b.get(i).getSRRewardID()%>&TeacherID=<%=b.get(i).getTeacherID()%>';document.f1.submit();"></input>
				</td>
			</tr>
		<%} %>
	</table>
	</center>
	</form>
          <%if(g!=-1&&b!=null){%>
          <br>
		  <center>
          <form action="" method="post" name="f">
          <table class="table table-striped"  style="border-collapse:collapse">
			<tr><td>工号</td><td><input type="text" name="TeacherID" value=" <%=id %>"  readonly="true"></td></tr>
			<tr><td>姓名</td><td><input type="text" name="TeacherName" value="<%=pidao.getTeacherName(id) %>" readonly="true"></td></tr>
			<tr><td>ID</td><td><input type="text" name="SRRewardID" value="<%=b.get(g).getSRRewardID() %>" readonly="true"/></td></tr>
			<tr><td>奖励名称</td><td><input type="text" name="SRRewardName" value="<%=b.get(g).getSRRewardName() %>" /></td></tr>
			<tr><td>获奖时间</td><td><input type="text" name="RewardDate"value="<%=b.get(g).getRewardDate() %>" /></td></tr>
			<tr><td>授奖部门</td><td><input type="text" name="AwardDepartment"value="<%=b.get(g).getAwardDepartment() %>" /></td></tr>
			<tr><td>奖励级别</td>
			<td><select name="RewardLevelName"  >
			<option value="<%=b.get(g).getRewardLevelName()%>"  selected><%=b.get(g).getRewardLevelName() %></option>
			     <%for(int i=0;i<c.size();i++){ %>
					<option value="<%=c.get(i)%>"  selected><%=c.get(i) %></option>
				<%} %>		
				</select>	
			</td></tr>
			<tr><td>项目类别</td>
			<td><select name="RewardTypeName"  >
			<option value="<%=b.get(g).getRewardTypeName()%>"  selected><%=b.get(g).getRewardTypeName() %></option>
			     <%for(int i=0;i<d.size();i++){ %>
					<option value="<%=d.get(i)%>"  selected><%=d.get(i) %></option>
				<%} %>			
				 </select>
			</td></tr>
			<tr><td>获奖总人数</td><td><input type="text" name="RewardTotalPeople" value="<%=b.get(g).getRewardTotalPeople() %>" /></td></tr>
			
			<tr><td>本人排名</td><td><input type="text" name="SelfRanking" value="<%=b.get(g).getSelfRanking() %>" /></td></tr>
			<tr><td>得分</td><td><input type="text"  name="FinalScore" value="<%=b.get(g).getFinalScore() %>" /></td></tr>
			</td></tr>
			  <tr>
				  <td>操作</td>
				  <td><input type="submit" value="提交"
							 onclick="document.f.action='DoSRReward!adminupdateSRReward?teacherID=<%=id %>';document.f.submit();"></input>
				  </td>
			  </tr>
	</table>
	</form>
	</center>
	<%} %>	
	<%if(h!=-1){ %>
	<br>
	<center>
          <form action="" method="post" name="f2">
          <table class="table table-striped"  style="border-collapse:collapse">
			<tr><td>工号</td><td><input type="text" name="TeacherID" value="<%=id%>"  readonly="true"></td></tr>
			<tr><td>姓名</td><td><input type="text" name="TeacherName" value="<%=pidao.getTeacherName(id) %>" readonly="true"></td></tr>
			<tr><td>ID</td><td><input type="text" name="SRRewardID"/></td></tr>
			<tr><td>奖励名称</td><td><input type="text" name="SRRewardName" /></td></tr>
			<tr><td>获奖时间</td><td><input type="text" name="RewardDate"/></td></tr>
			<tr><td>授奖部门</td><td><input type="text" name="AwardDepartment"/></td></tr>
			<tr><td>奖励级别</td>
			<td><select name="RewardLevelName"  >
			     <%if(c!=null)for(int i=0;i<c.size();i++){ %>
					<option value="<%=c.get(i)%>"  selected><%=c.get(i) %></option>
				<%} %>			
				 </select>
			</td></tr>
			<tr><td>奖励类别</td>
			<td><select name="RewardTypeName"  >
			     <%if(d!=null)for(int i=0;i<d.size();i++){ %>
					<option value="<%=d.get(i)%>"  selected><%=d.get(i) %></option>
				<%} %>			
				 </select>
			</td></tr>
			<tr><td>获奖总人数</td><td><input type="text" name="RewardTotalPeople" /></td></tr>
			
			<tr><td>本人排名</td><td><input type="text" name="SelfRanking" /></td></tr>
			  <tr>
				  <td>操作</td>
				  <td><input type="submit" value="提交"
							 onclick="document.f2.action='DoSRReward!adminAddSRReward?teacherID=<%=id%>';document.f2.submit();"></input>
				  </td>
			  </tr>
	</table>
	</form>
	</center>
<%} %>
	<s:debug></s:debug>
     
</body>
</html>