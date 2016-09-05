<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.nuaa.ec.science.baseSet.action.MidifyPwdAction"/>
<jsp:directive.page import="com.nuaa.ec.science.baseSet.action.LoginAction"/>
<jsp:directive.page import="com.nuaa.ec.science.Permodel.TeacherLoginStatus"/>
<jsp:directive.page import="javax.servlet.http.HttpSession"/>
<jsp:directive.page import="java.text.DateFormat"/>
<jsp:directive.page import="java.util.Date"/>
<%@page import="com.nuaa.ec.science.model.ResearchLab"%>
<%@page import="com.nuaa.ec.science.model.Department"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
TeacherLoginStatus a = (TeacherLoginStatus)session.getAttribute("teacherloginStatus");

List<Department> depart = (List)session.getAttribute("departmentinfo");
	List<ResearchLab> research = (List)session.getAttribute("researchlabinfo");
	
%>
<head>
	<base target="_self"> 
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>NUAA-CEM绩效管理系统 - 添加教师</title>
   

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">

</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
             <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>添加教师 </h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="form_basic.html#">选项1</a>
                                </li>
                                <li><a href="form_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="text-center">
                            <a data-toggle="modal" data-backdrop="true" class="btn btn-primary" href="xiugaimima.jsp#modal-form">添加教师</a>
                        </div>
                    </div>
                </div>
            </div>
            </div>

<div class="row">
             <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>修改教师信息</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="form_basic.html#">选项1</a>
                                </li>
                                <li><a href="form_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="text-center">
                            <a data-toggle="modal" data-backdrop="true" class="btn btn-primary" href="xiugaimima.jsp#modal-form">修改教师信息</a>
                        </div>
                    </div>
                </div>
            </div>
            </div>

    <div id="modal-form" class="modal fade" aria-hidden="true" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        
                            <h3 class="m-t-none m-b">添加教师</h3>

                            

                            <form role="form" action="doadd!add">
                                
                                <div class="form-group">
                                    <label>教师工号：</label>
                                    <input type="text"  class="form-control" name="TeacherID"  >
                                </div>
                                <div class="form-group">
                                    <label>教师姓名：</label>
                                    <input type="text" class="form-control" name="TeacherName">
                                </div>
                                <div class="form-group">
                                    <label>所属研究所：</label>
                                    <select class="form-control m-b" name="ResearchLab">
                                       <%if(research!=null)for(int i=0;i<research.size();i++){ %>
                                           <option><%=research.get(i).getInstituteName()%></option>
                                      <%} %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>所属系：</label>
                                    <select class="form-control m-b" name="Department">
                                       <%if(depart!=null)for(int i=0;i<depart.size();i++){ %>
                                          <option><%=depart.get(i).getDepartmentName()%></option>
                                       <%} %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>用户等级：</label>
                                    <select class="form-control m-b" name="Level">
                                        <option >普通教师</option>
                                        <option >管理员教师</option>
                                    </select>
                                </div>
                                <div>
                                    <button type="button" class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                    <button class="btn  btn-primary pull-left m-t-n-xs " type="submit">
                                     <i class="fa fa-check"></i>
                                    <strong>提交</strong>
                                    </button>
                                </div>
                            </form>
                     
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
  </body>
</html>
