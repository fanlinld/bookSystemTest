<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乐辅通管理平台修改密码</title>
<link href="../../images/favicon.ico" rel="shortcut icon">
<style type="text/css">
table td{
	word-break: keep-all;
	white-space:nowrap;
}
</style>
</head>
<body class="easyui-layout" style="overflow-y: hidden" fit="true"
	scroll="no">
	<jsp:include page="../common/top.jsp"></jsp:include>
	<jsp:include page="../common/foot.jsp"></jsp:include>
	<jsp:include page="../common/left.jsp"></jsp:include>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="p" class="easyui-panel" title="账号管理>修改密码"
			style="width: 100%; height: 100%; padding: 10px;">
		<div id="w" class="easyui-panel" title="修改密码"
			style="width: 350px; padding: 10px;">
			<table  cellpadding="5">
              <tr>
               <td >旧密码:</td>
                <td>
				<input id="oldpass" class="easyui-textbox" type="password"
					style="width: 200px; height: 30px; padding: 12px"
					data-options="prompt:'旧密码',iconCls:'icon-lock',iconWidth:38">
				</td>
				</tr>
                <tr>
				<td  width="50px">新密码:</td>
                <td>
				<input id="newpass" class="easyui-textbox" type="password"
					style="width: 200px; height: 30px; padding: 12px"
					data-options="prompt:'新密码',iconCls:'icon-lock',iconWidth:38">
				</td>
				</tr>
                <tr>
				<td width="50px">确认密码:</td>
                <td>
				<input id="confirmpass" class="easyui-textbox" type="password"
					style="width: 200px; height: 30px; padding: 12px"
					data-options="prompt:'确认密码',iconCls:'icon-lock',iconWidth:38">
				</td>
			</tr>
            </table>
			<div style="padding: 10px 100px">
				<a id="edit" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-ok'"
					style="padding: 5px 0px; width: 100px;"> 
				<span
					style="font-size: 14px;">修改</span>
				</a>
			</div>
		</div>
	</div>
</div>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$("#edit").click(function(){
			var oldPass=$("#oldpass").val();
			if(oldPass==null || oldPass==""){
				$.messager.alert('提示信息','请输入旧密码','info');
				$("#oldpass").select();
				return;
			}
			var newPass=$("#newpass").val();
			if(newPass==null || newPass==""){
				$.messager.alert('提示信息','请输入新密码','info');
				$("#newpass").select();
				return;
			}
			
			var confirmPass=$("#confirmpass").val();
			if(confirmPass==null || confirmPass==""){
				$.messager.alert('提示信息','请再次输入新密码','info');
				$("#confirmPass").select();
				return;
			}
			if(newPass==oldPass){
				$.messager.alert('提示信息','新密码不能与原密码相同','info');
				$("#newpass").select();
				return;
			}
			if(confirmPass!=newPass){
				$.messager.alert('提示信息','两次填写的密码不一致','info');
				$("#confirmPass").select();
				return;
			}
			var url="../../user/editpass";
			$.getJSON(url,{"oldPass":$.md5(oldPass),"newPass":$.md5(newPass),"loginType":3},function(data){
				if(data!=null){
					if(data.success){
						$.messager.alert('提示信息',data.message,'info',function(){
							loginOut();
						});
					}else{
						$.messager.alert('提示信息',data.message,'info');
					}
				}
			});
		});
	});
</script>
</body>
</html>