<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.nuaa.ec.science.daoimpl.ResearchLabDAOimpl"%>
<%@page import="com.nuaa.ec.science.model.ScientificResearchExportDataType"%>
<%@page import="com.nuaa.ec.science.model.ScientificResearchReward"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<ScientificResearchExportDataType>  SREDTdao = (List)session.getAttribute("expor");
ResearchLabDAOimpl researdao = new ResearchLabDAOimpl(); 
List<String> AuditResult = new ArrayList<String>();
String oint = (String)request.getParameter("oint");
int uu = 4;
int o = 0;
if(oint!=null)
	o = Integer.parseInt(oint);
%>
<jsp:directive.page import="javax.servlet.http.HttpSession"/>
    <%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <base target="_self"> 
    </head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>periodicalPaperAudit</title>
     <script src="js/checkbox.js"></script>
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
			var selectedcheck = <%=o %>;
			var obj = document.getElementById("ointselect");
			for(var i=0;i<obj.options.length;i++){
				if(selectedcheck==obj.options[i].value)
					obj.options[i].selected = true;
			}			
		}
    </script>    
</head>

<body class="gray-bg"  onload="DoCheck()">
<%--${pageContext.request.contextPath }<br />
${basePath } --%>
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>期刊论文 <small></small></h5>
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
                    <h1 class="page-header">审核</h1>
						<form name="ff" method="post">
							<table>
								<tr> 
									<td><strong>研究所:</strong></td>
									<td>
										<select name = "oint"   id="ointselect"  class="form-control" >
											<%if(SREDTdao!=null)
														for(int i=0;i<SREDTdao.size();i++){%>
											<option value="<%=i %>">
												<%= SREDTdao.get(i).getResearchLabName()%>
											</option>
											<%} %>
										</select>
									</td>		
									<td>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button class="btn-primary  "  onclick="javascript:document.ff.action='ScientificAuditbrowse?oints=<%=o %>';document.ff.submit();"  type="submit">
													<strong style="fontsize: 36px">更替项目</strong>
											</button>			
									</td>							
								</tr>
							</table>							
						</form>
						<hr /><br />                           
                        <div class="example">
                        <form method="post" name="Audit">
                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example" >
                            <thead>
                                <tr>
									<td>期刊论文编号</td>				<td>期刊论文标题</td>
									<td>期刊名称</td>				<td>第一作者</td>
									<td>第二作者</td>				<td>年份</td>
									<td>卷</td>				<td>期</td>
									<td>教师编号</td>				<td>教师姓名</td>
									<td>相关描述</td>				<td>最终分数</td>
									<td>全通过&nbsp;<input type = "checkbox"  name =""  id="allCheck" onchange="allAlowOrNot()" /></td>
								</tr>
                            </thead>
                            <tbody>                               
									<%
										if(o!=100000)
											if(SREDTdao.get(o).getTP()!=null)
												for(int i=0;i<SREDTdao.get(o).getTP().size();i++){ %>	
										<tr>
											<td><%=SREDTdao.get(o).getTP().get(i).getPPId() %></td>
											<td><%=SREDTdao.get(o).getTP().get(i).getThesisTitle() %></td>
											<td><%=SREDTdao.get(o).getTP().get(i).getPeriodicalName() %></td>
											<td><%=SREDTdao.get(o).getTP().get(i).getFirstAuthor() %></td>
											<td><%=SREDTdao.get(o).getTP().get(i).getSecondAuthor() %></td>
											<td><%=SREDTdao.get(o).getTP().get(i).getYear() %></td>
											<td><%=SREDTdao.get(o).getTP().get(i).getFile() %></td>
											<td><%=SREDTdao.get(o).getTP().get(i).getPhase() %></td>
											<td><%=SREDTdao.get(o).getTP().get(i).getTeacherId() %></td>
											<td><%=SREDTdao.get(o).getTP().get(i).getTeacherNmae() %></td>
											<td><%=SREDTdao.get(o).getTP().get(i).getDescribe() %></td>
											<td><%=SREDTdao.get(o).getTP().get(i).getBaseScore() %></td>
											<td>
											<input type = "checkbox"  name =""  
											id="<%=SREDTdao.get(o).getTP().get(i).getTeacherId()+SREDTdao.get(o).getTP().get(i).getPPId() %>" 
											value="<%=SREDTdao.get(o).getTP().get(i).getTeacherId() %>,<%=SREDTdao.get(o).getTP().get(i).getPPId() %>"
											onchange="nullChange('<%=SREDTdao.get(o).getTP().get(i).getTeacherId()+SREDTdao.get(o).getTP().get(i).getPPId() %>')"/></td>
										</tr>
										<%}%>
                            </tbody>                           
                        </table>
								<div>
									<button class="btn  btn-primary pull-left m-t-n-xs " onclick="changeCheckboxValueAndSubmit('Periodical',<%=o%>);"	type="submit">
										<i class="fa fa-check"></i> <strong>提交</strong>
									</button>
								</div>
							</form>                        
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
                            <form role="form" id="onlyForm" name="adds"action="Departmentset!doadd">                            
                                <div class="form-group">                                
                                    <label>系名称:</label>
                                    <input id="DepartmentName" type="text"  class="form-control" name="DepartmentName" value="">
                                </div>															
								<div>
										<button type="button"
											class="btn btn-outline btn-primary pull-right m-t-n-xs"
											data-dismiss="modal">关闭</button>
										<button class="btn  btn-primary pull-left m-t-n-xs " type="submit">
											<i class="fa fa-check"></i> <strong>提交</strong>
										</button>
									</div>
							</form>
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
        $(document).ready(function(){$(".dataTables-example").dataTable();var oTable=$("#editable").dataTable();oTable.$("td").editable("../example_ajax.php",{"callback":function(sValue,y){var aPos=oTable.fnGetPosition(this);oTable.fnUpdate(sValue,aPos[0],aPos[1])},"submitdata":function(value,settings){return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}},"width":"90%","height":"100%"})});function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};         
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});            
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>