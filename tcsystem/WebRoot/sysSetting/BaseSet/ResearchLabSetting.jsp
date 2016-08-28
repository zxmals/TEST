<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.nuaa.ec.model.ResearchLab"%>
<%@page import="com.nuaa.ec.utils.StoreData"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<String,Object> mp = StoreData.getTeachertranslate();
request.setAttribute("teachertranslate", mp);
List<ResearchLab> reseach = (List)request.getAttribute("ResearchLab");
%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <base target="_self"> 
    </head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>ResearchLabSet</title>
    
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    
    <!-- Sweet Alert -->
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <link href="css/zxma.css" rel="stylesheet">
</head>

<body class="gray-bg"  onload="DoCheck()">

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>所 <small></small></h5>
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
                         <button class="btn  btn-primary initaddD" type="submit" data-backdrop="true" data-toggle="modal" data-target="#add">
                         <strong>添加</strong>
                         </button>
                            
                        </div>
                        <div class="example">
                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
									<td>研究所ID</td>
									<td>研究所</td>
									<td>研究所所长编号</td>
									<td>研究所所长</td>
									<td>操作</td>
								</tr>
                            </thead>
                            <tbody>                                 
								<%
								if(reseach!=null)
								for(int i=0;i<reseach.size();i++){%>
								<tr>
									<td><%=reseach.get(i).getResearchLabId() %></td>
									<td><%=reseach.get(i).getResearchLabName() %></td>
									<td><%=reseach.get(i).getResearchLabAdminId() %></td>
									<td><%=mp.get(reseach.get(i).getResearchLabAdminId()) %></td>
									<td><button data-toggle="modal" class="getdata" data-target="#update" >修改</button>&nbsp;<button class="del_depart">删除</button></td>
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
                            <h3 class="m-t-none m-b">修改</h3>
                                <div class="form-group" style="display:none">
                                	<label>ID:</label>                                	
									<input id="ResearchLabID" type="text"  class="form-control" name="ResearchLabID" value=""  readonly="readonly">
                                </div>
                                <div class="form-group">                                
                                    <label>研究所:</label>
                                    <input id="ResearchLab" type="text"  class="form-control keyUp" name="ResearchLab" value="">
                                </div>
								<div class="form-group">                                
                                    <label>研究所所长:</label>
									<input id="researchAdmin" type="text"  autocomplete="off"   class="form-control keyUp" placeholder="输入教师工号或姓名查询">
									<div style="width:568px;overflow-y: auto;display: none" class="selecthead">
										<c:forEach items="${teachertranslate}" var="teacher">
											<div class="selectsele">${teacher.key}-${teacher.value}</div>
										</c:forEach>
									</div>
								</div>
                                <div>
                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                    <button  class="btn btn-primary pull-left m-t-n-xs " id="subadd" type="submit">
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
                            <form method="post" role="form" id="onlyForm" name="addrech"action="ATResearchLabBaseset!addResearchLab">                            
                                <div class="form-group">                                
                                    <label>研究所:</label>
                                    <input id="addResearchLab" type="text"  class="form-control keyUp" name="research.researchLabName" value="">
                                </div>                                                           
                            </form>
                                <div>
                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                    <button  class="btn  btn-primary pull-left m-t-n-xs " id="addresearch" type="submit">
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
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
    <script>
    
    $('.del_depart').click(function() {
		var x = confirm("确定删除?");
		var diedelement = $(this);
		if(x){
			$.post("<%=basePath %>ATResearchLabBaseset!deleteResearch",
					{"research.researchLabId":$(this).parent().parent()[0].cells[0].innerHTML},
					function(data,status){
						if(status=="success"){
							if(data=="succ"){
								diedelement.parent().parent().remove();
								alert("删除成功");
							}else{
								alert("删除失败");
							}
						}else{
							alert("请求失败");
						}
			});
		}
	});
    
	$('#addresearch').click(function() {
		if($('#addResearchLab').val().trim()==""){
			$('#addResearchLab')[0].value = "";
			$('#addResearchLab').css("background-color","green");
			if($('#addResearchLab').attr("placeholder")==undefined){
				$('#addResearchLab').attr("placeholder","请填写系名");
			}
			return;
		}
		document.addrech.submit();
	});
	$('.initaddD').click(function() {
		$('#addResearchLab').css("background-color","white");
		$('#addResearchLab')[0].value="";
	});
	$('.getdata').click(function() {
		$('#ResearchLabID')[0].value = $(this).parent().parent()[0].cells[0].innerHTML;
		$('#ResearchLab')[0].value = $(this).parent().parent()[0].cells[1].innerHTML;
		$('#researchAdmin')[0].value = $(this).parent().parent()[0].cells[2].innerHTML+"-"+$(this).parent().parent()[0].cells[3].innerHTML;
		$('#ResearchLab').css("background-color","white");
		$('#researchAdmin').css("background-color","white");
		$('.selecthead').css("display","none");
	});
    $('#subadd').click(function() {
		var flag = true;
		if($('#ResearchLab').val().trim()==""){
			$('#ResearchLab')[0].value="";
			$('#ResearchLab').css("background-color","green");
			if($('#ResearchLab').attr("placeholder")==undefined){
				$('#ResearchLab').attr("placeholder","请填写系名");
			}
			flag = false;
		}
		if($('#researchAdmin').val().trim()==""){
			$('#researchAdmin')[0].value="";
			$('#researchAdmin').css("background-color","green");
			flag = false;
		}
		if(!flag){
			return;
		}
		//保存修改，并验证教师合法性（属于当前所在所）
		$.post("<%=basePath %>ATResearchLabBaseset!updateResearchLab",
				{"research.researchLabId":$('#ResearchLabID').val(),
				 "research.researchLabName":$('#ResearchLab').val(),
				 "research.researchLabAdmin":$('#researchAdmin').val()},
				function(data,status) {
					if(status=="success"){
						if(data=="succ"){
							alert("修改成功");
							window.location.replace("<%=basePath %>ATResearchLabBaseset!getResearchLabinfo");
						}else{
							alert("该教师不属于该所，或该教师信息错误");
						}
					}else{
						alert("请求失败");
					}
		});
	});
	$('.selectsele').click(function(){
		$('#researchAdmin')[0].value = $(this)[0].innerHTML;
		$('#researchAdmin').css("background-color","white");
	});
    $('#researchAdmin').keyup(function() {
		var vals = $('.selectsele');
		var targets = $(this).val();
		if($('.selecthead').css('display')=="none"){
    		for(var i=0;i<vals.length;i++){
    			vals[i].style.display = "";
    		}
			$('.selecthead').css("height","150px");
			$('.selecthead').slideDown();
		}
		for(var i=0;i<vals.length;i++){
			if(vals[i].innerHTML.indexOf(targets)>=0){
				vals[i].style.display = "";
			}else{
				vals[i].style.display = "none";
			}
		}
	});
    $('#researchAdmin').click(function(){
		var selects = $('.selectsele');
		if($('.selecthead').css('display')=="none"){
    		for(var i=0;i<selects.length;i++){
    			selects[i].style.display = "";
    		}
			$('.selecthead').css("height","150px");
			$('.selecthead').slideDown();
		}else{
			$('.selecthead').slideUp();
		}
	});
	$(document).ready(function(){
		var selects = $('.selectsele');
		for(var i=0;i<selects.length;i++){
			if((i+1)%2==0){
				selects[i].style.backgroundColor = "#E5E6E7";
			}
		}
	});
	/*keyup for-all*/
	$('.keyUp').keyup(function() {
		$(this).css("background-color","white");
	});
        $(document).ready(function(){$(".dataTables-example").dataTable();var oTable=$("#editable").dataTable();oTable.$("td").editable("../example_ajax.php",{"callback":function(sValue,y){var aPos=oTable.fnGetPosition(this);oTable.fnUpdate(sValue,aPos[0],aPos[1])},"submitdata":function(value,settings){return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}},"width":"90%","height":"100%"})});function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};         
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});            
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>