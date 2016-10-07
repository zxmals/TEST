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

    <title>ScienceReward --reward-Set</title>
    
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
			<form action="GTscienceresearch-rewardset!getscienceReward?pagenum=1" method="post" name="pickdate" id="pickdates">
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
	                        <h5>科研奖励管理<small></small></h5>
	                        <div class="ibox-tools" >
	                        </div>
	                    </div>
	                    <div class="ibox-content" style="height:540px;">
	                    	 <button class="btn  btn-primary openaddm" type="submit" data-backdrop="true" data-toggle="modal" data-target="#utdialog">
	                        	 <strong>新增科研奖励</strong>
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
										<td>科研奖励Id</td>
										<td>奖励名称</td>
										<td>奖励类型</td>
										<td>奖励级别</td>
										<td>获奖日期</td>
										<td>授奖部门</td>
										<td>获奖人数</td>
										<td>登记负责人Id</td>
										<td>登记负责人</td>
										<td>状态</td>
										<td>操作</td>
									</tr>
	                            </thead>
	                            <tbody>
									<c:forEach var="ebj" items="${sciencerewards }">
										<tr>
											<td>${ebj.srrewardId }</td>
											<td>${ebj.srrewardName }</td>
											<td title="${ebj.rewardType.rewardTypeId }">${ebj.rewardType.rewardTypeName }</td>
											<td title="${ebj.rewardLevel.rewardLevelId }">${ebj.rewardLevel.rewardLevelName }</td>
											<td>${ebj.rewardDate }</td>
											<td>${ebj.awardDepartment }</td>
											<td>${ebj.rewardTotalPeople }</td>
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
													</c:if>
													
													<c:if test="${ebj.checkout==1 }">
														<a  class="btn btn-primary btn-sm openupdatem carrydata" data-toggle="modal" data-target="#utdialog">编辑</a>
														&nbsp;&nbsp;
														<a  class="btn btn-primary btn-sm getMember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>
													</c:if>
													
													<c:if test="${ebj.checkout==3 }">
														<a  class="btn btn-primary btn-sm openupdatem carrydata" data-toggle="modal" data-target="#utdialog">编辑</a>
														&nbsp;&nbsp;
														<a  class="btn btn-primary btn-sm getMember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>
													</c:if>
													<c:if test="${ebj.checkout==2 }">
														<a  class="btn btn-primary btn-sm getMember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>
													</c:if>
												</c:if>
												<c:if test="${sessionScope.teacher.teacherId!=ebj.chargePersonId }">
													<c:if test="${ebj.checkout==0 }">
														<a  class="btn btn-primary btn-sm joinProj" data-toggle="modal">加入</a>
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
	                        	<a class="comphref" href="GTscienceresearch-rewardset!getscienceReward?pagenum=1">首页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTscienceresearch-rewardset!getscienceReward?pagenum=${prepage }">上一页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTscienceresearch-rewardset!getscienceReward?pagenum=${nextpage }">下一页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTscienceresearch-rewardset!getscienceReward?pagenum=${sumpage }">尾页</a>
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
	                            <h3 class="m-t-none m-b" id="addmodaldialogTitle">新增学术著作</h3>
	                            <h3 class="m-t-none m-b" id="updatemodaldialogTitle">修改学术著作</h3>
	                            <hr >
	                            	<div class="form-group" style="display: none">                                
	                                    <label>著作ID:</label>
	                                    <input id="workId" type="text" class="form-control nullcheck upcheck">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>著作名称:</label>
	                                    <input id="workname" type="text"  class="form-control nullcheck addcheck" >
	                                </div>
	                                <div class="form-group">                            
	                                    <label>登记人身份:</label>
	                                    <select class="form-control nullcheck addcheck" Id="isFauthor" >
	                                    	<option></option>
	                                    	<option value="first">第一作者</option>
	                                    	<option value="other">其他作者</option>
	                                    </select>
	                                </div> 
	                                <div class="form-group">                            
	                                    <label>出版社:</label>
	                                    <select id="publishclub" class="form-control nullcheck addcheck" >
	                                    	<option></option>
	                                    	<c:forEach items="${publishclubli }" var="obj">
	                                    		<option value="${obj.publishClubId }">${obj.publishClubName }</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>  
	                                <div class="form-group">                            
	                                    <label>字数:</label>
	                                    <select id="wordnum" class="form-control nullcheck addcheck"  >
	                                    	<option></option>
	                                    	<c:forEach items="${wordnum }" var="obj">
	                                    		<option value="${obj.wordId }">${obj.wordNumber }</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>  
	                                <div class="form-group">                                
	                                    <label>出版日期:</label>
	                                    <input id="publishdate" type="text"  class="form-control nullcheck addcheck" onClick="WdatePicker()" readonly="readonly">
	                                </div>
	                                <div class="form-group">                            
	                                    <label>是否有其他作者参与:</label>
	                                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                    	是:<input type="radio"  value="1" class="author checkattr"  name="otherAuthorJoin"> 
	                                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                    	否:<input type="radio" value="0" class="author checkattr" name="otherAuthorJoin">
	                                </div>
	                                <div class="form-group" id="hideisbn">
	                                    <label>著作ISBN：</label>
	                                    ISBN-10:<input type="radio"  class="isbncontrol checkattr" value="10"  name="ISBN"> &nbsp;&nbsp;ISBN-13:<input type="radio" class="isbncontrol checkattr"  value="13" name="ISBN">
	                                </div>
	                                <div class="form-group" style="display: none" id="isbn13">
	                                    <input type="text" class="form-control ISBN" data-mask="999-9-999-99999-9" placeholder="">
	                                    <span class="help-block">999-9-999-99999-9</span>
	                                </div>
	                                <div class="form-group" style="display: none" id="isbn10">
	                                    <input type="text" class="form-control ISBN" data-mask="9-999-99999-9" placeholder="">
	                                    <span class="help-block">9-999-99999-9</span>
	                                </div>
	                                <!-- 更新时所用的ISBN -->
	                                <div class="form-group" style="display: none" id="cryisbn">
	                                	<label>著作ISBN：</label>
	                                    <input type="text" class="form-control nullcheck" id="upIsbn">
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
	    
	    <div id="joinaca" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog"     aria-labelledby="myModalLabel">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-body">
	                    <div class="row">
	                    			<div class="form-group" style="display: none">                                
	                                    <label>著作ID:</label>
	                                    <input id="joinacaId" type="text"  class="form-control nullcheck">
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
		window.location.replace("GTacademicwork-workset!getWorkall?pagenum=1&limit="+$(this).val().trim()+"&foredate="+$('#date1').val().trim()+"&afterdate="+$('#date2').val().trim());
	});
    $('.isbncontrol').click(function() {
		var vals = $(this).val().trim();
		if(vals=="10"){
			$('#isbn10').css("display","");
			$('#isbn13')[0].firstElementChild.value = "";
			$('#isbn13').css("display","none");
		}
		if(vals=="13"){
			$('#isbn10').css("display","none");
			$('#isbn10')[0].firstElementChild.value = "";
			$('#isbn13').css("display","");
		}
	});
    $('.openaddm').click(function() {
    	$('#addmodaldialogTitle').css("display","");
    	$('#updatemodaldialogTitle').css("display","none");
    	$('#subadds').css("display","");
    	$('#subup').css("display","none");
    	$('#cryisbn').css("display","none");
    	$('#hideisbn').css("display","");
    	$('#crystatus').css("display","none");
    	$('#subdel').css("display","none");
	});
    $('#subadds').click(function() {
    	var firstauthor = $('#isFauthor').val().trim()!="first"?"":"${sessionScope.teacher.teacherId }";
    	var isbn = $('.ISBN').get(0).value.trim()==""?$('.ISBN').get(1).value.trim():$('.ISBN').get(0).value.trim();
    	var author = $('.author').get(0).checked==false?($('.author').get(1).checked==true?$('.author').get(1).value.trim():""):$('.author').get(0).value.trim();
		if(checkadds()&&author!=""&&isbn!=""){
			if(checkISBN(isbn)){
				$.post("GTacademicwork-workset!addAcademicWork?pagenum=1",
						{"academicwk.workName":$('#workname').val().trim(),
						 "academicwk.firstAuthor":firstauthor,
						 "academicwk.publishClub.publishClubId":$('#publishclub').val().trim(),
						 "academicwk.wordsNumber.wordId":$('#wordnum').val().trim(),
						 "academicwk.publishDate":$('#publishdate').val().trim(),
						 "academicwk.otherAuthorJoin":author,
						 "academicwk.isbn":isbn},
						function(data,status){
							 if(status=="success"){
								 if(data=="succ"){
									 swal("添加成功","","success");
    								 setTimeout(function() {
    									 window.location.replace("GTacademicwork-workset!getWorkall?pagenum=1");
									}, 2000);
								 }else{
									 swal("新增失败");
								 }
							 }else{
								 swal("请求失败");
							 }
						}
				);
	    	}else{
	    		swal("ISBN ["+isbn+"] 错误","请完善所有信息后提交","warning");
	    	}
		}else{
				swal("是否还有没填的?","请完善所有信息后提交","warning");
		}
	});
    
    $('.carrydata').click(function() {
    	var row = $(this).parent().parent(); 
    	$('#addmodaldialogTitle').css("display","none");
    	$('#updatemodaldialogTitle').css("display","");
    	$('#cryisbn').css("display","");
    	$('#hideisbn').css("display","none");
    	$('#subadds').css("display","none");
    	$('#subup').css("display","");
    	$('#crystatus').css("display","");
    	$('#subdel').css("display","");
		$('#workId').prop("value",row[0].cells[0].innerHTML);
		$('#workname').prop("value",row[0].cells[1].innerHTML);
		set_selected_option($('#isFauthor option'), row[0].cells[2].title.trim()=="${teacher.teacherId}"?"first":"other");
		set_selected_option($('#publishclub option'), row[0].cells[3].innerHTML.trim());
		set_selected_option($('#wordnum option'), row[0].cells[5].innerHTML.trim());
		$('#publishdate').prop("value",row[0].cells[7].innerHTML.trim());
		$('#upIsbn').prop("value",row[0].cells[8].innerHTML.trim());
		$('input[type="radio"][name="otherAuthorJoin"][value="'+row[0].cells[9].title.trim()+'"]').prop("checked",true);
		$('input[type="radio"][name="proJpeople"][value="'+(row[0].cells[12].title.trim()=="0"?"0":"1")+'"]').prop("checked",true);
		$('input[type="radio"][name="proJpeople"]:checked').prop("value",row[0].cells[12].title.trim());
	});
    $('#subup').click(function() {
    	var firstauthor = $('#isFauthor').val().trim()!="first"?"":"${sessionScope.teacher.teacherId }";
    	var isbn = $('#upIsbn').val().trim();
    	var author = $('.author').get(0).checked==false?($('.author').get(1).checked==true?$('.author').get(1).value.trim():""):$('.author').get(0).value.trim();
    	if(checkadds()&&isbn!=""){
    		if(checkISBN(isbn)){
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
    	    					$.post("GTacademicwork-workset!updateAcademicWork?pagenum=1",
    	    						 	{"academicwk.acaworkId":$('#workId').val().trim(),
    	    	    					 "academicwk.workName":$('#workname').val().trim(),
    	    							 "academicwk.firstAuthor":firstauthor,
    	    							 "academicwk.publishClub.publishClubId":$('#publishclub').val().trim(),
    	    							 "academicwk.wordsNumber.wordId":$('#wordnum').val().trim(),
    	    							 "academicwk.publishDate":$('#publishdate').val().trim(),
    	    							 "academicwk.otherAuthorJoin":author,
    	    							 "academicwk.checkout":$('input[type="radio"][name="proJpeople"]:checked').val().trim(),
    	    							 "academicwk.isbn":isbn},
    	    	    					function(data,status){
    	    	    						if(status=="success"){
    	    	    							 if(data=="succ"){
    	    	    								 swal("更新成功","","success");
    	    	    								 setTimeout(function() {
    	    	    									 window.location.replace("GTacademicwork-workset!getWorkall?pagenum=1");
    	    										}, 2000);
    	    	    							 }else{
    	    	    								 swal("操作失败","","error");
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
    			swal("ISBN ["+isbn+"] 错误","请完善所有信息后提交","error");
    		}
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
    					$.post("GTacademicwork-workset!deleteAcademicWork?pagenum=1",
    							{"academicwk.acaworkId":$('#workId').val().trim()},
    							function(data,status){
    								if(status=="success"){
    									if(data=="succ"){
    										swal("删除成功","","success");
    										setTimeout(function() {
    											window.location.replace("GTacademicwork-workset!getWorkall?pagenum=1");
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
		$.post("GTacademicwork-workset!getMember",
				{"academicwk.acaworkId":row[0].cells[0].innerHTML},
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
    $('.joinProj').click(function(e) {
    	var btn = $(this);
	    if(btn.attr("isConfirm")!="1"){
		    btn.removeAttr("data-target");
	    }else{
	    	btn.removeAttr("isConfirm");
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
					btn.attr("data-target","#joinaca");
					$('#joinacaId').prop("value",btn.parent().parent()[0].cells[0].innerHTML.trim());
					btn.click();
    			}
    		});
	});
    $('#subjoin').click(function() {
    	if($('#joinacaId').val().trim()!=""&&$('#joinIdentity').val().trim()!=""){
    		$.post("GTacademicwork-workset!joinwork",
    				{"academicwk.acaworkId":$('#joinacaId').val().trim(),
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
    	}
	});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>