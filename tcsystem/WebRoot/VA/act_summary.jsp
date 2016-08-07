<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>act_manage_summary</title>
        <link rel="stylesheet" type="text/css" href="../css/zxma.css">
        <script type="text/javascript" src="../js/act_summary.js"></script>
    </head>
    <body>
        <div style="width: auto">
            <div class="actmana">
                <div id="inact">
                    <span id="span1" onmousedown="change_s('span1')" onmouseup="recoberstyle('span1','test.html')" title="记得在第三个按钮上多停留一会儿哦 . . . " ><a  target="ifm" href="test.html" >我的公益活动</a></span> <br>
                    <span id="span2" onmousedown="change_s('span2')" onmouseup="recoberstyle('span2','../VA/add_join_act.jsp')" title="记得在第三个按钮上多停留一会儿哦 . . . "><a target="ifm" href="../VA/add_join_act.jsp">添加我所参与过的公益活动</a></span><br>
                    <span id="span3" onmousedown="change_s('span3')" onmouseup="recoberstyle('span3','test.html')" title="未补充将对此次规定性公益活动的绩效分-累计对应的负分"><a target="ifm" href="test.html">补充未参加的规定性活动的原因</a></span>
                </div>
            </div>
            <div class="framework">
                <div id="ifk">
                    <img id="imges" src="../img/close.png" alt="" title="点击关闭" onmousedown="closeframemoused()" onmouseup="closeframemouseu()">
                </div>
                <iframe id="of" name="ifm" src=""></iframe>
            </div>
        </div>
    </body>
</html>