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

    <title>PeriodicalPaper --paper-Set</title>
    
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
			<form action="GTperiodicalpaper-paperset!getPeriodicalPaperINF" method="post" name="pickdate">
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
	                        <h5>期刊论文管理<small></small></h5>
	                        <div class="ibox-tools">
	                        </div>
	                    </div>
	                    <div class="ibox-content">
	                    
	                    <div class="">
	                         <button class="btn  btn-primary openaddm" type="submit" data-backdrop="true" data-toggle="modal" data-target="#add">
	                         <strong>新增期刊论文</strong>
	                         </button>
	                         <button class="btn  btn-primary addrows" style="margin-left: 90%;" type="submit">
	                         <strong>数据 +100</strong>
	                         </button>
	                    </div>
	                        <div class="example">
	                        <form method="post" name="f">
	                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example">
	                            <thead>
	                                <tr>
	                                	<td>序号</td>
										<td>期刊论文Id</td>
										<td>期刊论文标题</td>
										<td>期刊</td>
										<td>年</td>
										<td>卷</td>
										<td>期</td>
										<td class="addws">论文概述</td>
										<td>登记负责人ID</td>
										<td>登记负责人</td>
										<td>状态</td>
										<td>操作</td>
									</tr>
	                            </thead>
	                            <tbody>
									<c:forEach var="ebj"  items="${periodicalpaperli }">
									<tr>
										<td></td>
										<td>${ebj.ppid }</td>
										<td>${ebj.thesisTitle }</td>
										<td style="display: none">${ebj.periodicalId }</td>
										<td>${periodicalli[ebj.periodicalId] }</td>
										<td>${ebj.year }</td>
										<td>${ebj.file }</td>
										<td>${ebj.phase }</td>
										<td class="describe" title="${ebj.describe }"></td>
										<td>${ebj.chargePersonId }</td>
										<td>${teachermp[ebj.chargePersonId] }</td>
										<td>
											<c:if test="${ebj.checkout==0 }">待完善</c:if>
											<c:if test="${ebj.checkout==1 }">已完善,待审核</c:if>
											<c:if test="${ebj.checkout==2 }">已审核</c:if>
											<c:if test="${ebj.checkout==3 }">审核未通过</c:if>
										</td>
										<td>
											<c:if test="${ebj.chargePersonId==teacher.teacherId}">
												<a  class="btn btn-primary btn-sm carrydata" data-toggle="modal" data-target="#update">编辑</a>
											</c:if>
											<c:if test="${ebj.chargePersonId==teacher.teacherId}">
												<a  class="btn btn-primary btn-sm searchmember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>
											</c:if>
											<c:if test="${ebj.chargePersonId!=teacher.teacherId}">
												<c:if test="${ebj.checkout==0 }"><a  class="btn btn-primary btn-sm join">加入</a></c:if>
												<c:if test="${ebj.checkout==1 }"><a  class="btn btn-primary btn-sm" style="background-color: #999999">加入</a></c:if>
											</c:if>
										</td>
										<td style="display: none">${ebj.firstAuthor }</td>
										<td style="display: none">${ebj.secondAuthor }</td>
										<td style="display: none">${ebj.periodicalPid }</td>
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
	                            	<div class="form-group" style="display: none">                                
	                                    <label>论文标题ID:</label>
	                                    <input id="upppId" type="text"  class="form-control nullcheck upcheck" name=periopaper.  value="">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>论文标题:</label>
	                                    <input id="upppt" type="text"  class="form-control nullcheck upcheck" name=periopaper.thesisTitle  value="">
	                                </div>
