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

    <title>AcademicWork --work-Set</title>
    
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
	<div class="datepick">
		<span>选择日期范围</span>
		<div>
			<form action="GTacademicwork-workset!getWorkall?pagenum=1" method="post" name="pickdate" id="pickdates">
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
	                        <h5>个人参刊论文管理<small></small></h5>
	                        <div class="ibox-tools" >
	                        </div>
	                    </div>
	                    <div class="ibox-content" style="height:540px;">
	                    	 <button class="btn  btn-primary openaddm" type="submit" data-backdrop="true" data-toggle="modal" data-target="#utdialog">
	                        	 <strong>新增学术著作</strong>
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
										<td>学术著作Id</td>
										<td>著作名称</td>
										<td>第一作者</td>
										<td style="display: none">出版社Id</td>
										<td>出版社</td>
										<td style="display: none">字数Id</td>
										<td>字数</td>
										<td>出版日期</td>
										<td>ISBN</td>
										<td>是否有其他作者参与</td>
										<td>登记负责人Id</td>
										<td>登记负责人</td>
										<td>状态</td>
										<td>操作</td>
									</tr>
	                            </thead>
	                            <tbody>
									<c:forEach var="ebj" items="${academicwk }">
										<tr>
											<td>${ebj.acaworkId }</td>
											<td>${ebj.workName }</td>
											<td>${teachermp[ebj.firstAuthor] }</td>
											<td style="display: none">${ebj.publishClub.publishClubId }</td>
											<td>${ebj.publishClub.publishClubName }</td>
											<td style="display: none">${ebj.wordsNumber.wordId }</td>
											<td>${ebj.wordsNumber.wordNumber }</td>
											<td>${ebj.publishDate }</td>
											<td>${ebj.isbn }</td>
											<td>
												<c:if test="${ebj.otherAuthorJoin==1 }">是</c:if>
												<c:if test="${ebj.otherAuthorJoin==0 }">否</c:if>
											</td>
											<td>${ebj.chargePersonId }</td>
											<td>${teachermp[ebj.chargePersonId] }</td>
											<td>
												<c:if test="${ebj.checkout==0 }">待完善</c:if>
												<c:if test="${ebj.checkout==1 }">已完善,待审核</c:if>
												<c:if test="${ebj.checkout==2 }">已审核</c:if>
												<c:if test="${ebj.checkout==3 }">未通过</c:if>
											</td>
											<td>
												<c:if test="${sessionScope.teacher.teacherId==ebj.chargePersonId }">
													<c:if test="${ebj.checkout==0 }">
														<a  class="btn btn-primary btn-sm carrydata" data-toggle="modal" data-target="#update">编辑</a>
														&nbsp;&nbsp;
														<a  class="btn btn-primary btn-sm">查看项目成员</a>
													</c:if>
													
													<c:if test="${ebj.checkout==1 }">
														<a  class="btn btn-primary btn-sm carrydata" data-toggle="modal" data-target="#update">编辑</a>
														&nbsp;&nbsp;
														<a  class="btn btn-primary btn-sm">查看项目成员</a>
													</c:if>
													
													<c:if test="${ebj.checkout==3 }">
														<a  class="btn btn-primary btn-sm carrydata" data-toggle="modal" data-target="#update">编辑</a>
														&nbsp;&nbsp;
														<a  class="btn btn-primary btn-sm">查看项目成员</a>
													</c:if>
													<c:if test="${ebj.checkout==2 }">
														<a  class="btn btn-primary btn-sm">查看项目成员</a>
													</c:if>
												</c:if>
												<c:if test="${sessionScope.teacher.teacherId!=ebj.chargePersonId }">
													<c:if test="${ebj.checkout==0 }">
														<a  class="btn btn-primary btn-sm">加入</a>
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
	                        	<a class="comphref" href="GTacademicwork-workset!getWorkall?pagenum=1">首页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTacademicwork-workset!getWorkall?pagenum=${prepage }">上一页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTacademicwork-workset!getWorkall?pagenum=${nextpage }">下一页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTacademicwork-workset!getWorkall?pagenum=${sumpage }">尾页</a>
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
	                            <h3 class="m-t-none m-b">新增学术著作</h3>
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
	                                    	是:<input type="radio"  value="1" class="author checkattr"  > 
	                                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                    	否:<input type="radio" value="0" class="author checkattr" >
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
	                                    <input type="text" class="form-control" >
	                                </div>
	                                <div>
	                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
	                                    <button id="subadds" class="btn  btn-primary pull-left m-t-n-xs subcheck"  type="button">
	                                     <i class="fa fa-check"></i>
	                                    <strong>提交</strong>
	                                    </button	>
	                               </div>
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
									 alert("添加成功");
									 window.location.replace("GTacademicwork-workset!getWorkall?pagenum=1");
								 }else{
									 alert("新增失败");
								 }
							 }else{
								 alert("请求失败");
							 }
						}
				);
	    	}else{
	    		alert("ISBN  ["+isbn+"]  错误");
	    	}
		}else{
				alert(" 是否还有没填的 ？");
		}
	});
    
    $('.carrydata').click(function() {
		$('')
	});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>