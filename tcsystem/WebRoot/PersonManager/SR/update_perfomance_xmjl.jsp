<%@ page language="java" import="com.nuaa.ec.science.Permodel.TeacherLoginStatus" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.nuaa.ec.science.Permodel.ViewXMJL"/>
<jsp:directive.page import="com.nuaa.ec.science.dao.RewardLevelDAO"/>
<jsp:directive.page import="com.nuaa.ec.science.dao.RewardTypeDAO"/>
<%@ page import="com.nuaa.ec.science.dao.TeacherANDScientificResearchRewardDAO" %>
<%@ page import="com.nuaa.ec.science.model.ScientificResearchReward" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
    TeacherLoginStatus a = (TeacherLoginStatus) session.getAttribute("teacherloginStatus");
    RewardLevelDAO rldao = new RewardLevelDAO();
    RewardTypeDAO rtdao = new RewardTypeDAO();
    List<String> c = rldao.getAllRewardLevelName();
    List<String> d = rtdao.getAllRewardTypeName();
    TeacherANDScientificResearchRewardDAO dao = new TeacherANDScientificResearchRewardDAO();
    List<ViewXMJL> f = new ArrayList<ViewXMJL>();
    List<ScientificResearchReward> h = new ArrayList<ScientificResearchReward>();
    f = dao.getAllXMJL(a.getTeacherID());
    h = dao.getAllXMJLproject();
  %>
  <%//判断操作是否成功
     int g = 888;
     if(request.getAttribute("CheckData")!=null){
	  g =(Integer)request.getAttribute("CheckData");
	 }
%>

<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh-CN">

  <head>
  <base target="_self"> 
    </head>

     
    <script>
    function DoCheck(){
     var checkid = <%=g%>;
     if(checkid!=999&&checkid!=888){
     switch(checkid){
         case 1:
             swal("操作成功", "已成功提交项目!", "success");
             break;
         case 4:
             swal("操作成功", "已成功提交项目!", "success");
             break;
         case 5:
             swal("操作成功", "已成功修改!", "success");
             break;
         case 2:
             swal("操作成功", "已成功修改!", "success");
             break;
         case 6:
             swal("操作成功", "已成功删除!", "success");
             break;
     default: swal("Oooops", "操作失败!", "error");break;
     }
     }
     checkid = 888;
    }
    </script>

  <script>
      function Display() {
          document.getElementById("div2").style.display = "";
      }
  </script>

  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>NUAA-CEM绩效管理系统 - 绩效操作</title>

  <link rel="shortcut icon" href="favicon.ico">
  <link href="../css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
  <link href="../css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
  <link href="../css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
  <link href="../css/plugins/iCheck/custom.css" rel="stylesheet">
  <link href="../css/plugins/chosen/chosen.css" rel="stylesheet">
  <link href="../css/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
  <link href="../css/plugins/cropper/cropper.min.css" rel="stylesheet">
  <link href="../css/plugins/switchery/switchery.css" rel="stylesheet">
  <link href="../css/plugins/jasny/jasny-bootstrap.min.css" rel="stylesheet">
  <link href="../css/plugins/nouslider/jquery.nouislider.css" rel="stylesheet">
  <link href="../css/plugins/datapicker/datepicker3.css" rel="stylesheet">
  <link href="../css/plugins/ionRangeSlider/ion.rangeSlider.css" rel="stylesheet">
  <link href="../css/plugins/ionRangeSlider/ion.rangeSlider.skinFlat.css" rel="stylesheet">
  <link href="../css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
  <link href="../css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
    <!-- Sweet Alert -->
  <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Data Tables -->
  <link href="../css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

  <link href="../css/animate.min.css" rel="stylesheet">
  <link href="../css/style.min.css?v=4.0.0" rel="stylesheet">

    <script type="text/javascript">
    </script>
</head>

