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

    <title>MainacademicMeeting --meeting-Set</title>
    
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
	<div class="datepick">
		<span>选择日期范围</span>
		<div>
			<form action="GTundertakeacademicmeet-meetset!gainAllacademicm?pagenum=1" method="post" name="pickdate" id="pickdates">
				从:<input type="text" id="date1" class="Wdate" onClick="WdatePicker()"  value="${foredate }" name="foredate" />到:<input type="text" id="date2" onClick="WdatePicker()" class="Wdate"  value="${afterdate }" name="afterdate" />
				&nbsp;&nbsp;<input type="submit" id="datep" value="查寻" title="点击查询" >
			</form>
		</div>
	</div>
	<div class="chooseact">
    <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
	            <div class="col-sm-12">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>学术会议管理<small></small></h5>
	                        <div class="ibox-tools" >
	                        </div>
	                    </div>
	                    <div class="ibox-content" style="height:540px;">
	                    	 <button class="btn  btn-primary openaddm" type="submit" data-backdrop="true" data-toggle="modal" data-target="#utdialog">
	                        	 <strong>新增学术会议</strong>
	                         </button><br><br>
	                    <div>
	                    	<a>每页   
	                    	<select id="changelength" style="width:45px">
	                    		<option selected="selected"></option>
	                    		<option>2</option>
	                    		<option>1</option>
	                    		<option>5</option>
	                    		<option>10</option>
	                    		<option>15</option>
	                    		<option>20</option>
	                    	</select>  条记录</a>
	                    </div>
	                    <br>
	                        <div class="example">
	                        <form method="post" name="f">
	                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example">
	                            <thead>
	                                <tr>
										<td>学术会议Id</td>
										<td>会议名称</td>
										<td>会议类型</td>
										<td>会议地点</td>
										<td>会议日期</td>
										<td>登记负责人Id</td>
										<td>登记负责人</td>
										<td>状态</td>
										<td>操作</td>
									</tr>
	                            </thead>
	                            <tbody>
									<c:forEach var="ebj" items="${undertakemeeting }">
										<tr>
											<td>${ebj.acaMeetingId }</td>
											<td>${ebj.acaMeetingName }</td>
											<td title="${ebj.mainUndertakeAcademicMeetingType.acaMeetTypeId }">${ebj.mainUndertakeAcademicMeetingType.acaMeetType }</td>
											<td title="${ebj.mainUndertakeAcademicMeetingPlace.acaMeetPlaceId }">${ebj.mainUndertakeAcademicMeetingPlace.acaMeetPlace }</td>
											<td>${ebj.meetingdate }</td>
											<td>${ebj.chargePersonId }</td>
											<td>${teachermp[ebj.chargePersonId] }</td>
											<td title="${ebj.checkout }">
												<c:if test="${ebj.checkout==0 }">待完善</c:if>
												<c:if test="${ebj.checkout==1 }">已完善,待审核</c:if>
												<c:if test="${ebj.checkout==2 }">已审核</c:if>
												<c:if test="${ebj.checkout==3 }">未通过</c:if>
											</td>
											<td>
												<c:if test="${sessionScope.teacher.teacherId==ebj.chargePersonId }">
													<c:if test="${ebj.checkout==0 }">
														<a  class="btn btn-primary btn-sm openupdatem carrydata" data-toggle="modal" data-target="#utdialog">编辑</a>
														&nbsp;&nbsp;
														<a  class="btn btn-primary btn-sm getMember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>
														&nbsp;&nbsp;
													</c:if>
													
													<c:if test="${ebj.checkout==1 }">
														<a  class="btn btn-primary btn-sm openupdatem carrydata" data-toggle="modal" data-target="#utdialog">编辑</a>
														&nbsp;&nbsp;
														<a  class="btn btn-primary btn-sm getMember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>
														&nbsp;&nbsp;
													</c:if>
													
													<c:if test="${ebj.checkout==3 }">
														<a  class="btn btn-primary btn-sm openupdatem carrydata" data-toggle="modal" data-target="#utdialog">编辑</a>
														&nbsp;&nbsp;
														<a  class="btn btn-primary btn-sm getMember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>
														&nbsp;&nbsp;
													</c:if>
													<c:if test="${ebj.checkout==2 }">
														<a  class="btn btn-primary btn-sm getMember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>
														&nbsp;&nbsp;
													</c:if>
												</c:if>
												<c:if test="${sessionScope.teacher.teacherId!=ebj.chargePersonId }">
													<c:if test="${ebj.checkout==0 }">
														<a  class="btn btn-primary btn-sm openaddm joinProj" data-toggle="modal">加入</a>
													</c:if>
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
	                        	<a class="comphref" href="GTundertakeacademicmeet-meetset!gainAllacademicm?pagenum=1">首页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTundertakeacademicmeet-meetset!gainAllacademicm?pagenum=${prepage }">上一页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTundertakeacademicmeet-meetset!gainAllacademicm?pagenum=${nextpage }">下一页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTundertakeacademicmeet-meetset!gainAllacademicm?pagenum=${sumpage }">尾页</a>
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
	                            <h3 class="m-t-none m-b" id="addmodaldialogTitle">新增学术会议</h3>
	                            <h3 class="m-t-none m-b" id="updatemodaldialogTitle">修改学术会议</h3>
	                            <hr >
	                            	<div class="form-group" style="display: none">                                
	                                    <label>学术会议Id:</label>
	                                    <input id="academicmeetingId" type="text" class="form-control nullcheck">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>会议名称:</label>
	                                    <input id="academicmeetingname" type="text"  class="form-control nullcheck addcheck" >
	                                </div>
	                                <div class="form-group">                            
	                                    <label>会议地点:</label>
	                                    <select id="meetingplace" class="form-control nullcheck addcheck" >
	                                    	<option></option>
	                                    	<c:forEach items="${meetingplace }" var="obj">
	                                    		<option value="${obj.acaMeetPlaceId }">${obj.acaMeetPlace }</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>  
	                                <div class="form-group">                            
	                                    <label>会议类型:</label>
	                                    <select id="meetingtype" class="form-control nullcheck addcheck"  >
	                                    	<option></option>
	                                    	<c:forEach items="${meetingtype }" var="obj">
	                                    		<option value="${obj.acaMeetTypeId }">${obj.acaMeetType }</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>  
	                                <div class="form-group">                                
	                                    <label>会议日期:</label>
	                                    <input id="meetingDate" type="text"  class="form-control nullcheck addcheck" onClick="WdatePicker()" readonly="readonly">
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
							                    <th>备注</th>
							                </thead>
							            </table>
        							</div>
	                                <div>
	                                    <button type="button"   class="btn btn-outline btn-primary m-t-n-xs" style="margin-top: 10px;margin-left: 43%" data-dismiss="modal">关闭</button>
	                               </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	    <div id="joinacam" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog"     aria-labelledby="myModalLabel">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-body">
	                    <div class="row">
	                    			<div class="form-group" style="display: none">                                
	                                    <label>著作ID:</label>
	                                    <input id="joinacamId" type="text"  class="form-control nullcheck">
	                                </div>
	                    			<div class="form-group">                            
	                                    <label>参与身份:</label>
	                                    <select id="joinIdentity" class="form-control nullcheck">
	                                    	<option></option>
	                                    	<c:forEach items="${selfdown }" var="obj">
	                                    		<option value="${obj.undertakeTaskId }">${obj.undertakeTaskName }</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div> 
	                            	<div>
	                                    <button type="button" id="closebtn"  class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
	                                    <button id="subjoin" class="btn  btn-primary pull-left m-t-n-xs subcheck"  type="button">
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
	comphref(limit);
	set_selected_option($('#changelength option'), limit);
	//对所有跳转链接加 limit字段
	function comphref(limits) {
		var hrefs = $('.comphref');
		for(var i=0;i<hrefs.length;i++){
			hrefs[i].href = hrefs[i].href+"&limit="+limits+"&foredate="+$('#date1').val().trim()+"&afterdate="+$('#date2').val().trim();
		}
		$('#pickdates')[0].action = $('#pickdates')[0].action+"&limit="+limits;
	}
	//变更每页显示记录数
    $('#changelength').change(function() {
    	comphref($(this).val().trim());
		window.location.replace("GTundertakeacademicmeet-meetset!gainAllacademicm?pagenum=1&limit="+$(this).val().trim()+"&foredate="+$('#date1').val().trim()+"&afterdate="+$('#date2').val().trim());
	});
    $('.openaddm').click(function() {
    	$('#addmodaldialogTitle').css("display","");
    	$('#updatemodaldialogTitle').css("display","none");
    	$('#subadds').css("display","");
    	$('#subup').css("display","none");
    	$('#addhelp').css("display","");
    	$('#crystatus').css("display","none");
    	$('#subdel').css("display","none");
	});
    //adds 
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
	    					$.post("GTundertakeacademicmeet-meetset!addUndertakemeeting",
	    							{"undertakemeet.acaMeetingName":$('#academicmeetingname').val().trim(),
	    							 "undertakemeet.meetingdate":$('#meetingDate').val().trim(),
	    							 "meetplace.acaMeetPlaceId":$('#meetingplace').val().trim(),
	    							 "meettype.acaMeetTypeId":$('#meetingtype').val().trim()},
	    							function(data,status){
	    								 if(status=="success"){
	    									 if(data=="succ"){
	    										 swal("添加成功","","success");
	    	    								 setTimeout(function() {
	    	    									 window.location.replace("GTundertakeacademicmeet-meetset!gainAllacademicm?pagenum=1");
	    										}, 2000);
	    									 }else{
	    										 swal(data,"","warning");
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
    $('.carrydata').click(function() {
    	//通用
    	var row = $(this).parent().parent(); 
    	$('#addmodaldialogTitle').css("display","none");
    	$('#updatemodaldialogTitle').css("display","");
    	$('#subadds').css("display","none");
    	$('#subup').css("display","");
    	$('#crystatus').css("display","");
    	$('#subdel').css("display","");
    	//个别
		$('#academicmeetingId').prop("value",row[0].cells[0].innerHTML);
		$('#academicmeetingname').prop("value",row[0].cells[1].innerHTML);
		set_selected_option($('#meetingplace option'), row[0].cells[3].title.trim());
		set_selected_option($('#meetingtype option'), row[0].cells[2].title.trim());
		$('#meetingDate').prop("value",row[0].cells[4].innerHTML.trim());
		$('input[type="radio"][name="proJpeople"][value="'+(row[0].cells[7].title.trim()=="0"?"0":"1")+'"]').prop("checked",true);
		$('input[type="radio"][name="proJpeople"]:checked').prop("value",row[0].cells[7].title.trim());
	});
    $('#subup').click(function() {
    	var acameetingID = $('#academicmeetingId').val().trim();
    	if(checkadds()&&acameetingID!=""){	
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
    	    					$.post("GTundertakeacademicmeet-meetset!updateUnderacademic",
    	    							{"undertakemeet.acaMeetingId":acameetingID,
	    	    						 "undertakemeet.acaMeetingName":$('#academicmeetingname').val().trim(),
		    							 "undertakemeet.meetingdate":$('#meetingDate').val().trim(),
		    							 "meetplace.acaMeetPlaceId":$('#meetingplace').val().trim(),
		    							 "meettype.acaMeetTypeId":$('#meetingtype').val().trim(),
		    							 "undertakemeet.checkout":$('input[type="radio"][name="proJpeople"]:checked').val().trim()},
    	    	    					function(data,status){
    	    	    						if(status=="success"){
    	    	    							 if(data=="succ"){
    	    	    								 swal("更新成功","","success");
    	    	    								 setTimeout(function() {
    	    	    									 window.location.replace("GTundertakeacademicmeet-meetset!gainAllacademicm?pagenum=1");
    	    										}, 2000);
    	    	    							 }else{
    	    	    								 swal(data,"","error");
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
				swal("是否还有没填的?","请完善所有信息后提交","warning");
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
    					$.post("GTundertakeacademicmeet-meetset!deleteAcademicm",
    							{"undertakemeet.acaMeetingId":$('#academicmeetingId').val().trim()},
    							function(data,status){
    								if(status=="success"){
    									if(data=="succ"){
    										swal("删除成功","","success");
    										setTimeout(function() {
    											window.location.replace("GTundertakeacademicmeet-meetset!gainAllacademicm?pagenum=1");
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
    $('.getMember').click(function() {
		var row = $(this).parent().parent();
		$.post("GTundertakeacademicmeet-meetset!getMember",
				{"undertakemeet.acaMeetingId":row[0].cells[0].innerHTML},
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
									+"<td> </td>"
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
    <!-- join operate -->
    <script>
    $('.joinProj').click(function(e) {
    	var btn = $(this);
	    if(btn.attr("isConfirm")!="1"){
		    btn.removeAttr("data-target");
	    }else{
	    	btn.removeAttr("isConfirm");
	    	$('#joinacamId').prop("value",btn.parent().parent()[0].cells[0].innerHTML.trim());
	    }
		swal({
			title: "确定加入?",   
    		text: "",   
    		type: "warning",   
    		showCancelButton: true,   
    		confirmButtonColor: "#DD6B55",   
    		confirmButtonText: "确定",
    		cancelButtonText: "取消",   
    		closeOnConfirm: true,   
    		closeOnCancel: true },
    		function(isConfirm){
    			if(isConfirm){
    				btn.attr("isConfirm","1");
					btn.attr("data-target","#joinacam");
					btn.click();
    			}
    		});
	});
    $('#subjoin').click(function() {
    	if($('#joinIdentity').val().trim()!=""){
    		$.post("GTundertakeacademicmeet-meetset!joinmainacam",
    				{"undertakemeet.acaMeetingId":$('#joinacamId').val().trim(),
    				 "selftask.undertakeTaskId":$('#joinIdentity').val().trim()},
    				function(data,status){
        				 if(status=="success"){
        					 if(data=="succ"){
        						 swal("加入成功","","success");
        					 }else{
        						 swal(data,"","warning");
        					 }
        					 $('#closebtn').click();
        				 }else{
        					 swal("请求失败","","error");
        				 }
    				}
    		);
    	}else{
    		swal("是否还有空的？","","warning");
    	}
	});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>