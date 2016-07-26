<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.nuaa.ec.science.Permodel.TeacherLoginStatus"%>
<jsp:directive.page import="com.nuaa.ec.science.dao.PersonInfoDAO"/>

<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	PersonInfoDAO s = new PersonInfoDAO();
	TeacherLoginStatus a = (TeacherLoginStatus)session.getAttribute("teacherloginStatus");
	
	%>
    <!--  String ID = a.getTeacherID();
	String name = s.getTeacherName(a.getTeacherID());
	String birthdate = s.getBirthDate(a.getTeacherID());
	String logindate = s.getLastLoginDate(a.getTeacherID());
	String department = s.getDepartmentName(a.getTeacherID());
	String institute = s.getInstituteName(a.getTeacherID());
	String level = s.getLevel(a.getTeacherID());
	System.out.println("********************************"+a.getTeacherID());
    -->
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>NUAA-CEM绩效管理系统 - 个人资料</title>
    
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>个人资料</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="table_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="table_basic.html#">选项1</a>
                                </li>
                                <li><a href="table_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>姓名</th>
                                    <th>所属系</th>
                                    <th>所属研究所</th>
                                    <th>上次登陆日期</th>
                                    <th>身份</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><%=a.getTeacherID()%></td>
                                    <td><%=s.getTeacherName(a.getTeacherID())%></td>
                                    <td><%=s.getDepartmentName(a.getTeacherID())%></td>
                                    <td><%=s.getResearchLabName(a.getTeacherID())%></td>
                                    <td><%=s.getLastLoginDate(a.getTeacherID())%></td>
                                    <td><%
                                        if (s.getLevel(a.getTeacherID()).equals("2")) {
                                            out.println("普通教师");
                                        } else if (s.getLevel(a.getTeacherID()).equals("1")) {
                                            out.println("管理员教师");
                                        }
                                    %></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        
        </div>

        </div>
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/plugins/peity/jquery.peity.min.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script src="js/demo/peity-demo.min.js"></script>
    <script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>

</html>