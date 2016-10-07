<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<jsp:directive.page import="com.nuaa.ec.teaching.dao.GetAllDAO"/>
<jsp:directive.page import="com.nuaa.ec.teaching.dao.DegreeThesisGuidance_PerformanceDAO"/>
<jsp:directive.page import="com.nuaa.ec.science.Permodel.TeacherLoginStatus"/>
<jsp:directive.page import="com.nuaa.ec.teaching.model.TFDegreeThesisGuidance_Performance"/>
<jsp:directive.page import="javax.servlet.http.HttpSession"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
    TeacherLoginStatus a = (TeacherLoginStatus) session.getAttribute("teacherloginStatus");
    GetAllDAO gadao = new GetAllDAO();

    List<String> b = gadao.getAllColname("TFDegreeThesisGuidance_RewardLevel", "RewardLevel");
    DegreeThesisGuidance_PerformanceDAO dtgpdao = new DegreeThesisGuidance_PerformanceDAO();
    List<TFDegreeThesisGuidance_Performance> f = dtgpdao.getAllInfoDegreeThesisGuidance(a.getTeacherID());


%>
  <%//判断操作是否成功
     int g = 888;
     if(request.getAttribute("CheckData")!=null){
	  g =(Integer)request.getAttribute("CheckData");
	 }
%>

<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh-CN">

  <head>
  <base target="_self"> 
    </head>

     
    <script>
    function DoCheck(){
     var checkid = <%=g%>;
    switch(checkid){
     case 1: swal("操作成功", "已成功提交!", "success");break;
     case 2: swal("操作成功", "已成功修改!", "success");break;
     case 3: swal("操作成功", "已成功删除!", "success");break;
     case 0: swal("Oooops", "操作失败!", "error");break;
     }
     checkid = 888;
    }
    </script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>NUAA-CEM绩效管理系统 - 绩效操作</title>
    
    <link rel="shortcut icon" href="favicon.ico"> <link href="../css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="../css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    
    <!-- Sweet Alert -->
    <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="../css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="../css/animate.min.css" rel="stylesheet">
    <link href="../css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    
    <script type="text/javascript">
    </script>
