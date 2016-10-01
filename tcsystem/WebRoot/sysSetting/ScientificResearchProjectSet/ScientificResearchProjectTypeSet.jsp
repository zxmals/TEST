<%@ page language="java" import="java.util.*,com.nuaa.ec.model.ProjectType" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%	
	/* List<ProjectType> scientypelist=(List<ProjectType>)session.getAttribute("projectType"); */
	String add = (String)request.getAttribute("add"); 
	String uudate = (String)request.getAttribute("i");
	int uodate = 0;
	if(uudate!=null)
		uodate = Integer.parseInt(uudate);
	String update = (String)request.getAttribute("update");
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <base target="_self"> 
    </head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>periodicalTypeSet</title>
    
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    
    <!-- Sweet Alert -->
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <script type="text/javascript">
    	function DoCheck() {
    		var res = '${operstatus}';
    		//alert(addres);
			switch (res){
				case '-1':alert("操作失败 fail !!!");
				break;
				case '1':alert("添加成功!");
				break;
				default: break;
			}
		}
    </script>
</head>

<body class="gray-bg"  onload="DoCheck()">

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>科研项目类型信息 <small></small></h5>
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
                         <button class="btn  btn-primary openaddm" type="submit" data-backdrop="true" data-toggle="modal" data-target="#add">
                         <strong>添加</strong>
                         </button>
                            
                        </div>
                        <div class="example">
                        <form method="post" name="f">
                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
									<td>科研项目类型编号</td>
									<td>科研项目类型名称</td>
									<td>操作</td>
								</tr>
                            </thead>
                            <tbody>
								<c:forEach var="proType"  items="${requestScope.projectType }">
								<tr>
									<td>${proType.projectTypeId }</td>
									<td>${proType.projectTpName }</td>
									<td><a   class="btn btn-primary btn-sm delSRPType"  data-toggle="modal"  >删除</a>					
											<a  class="btn btn-primary btn-sm openupdatem carrydata update"  data-toggle="modal"  data-target="#update" >修改</a>
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
                                <div class="form-group">
                                	<label>ID:</label>                                	
									<input id="upSRPTypeId" type="text"  class="form-control" name="upProType.projectTypeId" value=""  readonly="readonly">
                                </div>
                                <div class="form-group">
                                    <label>科研项目名称:</label>
                                    <input id="upSRPTypeName" type="text"  class="form-control nullcheck" name="upProType.projectTpName" value="">
                                </div>                                                           
                                <div>
                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                    <button id="updateSRPType"  class="btn  btn-primary pull-left m-t-n-xs subcheck"  type="submit">
                                     <i class="fa fa-check"></i>
                                    <strong>提交</strong>
                                    </button	>
                               </div>
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
                            <form role="form" id="onlyForm" name="adds" action="ATScientificResearchProjectTypeset!addProjectType" method="post">                            
                                <div class="form-group">                                
                                    <label>科研项目名称:</label>&nbsp;<label></label>
                                    <input id="SRPTypeName" type="text"  class="form-control nullcheck" name="proType.projectTpName" value="">
                                </div>                                                           
                            </form>
                                <div>
                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                    <button id="addSRPType" class="btn  btn-primary pull-left m-t-n-xs subcheck"  type="submit">
                                     <i class="fa fa-check"></i>
                                    <strong>提交</strong>
                                    </button	>
                               </div>
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
    <script  src="js/PublicCheck/PUB_SET.js"></script>
    
    <script>
    $('#addSRPType').click(function() {
		if($('#SRPTypeName').val().trim()!=""){
			document.adds.submit();
		}
	});
    $('#updateSRPType').click(function() {
    	if($('#upSRPTypeName').val().trim()!=""){
    		$.post("ATScientificResearchProjectTypeset!updateProjectType",
    				{"proType.projectTypeId":$('#upSRPTypeId').val().trim(),
    				 "proType.projectTpName":$('#upSRPTypeName')[0].value},
    				function(data,status){
    					if(status=="success"){
    						if(data=="succ"){
    							alert("修改成功");
    							window.location.replace("<%=basePath %>ATScientificResearchProjectTypeset!viewScienResearchProjectType");
    						}else{
    							alert("修改失败"+data);
    						}
    					}else{
    						alert("请求失败");
    					}
    				});
    	}
	});
    /* 原来弹出的修改信息窗口没有显示ID和Name值 导致提交修改的时候后台接受不到ID值，所以在这里设置了ID和name的值 */
    $('.update').click(function() {
		$('#upSRPTypeId').attr("value",$(this).parent().parent()[0].cells[0].innerHTML);
		$('#upSRPTypeName')[0].value = $(this).parent().parent()[0].cells[1].innerHTML;
	});
    $('.delSRPType').click(function() {
		var x = confirm("确定删除 ? ");
		var row = $(this).parent().parent();
		if(x){
			$.post("ATScientificResearchProjectTypeset!delProjectType",
    				{"proType.projectTypeId":row[0].cells[0].innerHTML},
    				function(data,status){
    					if(status=="success"){
    						if(data=="succ"){
    							alert("删除成功");
    							row.remove();
    						}else{
    							alert("删除失败");
    						}
    					}else{
    						alert("请求失败");
    					}
    				});
		}
	});
        $(document).ready(function(){$(".dataTables-example").dataTable();var oTable=$("#editable").dataTable();oTable.$("td").editable("../example_ajax.php",{"callback":function(sValue,y){var aPos=oTable.fnGetPosition(this);oTable.fnUpdate(sValue,aPos[0],aPos[1])},"submitdata":function(value,settings){return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}},"width":"90%","height":"100%"})});function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};         
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});            
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>