<!-- 	                                <div class="form-group">                             -->
<!-- 	                                    <label>登记人身份:</label> -->
<!-- 	                                    <select id="upppI" class="form-control nullcheck upcheck" name="author"> -->
<!-- 	                                    	<option></option> -->
<!-- 	                                    	<option value="first">第一作者</option> -->
<!-- 	                                    	<option value="second">第二作者</option> -->
<!-- 	                                    </select> -->
<!-- 	                                </div>  -->
	                                <div class="form-group">                            
	                                    <label>期刊:</label>
	                                    <select id="upp" class="form-control nullcheck upcheck" name=periopaper.periodical.periodicalId >
	                                    	<option></option>
	                                    	<c:forEach items="${periodicalli }" var="obj">
	                                    		<option value="${obj.key }">${obj.value }</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>  
	                                <div class="form-group">                                
	                                    <label>年:</label>
	                                    <input id="upppy" type="text"  class="form-control nullcheck upcheck" onClick="WdatePicker()" name=periopaper.year value="" readonly="readonly">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>卷:</label>
	                                    <input id="upppf" type="text"  class="form-control nullcheck upcheck" name=periopaper.file value="">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>期:</label>
	                                    <input type="text" id="uppph" class="form-control nullcheck upcheck" name=periopaper.phase value="">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>论文概述:</label>
	                                    <textarea  type="text" id="upppde" class="form-control nullcheck upcheck" name=periopaper.describe  value=""></textarea>
	                                </div>
	                                <div class="form-group">                                
	                                    <label>项目人数:</label>
	                                    <select id="upppcheck" class="form-control nullcheck upcheck" name="checkout">
	                                    	<option></option>
	                                    	<option value="1">人数已满</option>
	                                    	<option value="0">人数未满</option>
	                                    </select>
	                                </div>  
	                                <div class="form-group" style="display: none">                                
	                                    <label>第一作者:</label>
	                                    <textarea  type="text" id="upppfirst" class="form-control nullcheck " name=periopaper.describe  value=""></textarea>
	                                </div>
	                                <div class="form-group" style="display: none">                                
	                                    <label>第二作者:</label>
	                                    <textarea  type="text" id="upppsecond" class="form-control nullcheck " name=periopaper.describe  value=""></textarea>
	                                </div>
	                                <div class="form-group" style="display: none">                                
	                                    <label>期刊论文Idano:</label>
	                                    <textarea  type="text" id="upppptid" class="form-control nullcheck upcheck" name=periopaper.describe  value=""></textarea>
	                                </div>                                                       
	                                <div>
	                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
	                                    <button type="button"  id="subdel"  class="btn btn-primary meddle m-t-n-xs" style="margin-left: 28%">删除</button>
	                                    <button id="subupdate"  class="btn  btn-primary pull-left m-t-n-xs subcheck"  type="submit">
	                                     <i class="fa fa-check"></i>
	                                    <strong>保存</strong>
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
	                            <h3 class="m-t-none m-b">添加期刊论文</h3>
	                            <hr >
	                            <form role="form" id="onlyForm" name="adds" action="GTperiodicalpaper-paperset!addPeriodicalPaper" method="post">                            
	                                <div class="form-group">                                
	                                    <label>论文标题:</label>
	                                    <input id="addinf" type="text"  class="form-control nullcheck addcheck" name=periopaper.thesisTitle  value="">
	                                </div>
	                                <div class="form-group">                            
	                                    <label>登记人身份:</label>
	                                    <select class="form-control nullcheck addcheck" name="author" >
	                                    	<option></option>
	                                    	<option value="first">第一作者</option>
	                                    	<option value="second">第二作者</option>
	                                    </select>
	                                </div> 
	                                <div class="form-group">                            
	                                    <label>期刊:</label>
	                                    <select class="form-control nullcheck addcheck" name=periopaper.periodical.periodicalId >
	                                    	<option></option>
	                                    	<c:forEach items="${periodicalli }" var="obj">
	                                    		<option value="${obj.key }">${obj.value }</option>
	                                    	</c:forEach>
	                                    </select>
	                                </div>  
	                                <div class="form-group">                                
	                                    <label>年:</label>
	                                    <input  type="text" name=periopaper.year class="form-control nullcheck addcheck" onClick="WdatePicker()" readonly="readonly">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>卷:</label>
	                                    <input  type="text" name=periopaper.file class="form-control nullcheck addcheck"  value="">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>期:</label>
	                                    <input type="text" name=periopaper.phase class="form-control nullcheck addcheck" value="">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>论文概述:</label>
	                                    <textarea  type="text" name=periopaper.describe  class="form-control nullcheck addcheck" value=""></textarea>
	                                </div>                                               
	                            </form>
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
	    
    </div>
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <!-- sweet-alert -->
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
    <script  src="js/PublicCheck/PUB_SET.js"></script>
    <script  src="My97DatePicker/WdatePicker.js"></script>
    <script>
    $('#subadds').click(function() {
    	if(checkadds()){
    		document.adds.submit();
    	}else{
    		alert("是不是还有没填的？");
    	}
	});
    
    //事件监听处理
    $('#tb').click(function(e) {
		if(e.target.className.indexOf("carrydata")>=0){
			initoper();
			var row  = $(e.target).parent().parent();
			$('#upppId')[0].value = row[0].cells[1].innerHTML;
			$('#upppt')[0].value = row[0].cells[2].innerHTML;
			//select upppI
			set_selected_option($('#upp option'),row[0].cells[3].innerHTML);
			$('#upppy')[0].value = row[0].cells[5].innerHTML;
			$('#upppf')[0].value = row[0].cells[6].innerHTML;
			$('#uppph')[0].value = row[0].cells[7].innerHTML;
			$('#upppde')[0].value = row[0].cells[8].title;
			$('#upppfirst')[0].value = row[0].cells[13].innerHTML;
			$('#upppsecond')[0].value = row[0].cells[14].innerHTML;
			$('#upppptid')[0].value = row[0].cells[15].innerHTML;
			var status = row[0].cells[11].innerHTML.trim()=="待完善"?"0":"1";
			set_selected_option($('#upppcheck option'),status);
		}
		if(e.target.className.indexOf("join")>=0){
			if(confirm("确定加入该项目 ？")){
				var row = $(e.target).parent().parent();
				if(!(row[0].cells[13].innerHTML.trim()!=""&row[0].cells[14].innerHTML.trim()!="")){
					var authors = row[0].cells[13].innerHTML.trim()==""?"默认:第一作者":"默认:第二作者";
					var ahs = row[0].cells[13].innerHTML.trim()==""?"f":"s";
					alert(authors);
					$.post("GTperiodicalpaper-paperset!joinPeriodicalPaper",
							{"periopaper.ppid":row[0].cells[1].innerHTML,
							 "author":ahs},
							function(data,status){
								if(status=="success"){
									if(data.trim()==""){
										alert("加入失败！！");
									}else{
										alert(data);
									}
								}else{
									alert("请求失败！！！");
								}
							}
					);
				}else{
					alert("该项目已满");
				}
			}
		}
		//查看项目成员
		if(e.target.className.indexOf("searchmember")>=0){
			var row = $(e.target).parent().parent();
			$.post("GTperiodicalpaper-paperset!getMember",
					{"periopaper.ppid":row[0].cells[1].innerHTML},
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
							alert("请求失败");
						}
					}
			);
		}
	});
    $('.openupdatem').on("click",function() {
		$('#upinfID').attr("value",$(this).parent().parent()[0].cells[0].innerHTML);
		$('#upinf')[0].value = $(this).parent().parent()[0].cells[1].innerHTML;
	});
    $(document).ready(function() {
    	//加序号
    	var t = $('#tb').DataTable( {
            "columnDefs": [ {
                "searchable": false,
                "orderable": false,
                "targets": 0
            } ],
            "order": [[ 1, 'asc' ]]
        } );
     
        t.on( 'order.dt search.dt', function () {
            t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
                cell.innerHTML = i+1;
            } );
        } ).draw();
        t.column(3).visible( true, false );
        t.columns.adjust().draw( true );
        //加一百行
        $('.addrows').click(function() {
        	//alert($(t.row( ':last').node())[0].cells[0].innerHTML);
    		$.post("GTperiodicalpaper-paperset!addRows",
    				{"currentrow":parseInt($(t.row( ':last').node())[0].cells[0].innerHTML.trim()),
    				 "foredate":$('#date1').val().trim(),
    				 "afterdate":$('#date2').val().trim()},
    				function(data,status){
    					alert(data);
        				var obj = JSON.parse(data);
    					if(status=="success"){
    						for(var i=0;i<obj.length;i++){
    							var status = "待完善";
    							var choice = '<a class="btn btn-primary btn-sm join">加入</a>';
    							if(obj[i].checkout!='0'){
    								status = "已完善";
    								choice = '<a class="btn btn-primary btn-sm" style="background-color: #999999">加入</a>';
    							}
    							if(obj[i].chargePersonId==teacherIds){
    								choice = '<a  class="btn btn-primary btn-sm carrydata" data-toggle="modal" data-target="#update" >编辑</a> <a  class="btn btn-primary btn-sm searchmember" data-toggle="modal" data-target="#checkmember">查看项目成员</a>';
    							}
    							var	rnode = t.row.add(["",
    							           obj[i].ppid,
    							           obj[i].thesisTitle,
    							           obj[i].periodicalId,
    							           obj[i].periodicalName,
    							           obj[i].year,
    							           obj[i].file,
    							           obj[i].phase,
    							           obj[i].describe.substring(0,5)+" . . . . .",
    							           obj[i].chargePersonId,
    							           obj[i].chargePerson,
    							           status,
    							           choice,
    							           obj[i].firstAuthor,
    							           obj[i].secondAuthor,
    							           obj[i].periodicalPid
    							           ]).draw().node();
    							var w = $( rnode );
    							w[0].cells[3].style.display = "none";
    							w[0].cells[13].style.display = "none";
    							w[0].cells[14].style.display = "none";
    							w[0].cells[15].style.display = "none";
    							w[0].cells[8].title = obj[i].describe;
    						}
    					}else{
    						alert("请求失败");
    					}
    				});
    	});
     //省略处理论文概述
     var tdes = $('.describe');
     for(var i=0;i<tdes.length;i++){
    	 tdes[i].innerHTML = tdes[i].title.substring(0,5)+" . . . . . .";
     }
	});
  //更新
  $('#subupdate').click(function() {
		if(checkupdates()){
			swal({   
	    		title: "确定提交?",   
	    		text: "",   
	    		type: "warning",   
	    		showCancelButton: true,   
	    		confirmButtonColor: "#DD6B55",   
	    		confirmButtonText: "确定",
	    		cancelButtonText: "取消",   
	    		closeOnConfirm: false,   
	    		closeOnCancel: false }, 
	    			function(isConfirm){   
	    				if (isConfirm) {
	    					$.post("GTperiodicalpaper-paperset!updatesppaer",
	    							{"periopaper.ppid":$('#upppId').val().trim(),
	    							 "periopaper.thesisTitle":$('#upppt').val().trim(),
	    							 "periopaper.periodical.periodicalId":$('#upp').val().trim(),
	    							 "periopaper.year":$('#upppy').val().trim(),
	    							 "periopaper.file":$('#upppf').val().trim(),
	    							 "periopaper.phase":$('#uppph').val().trim(),
	    							 "periopaper.describe":$('#upppde').val().trim(),
	    							 "periopaper.checkout":$('#upppcheck').val().trim(),
	    							 "periopaper.periodicalPid":$('#upppptid').val().trim(),
	    							 "periopaper.firstAuthor":$('#upppfirst').val().trim(),
	    							 "periopaper.secondAuthor":$('#upppsecond').val().trim()},
	    							function(data,status){
	    								if(status=="success"){
	    									if(data=="succ"){
	    										swal("更新成功","","success");
	    										setTimeout(function() {
	    											window.location.replace("GTperiodicalpaper-paperset!getPeriodicalPaperINF?currentrow=0");
												}, 2000);
	    									}else{
	    										swal("更新失败: "+data);
	    									}
	    								}else{
	    									swal("请求失败！");
	    								}
	    							});
	    				}else{
	    					swal("已取消");
	    				}
	    			}
	    	);
		}else{
			alert("是不是还有没填的？");
		}
	});
   //删除
   $('#subdel').click(function() {
		if(confirm("确定删除 ?")){
			$.post("GTperiodicalpaper-paperset!deleteppaer",
					{"periopaper.ppid":$('#upppId').val().trim(),
					 "periopaper.thesisTitle":$('#upppt').val().trim(),
					 "periopaper.periodical.periodicalId":$('#upp').val().trim(),
					 "periopaper.year":$('#upppy').val().trim(),
					 "periopaper.file":$('#upppf').val().trim(),
					 "periopaper.phase":$('#uppph').val().trim(),
					 "periopaper.describe":$('#upppde').val().trim(),
					 "periopaper.checkout":$('#upppcheck').val().trim(),
					 "periopaper.periodicalPid":$('#upppptid').val().trim(),
					 "periopaper.firstAuthor":$('#upppfirst').val().trim(),
					 "periopaper.secondAuthor":$('#upppsecond').val().trim()},
					function(data,status){
						if(status=="success"){
							if(data=="succ"){
								alert("删除成功");
								window.location.replace("GTperiodicalpaper-paperset!getPeriodicalPaperINF?currentrow=0");
							}else{
								alert("更新失败");
							}
						}else{
							alert("请求失败！");
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