</head>

  <body class="gray-bg" onload="Deck()">

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>学位论文指导 <small></small></h5>
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
                    
                    <div class="">
                         <button class="btn  btn-primary" type="submit" data-backdrop="true" data-toggle="modal" data-target="#add">
                         <strong>添加绩效</strong>
                         </button>
                            
                        </div>
                        <div class="example">
                        <form method="post" name="f">
                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>      
                                    <th>工号</th>
                                    <th>姓名</th>
                                    <th>ID</th>
                                    <th>学位论文名称</th>
                                    <th>奖励级别</th>
                                    <th>得分</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(f!=null)for(int i=0;i<f.size();i++){ %>
                                <tr class="gradeA">
                                    <td><%=a.getTeacherID()%></td>
			                        <td><%=a.getTeacherName()%></td>
			                        <td><%=f.get(i).getDegree_thesisID()%></td>
			                        <td><%=f.get(i).getDegree_thesisnName()%></td>
			                        <td><%=f.get(i).getRewardLevel()%></td>
			                        <td><%=f.get(i).getFinalScore()%></td>
			                        <td><a    class="btn btn-primary btn-sm" data-toggle="modal"    onclick="doupdate('<%=i %>')" data-target="#update" >修改</a>
			                             <a  onclick="dodel('<%=f.get(i).getDegree_thesisID() %>')"  class="btn btn-primary btn-sm del" >删除</a>
                                    </td>
                                </tr>
                                <%} %>
                            </tbody>
                            <tfoot>
                                <tr>
                                  <th>工号</th>
                                    <th>姓名</th>
                                    <th>ID</th>
                                    <th>学位论文名称</th>
                                    <th>奖励级别</th>
                                    <th>得分</th>
                                    <th>操作</th>
                                </tr>
                            </tfoot>
                        </table>
                        </form>
                   </div>
                </div>
            </div>
        </div>
    </div>
    <div id="add" class="modal fade" aria-hidden="true" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                            <h3 class="m-t-none m-b">添加绩效</h3>
                            <form role="form" action="AddDegreeThesisGuidance!addDegreeThesisGuidance">
                                <div class="form-group">
                                    <label>工号：</label>
                                    <input type="text" readonly="true"  class="form-control" name="TeacherID" value="<%=a.getTeacherID() %>">
                                </div>
                                <div class="form-group">
                                    <label>姓名：</label>
                                    <input type="text" readonly="true" class="form-control" name="TeacherName" value="<%=a.getTeacherName() %>">
                                </div>
                                <div class="form-group">
                                    <label>ID：</label>
                                    <input type="text" class="form-control" name="Degree_thesisID"><span class="help-block m-b-none">请输入ID</span>
                                </div>
                                

                                 <div class="form-group">
                                    <label>学位论文名称：</label>
                                    <input type="text" class="form-control" name="Degree_thesisnName"><span class="help-block m-b-none"></span>
                                </div>
                                    
                                <div class="form-group">
                                <label>奖励级别：</label>
                                    <select class="form-control m-b" name="RewardLevel">
                                        <%if (b!=null ){ for(int i=0;i<b.size();i++){ %>
					                         <option ><%=b.get(i) %></option>
				                        <%} }%>			
                                    </select>
                                </div>
                               
                              
                                <div>
                                    <button type="button" class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                    <button  class="btn  btn-primary pull-left m-t-n-xs " type="submit">
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
   <div id="update" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog" 
    aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                            <h3 class="m-t-none m-b">修改绩效</h3>
                            <form role="form" id="onlyForm" action="AddDegreeThesisGuidance!updateDegreeThesisGuidance">
                            
                                <div class="form-group">
                                    <label>工号：</label>
                                    <input id="TeacherID" type="text" readonly="true"  class="form-control" name="TeacherID" value="<%=a.getTeacherID() %>">
                                </div>
                                <div class="form-group">
                                    <label>姓名：</label>
                                    <input id="TeacherName" type="text" readonly="true" class="form-control" name="TeacherName" readonly="true" value="<%=a.getTeacherName()%>">
                                </div>
                                <div class="form-group">
                                    <label>ID：</label>
                                    <input id = "cell2" type="text" class="form-control" name="Degree_thesisID" readonly="true" value="" ><span class="help-block m-b-none" value="$(tid).data(id)" readonly="true">请输入ID</span>
                                </div>
                                 <div class="form-group">
                                    <label>学位论文名称：</label>
                                    <input id="cell3" type="text" class="form-control" name="Degree_thesisnName" value=""><span class="help-block m-b-none"></span>
                                </div>    
                                <div class="form-group">
                                <label>奖励级别：</label>
                                    <select id= "cell4" class="form-control m-b" name="RewardLevel">
                                        
                                        <%if (b!=null ){ for(int i=0;i<b.size();i++){ %>
					                         <option ><%=b.get(i) %></option>
				                        <%} }%>			
                                    </select>
                                </div>
                                <div>
                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                    <button onclick="" class="btn  btn-primary pull-left m-t-n-xs " type="submit">
                                     <i class="fa fa-check"></i>
                                    <strong>提交</strong>
                                    </button	>
                               </div>
                            </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="../js/jquery.min.js?v=2.1.4"></script>
    <script src="../js/bootstrap.min.js?v=3.3.5"></script>
    <script src="../js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="../js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="../js/content.min.js?v=1.0.0"></script>
    <script src="../js/plugins/iCheck/icheck.min.js"></script>
    <script src="../js/plugins/sweetalert/sweetalert.min.js"></script>
    <script>
    function doupdate(upid){
     hang=parseInt(upid)+1;
    document.getElementById("cell2").value=document.getElementById("tb").rows[hang].cells[2].innerHTML;
    document.getElementById("cell3").value=document.getElementById("tb").rows[hang].cells[3].innerHTML;
    document.getElementById("cell4").value=document.getElementById("tb").rows[hang].cells[4].innerHTML;
    }
    </script>
    <script>
    function dodel(delid){
    swal({ 
        title: "您确定要删除吗？",  
        text: "您确定要删除这条数据？",  
        type: "warning", 
        showCancelButton: true, 
        closeOnConfirm: true, 
        confirmButtonText: "是的，我要删除", 
        confirmButtonColor: "#ec6c62" 
    }, function() { 
        document.f.action="AddDegreeThesisGuidance!delDegreeThesisGuidance?Degree_thesisID="+delid;
        f.submit();
    }); 
   
} 
    </script>
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