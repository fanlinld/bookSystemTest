<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
<link href="../../images/favicon.ico" rel="shortcut icon">
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 160px;
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
		<div id="p" class="easyui-panel" title="系统管理>用户管理"
			style="width: 100%; height: 100%; padding: 10px;">
			<table id="dg">
				<thead>
					<tr>
						<th data-options="field:'user_name',width:70">用户名</th>
						<th
							data-options="field:'power',width:30,
							formatter: function(value,row,index){
								if (value==1){
									return '系统管理员';
								}else if(value==2){
									return '图书管理员';
								}else if(value==3){
									return '用户';
								}
							}">身份</th>
						<th data-options="field:'phone',width:50">手机</th>
						<th data-options="field:'qq',width:50">QQ</th>
					</tr>
				</thead>
			</table>

			<div id="userInfo" style="margin-top: 20px; display: none">
				<div class="easyui-panel" title="用户信息" style="width: 650px">
					<div style="padding: 10px 20px 10px 20px">
						<form id="fm">
							<table cellpadding="3" cellspacing="3">
								<tr>
									<td>身份:</td>
									<td><input id="selpower" class="easyui-combobox"
										name="selpower" style="width: 150px"></td>
								</tr>
								<tr>
									<td>用户名:</td>
									<td><input id="userName" name="userName"
										class="easyui-textbox" style="width: 200px;"></input></td>
								</tr>
								<tr>
									<td>真实姓名:</td>
									<td><input id="realName" name="realName"
										class="easyui-textbox" prompt="选填" style="width: 200px;"></input></td>
								</tr>
								<tr>
									<td>电话号码:</td>
									<td><input id="phone" name="phone" class="easyui-textbox"
										prompt="选填" style="width: 200px;"></input></td>
								</tr>
								<tr>
									<td>QQ:</td>
									<td><input id="qq" name="qq" class="easyui-textbox"
										prompt="选填" style="width: 200px;"></input></td>
								</tr>
								<tr>
									<td>登录密码:</td>
									<td><input id="password" name="password" type="password"
										class="easyui-textbox" style="width: 200px;"></input></td>
								</tr>
								<tr>
									<td>确认密码:</td>
									<td><input id="qpassword" name="qpassword" type="password"
										class="easyui-textbox" style="width: 200px;"></input></td>
								</tr>
							</table>
						</form>
					</div>

					<div id="dlg-buttons" style="padding: 0px 120px 20px 120px">
						<a href="javascript:void(0)" class="easyui-linkbutton c6"
							iconCls="icon-ok" onclick="saveUser()" style="width: 90px">保存</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-cancel"
							onclick="javascript:$('#fm').form('clear');" style="width: 90px">清空</a>
					</div>

					<div id="toolbar">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-add" plain="true" onclick="newUser()">添加</a> <a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-redo" plain="true" onclick="resetPass()">重置密码</a> <a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-remove" plain="true" onclick="delInfo()">删除</a> <a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-undo" plain="true" onclick="cancel()">取消</a> <input
							id="ss" class="easyui-searchbox" style="width: 250px"></input>
					</div>

					<div id="mm" style="width: 120px;">
						<div data-options="name:'userName'">用户名</div>
						<div data-options="name:'phone'">手机号</div>
						<div data-options="name:'qq'">QQ号</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>


