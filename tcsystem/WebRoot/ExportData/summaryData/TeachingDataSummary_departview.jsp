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

    <title>Teaching-DATASUMMARY --DEPART-</title>
    
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
    <script  src="My97DatePicker/WdatePicker.js"></script>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script  src="js/PublicCheck/PUB_SET.js"></script>
    <!-- ISBN输入控制 -->
    <script src="js/plugins/jasny/jasny-bootstrap.min.js"></script>
    <!-- sweet-alert -->
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
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

<body class="gray-bg">
	                    <div class="ibox-content" style="height:540px;">
	                    <button class="btn  btn-primary exportsumd" type="button" data-backdrop="true">
	                        	 <strong>导出</strong>
	                         </button>
	                    <br>
	                        <div class="example" style="overflow: auto">
	                       <table id="tb" class="table table-striped table-bordered table-hover dataTables-example">
	                                <tr>
										<td></td>
										<td colspan="5" style="text-align: center;">教学能力与实效</td>
										<td colspan="8" style="text-align: center;">综合改革与教学研究</td>
										<td colspan="5" style="text-align: center;">学生指导工作</td>
										<td></td>
									</tr>
									<tr>
										<td>系</td>
										<td>课堂教学(总/均)</td>
										<td>学位论文指导质量(总/均)</td>
										<td>教学竞赛(总/均)</td>
										<td>教学能力提升(总/均)</td>
										<td>教学名师和教学团队(总/均)</td>
										<td>教学研究(总/均)</td>
										<td>教学论文(总/均)</td>
										<td>教学成果奖(总/均)</td>
										<td>教材建设(总/均)</td>
										<td>精品课程建设(总/均)</td>
										<td>专业建设项目申报(总/均)</td>
										<td>企业工作站和联合培养基地建设(总/均)</td>
										<td>暑期课程与国际课程建设(总/均)</td>
										<td>实践创新指导(总/均)</td>
										<td>学生竞赛指导(总/均)</td>
										<td>参与学生活动(总/均)</td>
										<td>本科生导师指导(总/均)</td>
										<td>校外实践指导(总/均)</td>
										<td>总计(总/均)</td>
									</tr>
	                            <tbody>
									<c:forEach var="ebj" items="${alldepartsumdata }">
										<tr>
											<td title="${ebj.departs.departmentId }">${ebj.departs.departmentName }</td>
											<td>${ebj.classTeaching.sum }/${ebj.classTeaching.avg }</td>
											<td>${ebj.degreeGuidance.sum }/${ebj.degreeGuidance.avg }</td>
											<td>${ebj.teachingCompetition.sum }/${ebj.teachingCompetition.avg }</td>
											<td>${ebj.teachingAbilityImprove.sum }/${ebj.teachingAbilityImprove.avg }</td>
											<td>${ebj.famousTeacherTeam.sum }/${ebj.famousTeacherTeam.avg }</td>
											<td>${ebj.teachingResearch.sum }/${ebj.teachingResearch.avg }</td>
											<td>${ebj.teachingPaper.sum }/${ebj.teachingPaper.avg }</td>
											<td>${ebj.teachingAchievement.sum }/${ebj.teachingAchievement.avg }</td>
											<td>${ebj.textBookConstruction.sum }/${ebj.textBookConstruction.avg }</td>
											<td>${ebj.fineCourse.sum }/${ebj.fineCourse.avg }</td>
											<td>${ebj.professionalProjectConstruction.sum }/${ebj.professionalProjectConstruction.avg }</td>
											<td>${ebj.firmWorkstationTrainingBase.sum }/${ebj.firmWorkstationTrainingBase.avg }</td>
											<td>${ebj.summerInternationalCourse.sum }/${ebj.summerInternationalCourse.avg }</td>
											<td>${ebj.practiceInnovationGuidance.sum }/${ebj.practiceInnovationGuidance.avg }</td>
											<td>${ebj.studentsCompetitionGuidance.sum }/${ebj.studentsCompetitionGuidance.avg }</td>
											<td>${ebj.studentsActivity.sum }/${ebj.studentsActivity.avg }</td>
											<td>${ebj.undergraduateGuidance.sum }/${ebj.undergraduateGuidance.avg }</td>
											<td>${ebj.off_campusPracticeGuidance.sum }/${ebj.off_campusPracticeGuidance.avg }</td>
											<td>${ebj.sumUP.sum }/${ebj.sumUP.avg }</td>
										</tr>
									</c:forEach>
	                            </tbody>                           
	                        </table>
	                   </div>
	                </div>
    
    
	<script type="text/javascript">
	
    $(document).click(function(e) {
    	if(e.target.className.indexOf("deletemember")>=0){
    		var row  = $(e.target).parent().parent();
    		swal({   
        		title: "确定移除该成员?",   
        		text: "即将移除...",   
        		type: "warning",   
        		showCancelButton: true,   
        		confirmButtonColor: "#DD6B55",   
        		confirmButtonText: "移除",
        		cancelButtonText: "取消",   
        		closeOnConfirm: false,   
        		closeOnCancel: true }, 
        			function(isConfirm){   
        				if (isConfirm&pubsprojectId!="") {
        					$.post("ATacademicwk-workset!deleteMember",
        							{"academicwk.acaworkId":pubsprojectId,
        						 	 "teacherandaw.teacher.teacherId":row[0].cells[0].innerHTML.trim()},
        							function(data,status){
        								if(status=="success"){
        									if(data=="succ"){
        										swal("删除成功","","success");
        										setTimeout(function() {
        											window.location.replace("ATacademicwk-workset!getWorkall?pagenum=1");
    											}, 2000);
        									}else{
        										swal("操作失败",data,"error");
        									}
        								}else{
        									swal("请求失败","","error");
        								}
        							}
        					);
        				}else{
        					swal("操作失败","意外故障...","error");
        				}
        			}
        	);
    	}
    });
    $('.exportsumd').click(function() {
		swal("正在导出 . . . ");
	});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>