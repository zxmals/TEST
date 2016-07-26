<%@ page language="java" pageEncoding="UTF-8" %>

<jsp:directive.page import="com.nuaa.ec.science.Permodel.TeacherLoginStatus"/>

<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
TeacherLoginStatus a = (TeacherLoginStatus)session.getAttribute("teacherloginStatus");
 
%>

<!DOCTYPE html>
<html>

<head>
    <base target="_self"> 
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>NUAA-CEM绩效管理系统 - 项目</title>
    

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet">
    <base target="_blank">
    
    <script type="text/javascript">
  		function test() {
      		var obj=document.getElementById('mySelect');  
      		var index=obj.selectedIndex; //序号，取当前选中选项的序号  
      		var val = obj.options[index].value; 
      		window.location.replace('ScientificAudit?oints='+val);
		}
  		
  		function modualchange() {
			var obj = document.getElementById("modualSelect").valueOf();
			var index=obj.selectedIndex; 
      		var val = obj.options[index].value; 
			if(val!="null"){
				if(val==0){
					document.getElementById("case1").style.display="none";
					document.getElementById("case2").style.display="none";
				}
				if(val==1){
					document.getElementById("case0").style.display="none";
					document.getElementById("case2").style.display="none";
				}	
				if(val==2){
					document.getElementById("case0").style.display="none";
					document.getElementById("case1").style.display="none";
				}	
				document.getElementById("case"+val).style.display="block";
			}
			else{
				document.getElementById("case0").style.display="none";
				document.getElementById("case1").style.display="none";
				document.getElementById("case2").style.display="none";
			}
			
		}
  </script>
  
</head>

