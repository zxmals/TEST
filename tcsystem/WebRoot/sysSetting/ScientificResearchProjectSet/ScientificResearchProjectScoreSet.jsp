
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.nuaa.ec.model.ScientificResearchProjectScore" %>
<%@ page import="com.nuaa.ec.model.ProjectType" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
    	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

<%
	/* List<ProjectType> scientypelist = (List)session.getAttribute("projectType"); */
	/* List<ScientificResearchProjectScore> scienscorelist = (List)session.getAttribute("ScienScore"); */
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
                        <h5>科研项目评分信息 <small></small></h5>
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
                         <button id="submitBtn" onclick="setProTypeDefaultId()" class="btn  btn-primary openaddm" type="submit" data-backdrop="true" data-toggle="modal" data-target="#add">
                         <strong>添加</strong>
                         </button>
                            
                        </div>
                        <div class="example">
                        <form method="post" name="f">
                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
									<td style="display: none">&nbsp;子模块编号&nbsp;</td> 
									<td style="display:none;">&nbsp;科研项目类型编号&nbsp;</td>
									<td>&nbsp;科研项目评分编号&nbsp;</td>
									<td>&nbsp;科研项目类型名称&nbsp;</td>
									<td>&nbsp;基础分数&nbsp;</td>
									<td>&nbsp;操作&nbsp;</td>
								</tr>
                            </thead>
                            <tbody>
								<c:forEach var="item" items="${requestScope.ScienScore }">
									<tr>
										<td style="display: none;">${item.subModular.subModularId} </td>
										<td style="display:none;">${item.projectType.projectTypeId }</td>
										<td>${item.srprojectScoreId }</td>
										<td>${item.projectType.projectTpName }</td>
										<td>${item.score }</td>
										<td><a class="btn btn-primary btn-sm delSRPScore"  data-toggle="modal"  >删除</a>					
											<a  class="btn btn-primary btn-sm updateBtn carrydata"  data-toggle="modal"  data-target="#update" >修改</a>
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
                                <div class="form-group" style="display:none;">
                                	<label>ID:</label>                                	
									<input id="upSRPScoreId" type="text"  class="form-control" name="word.wordID" value=""  readonly="readonly">
                                </div>
                                <div class="form-group" style="display:none;">
                                	<label>子模块ID:</label>                                	
									<input id="upSRPSubId" type="text"  class="form-control" name="word.wordID" value=""  readonly="readonly">
                                </div>
                                <div class="form-group">
                                	<label>科研项目类型ID:</label>                                	
									<input id="upSRPTypeId" type="text"  class="form-control" name="word.wordID" value=""  readonly="readonly">
                                </div>
                                <div class="form-group">
                                    <label>基础分数</label>&nbsp;
                                    <input id="updateScore" type="text"  class="form-control nullcheck" name="word.wordNumber" value="">
                                </div>                                                           
                                <div>
                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                    <button id="updateSRPScore"  class="btn  btn-primary pull-left m-t-n-xs subcheck"  type="submit">
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
                            <form role="form" id="onlyForm" name="adds"action="ATScientificResearchProjectScoreset!addSRPScore" method="post">  
                            	<div class="form-group" style="display:none;">                                
                                    <label>科研项目评分编号</label>&nbsp;
                                    <input id="SRPScoreID" type="text"  class="form-control nullcheck" name="SRPScore.srprojectScoreId" value="">
                                </div> 
                                <div class="form-group">                                
                                    <label>科研项目类型名称</label>&nbsp;
                                     <select id="upProTypeIDSelector"  class="form-control"   name="proType.projectTypeId" onchange="getProTypeId()">
		                                    <c:forEach  var="item"  items="${proTypeList }">
		                                    	<option value="${item.projectTypeId }">${item.projectTpName }</option>
		                                    </c:forEach>
	                                 </select>
                                </div>
                                 <input type="hidden" name="" value="" id="projectTypeId"/>
                                 <script type="text/javascript">
                                 function setProTypeDefaultId(){
	                                 	$("#projectTypeId").val($("#upProTypeIDSelector option:first").text());
	                                 	/* alert($("#projectTypeId").val()); */
                                 }
                                 function getProTypeId(){
                                	 $("#projectTypeId").val($("#upProTypeIDSelector option:selected").text());
                                	 /* $("#upProTypeIDSelector option:selected").text(); */
                                	  /* alert($("#projectTypeId").val()); */
                                 }
                                 </script>
                                <div class="form-group" style="display: none;">                                
                                    <label>子模块类型名称</label>&nbsp;
                                    <input id="wordnumber" type="text"  class="form-control nullcheck" name="wordnum.wordNumber" value="">
                                </div>                        
                                <div class="form-group">                                
                                    <label>基础分数</label>&nbsp;
                                    <input id="score" type="text"  class="form-control nullcheck" name="SRPScore.score" value="">
                                </div>                                                          
                            </form>
                           	<div>
                                  <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                  <button id="addSRPScore" class="btn  btn-primary pull-left m-t-n-xs subcheck"  type="submit" onclick="nullCheck()">
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
    function nullcheck() {
		var perios = $('.nullcheck');
		for(var i=0;i<perios.length;i++){
			if(perios[i].value.trim()==""){
				perios[i].value = "";
				perios[i].style.backgroundColor = "green";
				perios[i].placeholder = "请填充空白";
			}
		}
	}
    $('#addSRPScore').click(function() {
    	nullcheck();
		if($('#score').val().trim()!=""){
			document.adds.submit();
		}
	});
    $('#updateSRPScore').click(function() {
    	if($("#updateScore").val()==""){
    		$("#updateScore").css("background-color","green");
    	}
    	if($('#updateScore').val().trim()!=""){
    		$.post("ATScientificResearchProjectScoreset!updateSRPScore",
    				{"SRPScore.srprojectScoreId":$('#upSRPScoreId').val().trim(),
    			     "SRPScore.score":$('#updateScore').val().trim(),
    				 "subModular.subModularId":$('#upSRPSubId').val().trim(),
    				 "proType.projectTypeId":$('#upSRPTypeId').val().trim()},
    				function(data,status){
    					if(status=="success"){
    						if(data=="succ"){
    							alert("修改成功"+data);
    							window.location.replace("<%=basePath %>ATScientificResearchProjectScoreset!viewScienResearchProjectScore");
    						}else{
    							alert("修改失败"+data);
    						}
    					}else{
    						alert("请求失败");
    					}
    				});
    	}
	});
    $('.delSRPScore').click(function() {
		var x = confirm("确定删除 ? ");
		var row = $(this).parent().parent();
		if(x){
			$.post("ATScientificResearchProjectScoreset!delSRPScore",
    				{
					"subModular.subModularId":row[0].cells[0].innerHTML,
					"proType.projectTypeId":row[0].cells[1].innerHTML,
    				 "SRPScore.srprojectScoreId":row[0].cells[2].innerHTML},
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
    $('.updateBtn').click(function() {
    	$("#updateScore").css("background-color","white");
		$('#upSRPScoreId').attr("value",$(this).parent().parent()[0].cells[2].innerHTML);
		$('#upSRPSubId')[0].value = $(this).parent().parent()[0].cells[0].innerHTML;
		$('#upSRPTypeId')[0].value = $(this).parent().parent()[0].cells[1].innerHTML;
		$('#updateScore')[0].value=$(this).parent().parent()[0].cells[4].innerHTML;
	});
    $('.nullcheck').keyup(function() {
    	$(this)[0].style.backgroundColor = "white";
	});
        $(document).ready(function(){$(".dataTables-example").dataTable();var oTable=$("#editable").dataTable();oTable.$("td").editable("../example_ajax.php",{"callback":function(sValue,y){var aPos=oTable.fnGetPosition(this);oTable.fnUpdate(sValue,aPos[0],aPos[1])},"submitdata":function(value,settings){return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}},"width":"90%","height":"100%"})});function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};         
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});            
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>