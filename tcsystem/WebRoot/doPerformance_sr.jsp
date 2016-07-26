<%@ page language="java"  pageEncoding="UTF-8"%>

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

                        <div class="project-list">

                            <table class="table table-hover">
                                <tbody>
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">期刊论文</a>
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
                                            <a href="<%=basePath %>PersonManager/updatePerformance_qklw" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-default">已结束
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">学术著作</a>
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
                                            <a href="<%=basePath %>PersonManager/updatePerformance_xszz" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">科研项目</a>
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
                                            <a href="<%=basePath %>PersonManager/updatePerformance_kyxm"
                                               class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">项目奖励</a>
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
                                            <a href="<%=basePath %>PersonManager/updatePerformance_xmjl"
                                               class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">参加学术会议</a>
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
                                            <a href="<%=basePath %>PersonManager/updatePerformance_cjxshy"
                                               class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">邀请专家讲学</a>
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
                                            <a href="<%=basePath %>PersonManager/updatePerformance_yqzjjx"
                                               class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">主承办学术会议</a>
                                            <br/>
                                            <small>创建于 2014.08.15</small>
                                        </td>
                                        <td class="project-completion">
                                                <small>当前进度： 48%</small>
                                                <div class="progress progress-mini">
                                                    <div style="width: 60%;" class="progress-bar"></div>
                                                </div>
                                        </td>
                                        <td class="project-people">
                                        </td>
                                        <td class="project-actions">
                                            <a href="<%=basePath %>PersonManager/updatePerformance_zcbxshy"
                                               class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
                                        </td>
                                    </tr>
                                    
                                     <tr>
                                        <td class="project-status">
                                            <span class="label label-primary">进行中
                                        </td>
                                        <td class="project-title">
                                            <a href="project_detail.html">入选人才工程</a>
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
                                            <a href="<%=basePath %>PersonManager/updatePerformance_rxrcgc"
                                               class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 查看 </a>
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
