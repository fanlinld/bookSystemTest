<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=basePath%>css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.4/themes/metro-blue/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.4/themes/icon.css" />

<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="<%=basePath%>js/util.js"></script>
<script type="text/javascript" src="<%=basePath%>js/MD5.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-form.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.4/plugins/jquery.parser.js"></script>

<link rel="stylesheet" href="<%=basePath%>zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=basePath%>zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=basePath%>zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=basePath%>zTree_v3/js/jquery.ztree.exedit-3.5.js"></script>

<noscript>
	<div style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
		<img src="<%=basePath%>images/noscript.gif" alt='抱歉，请开启脚本支持！' />
	</div>
</noscript>
<div region="north" split="false" border="false"
	style="overflow: hidden; height: 80px; background: url(<%=basePath%>images/bkowl.png) #7f99be center 50%; line-height: 55px; color: #fff; font-family: Verdana, Î¢ÈíÑÅºÚ, ºÚÌå">
	<span style="float: right; padding-right: 20px;" class="head">
        <a href="javascript:void(0)"><i class="icon man">man</i>${BookManageSystem_User.user_name}</a>
		<a href="<%=basePath%>page/index.jsp" id="firstpage">系统消息(0)</a>
		<a href="<%=basePath%>page/selfcenter/editpass.jsp" id="editpass">系统管理</a>
		<a href="javascript:loginOut();" id="loginOut">退出</a>
	</span>
	<span style="padding-left: 10px; font-size: 16px;">
		<img src="<%=basePath%>images/logo02.png" width="36" height="36" align="absmiddle" />
		<span class="title">OWL书籍管理系统</span>
	</span>
</div>
<script>
	function loginOut(){
		var url="<%=basePath%>user/loginOut";
		$.post(url,function(data){
			delCookie("auto_login");
			window.location.href="<%=basePath%>login.htm";
		});
	}
</script>