<script type="text/javascript">
	var seluserName="";
	var selphone="";
	var selqq="";
	$(document).ready(function() {
		loadData();
	});
	function loadData() {
		//datagrid初始化 
		$('#dg').datagrid({
			title : 'My Users',
			//iconCls : 'icon-edit',//图标 
			width : 860,
			height : 367,
			url : '../../user/selectUsers?selUserName='+encodeURIComponent(seluserName)
					+'&selphone='+selphone+'&selqq='+selqq,
			idField : 'fldId',
			singleSelect : true,//是否单选 
			pagination : true,//分页控件 
			rownumbers : true,//行号 
			fitColumns : true,
			toolbar : "#toolbar"
		});

		//设置分页控件 
		var p = $('#dg').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10 
			pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表 
			beforePageText : '第',//页数文本框前显示的汉字 
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
		// 设置搜索框
		$('#ss').searchbox({
			menu : '#mm',
			prompt : 'Please Input Value'
		});
	}
	
	$('#ss').searchbox({
		searcher : function(value, name) {
			//alert(value + "," + name);
			var url = '../../user/selectUsers';
			if (name == 'userName') {
				url = '../../user/selectUsers?selUserName='
						+ encodeURIComponent(value);
				seluserName=value;
				selphone="";
				selqq="";
			}
			if (name == 'phone') {
				url = '../../user/selectUsers?selphone='
						+ encodeURIComponent(value);
				selphone=value;
				seluserName="";
				selqq="";
			}
			if (name == 'qq') {
				url = '../../user/selectUsers?selqq='
						+ encodeURIComponent(value);
				selqq=value;
				seluserName="";
				selphone="";
			}
			$('#dg').datagrid('options').url = url;
			$('#dg').datagrid('reload');
		}
	});

	function newUser() {
		$('#fm').form('clear');
		$("#userInfo").show();
		$('#selpower').combobox({
			url : '../../roles/getRolesComeBox',
			valueField : 'id',
			textField : 'title',
			editable : false
		});
	}

	function saveUser() {
		var power = $('#selpower').combobox('getValue');
		var userName = $("#userName").val();
		if (userName == null || userName == "") {
			$.messager.alert("提示信息", "请输入用户名", "info");
			return;
		}
		var realName = $("#realName").val();
		var phone = $("#phone").val();
		var qq = $("#qq").val();

		var password = $("#password").val();
		if (password == null || password == "") {
			$.messager.alert("提示信息", "请输入登录密码", "info");
			return;
		}
		var qpassword = $("#qpassword").val();
		if (qpassword == null || qpassword == "") {
			$.messager.alert("提示信息", "请输入确认登录密码", "info");
			return;
		} else {
			if (password != qpassword) {
				$.messager.alert("提示信息", "两次输入的密码不一致", "info");
				return;
			}
		}
		var url = "../../user/insertUser";
		$.post(url, {
			"power" : power,
			"user_name" : userName,
			"password" : $.md5(password),
			"realName" : realName,
			"phone" : phone,
			"qq" : qq
		}, function(data) {
			var result = $.parseJSON(data);
			$.messager.alert("提示信息", result.message, "info");
			if (result.success) {
				loadData();
				$("#userInfo").hide();
			}
		});
	}

	function delInfo() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm("删除提示", "确定要删除吗？", function(data) {
				if (data) {
					userid = row.id;
					var url = "../../user/deleteUser";
					$.post(url, {
						"id" : userid
					}, function(result) {
						result = $.parseJSON(result);
						if (result.success) {
							loadData();
							$.messager.alert('提示信息', result.message, 'info');
						} else {
							$.messager.alert('提示信息', result.message, 'info');
						}
					});
				}
			});
		} else {
			$.messager.alert('提示信息', '请选择一条数据', 'info');
		}
	}

	function resetPass() {
		var row = $('#dg').datagrid('getSelected');
		$.messager.confirm("信息提示", "确定要重置密码吗？", function(data) {
			if (data) {
				if (row) {
					userid = row.id;
					var url = "../../user/resetUserpass";
					$.post(url, {
						"id" : userid
					}, function(result) {
						result = $.parseJSON(result);
						if (result.success) {
							$.messager.alert('提示信息', result.message, 'info');
						} else {
							$.messager.alert('提示信息', result.message, 'info');
						}
					});
				} else {
					$.messager.alert('提示信息', '请选择一条数据', 'info');
				}
			}
		});
	}

	function cancel() {
		$("#userInfo").hide();
		$("#dg").datagrid('unselectAll');
	}
</script>
</html>