<body class="gray-bg"  onload="DoCheck()">

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>项目奖励-绩效
                            <small></small>
                        </h5>
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
                            <a class="btn  btn-primary" data-backdrop="true" onclick="Display()">
                                <strong>项目奖励管理</strong>
                            </a>
                        </div>
                        <div class="example">
                        <form method="post" name="f">
                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>      
                                    <th>工号</th>
                                    <th>姓名</th>
                                    <th>ID</th>
                                    <th>奖励名称</th>
                                    <th>获奖时间</th>
                                    <th>授奖部门</th>
                                    <th>奖励级别</th>
                                    <th>奖励类别</th>
                                    <th>获奖总人数</th>
                                    <th>本人排名</th>
                                    <th>得分</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(f!=null)for(int i=0;i<f.size();i++){ %>
                                <tr class="gradeA">
                                    <td><%=a.getTeacherID()%></td>
			                        <td><%=a.getTeacherName()%></td>
                                    <td><%=f.get(i).getSRRewardID()%>
                                    </td>
                                    <td><%=f.get(i).getSRRewardName()%>
                                    </td>
                                    <td><%=f.get(i).getRewardDate() %>
                                    </td>
                                    <td><%=f.get(i).getAwardDepartment() %>
                                    </td>
                                    <td><%=f.get(i).getRewardLevelName() %>
                                    </td>
                                    <td><%=f.get(i).getRewardTypeName() %>
                                    </td>
                                    <td><%=f.get(i).getRewardTotalPeople()%>
                                    </td>
                                    <td><%=f.get(i).getSelfRanking() %>
                                    </td>
                                    <td><%=f.get(i).getFinalScore() %>
                                    </td>
                                    <td><%if (f.get(i).getCheckout().equals("1")) {%><a><i
                                            class="fa fa-check text-navy"></i>已审核</a><%} else {%><a>未审核</a><%}%>
                                    </td>
                                    <td><a class="btn btn-primary btn-sm" data-toggle="modal"
                                           onclick="doupdateJ(this)" data-target="#updateJ">修改</a>
                                        <a onclick="dodelJ('<%=f.get(i).getSRRewardID()%>','<%=a.getTeacherID()%>')"
                                           class="btn btn-primary btn-sm del">删除</a>
                                    </td>
                                </tr>
                                <%} %>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>工号</th>
                                    <th>姓名</th>
                                    <th>ID</th>
                                    <th>奖励名称</th>
                                    <th>获奖时间</th>
                                    <th>授奖部门</th>
                                    <th>奖励级别</th>
                                    <th>奖励类别</th>
                                    <th>获奖总人数</th>
                                    <th>本人排名</th>
                                    <th>得分</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                            </tfoot>
                        </table>
                        </form>
                   </div>
                </div>
            </div>
        </div>
        </div>
    </div>
    <div id="div2" style="display:none" class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>项目奖励
                            <small></small>
                        </h5>
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
                            <button class="btn  btn-primary" type="submit" data-backdrop="true" data-toggle="modal"
                                    data-target="#addX">
                                <strong>添加新的项目奖励</strong>
                            </button>
                        </div>
                        <div class="example">
                            <form method="post" name="f1">
                                <table id="tb2"
                                       class="table table-striped table-bordered table-hover dataTables-example">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>奖励名称</th>
                                        <th>获奖时间</th>
                                        <th>授奖部门</th>
                                        <th>奖励级别</th>
                                        <th>奖励类别</th>
                                        <th>获奖总人数</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%if (h != null) for (int i = 0; i < h.size(); i++) { %>
                                    <tr class="gradeA">
                                        <td><%=h.get(i).getSRRewardID()%>
                                        </td>
                                        <td><%=h.get(i).getSRRewardName()%>
                                        </td>
                                        <td><%=h.get(i).getRewardDate()%>
                                        </td>
                                        <td><%=h.get(i).getAwardDepartment()%>
                                        </td>
                                        <td><%=h.get(i).getRewardLevel() %>
                                        </td>
                                        <td><%=h.get(i).getRewardType() %>
                                        </td>
                                        <td><%=h.get(i).getRewardTotalPeople()%>
                                        </td>
                                        <td><%if (h.get(i).getCheckout().equals("1")) {%><a><i
                                                class="fa fa-check text-navy"></i>已审核</a><%} else {%><a>未审核</a><%}%>
                                        </td>
                                        <td><%if (h.get(i).getChargePersonID().equals(a.getTeacherID())) {%>
                                            <a class="btn btn-primary btn-sm" data-toggle="modal"
                                               onclick="doupdateX(this)" data-target="#updateX">修改</a>
                                            <a onclick="dodelX('<%=h.get(i).getSRRewardID() %>')"
                                               class="btn btn-primary btn-sm del">删除</a>
                                            <%} else if (h.get(i).getChargePersonID() != a.getTeacherID()) {%>
                                            <a class="btn btn-primary btn-sm" data-toggle="modal"
                                               onclick="doaddJ(this)" data-target="#addJ">添加绩效</a>
                                            <%}%>
                                        </td>
                                    </tr>
                                    <%} %>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <th>ID</th>
                                        <th>奖励名称</th>
                                        <th>获奖时间</th>
                                        <th>授奖部门</th>
                                        <th>奖励级别</th>
                                        <th>奖励类别</th>
                                        <th>获奖总人数</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </tfoot>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="addX" class="modal fade" aria-hidden="true" tabindex="-1" role="dialog"
   aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <h3 class="m-t-none m-b">添加项目</h3>
                        <form role="form" action="AddSRReward!AddSRReward">
                                 <div class="form-group">
                                     <label>奖励名称：</label>
                                     <input type="text" class="form-control" name="SRRewardName"><span
                                         class="help-block m-b-none"></span>
                                </div>
                                 <div class="form-group">
                                     <label>获奖时间：</label>
                                     <input type="text" class="form-control" name="RewardDate"><span
                                         class="help-block m-b-none"></span>
                                </div>
                                <div class="form-group">
                                    <label>授奖部门：</label>
                                    <input type="text" class="form-control" name="AwardDepartment"><span
                                        class="help-block m-b-none"></span>
                                </div>
                                <div class="form-group">
                                    <label>奖励级别：</label>
                                    <select class="form-control m-b" name="RewardLevelName">
                                        <%if (c!=null ){ for(int i=0;i<c.size();i++){ %>
					                         <option ><%=c.get(i) %></option>
				                        <%} }%>			
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>奖励类别：</label>
                                    <select class="form-control m-b" name="RewardTypeName">
                                        <%
                                            if (d != null) {
                                                for (int i = 0; i < d.size(); i++) {
                                        %>
                                        <option><%=d.get(i) %>
                                        </option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>获奖总人数：</label>
                                    <input type="text" class="form-control" name="RewardTotalPeople"><span
                                        class="help-block m-b-none"></span>
                                </div>
                            <div class="form-group">
                                <label>本人排名：</label>
                                <input type="text" class="form-control" name="SelfRanking"><span
                                    class="help-block m-b-none"></span>
                            </div>
                            <div class="form-group">
                                <div>
                                    <button type="button" class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                    <button  class="btn  btn-primary pull-left m-t-n-xs " type="submit">
                                     <i class="fa fa-check"></i>
                                    <strong>提交</strong>
                                    </button>
                               </div>
                                </div>
                               </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="updateX" class="modal fade" aria-hidden="true" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <h3 class="m-t-none m-b">修改项目</h3>
                        <form role="form" id="onlyForm" action="AddSRReward!updateSRReward">
                                <div class="form-group">
                                    <label>ID：</label>
                                    <input id="cell0" type="text" class="form-control" name="SRRewardID" readonly="true"
                                           value=""><span class="help-block m-b-none" value="$(tid).data(id)"
                                                          readonly="true">请输入ID</span>
                                </div>
                                <div class="form-group">
                                    <label>奖励名称：</label>
                                    <input id="cell1" type="text" class="form-control" name="SRRewardName"><span
                                        class="help-block m-b-none"></span>
                                </div>
                                <div class="form-group">
                                    <label>获奖时间：</label>
                                    <input id="cell2" type="text" class="form-control" name="RewardDate"><span
                                        class="help-block m-b-none"></span>
                                </div>
                                <div class="form-group">
                                    <label>授奖部门：</label>
                                    <input id="cell3" type="text" class="form-control" name="AwardDepartment"><span
                                        class="help-block m-b-none"></span>
                                </div>
                                <div class="form-group">
                                    <label>奖励级别：</label>
                                    <select id="cell4" class="form-control m-b" name="RewardLevelName">
                                        <%if (c!=null ){ for(int i=0;i<c.size();i++){ %>
                                        <option><%=c.get(i) %>
                                        </option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                            <div class="form-group">
                                <label>奖励类别：</label>
                                <select id="cell5" class="form-control m-b" name="RewardTypeName">
                                    <%
                                        if (d != null) {
                                            for (int i = 0; i < d.size(); i++) {
                                    %>
                                    <option><%=d.get(i) %>
                                    </option>
                                    <%
                                            }
                                        }
                                    %>
                                    </select>
                                </div>
                            <div class="form-group">
                                <label>获奖总人数：</label>
                                <input id="cell6" type="text" class="form-control" name="RewardTotalPeople"><span
                                    class="help-block m-b-none"></span>
                            </div>

                                <div>
                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                                    <button onclick="" class="btn  btn-primary pull-left m-t-n-xs " type="submit">
                                     <i class="fa fa-check"></i>
                                    <strong>提交</strong>
                                    </button	>
                               </div>
                            </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="addJ" class="modal fade" aria-hidden="true" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <h3 class="m-t-none m-b">添加绩效</h3>
                            <form role="form" id="joinproject" action="AddSRReward!AddTandSRReward">
                                <div class="form-group">
                                    <label>工号：</label>
                                    <input id="num0" type="text" readonly="true" class="form-control" name="TeacherID"
                                           value="<%=a.getTeacherID() %>">
                                </div>
                                <div class="form-group">
                                    <label>姓名：</label>
                                    <input id="num1" type="text" readonly="true" class="form-control" name="TeacherName"
                                           readonly="true" value="<%=a.getTeacherName()%>">
                                </div>
                                <div class="form-group">
                                    <label>项目ID：</label>
                                    <input id="add2" type="text" class="form-control" name="SRRewardID" readonly="true"
                                           value="" readonly="true"><span class="help-block m-b-none"
                                                                          value="$(tid).data(id)"
                                                                          readonly="true">请输入ID</span>
                                </div>
                                <div class="form-group">
                                    <label>本人排名：</label>
                                    <input id="add3" type="text" class="form-control" name="SelfRanking"><span
                                        class="help-block m-b-none"></span>
                                </div>
                                <div>
                                    <button type="button" class="btn btn-outline btn-primary pull-right m-t-n-xs"
                                            data-dismiss="modal">关闭
                                    </button>
                                    <button onclick="" class="btn  btn-primary pull-left m-t-n-xs " type="submit">
                                        <i class="fa fa-check"></i>
                                        <strong>提交</strong>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div id="updateJ" class="modal fade" aria-hidden="true" tabindex="-1" role="dialog"
                 aria-labelledby="myModalLabel">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <div class="row">
                                <h3 class="m-t-none m-b">修改绩效</h3>
                                <form role="form" id="upJ" action="AddSRReward!updateTandSRReward">
                                    <div class="form-group">
                                        <label>工号：</label>
                                        <input id="up0" type="text" readonly="true" class="form-control"
                                               name="TeacherID" value="<%=a.getTeacherID() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>姓名：</label>
                                        <input id="up1" type="text" readonly="true" class="form-control"
                                               name="TeacherName" readonly="true" value="<%=a.getTeacherName()%>">
                                    </div>
                                    <div class="form-group">
                                        <label>项目ID：</label>
                                        <input id="num2" type="text" class="form-control" name="SRRewardID"
                                               readonly="true"
                                               value="" readonly="true"><span class="help-block m-b-none"
                                                                              value="$(tid).data(id)"
                                                                              readonly="true">请输入ID</span>
                                    </div>
                                    <div class="form-group">
                                        <label>本人排名：</label>
                                        <input id="num3" type="text" class="form-control" name="SelfRanking"><span
                                            class="help-block m-b-none"></span>
                                    </div>
                                    <div>
                                        <button type="button" class="btn btn-outline btn-primary pull-right m-t-n-xs"
                                                data-dismiss="modal">关闭
                                        </button>
                                        <button onclick="" class="btn  btn-primary pull-left m-t-n-xs " type="submit">
                                            <i class="fa fa-check"></i>
                                            <strong>提交</strong>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <script src="../js/jquery.min.js?v=2.1.4"></script>
                <script src="../js/bootstrap.min.js?v=3.3.5"></script>
                <script src="../js/plugins/jeditable/jquery.jeditable.js"></script>
                <script src="../js/plugins/chosen/chosen.jquery.js"></script>
                <script src="../js/plugins/jsKnob/jquery.knob.js"></script>
                <script src="../js/plugins/jasny/jasny-bootstrap.min.js"></script>
                <script src="../js/plugins/dataTables/jquery.dataTables.js"></script>
                <script src="../js/plugins/dataTables/dataTables.bootstrap.js"></script>
                <script src="../js/content.min.js?v=1.0.0"></script>
                <script src="../js/plugins/iCheck/icheck.min.js"></script>
                <script src="../js/plugins/sweetalert/sweetalert.min.js"></script>
                <script src="../js/plugins/cropper/cropper.min.js"></script>
                <script src="../js/plugins/datapicker/bootstrap-datepicker.js"></script>
                <script src="../js/plugins/metisMenu/jquery.metisMenu.js"></script>
                <script src="../js/plugins/clockpicker/clockpicker.js"></script>
 
   

  

    <script>
        function doupdateX(node) {
            var tr1 = node.parentNode.parentNode;
//            alert(tr1.rowIndex);
            hang = tr1.rowIndex;
            document.getElementById("cell0").value = document.getElementById("tb2").rows[hang].cells[0].innerHTML;
            document.getElementById("cell1").value = document.getElementById("tb2").rows[hang].cells[1].innerHTML;
            document.getElementById("cell2").value = document.getElementById("tb2").rows[hang].cells[2].innerHTML;
            document.getElementById("cell3").value = document.getElementById("tb2").rows[hang].cells[3].innerHTML;
            document.getElementById("cell4").value = document.getElementById("tb2").rows[hang].cells[4].innerHTML;
            document.getElementById("cell5").value = document.getElementById("tb2").rows[hang].cells[5].innerHTML;
            document.getElementById("cell6").value = document.getElementById("tb2").rows[hang].cells[6].innerHTML;
    }

        function doaddJ(node) {
            var tr1 = node.parentNode.parentNode;
//            alert(tr1.rowIndex);
            hang = tr1.rowIndex;
            document.getElementById("add2").value = document.getElementById("tb2").rows[hang].cells[0].innerHTML;
        }
        function doupdateJ(node) {
            var tr1 = node.parentNode.parentNode;
//            alert(tr1.rowIndex);
            hang = tr1.rowIndex;
            document.getElementById("num2").value = document.getElementById("tb").rows[hang].cells[2].innerHTML;
            document.getElementById("num3").value = document.getElementById("tb").rows[hang].cells[9].innerHTML
        }

        function dodelX(delid) {
    swal({ 
        title: "您确定要删除吗？",  
        text: "您确定要删除这条数据？",  
        type: "warning", 
        showCancelButton: true, 
        closeOnConfirm: true, 
        confirmButtonText: "是的，我要删除", 
        confirmButtonColor: "#ec6c62" 
    }, function() {
        document.f.action = "AddSRReward!delSRReward?SRRewardID=" + delid;
        f.submit();
    });
        }
        function dodelJ(delid, teacherid) {
            swal({
                title: "您确定要删除吗？",
                text: "您确定要删除这条数据？",
                type: "warning",
                showCancelButton: true,
                closeOnConfirm: true,
                confirmButtonText: "是的，我要删除",
                confirmButtonColor: "#ec6c62"
            }, function () {
                document.f.action = "AddSRReward!delTandSRReward?SRRewardID=" + delid + "&TeacherID=" + teacherid;
                f.submit();
            });
        }
    </script>
    <script>
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
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>