<%@page import="com.nuaa.ec.utils.StoreData"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("teachermp", StoreData.getTeachertranslate());
%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <base target="_self"> 
    </head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>TFtextbook --project-Set</title>
    
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    
    <!-- Sweet Alert -->
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="css/zxma.css">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <link rel="stylesheet" href="css/mermberTab.css" >
    <script type="text/javascript">
    	var teacherIds = "${teacher.teacherId}"; 
    	function DoCheck() {
    		var res = '${operstatus}';
    		//alert(addres);
			switch (res){
				case '-1':swal("操作失败 fail !!!");
				break;
				case '1':swal("添加成功!");
				break;
				default: break;
			}
		}
    </script>
</head>

<body class="gray-bg"  onload="DoCheck()">
	<div class="chooseact">
    <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
	            <div class="col-sm-12">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>教材建设管理<small></small></h5>
	                        <div class="ibox-tools" >
	                        </div>
	                    </div>
	                    <div class="ibox-content" style="height:540px;">
	                    	 <br>
	                    <form id="adjusts" action="ATteachingtextbookPerformanceSet!gainAllProject" method="get">
							<div>
			                    	<a>每页   
			                    	<select name="limit" id="changelength" style="width:60px;height:25px;border-radius:3px;">
			                    		<option selected="selected"></option>
			                    		<option>2</option>
			                    		<option>1</option>
			                    		<option>5</option>
			                    		<option>10</option>
			                    		<option>15</option>
			                    		<option>20</option>
			                    	</select>  条记录</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="#337AB7">学期：</font>
								<select id="termSelection" name="term.termId" style="width:120px;height:25px;border-radius:3px;">
									<option value="">全部学期</option>
									<c:forEach var="tfterm" items="${tftermList }">
										<option value="${tfterm.termId }">${tfterm.term }</option>
									</c:forEach>
								</select>
								&nbsp;&nbsp;&nbsp;
								<button class="button_set" type="submit" id="AlterPageSize"
								data-backdrop="true" data-toggle="modal">
								<strong>查询</strong>
								</button>
							</div>
						</form>
						<br>
	                    <br>
	                        <div class="example">
	                        <form method="post" name="f">
	                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example">
	                            <thead>
	                                <tr>
										<td>教材建设Id</td>
										<td>教材名称</td>
										<td>是否合作</td>
										<td>教材等级</td>
										<td>项目总分</td>
										<td>学期</td>
										<td>登记负责人Id</td>
										<td>登记负责人</td>
										<td>状态</td>
										<td>操作</td>
									</tr>
	                            </thead>
	                            <tbody>
									<c:forEach var="ebj" items="${textbookproli }">
										<tr>
											<td>${ebj.bookId }</td>
											<td>${ebj.bookName }</td>
											<td title="${ebj.cooperator }">
												<c:if test="${ebj.cooperator=='1' }">是</c:if>
												<c:if test="${ebj.cooperator=='0' }">否</c:if>
											</td>
											<td title="${ebj.tftextbookConstructionTblevel.tblevelId }">${ebj.tftextbookConstructionTblevel.tblevel }</td>
											<td>${ebj.projectSumScore }</td>
											<td title="${ebj.tfterm.termId }">${ebj.tfterm.term }</td>
											<td>${ebj.chargePersonId }</td>
											<td>${teachermp[ebj.chargePersonId] }</td>
											<td title="${ebj.checkOut }">
												<c:if test="${ebj.checkOut==5 }">待完善</c:if>
												<c:if test="${ebj.checkOut==0 }">已完善,待审核</c:if>
												<c:if test="${ebj.checkOut==1 }">已审核</c:if>
												<c:if test="${ebj.checkOut==2 }">未通过</c:if>
											</td>
											<td>
													<c:if test="${ebj.checkOut==5 }">
														<a  class="btn btn-primary btn-sm openupdatem carrydata" data-toggle="modal" data-target="#utdialog">编辑</a>
														&nbsp;&nbsp;
														<a  class="btn btn-primary btn-sm getMember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>
													</c:if>
													
													<c:if test="${ebj.checkOut==0 }">
														<a  class="btn btn-primary btn-sm openupdatem carrydata" data-toggle="modal" data-target="#utdialog">编辑</a>
														&nbsp;&nbsp;
														<a  class="btn btn-primary btn-sm getMember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>
													</c:if>
													
													<c:if test="${ebj.checkOut==2 }">
														<a  class="btn btn-primary btn-sm openupdatem carrydata" data-toggle="modal" data-target="#utdialog">编辑</a>
														&nbsp;&nbsp;
														<a  class="btn btn-primary btn-sm getMember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>
													</c:if>
													<c:if test="${ebj.checkOut==1 }">
														<a  class="btn btn-primary btn-sm getMember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>
													</c:if>
											</td>
										</tr>
									</c:forEach>
	                            </tbody>                           
	                        </table>
	                        </form>
	                        <div style="text-align: center;">
	                        	(共查询到${sumrow }条记录)&nbsp;&nbsp;&nbsp;&nbsp;
	                        	第${pagenum }/${sumpage }页&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="ATteachingtextbookPerformanceSet!gainAllProject?pagenum=1">首页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="ATteachingtextbookPerformanceSet!gainAllProject?pagenum=${prepage }">上一页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="ATteachingtextbookPerformanceSet!gainAllProject?pagenum=${nextpage }">下一页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="ATteachingtextbookPerformanceSet!gainAllProject?pagenum=${sumpage }">尾页</a>
	                        </div>
	                   </div>
	                </div>
	            </div>
	        </div>
	    </div>   
    </div>
    
     <div id="utdialog" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog"  aria-labelledby="myModalLabel">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-body">
	                    <div class="row">
	                            <h3 class="m-t-none m-b" id="addmodaldialogTitle">新增教材建设</h3>
	                            <h3 class="m-t-none m-b" id="updatemodaldialogTitle">修改教材建设</h3>
	                            <hr >
	                            	<div class="form-group" style="display: none">                                
	                                    <label>项目ID:</label>
	                                    <input id="projectId" type="text" class="form-control nullcheck">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>教材名称:</label>
	                                    <input id="gainName" type="text" class="form-control nullcheck addcheck">
	                                </div>
	                                <div class="form-group">                            
	                                    <label>教材等级:</label>
	                                    <select id="level" class="form-control nullcheck addcheck" >
	                                    	<option></option>
	                                    	<c:forEach items="${tbooklevel }" var="obj">
	                                    		<option value="${obj.tblevelId }">${obj.tblevel }</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>
	                                <div class="form-group">                            
	                                    <label>是否合作:</label>
	                                    <select id="otherAuthorJoin" class="form-control nullcheck addcheck" >
	                                    	<option selected="selected"></option>
	                                    	<option value="1">是</option>
	                                    	<option value="0">否</option>
	                                    </select>
	                                </div>   
	                                <div class="form-group">                            
	                                    <label>学期:</label>
	                                    <select id="aup_term" class="form-control nullcheck addcheck"  >
	                                    	<option></option>
	                                    	<c:forEach items="${tftermList }" var="obj">
	                                    		<option value="${obj.termId }">${obj.term }</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>  
	                                <div class="form-group" style="display: none" id="crystatus">
	                                	<label>项目人数：</label>
	                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                                                                          已满:<input type="radio"  value="1" class="author checkattr"  name="proJpeople"> 
	                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                                                                          未满:<input type="radio" value="0" class="author checkattr" name="proJpeople">
	                                </div>
	                                <div>
	                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
	                                    <button id="subadds" class="btn  btn-primary pull-left m-t-n-xs subcheck"  type="button">
		                                     <i class="fa fa-check"></i>
		                                     <strong>提交</strong>
	                                    </button>
	                                    <button id="subup" class="btn  btn-primary pull-left m-t-n-xs subcheck"  type="button" style="display: none">
		                                     <i class="fa fa-check"></i>
		                                     <strong>提交</strong>
	                                    </button	>
	                                    <button id="subdel" class="btn  btn-primary pull-left m-t-n-xs"  type="button" style="display: none;margin-left: 30%;">
		                                     <strong>删除</strong>
	                                    </button>
	                               </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div> 
    </div>
    
    <div id="checkmember" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog"     aria-labelledby="myModalLabel">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-body">
	                    <div class="row">
	                            <h3 class="m-t-none m-b" style="margin-left: 41%">查看项目成员</h3>
	                            <hr >
	                                <div class="membertab form-control">
							            <table id="membtab">
							                <thead>
							                    <th>ID</th>
							                    <th>姓名</th>
							                    <th>得分</th>
							                    <th>操作</th>
							                </thead>
							            </table>
        							</div>
	                                <div>
	                                     <button type="button" id="closescorewin" style="margin-top: 10px;" class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
	                                     <button id="subchangescore" style="margin-top: 10px;" class="btn  btn-primary pull-left m-t-n-xs subcheck"  type="button">
		                                     <i class="fa fa-check"></i>
		                                     <strong>提交</strong>
	                                    </button>
	                               </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script  src="js/PublicCheck/PUB_SET.js"></script>
    <script  src="My97DatePicker/WdatePicker.js"></script>
    <!-- ISBN输入控制 -->
    <script src="js/plugins/jasny/jasny-bootstrap.min.js"></script>
    <!-- sweet-alert -->
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
    <script>
    //页面初始化处理
	var limit = getParameters("limit");
	var termId = getParameters("term.termId");
	set_selected_option($('#changelength option'), limit);
	set_selected_option($('#termSelection option'), termId);
	comphref(limit);
	//对所有跳转链接加 limit字段
	function comphref(limits) {
		var hrefs = $('.comphref');
		for(var i=0;i<hrefs.length;i++){
			hrefs[i].href = hrefs[i].href+"&limit="+limits+"&term.termId="+$('#termSelection').val().trim();
		}
	}
    </script>
    <!-- add -project -->
    <script type="text/javascript">
    $('#subadds').click(function() {
		if(checkadds()){
			swal({   
	    		title: "确定提交?",   
	    		text: "",   
	    		type: "warning",   
	    		showCancelButton: true,   
	    		confirmButtonColor: "#DD6B55",   
	    		confirmButtonText: "确定",
	    		cancelButtonText: "取消",   
	    		closeOnConfirm: false,   
	    		closeOnCancel: true }, 
	    			function(isConfirm){   
	    				if (isConfirm) {
	    					$.post("ATteachingtextbookPerformanceSet!addProject",
	    							{"textbookproject.bookName":$('#gainName').val().trim(),
	    							 "textbooklevel.tblevelId":$('#level').val().trim(),
	    							 "textbookproject.cooperator":$('#otherAuthorJoin').val().trim(),
	    							 "term.termId":$('#aup_term').val().trim()},
	    							function(data,status){
	    								 if(status=="success"){
	    									 if(data=="succ"){
	    										 swal("添加成功","","success");
	    	    								 setTimeout(function() {
	    	    									 window.location.replace("ATteachingtextbookPerformanceSet!gainAllProject?pagenum=1");
	    										}, 2000);
	    									 }else{
	    										 swal("成功","","warning");
	    									 }
	    								 }else{
	    									 swal("请求失败");
	    								 }
	    							}
	    					);
	    					
	    				}
	    		});
				
		}else{
				swal("是否还有没填的?","请完善所有信息后提交","warning");
		}
	});
    </script>
    <!-- update  -->
    <script>
    var chargePersonId="";
    $('.carrydata').click(function() {
    	var row = $(this).parent().parent(); 
    	chargePersonId = row[0].cells[6].innerHTML.trim();
    	$('#addmodaldialogTitle').css("display","none");
    	$('#updatemodaldialogTitle').css("display","");
    	$('#subadds').css("display","none");
    	$('#subup').css("display","");
    	$('#crystatus').css("display","");
    	$('#subdel').css("display","");
		$('#projectId').prop("value",row[0].cells[0].innerHTML);
		$('#gainName').prop("value",row[0].cells[1].innerHTML);
		set_selected_option($('#level option'), row[0].cells[3].title.trim());
		set_selected_option($('#otherAuthorJoin option'), row[0].cells[2].title.trim());
		set_selected_option($('#aup_term option'), row[0].cells[5].title.trim());
		$('input[type="radio"][name="proJpeople"][value="'+(row[0].cells[8].title.trim()=="0"?"0":"1")+'"]').prop("checked",true);
		$('input[type="radio"][name="proJpeople"]:checked').prop("value",row[0].cells[8].title.trim());
	});
    $('#subup').click(function() {
    	var projectId = $('#projectId').val().trim();
    	if(checkadds()&&projectId!=""){	
    			swal({   
    	    		title: "确定提交?",   
    	    		text: "",   
    	    		type: "warning",   
    	    		showCancelButton: true,   
    	    		confirmButtonColor: "#DD6B55",   
    	    		confirmButtonText: "确定",
    	    		cancelButtonText: "取消",   
    	    		closeOnConfirm: false,   
    	    		closeOnCancel: true }, 
    	    			function(isConfirm){   
    	    				if (isConfirm) {
    	    					$.post("ATteachingtextbookPerformanceSet!updateProject",
    	    							{"textbookproject.bookId":projectId,
	    	    						 "textbookproject.bookName":$('#gainName').val().trim(),
	    	    						 "textbookproject.chargePersonId":chargePersonId,
		    							 "textbooklevel.tblevelId":$('#level').val().trim(),
		    							 "textbookproject.cooperator":$('#otherAuthorJoin').val().trim(),
		    							 "term.termId":$('#aup_term').val().trim(),
		    							 "textbookproject.checkOut":$('input[type="radio"][name="proJpeople"]:checked').val().trim()},
    	    	    					function(data,status){
    	    	    						if(status=="success"){
    	    	    							 if(data=="succ"){
    	    	    								 swal("更新成功","","success");
    	    	    								 setTimeout(function() {
    	    	    									 window.location.replace("ATteachingtextbookPerformanceSet!gainAllProject?pagenum=1");
    	    										}, 2000);
    	    	    							 }else{
    	    	    								 swal("失败","","error");
    	    	    							 }
    	    	    						}else{
    	    	    							swal("请求失败");
    	    	    						}
    	    	    					}
    	    	    			);
    	    				}
    	    			}
    	    	);
    	}else{
				swal("是否还有没填的?","请完善所有信息后提交","error");
		}
	});
    $('#subdel').click(function() {
    	swal({   
    		title: "确定删除?",   
    		text: "",   
    		type: "warning",   
    		showCancelButton: true,   
    		confirmButtonColor: "#DD6B55",   
    		confirmButtonText: "删除",
    		cancelButtonText: "取消",   
    		closeOnConfirm: false,   
    		closeOnCancel: true }, 
    			function(isConfirm){   
    				if (isConfirm) {
    					$.post("ATteachingtextbookPerformanceSet!deleteProject",
    							{"textbookproject.bookId":$('#projectId').val().trim()},
    							function(data,status){
    								if(status=="success"){
    									if(data=="succ"){
    										swal("删除成功","","success");
    										setTimeout(function() {
    											window.location.replace("ATteachingtextbookPerformanceSet!gainAllProject?pagenum=1");
											}, 2000);
    									}else{
    										swal("操作失败","","error");
    									}
    								}else{
    									swal("请求失败","","error");
    								}
    							}
    					);
    				}
    			}
    	);
	});
    var projectId = "";
    var sumscore = "";
    $('.getMember').click(function() {
		var row = $(this).parent().parent();
		projectId = row[0].cells[0].innerHTML;
		sumscore = row[0].cells[4].innerHTML.trim();
		chargePersonId = row[0].cells[6].innerHTML.trim();
		$.post("ATteachingtextbookPerformanceSet!getMember",
				{"textbookproject.bookId":row[0].cells[0].innerHTML},
				function(data,status){
					var tabs = $('#membtab');
					var trs = tabs.find("tr");
					for(var i=1;i<trs.length;i++){
						trs[i].remove();
					}
					var obj = JSON.parse(data);
					if(status=="success"){
						for(var i=0;i<obj.length;i++){
							tabs.append("<tr>"
									+"<td>"+obj[i].teacherId+"</td>"
									+"<td>"+obj[i].teacherName+"</td>"
									+"<td><input class='add_scores' type='text' style='color:black;height:100%;width:100%;border:none;' value='"+obj[i].spare+"' /></td>"
									+"<td><a class='btn btn-primary btn-sm deletemember'>移除</a> </td>"
									+"</tr>");
						}
						trs = tabs.find("tr");
						for(var i=1;i<trs.length;i++){
			                if(i%2==0){
			                    trs[i].style.backgroundColor = "#e7cdfa";
			                    trs[i].style.color = "#928FA3";
			                }else{
			                    trs[i].style.backgroundColor = "#B5A0C9";
			                    trs[i].style.color = "#F4F4F6";
			                }
			            }
					}else{
						swal("请求失败");
					}
				}
		);
	});
    </script>
    <!-- member-opera -->
    <script type="text/javascript">
    $('#subchangescore').click(function() {
		var trs = $('#membtab tr');
		var jobj = "";
		for(var i=1;i<trs.length;i++){
			jobj += trs[i].cells[0].innerHTML+",";	
			jobj += trs[i].cells[2].firstChild.value+"_";
		}
		$.post("ATteachingtextbookPerformanceSet!changescore",
				{"mixs":jobj,
				 "sumscore":sumscore,
				 "textbookproject.bookId":projectId},
		function(data,status){
			if(status=="success"){
				if(data=="succ"){
					swal("更新成功","success","success");
					setTimeout(function() {
						$('#closescorewin').click();
					}, 2000);
				}else{
					swal("操作失败",data,"error");
				}
			}else{
				swal("请求失败","","error");
			}		
		});
	});
    </script>
    <!-- DELETE MEMBER FROM PROJECT -->
    <script>
    $(document).click(function(e) {
    	if(e.target.className.indexOf("deletemember")>=0){
    		var row  = $(e.target).parent().parent();
    		swal({   
        		title: "确定移除该成员?",   
        		text: "即将移除...",   
        		type: "warning",   
        		showCancelButton: true,   
        		confirmButtonColor: "#DD6B55",   
        		confirmButtonText: "移除",
        		cancelButtonText: "取消",   
        		closeOnConfirm: false,   
        		closeOnCancel: true }, 
        			function(isConfirm){   
        				if (isConfirm&projectId!="") {
        					if(chargePersonId.trim()!=row[0].cells[0].innerHTML.trim()){
	        					$.post("ATteachingtextbookPerformanceSet!quitProject",
										{"textbookproject.bookId":projectId,
	        							 "tfteacher.teacherId":row[0].cells[0].innerHTML.trim()},
										function(data,status){
											 if(status=="success"){
												 if(data=="succ"){
													 swal("移除成功","","success");
													 row.remove();
												 }else{
													 swal("移除错误","","warning");
												 }
											 }else{
												 swal("请求失败","","warning");
											 }
										}
								);
        					}else{
        						swal("ERROR","不能移除项目负责人","error");
        					}
        				}else{
        					swal("操作失败","意外故障...","error");
        				}
        			}
        	);
    	}
    });
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>