<body class="gray-bg">

    <div class="wrapper wrapper-content animated fadeInUp">
        <div class="row">
            <div class="col-sm-12">

                <div class="ibox">
                    <div class="ibox-title">
                        <h5>所有项目</h5>
                        <div class="ibox-tools">
                            <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm">
                        <label class="col-sm-1 control-label">选择模块：</label>
                           <div class="col-sm-2 pull-left">
                                 <select class="form-control"  name=""  id="modualSelect"  onchange="modualchange()">
						             <option value="0">教学能力与实效</option>
						             <option value="1">综合改革与教学研究</option>
						             <option value="2">学生指导工作</option>
                               </select>
                          </div>
                          </div>
                          <div class="row m-b-sm m-t-sm">
                            <div class="col-md-1">
                                <button type="button" id="loading-example-btn" class="btn btn-white btn-sm"><i class="fa fa-refresh"></i> 刷新</button>
                            </div>
                            
                            
                            <div class="col-md-11">
                                <div class="input-group">
                                    <input type="text" placeholder="请输入项目名称" class="input-sm form-control"> <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
                        
                         <div id="case0" style="display:true" class="project-list">

                            <table class="table table-hover">
                                <tbody>
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">课堂教学绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                                <small>当前进度： 48%</small>
                                                <div class="progress progress-mini">
                                                    <div style="width: 48%;" class="progress-bar"></div>
                                                </div>
                                        </td>
                                        <td class="project-people">
                                        </td>
                                        <td class="project-actions">
                                            <a href="<%=basePath %>PersonManager/updatePerformance_tf_ktjx" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">学位论文指导质量绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                            <small>当前进度： 100%</small>
                                            <div class="progress progress-mini">
                                                <div style="width: 8%;" class="progress-bar"></div>
                                            </div>
                                        </td>
                                        <td class="project-people">
                                            
                                        </td>
                                        <td class="project-actions">
                                            <a href="<%=basePath %>PersonManager/updatePerformance_tf_xwlwzd" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">教学竞赛绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                                <small>当前进度： 48%</small>
                                                <div class="progress progress-mini">
                                                    <div style="width: 48%;" class="progress-bar"></div>
                                                </div>
                                        </td>
                                        <td class="project-people">
                                        </td>
                                        <td class="project-actions">
                                            <a href="<%=basePath %>PersonManager/updatePerformance_tf_jxjs" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">教学能力提升绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                                <small>当前进度： 48%</small>
                                                <div class="progress progress-mini">
                                                    <div style="width: 48%;" class="progress-bar"></div>
                                                </div>
                                        </td>
                                        <td class="project-people">
                                        </td>
                                        <td class="project-actions">
                                            <a href="<%=basePath %>PersonManager/updatePerformance_tf_jxnlts" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">教学名师和教学团队绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                                <small>当前进度： 48%</small>
                                                <div class="progress progress-mini">
                                                    <div style="width: 48%;" class="progress-bar"></div>
                                                </div>
                                        </td>
                                        <td class="project-people">
                                        </td>
                                        <td class="project-actions">
                                            <a href="<%=basePath %>PersonManager/updatePerformance_tf_jxmshjxtd" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    
                                    </tbody>
                                </table>
                            </div>

                        <div id="case1" style="display:none" class="project-list">

                            <table class="table table-hover">
                                <tbody>
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">教学研究绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                                <small>当前进度： 48%</small>
                                                <div class="progress progress-mini">
                                                    <div style="width: 48%;" class="progress-bar"></div>
                                                </div>
                                        </td>
                                        <td class="project-people">
                                        </td>
                                        <td class="project-actions">
                                            <a href="<%=basePath %>PersonManager/updatePerformance_tf_jxyj" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">教学论文绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                            <small>当前进度： 100%</small>
                                            <div class="progress progress-mini">
                                                <div style="width: 8%;" class="progress-bar"></div>
                                            </div>
                                        </td>
                                        <td class="project-people">
                                            
                                        </td>
                                        <td class="project-actions">
                                            <a href="<%=basePath %>PersonManager/updatePerformance_tf_jxlw" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-default">已结束
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">教学成果奖绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                            <small>当前进度： 100%</small>
                                            <div class="progress progress-mini">
                                                <div style="width: 8%;" class="progress-bar"></div>
                                            </div>
                                        </td>
                                        <td class="project-people">
                                            
                                        </td>
                                        <td class="project-actions">
                                            <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-default">已结束
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">教材建设绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                            <small>当前进度： 100%</small>
                                            <div class="progress progress-mini">
                                                <div style="width: 8%;" class="progress-bar"></div>
                                            </div>
                                        </td>
                                        <td class="project-people">
                                            
                                        </td>
                                        <td class="project-actions">
                                            <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-default">已结束
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">精品课程建设绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                            <small>当前进度： 100%</small>
                                            <div class="progress progress-mini">
                                                <div style="width: 8%;" class="progress-bar"></div>
                                            </div>
                                        </td>
                                        <td class="project-people">
                                            
                                        </td>
                                        <td class="project-actions">
                                            <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-default">已结束
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">专业建设项目申报绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                            <small>当前进度： 100%</small>
                                            <div class="progress progress-mini">
                                                <div style="width: 8%;" class="progress-bar"></div>
                                            </div>
                                        </td>
                                        <td class="project-people">
                                            
                                        </td>
                                        <td class="project-actions">
                                            <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">企业工作站和联合培养基地建设绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                            <small>当前进度： 100%</small>
                                            <div class="progress progress-mini">
                                                <div style="width: 8%;" class="progress-bar"></div>
                                            </div>
                                        </td>
                                        <td class="project-people">
                                            
                                        </td>
                                        <td class="project-actions">
                                            <a href=""
                                               class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-default">已结束
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">暑期课程与国际课程建设绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                            <small>当前进度： 100%</small>
                                            <div class="progress progress-mini">
                                                <div style="width: 8%;" class="progress-bar"></div>
                                            </div>
                                        </td>
                                        <td class="project-people">
                                            
                                        </td>
                                        <td class="project-actions">
                                            <a href="<%=basePath %>PersonManager/updatePerformance_tf_sqkc"
                                               class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                   
                                    
                                    </tbody>
                                </table>
                            </div>
                            
                            <div id="case2" style="display:none" class="project-list">

                            <table class="table table-hover">
                                <tbody>
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">实践创新指导绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                                <small>当前进度： 48%</small>
                                                <div class="progress progress-mini">
                                                    <div style="width: 48%;" class="progress-bar"></div>
                                                </div>
                                        </td>
                                        <td class="project-people">
                                        </td>
                                        <td class="project-actions">
                                            <a href="<%=basePath %>PersonManager/updatePerformance_tf_jxyj" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-default">已结束
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">学生竞赛指导绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                            <small>当前进度： 100%</small>
                                            <div class="progress progress-mini">
                                                <div style="width: 8%;" class="progress-bar"></div>
                                            </div>
                                        </td>
                                        <td class="project-people">
                                            
                                        </td>
                                        <td class="project-actions">
                                            <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-default">已结束
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">参与学生活动绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                            <small>当前进度： 100%</small>
                                            <div class="progress progress-mini">
                                                <div style="width: 8%;" class="progress-bar"></div>
                                            </div>
                                        </td>
                                        <td class="project-people">
                                            
                                        </td>
                                        <td class="project-actions">
                                            <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-default">已结束
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">本科生导师指导绩效</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                            <small>当前进度： 100%</small>
                                            <div class="progress progress-mini">
                                                <div style="width: 8%;" class="progress-bar"></div>
                                            </div>
                                        </td>
                                        <td class="project-people">
                                            
                                        </td>
                                        <td class="project-actions">
                                            <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-default">已结束
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">校外实践指导绩效审核</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                            <small>当前进度： 100%</small>
                                            <div class="progress progress-mini">
                                                <div style="width: 8%;" class="progress-bar"></div>
                                            </div>
                                        </td>
                                        <td class="project-people">
                                            
                                        </td>
                                        <td class="project-actions">
                                            <a href="projects.html#" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script>
        $(document).ready(function () {
            $("#loading-example-btn").click(function () {
                btn = $(this);
                simpleLoad(btn, true);
                simpleLoad(btn, false)
            })
        });
        function simpleLoad(btn, state) {
            if (state) {
                btn.children().addClass("fa-spin");
                btn.contents().last().replaceWith(" Loading")
            } else {
                setTimeout(function () {
                    btn.children().removeClass("fa-spin");
                    btn.contents().last().replaceWith(" Refresh")
                }, 2000)
            }
        }
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    </body>

  </body>
</html>
