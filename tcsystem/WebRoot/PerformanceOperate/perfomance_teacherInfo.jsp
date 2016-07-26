<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.nuaa.ec.science.dao.TeacherDAO"%>
<jsp:directive.page import="javax.servlet.http.HttpSession"/>
<jsp:directive.page import="com.nuaa.ec.science.Permodel.TeacherLoginStatus"/>
<jsp:directive.page import="com.nuaa.ec.science.Permodel.PreTeacher"/>
<jsp:directive.page import="com.nuaa.ec.science.dao.PersonInfoDAO"/>
<jsp:directive.page import="java.text.DateFormat"/>
<jsp:directive.page import="java.util.*"/>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    TeacherDAO dao = new TeacherDAO();
    List<PreTeacher> b = new ArrayList<PreTeacher>();
    b = dao.getTeacherInfo();
%>
 

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <head>
        <base target="_self">
        <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>NUAA-CEM绩效管理系统 - 绩效操作 - 查询教师</title>
    
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <script type="text/javascript">
    </script>
</head>

<body class="gray-bg">

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>教学研究 <small></small></h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="table_data_tables.html#">选项1</a>
                                </li>
                                <li><a href="table_data_tables.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                    
                   
                        <div class="example">
                        <form method="post" name="f">
                       <table  class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    
                                    <th>工号</th>
                                    <th>姓名</th>
                                    <th>期刊论文</th>
                                    <th>学术著作</th>
                                    <th>科研项目</th>
                                    <th>项目奖励</th>
                                    <th>参加学术会议</th>
                                    <th>邀请专家讲学</th>
                                    <th>主承办学术会议</th>
                                    <th>入选人才工程</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(b!=null)for(int i=0;i<b.size();i++){ %>
                                <tr class="gradeA">
                                    <td><%=b.get(i).getTeacherID() %></td>
				<td><%=b.get(i).getTeacherName()%></td>
                                    <td>
                                        <center><a class="btn btn-outline btn-primary btn-sm"
                                                   href="AddPPapers!AdminController?TeacherID=<%=b.get(i).getTeacherID()%>">查看</a>
                                        </center>
                                    </td>
                                    <td>
                                        <center><a class="btn btn-outline btn-primary btn-sm"
                                                   href="addAWork!AdminController?TeacherID=<%=b.get(i).getTeacherID()%>">查看</a>
                                        </center>
                                    </td>
                                    <td>
                                        <center><a class="btn btn-outline btn-primary btn-sm"
                                                   href="AddSRProject!AdminController?TeacherID=<%=b.get(i).getTeacherID()%>">查看</a>
                                        </center>
                                    </td>
                                    <td>
                                        <center><a class="btn btn-outline btn-primary btn-sm"
                                                   href="AddSRReward!AdminController?TeacherID=<%=b.get(i).getTeacherID()%>">查看</a>
                                        </center>
                                    </td>
                                    <td>
                                        <center><a class="btn btn-outline btn-primary btn-sm"
                                                   href=AddJAMeeting!AdminController?TeacherID=<%=b.get(i).getTeacherID()%>">查看</a>
                                        </center>
                                    </td>
                                    <td>
                                        <center><a class="btn btn-outline btn-primary btn-sm"
                                                   href="AddIESpeech!AdminController?TeacherID=<%=b.get(i).getTeacherID()%>">查看</a>
                                        </center>
                                    </td>
                                    <td>
                                        <center><a class="btn btn-outline btn-primary btn-sm"
                                                   href="AddMUAMeeting!AdminController?TeacherID=<%=b.get(i).getTeacherID()%>">查看</a>
                                        </center>
                                    </td>
                                    <td>
                                        <center><a class="btn btn-outline btn-primary btn-sm"
                                                   href="AddSTProject!AdminController?TeacherID=<%=b.get(i).getTeacherID()%>">查看</a>
                                        </center>
                                    </td>
                                </tr>
                                <%} %>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>工号</th>
                                    <th>姓名</th>
                                    <th>期刊论文</th>
                                    <th>学术著作</th>
                                    <th>科研项目</th>
                                    <th>项目奖励</th>
                                    <th>参加学术会议</th>
                                    <th>邀请专家讲学</th>
                                    <th>主承办学术会议</th>
                                    <th>入选人才工程</th>
                                </tr>
                            </tfoot>
                        </table>
                        </form>
                   </div>
                </div>
            </div>
        </div>
    </div>
   
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>

    <script>
        $(document).ready(function () {
            $(".dataTables-example").dataTable();
            var oTable = $("#editable").dataTable();
            oTable.$("td").editable("../example_ajax.php", {
                "callback": function (sValue, y) {
                    var aPos = oTable.fnGetPosition(this);
                    oTable.fnUpdate(sValue, aPos[0], aPos[1])
                }, "submitdata": function (value, settings) {
                    return {"row_id": this.parentNode.getAttribute("id"), "column": oTable.fnGetPosition(this)[2]}
                }, "width": "90%", "height": "100%"
            })
        });
        function fnClickAddRow() {
            $("#editable").dataTable().fnAddData(["Custom row", "New row", "New row", "New row", "New row"])
        }
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
    
        
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>