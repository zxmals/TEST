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

    <title>PeriodicalPaper --Set</title>
    
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    
    <!-- Sweet Alert -->
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="css/zxma.css">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
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
										<td style="display: none">期刊Id</td>
										<td>期刊</td>
										<td>年</td>
										<td>卷</td>
										<td>期</td>
										<td>论文概述</td>
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
										<td>${ebj.describe }</td>
										<td>${ebj.chargePersonId }</td>
										<td>${teachermp[ebj.chargePersonId] }</td>
										<td>
											<c:if test="${ebj.checkout==0 }">待完善</c:if>
											<c:if test="${ebj.checkout==1 }">已完善</c:if>
										</td>
										<td>
											<c:if test="${ebj.chargePersonId==teacher.teacherId}">
												<a  class="btn btn-primary btn-sm openupdatem carrydata" >编辑</a>
											</c:if>
											<c:if test="${ebj.chargePersonId==teacher.teacherId}">
												<a  class="btn btn-primary btn-sm openupdatem carrydata" >查看项目成员</a>
											</c:if>
											<c:if test="${ebj.chargePersonId!=teacher.teacherId}">
												<a  class="btn btn-primary btn-sm openupdatem carrydata">加入</a>
											</c:if>
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
	                                <div class="form-group"  style="display: none">
	                                	<label>ID:</label>                                	
										<input id="upinfID" type="text"  class="form-control" name="" value=""  readonly="readonly">
	                                </div>
	                                <div class="form-group">
	                                    <label>奖励级别:</label>
	                                    <input id="upinf" type="text"  class="form-control nullcheck" name="" value="">
	                                </div>                                                           
	                                <div>
	                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
	                                    <button id="subupdate"  class="btn  btn-primary pull-left m-t-n-xs subcheck"  type="submit">
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
	                            <h3 class="m-t-none m-b">添加期刊论文</h3>
	                            <hr >
	                            <form role="form" id="onlyForm" name="adds" action="GTperiodicalpaper-paperset!addPeriodicalPaper" method="post">                            
	                                <div class="form-group">                                
	                                    <label>论文标题:</label>
	                                    <input id="addinf" type="text"  class="form-control nullcheck addcheck" name=periopaper.thesisTitle  value="">
	                                </div>
	                                <div class="form-group">                            
	                                    <label>登记人身份:</label>
	                                    <select class="form-control nullcheck addcheck" name="author">
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
	                                    <input  type="text"  class="form-control nullcheck addcheck" onClick="WdatePicker()" name=periopaper.year value="" readonly="readonly">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>卷:</label>
	                                    <input  type="text"  class="form-control nullcheck addcheck" name=periopaper.file value="">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>期:</label>
	                                    <input type="text"  class="form-control nullcheck addcheck" name=periopaper.phase value="">
	                                </div>
	                                <div class="form-group">                                
	                                    <label>论文概述:</label>
	                                    <textarea  type="text"  class="form-control nullcheck addcheck" name=periopaper.describe  value=""></textarea>
	                                </div>                                               
	                            </form>
	                                <div>
	                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
	                                    <button id="subadds" class="btn  btn-primary pull-left m-t-n-xs subcheck"  type="submit">
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
			alert($(e.target).parent().parent()[0].cells[2].innerHTML);
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
    							var choice = '<a onclick=ts("'+obj[i].ppid+'") class="btn btn-primary btn-sm openupdatem carrydata">加入</a>';
    							if(obj[i].checkout!='0'){
    								status = "已完善";
    							}
    							if(obj[i].chargePersonId==teacherIds){
    								choice = '<a  class="btn btn-primary btn-sm openupdatem carrydata" >编辑</a> <a  class="btn btn-primary btn-sm openupdatem carrydata" >查看项目成员</a>';
    							}
    							var	rnode = t.row.add(["",
    							           obj[i].ppid,
    							           obj[i].thesisTitle,
    							           obj[i].periodicalId,
    							           obj[i].periodicalName,
    							           obj[i].year,
    							           obj[i].file,
    							           obj[i].phase,
    							           obj[i].describe,
    							           obj[i].chargePersonId,
    							           obj[i].chargePerson,
    							           status,
    							           choice
    							           ]).draw().node();
    							var w = $( rnode );
    							w[0].cells[3].style.display = "none";
    							w[0].cells[1].id = obj[i].ppid;
    						}
    					}else{
    						alert("请求失败");
    					}
    				});
    	});
	});
        $(document).ready(function(){$(".dataTables-example").dataTable();var oTable=$("#editable").dataTable();oTable.$("td").editable("../example_ajax.php",{"callback":function(sValue,y){var aPos=oTable.fnGetPosition(this);oTable.fnUpdate(sValue,aPos[0],aPos[1])},"submitdata":function(value,settings){return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}},"width":"90%","height":"100%"})});function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};         
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>