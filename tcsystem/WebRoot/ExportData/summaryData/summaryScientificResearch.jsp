<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
	<base target="_self"> 
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  
    <title>NUAA-CEM绩效管理系统 - 科研数据汇总</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">    
    
    <!-- Sweet Alert -->
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
    <script type="text/javascript">
    	function showResummaryDetail() {
			var detail = document.getElementById("Resummarydetail");
			detail.style.display = "block";
			var content = document.getElementById("content");
			content.style.display = "none";
		}
    	function back() {
			var detail = document.getElementById("Resummarydetail");
			detail.style.display = "none";
			var content = document.getElementById("content");
			content.style.display = "block";
		}
    	function summarydown() {
			window.location.replace("summaryScientificResearch!downSummary?date1="+"${param.date1}"+"&date2="+"${param.date2}");
		}
    </script>
</head>
<body class="gray-bg"  >
    <div  id="content"  class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-4">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>研究所数据汇总</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="text-center">
							<a data-toggle="modal"  data-backdrop="true" class="btn btn-primary"  data-target="#Resummary"  >预览研究所数据汇总</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>研究所明细数据汇总</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="text-center">
							<a class="btn btn-primary"   onclick="showResummaryDetail()">预览研究所明细数据汇总</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-4">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>下载汇总数据</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="text-center">
							<a class="btn btn-primary"   onclick="summarydown()">下载</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		</div>
		<div id="Resummary" class="modal fade" aria-hidden="true"  >
        <div class="modal-dialog">
            <div class="modal-content"  style="windth: 1000px">
                <div class="modal-body" >
                    <div class="row">                        
                            <h3 class="m-t-none m-b">科研汇总</h3>
							<form role="form" action="doadd!add">
								<div class="form-group">
									<div class="example">
										<form method="post" name="f">
											<table  id="tb" class="table table-striped table-bordered table-hover dataTables-example" >
					                            <thead>
					                                <tr>
														<td  class="sorting_asc">研究所ID</td>
														<td class="sorting_asc">研究所</td>
														<td class="sorting_asc">均分</td>
														<td class="sorting_asc">总分</td>
														<td class="sorting_asc">年度</td>
													</tr>
					                            </thead>
					                            <tbody>                               
													<c:forEach  var="sum"  items="${srs }">
														<tr>
															<td>${sum.researchLabID }</td>
															<td>${sum.researchLab }</td>
															<td>${sum.avgPerformance }</td>
															<td>${sum.sumPerformance }</td>
															<td>${param.date1 }-${param.date2 }</td>	
														</tr>
													</c:forEach>
					                            </tbody>                           
					                        </table>
										</form>
									</div>
								</div>
								<div>
									<button type="button" class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>									
								</div>
							</form>
						</div>
                </div>
            </div>
        </div>
    </div>                
      <div  class="wrapper wrapper-content animated fadeInRight">
	     <div  id="Resummarydetail"   class="row"  style="display: none">
	            <div class="col-sm-12">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>科研汇总明细 <small></small></h5>
	                        <div class="ibox-tools">
	                            <a class="collapse-link">
	                                <i class="fa fa-chevron-up"></i>
	                            </a>                           
	                            <a class="close-link">
	                                <i class="fa fa-times"></i>
	                            </a>
	                        </div>
	                    </div>
	                    <div class="ibox-content">
	                    
	                    <div class="">
	                         <button class="btn  btn-primary" type="submit"  onclick="back()">
	                         <strong>返回</strong>
	                         </button>	                            
	                        </div>
	                        <div class="example">
	                        <form method="post" name="f">
	                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example" >
	                            <thead>
	                                <tr>
	                                	<td  class="sorting_asc"></td>
										<td  class="sorting_asc">研究所ID</td>
										<td class="sorting_asc">研究所</td>
										<td class="sorting_asc">期刊论文</td>
										<td class="sorting_asc">学术著作</td>
										<td class="sorting_asc">邀请专家讲学</td>
										<td class="sorting_asc">主承办学术会议</td>
										<td class="sorting_asc">参加学术会议</td>
										<td class="sorting_asc">科研项目</td>
										<td class="sorting_asc">项目奖励</td>
										<td class="sorting_asc">入选人才工程</td>
										<td class="sorting_asc">年度</td>										
									</tr>
	                            </thead>
	                            <tbody>                               
									<c:forEach  var="sum"  items="${srs }">
										<tr>
											<td>总分</td>
											<td>${sum.researchLabID }</td>
											<td>${sum.researchLab }</td>
											<td>${sum.SRModular.periodicalPaperSum }</td>
											<td>${sum.SRModular.academicWorkSum }</td>
											<td>${sum.SRModular.invitedExpertsSpeechSum }</td>
											<td>${sum.SRModular.mainUndertakeAcademicMeetingSum }</td>
											<td>${sum.SRModular.joinAcademicMeetingSum }</td>
											<td>${sum.SRModular.scientificResearchProjectSum }</td>
											<td>${sum.SRModular.scientificResearchRewardSum }</td>
											<td>${sum.SRModular.selectedTalentProjectSum }</td>
											<td>${param.date1 }-${param.date2 }</td>
										</tr>
										<tr>
											<td>均分</td>
											<td>${sum.researchLabID }</td>
											<td>${sum.researchLab }</td>
											<td>${sum.SRModular.periodicalPaperAvg }</td>
											<td>${sum.SRModular.academicWorkAvg }</td>
											<td>${sum.SRModular.invitedExpertsSpeechAvg }</td>
											<td>${sum.SRModular.mainUndertakeAcademicMeetingAvg }</td>
											<td>${sum.SRModular.joinAcademicMeetingAvg }</td>
											<td>${sum.SRModular.scientificResearchProjectAvg }</td>
											<td>${sum.SRModular.scientificResearchRewardAvg }</td>
											<td>${sum.SRModular.selectedTalentProjectAvg }</td>
											<td>${param.date1 }-${param.date2 }</td>
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
	 </div>   
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script>
					$(document).ready(function() {
						$(".i-checks").iCheck({
							checkboxClass : "icheckbox_square-green",
							radioClass : "iradio_square-green",
						})
					});
				</script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
  </body>
</html>
