<%@page import="com.nuaa.ec.science.model.PeriodicalType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    

    <title>publishclubSet</title>
    
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    
    <!-- Sweet Alert -->
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <script type="text/javascript">
    function assignmentC(upPID,upP) {
	    document.getElementById("acadWorkscoreID").value = upPID;
	    document.getElementById("acadWorkscore").value = upP;
	    setSelected(document.getElementById("td"+upPID).value);
	}
	
	function setSelected(ID) {
		var select = document.getElementById("WordnumSelector");  
		for(var i=0; i<select.options.length; i++){
		    if(select.options[i].value == ID){  
		        select.options[i].selected = true;  
		        break;  
		    }  
		}  
	}
    	function confirmdel(id) {
			if(confirm("确定要删除吗？"))
				window.location.replace("AcademicWorkScoreset!dodelacadworkScore?acadworkScore.acaWorkScoreID="+id);
			else
				window.location.replace("#");
		}
    	function DoCheck() {
    		var res = '${resu}';
    		//alert(addres);
			switch (res){
				case '0':alert("operate fail !!!");
				break;
				case '1':alert("add success!");
				break;
				case '2':alert("update success!");
				break;
				case '3':alert("delete success !!!");
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
                        <h5>学术著作 <small></small></h5>
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
                         <button class="btn  btn-primary" type="submit" data-backdrop="true" data-toggle="modal" data-target="#add">
                         <strong>添加</strong>
                         </button>
                            
                        </div>
                        <div class="example">
                        <form method="post" name="f">
                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
									<td>学术著作评分编号</td>
									<td>字数</td>
									<td>基准分数</td>
									<td>操作</td>
								</tr>
                            </thead>
                            <tbody>
								<c:forEach var="acawscore"  items="${acadworkScoreLi }">
								<tr>
									<td>${acawscore.acaWorkScoreID }</td>
									<td>${acawscore.wordNumber }</td>
									<td>${acawscore.score }</td>
									<input id="td${acawscore.acaWorkScoreID }" type="text"  value = "${acawscore.wordID }" style="display: none">
									<td><a   class="btn btn-primary btn-sm"  data-toggle="modal"    onclick="confirmdel('${acawscore.acaWorkScoreID }')"  >删除</a>					
										<a   class="btn btn-primary btn-sm"  data-toggle="modal"    onclick="assignmentC('${acawscore.acaWorkScoreID }','${acawscore.score }')"  data-target="#update" >修改</a>
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
                            <form role="form" id="onlyForm" name="upd"action="AcademicWorkScoreset!doupdateAcadWorkScore">
                            
                                <div class="form-group">
                                	<label>ID:</label>                                	
									<input id="acadWorkscoreID" type="text"  class="form-control" name="acadworkScore.acaWorkScoreID" value=""  readonly="readonly">
                                </div>
                                <div class="form-group">                                
                                    <label>基础分数:</label>
                                   <input id="acadWorkscore" type="text"  class="form-control" name="acadworkScore.score" value="">
                                </div>                                                                      
                                <div>   
                                <div class="form-group">                                
                                    <label>字数:</label>
                                    <select id="WordnumSelector"  name="acadworkScore.wordID" style="width: 200px"  class="form-control" >
                                    	<c:forEach  var="word"  items="${wordnum }">
		                                    	<option value="${word.wordID }">${word.wordNumber }</option>
		                                    </c:forEach>
                                    </select>
                                </div>                                                        
                                <div>
                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                    <button  class="btn  btn-primary pull-left m-t-n-xs "  type="submit">
                                     <i class="fa fa-check"></i>
                                    <strong>提交</strong>
                                    </button	>
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
                            <form role="form" id="onlyForm" name="adds"action="AcademicWorkScoreset!doaddAcadWorkScore">                            
                                <div class="form-group">                                
                                    <label>字数:</label>
                                    <select id="WordnumSelector"  name="acadworkScore.wordID" style="width: 200px"  class="form-control" >
                                    	<c:forEach  var="word"  items="${wordnum }">
		                                    	<option value="${word.wordID }">${word.wordNumber }</option>
		                                    </c:forEach>
                                    </select>
                                </div>   
                                <div class="form-group">                                
                                    <label>基础分数:</label>
                                   <input id="acadWorkscore" type="text"  class="form-control" name="acadworkScore.score" value="">
                                </div>                                                                      
                                <div>
                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                    <button  class="btn  btn-primary pull-left m-t-n-xs "  type="submit">
                                     <i class="fa fa-check"></i>
                                    <strong>提交</strong>
                                    </button	>
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