<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" import="com.nuaa.ec.science.model.Department"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <script type="text/javascript">    	
			    function assignmentC(departID, depart, departadminID,departAdmin) {
					document.getElementById("DepartmentID").value = departID;
					document.getElementById("DepartmentName").value = depart;
					document.getElementById("upublicinputID").value = departadminID;
					document.getElementById("udepartAdmin").value = departAdmin;
			
					var obj = document.getElementById('upublicselectID');
					for(var i=0;i<obj.options.length; i++) {
						obj.options[i].style="display: block"; 
						if(obj.options[i].value.substring(9,16) !=departID&&obj.options[i].text!=""){
							//alert("obj.options["+i+"].value.substring(9,16):"+obj.options[i].value.substring(9,16)+"departID:"+departID);
							obj.options[i].style="display: none"; 							
						}
					}
					
				}

					function confirmdel(id) {
						if (confirm("确定要删除吗？"))
							window.location.replace("Departmentset!dodel?ID="+ id);
						else
							window.location.replace("#");
					}

					function DoCheck() {
						var res = '${resu}';
						//alert(addres);
						switch (res) {
						case '0':
							alert("operate fail !!!");
							break;
						case '1':
							alert("add success!");
							break;
						case '2':
							alert("update success!");
							break;
						case '3':
							alert("delete success !!!");
							break;
						case '4':
							alert("非法操作 . 一个教师不能同时存在于两个系");
							break;
						default:
							break;
						}
					}

					function selectTOinput(a, b, c) {
						var str = document.getElementById(a).value;
						document.getElementById(b).value = str.substring(0, 9);
						document.getElementById(c).value = str.substring(16,str.length);
						document.getElementById(c).style.webkitTextFillColor = "";
						document.getElementById(c).style.fontWeight = "";

					}
					function inputTOselect(a, b, c) {
						var str = document.getElementById(b).value.replace(/[ ]/g, ""); // pick out " "
						//alert("*"+str+"*");
						var obj = document.getElementById(a);
						var x = 0;
						for (var i = 0; i < obj.options.length; i++) {
							x++;
							if(obj.options[i].style.display!="none")
								if (obj.options[i].value.substring(0, 9) == str) {
									//alert("$"+obj.options[i].value.substring(0, 9)+"$");
									obj.options[i].selected = true;
									x--;
									break;
								}
						}
						if (x != obj.options.length) {
							obj = document.getElementById(a).value;
							document.getElementById(c).value = obj.substring(16,obj.length);
							document.getElementById(c).style.webkitTextFillColor = "";
							document.getElementById(c).style.fontWeight = "";
						} else {
							document.getElementById(c).value = "没有找到该教师！";
							document.getElementById(c).style.webkitTextFillColor = "red";
							document.getElementById(c).style.fontWeight = "600";
						}
					}
				</script>
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
                        <form method="post" name="f">
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
								<c:forEach  var="depart"  items="${Department }">
									<tr>
										<td>${depart.departmentID }</td>
										<td>${depart.departmentName }</td>
										<td>${depart.departAdminID }</td>
										<td>${depart.departAdmin }</td>
										<td><a   class="btn btn-primary btn-sm"  data-toggle="modal"    onclick="confirmdel('${depart.departmentID }')"  >删除</a>					
										<a   class="btn btn-primary btn-sm"  data-toggle="modal"    onclick="assignmentC('${depart.departmentID }','${depart.departmentName }','${depart.departAdminID }','${depart.departAdmin }')"  data-target="#update" >修改</a>
									</td>	
									</tr>
								</c:forEach>
                            </tbody>                           
                        </table>
                        </form>
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
                            <h3 class="m-t-none m-b">修改</h3>
                            <form role="form" id="onlyForm" name="upd"action="Departmentset!doupdate">
                            
                                <div class="form-group">
                                	<label>ID:</label>                                	
									<input id="DepartmentID" type="text"  class="form-control" name="DepartmentID" value=""  readonly="readonly">
                                </div>
                                <div class="form-group">                                
                                    <label>系名:</label>
                                    <input id="DepartmentName" type="text"  class="form-control" name="DepartmentName" value="">
                                </div>
                                 <div class="form-group">
									<label>系管理员编号:</label>
									<div style="position:relative;">
										<span style="margin-left:200px;width:18px;overflow:hidden;">
											<select onchange="selectTOinput('upublicselectID','upublicinputID','udepartAdmin')" style="width:218px;height:30px;margin-left:-200px" name="departs.departAdminID" id="upublicselectID">
												<option selected="selected"></option>
												<c:forEach var="tli" items="${teacherli }">																			
														<option value="${tli.teacherID }${tli.departmentID }${tli.teacherName }">${tli.teacherID }</option>
												</c:forEach>																										
											</select>
										</span> 
										<input  onchange="inputTOselect('upublicselectID','upublicinputID','udepartAdmin')" id="upublicinputID" style="width:200px;height: 30px;position:absolute;left:0px;">
										<span color="red"><输入或选择后请按“TAB”键></span>
									</div>
								</div>
								<div class="form-group">
									<label>系管理员:</label> 
									<input id="udepartAdmin" type="text" class="form-control" name="departs.departAdmin" value="" readonly="readonly">
								</div>                                 
                                <div>                                    
                                    <button  class="btn  btn-primary pull-left m-t-n-xs "  type="submit">
                                     <i class="fa fa-check"></i>
                                    <strong>提交</strong>
                                    </button	>
                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
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
                            <h3 class="m-t-none m-b">添加</h3>
                            <form role="form" id="onlyForm" name="adds"action="Departmentset!doadd">                            
                                <div class="form-group">                                
                                    <label>系名称:</label>
                                    <input id="DepartmentName" type="text"  class="form-control" name="DepartmentName" value="">
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
        $(document).ready(function(){$(".dataTables-example").dataTable();var oTable=$("#editable").dataTable();oTable.$("td").editable("../example_ajax.php",{"callback":function(sValue,y){var aPos=oTable.fnGetPosition(this);oTable.fnUpdate(sValue,aPos[0],aPos[1])},"submitdata":function(value,settings){return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}},"width":"90%","height":"100%"})});function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};         
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});            
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>