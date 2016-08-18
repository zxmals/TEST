<%@page import="com.nuaa.ec.model.Department"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.nuaa.ec.model.Teacher" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<String,Object> mp = (Map)session.getAttribute("teachertranslate");
List<Department> depart = (List)request.getAttribute("Department");
%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <base target="_self"> 
    </head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>departSet</title>
    
    <link rel="shortcut icon" href="favicon.ico"> <link href="../css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="../css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    
    <!-- Sweet Alert -->
    <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="../css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="../css/animate.min.css" rel="stylesheet">
    <link href="../css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
</head>

<body class="gray-bg"  onload="DoCheck()">
<%--${pageContext.request.contextPath }<br />
${basePath } --%>
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>系 <small></small></h5>
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
                         <strong>添加</strong>
                         </button>
                            
                        </div>
                        <div class="example">
                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example" >
                            <thead>
                                <tr>
									<td  class="sorting_asc">ID</td>
									<td class="sorting_asc">系名</td>
									<td class="sorting_asc">系管理员编号</td>
									<td class="sorting_asc">系管理员</td>
									<td class="sorting_asc">操作</td>
								</tr>
                            </thead>
                            <tbody>                               
								<%if(depart!=null)
									for(int i=0;i<depart.size();i++){%>
									<tr>
										<td><%=depart.get(i).getDepartmentId() %></td>
										<td><%=depart.get(i).getDepartmentName() %></td>
										<td><%=depart.get(i).getDepartAdminId() %></td>
										<td><%=mp.get(depart.get(i).getDepartAdminId()) %></td>
										<td><button  data-toggle="modal" data-target="#update">修改</button> <button>删除</button></td>
									</tr>
									<%} %>
                            </tbody>                           
                        </table>
                   </div>
                </div>
            </div>
        </div>
    </div>   
   
   
   <div id="update" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog"     aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                            <h3 class="m-t-none m-b">修改xi</h3>
                            <form role="form" action="Departmentset!adddepart" method="post">
                            	<div class="form-group" style="display: none">                                
                                    <label>系ID:</label>
                                    <input id="departmentId" type="text"  class="form-control" name="depart.departmentId">
                                </div>                           
                                <div class="form-group">                                
                                    <label>系名称:</label>
                                    <input id="departmentName" type="text"  class="form-control" name="depart.departmentName">
                                </div>
                                <div class="form-group">                                
                                    <label>系管理:</label>
                                    <div style="position:relative;">
										<span style="margin-left:200px;width:18px;overflow:hidden;">
											<select size="1" style="width:570px;height: 34px;margin-left:-200px" name="departs.departAdminID" id="inputin">
												<option selected="selected"></option>
												<option></option>
												<option>asdasdasd</option>
												<option>aaaaaaaaaa</option>
												<option>bbbbbbbbbb</option>
												<option>ccccccccc</option>
												<option>dddddddddddd</option>
											</select>
										</span> 
										<input id="selectin" style="width:570px;height: 34px;position:absolute;left:0px;">
									</div>
                                </div>															
								<div>
										<button type="button"
											class="btn btn-outline btn-primary pull-right m-t-n-xs"
											data-dismiss="modal">关闭</button>
										<button class="btn  btn-primary pull-left m-t-n-xs " type="submit">
											<i class="fa fa-check"></i> <strong>提交</strong>
										</button>
									</div>
							</form>
                    </div>
                </div>
            </div>
        </div>
    </div>              
    
    
    <div id="add" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog"     aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                            <h3 class="m-t-none m-b">添加xi</h3>
                            <form role="form" action="Departmentset!adddepart" method="post">                            
                                <div class="form-group">                                
                                    <label>系名称:</label>
                                    <input id="DepartmentName" type="text"  class="form-control" name="depart.departmentName">
                                </div>															
								<div>
										<button type="button"
											class="btn btn-outline btn-primary pull-right m-t-n-xs"
											data-dismiss="modal">关闭</button>
										<button class="btn  btn-primary pull-left m-t-n-xs " type="submit">
											<i class="fa fa-check"></i> <strong>提交</strong>
										</button>
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
    	$('#selectin').click(function() {
    		if($('#inputin').attr("size")==1){
    			$('#inputin').css("height","150px");
    			$('#inputin option').css("height","25px");
    			$('#inputin').attr("size",$('#inputin')[0].length);
    		}else{
    			$('#inputin').attr("size","1");
    			$('#inputin').css("height","30px");
    		}
		});
        $(document).ready(function(){$(".dataTables-example").dataTable();var oTable=$("#editable").dataTable();oTable.$("td").editable("../example_ajax.php",{"callback":function(sValue,y){var aPos=oTable.fnGetPosition(this);oTable.fnUpdate(sValue,aPos[0],aPos[1])},"submitdata":function(value,settings){return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}},"width":"90%","height":"100%"})});function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};         
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});            
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>