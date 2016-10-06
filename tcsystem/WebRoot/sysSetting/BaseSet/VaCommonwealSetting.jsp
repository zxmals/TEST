<%@page import="com.nuaa.ec.utils.StoreData"%>
<%@page import="com.nuaa.ec.model.VaCommonweal"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.nuaa.ec.model.Teacher" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<String,Object> mp = StoreData.getTeachertranslate();
request.setAttribute("teachertranslate", mp);
List<Teacher> teacher = (List)request.getAttribute("Teacher");
%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <base target="_self"> 
    </head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>VaCommonwealSet</title>
    
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
                         <button class="btn  btn-primary initaddD" type="submit" data-backdrop="true" data-toggle="modal" data-target="#add">
                         <strong>添加</strong>
                         </button>
                            
                        </div>
                        <div class="example">
                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example" >
                            <thead>
                                <tr>
									<td class="sorting_asc">公益管理员编号</td>
									<td class="sorting_asc">公益管理员</td>
									<td class="sorting_asc">操作</td>
								</tr>
                            </thead>
                            <tbody>                               
								<%if(teacher!=null)
									for(int i=0;i<teacher.size();i++){%>
									<tr>
										<td><%=teacher.get(i).getVaadmin() %></td>
										<td><%=mp.get(teacher.get(i).getVaadmin()) %></td>
										<td><button class="getdata" data-toggle="modal" data-target="#update">修改</button> <button class="del_va">删除</button></td>
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
                            <h3 class="m-t-none m-b">修改管理员</h3>
                                <div class="form-group">                                
                                    <label>公益管理:</label>
									<input id="VaAdmin" type="text"  autocomplete="off"   class="form-control keyUp" placeholder="输入教师工号或姓名查询">
									<div style="width:568px;overflow-y: auto;display: none" class="selecthead">
										<c:forEach items="${teachertranslate}" var="teacher">
											<div class="selectsele">${teacher.key}-${teacher.value}</div>
										</c:forEach>
									</div>
								</div>
                                </div>															
								<div>
										<button type="button"
											class="btn btn-outline btn-primary pull-right m-t-n-xs"
											data-dismiss="modal">关闭</button>
										<button class="btn  btn-primary pull-left m-t-n-xs " id="subadd" type="submit">
											<i class="fa fa-check"></i> <strong>提交</strong>
										</button>
									</div><br>
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
    $('.delnation').click(function() {
		var x = confirm("确定删除？");
		var now = $(this);
		if(x){
			$.post("<%=basePath %>ATNationalityBaseset!deletenation",
					{"teacher.vaa":$(this).parent().parent()[0].cells[0].innerHTML,
					 "nation.countryName":$(this).parent().parent()[0].cells[1].innerHTML},
					function(data,status){
						if(status=="success"){
							if(data=="succ"){
								now.parent().parent().remove();
								alert("删除成功");
							}
						}else{
							alert("请求失败");
						}
					});
		}
	});
    $('#checknationinf').click(function() {
		if($('#upnationality').val().trim()==""){
			$('#upnationality').css("background-color","green");
			$('#upnationality').attr("placeholder","请填写名称");
			$('#upnationality')[0].value = "";
			return;
		}
		$.post("<%=basePath %>ATNationalityBaseset!updateNationality",
				{"nation.countryId":$('#upnationalID').val(),
				 "nation.countryName":$('#upnationality').val()},
				function(data,status){
					if(status=="success"){
						if(data=="succ"){
							alert("修改成功");
							window.location.replace("<%=basePath %>ATNationalityBaseset!getNationalityinfo");
						}
					}else{
						alert("请求失败");
					}
				});
	});
    $('.updatenation').click(function() {
		$('#upnationalID')[0].value = $(this).parent().parent()[0].cells[0].innerHTML;
		$('#upnationality')[0].value = $(this).parent().parent()[0].cells[1].innerHTML;
		$(this).attr("data-toggle","modal");
		$(this).attr("data-target","#update");
	});
    $('.initoper').click(function() {
		var tar = $('.initar');
		for(var i=0;i<tar.length;i++){
			tar[i].style.backgroundColor = "white";
		}
	});
	$('.keyUp').keyup(function() {
		$(this).css("background-color","white");
	});
        $(document).ready(function () {
            $(".dataTables-example").dataTable();
            var oTable = $("#editable").dataTable();
            oTable.$("td").editable("../example_ajax.php", {
                "callback": function (sValue, y) {
                    var aPos = oTable.fnGetPosition(this);
                    oTable.fnUpdate(sValue, aPos[0], aPos[1])
                }, "submitdata": function (value, settings) {
                    return {"row_id": this.parentNode.getAttribute("id"), "column": oTable.fnGetPosition(this)[2]}
                }, "width": "90%", "height": "100%"
            })
        });
        function fnClickAddRow() {
            $("#editable").dataTable().fnAddData(["Custom row", "New row", "New row", "New row", "New row"])
        }
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});            
    </script>
	<s:debug></s:debug>
     
</body>
</html>