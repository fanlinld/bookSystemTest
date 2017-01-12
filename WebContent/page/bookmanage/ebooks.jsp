<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>书籍管理</title>
<link href="../../images/books.ico" rel="shortcut icon">

<style type="text/css">
	table td{
		word-break: keep-all;
	}

</style>
</head>
<body class="easyui-layout" style="overflow-y: hidden" fit="true"
	scroll="no">
	<jsp:include page="../common/top.jsp"></jsp:include>
	<jsp:include page="../common/foot.jsp"></jsp:include>
	<jsp:include page="../common/left.jsp"></jsp:include>
	<div id="mainPanle" region="center"	style="background: #eee; overflow-y: hidden">
		<div id="p" class="easyui-panel" title="书籍管理>图书管理"	style="width: 100%; height: 100%; padding: 10px;">
			<table id="dg">
				<thead>
					<tr>
						<th data-options="field:'name',width:40,formatter: function(value,row,index){
								return '<a href=javascript:openbook(\''+row.link+'\');>'+value+'</a>';
							}">书名</th>
						<th data-options="field:'author',width:30,sortable:true">作者</th>
						<th data-options="field:'a',width:20,align:'center',
							formatter: formatCheck"> 阅读记录</th>
					</tr>
				</thead>
			</table>
			<div style="padding: 5px 0px 5px 0px" id="toolbar">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" onclick="newBook()">添加</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" onclick="editBook()">修改</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true" onclick="delInfo()">删除</a>
				<a	href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-undo" plain="true" onclick="cancel()">取消</a> 
				<div style="padding: 5px" >
					<span>类别: </span>
	       			<input id="selclass" class="easyui-combobox" name="selclass" style="width: 150px"></input>  
	         			
	         			<span>书名: </span>
	       			<input id="selBookName" class="easyui-textbox" prompt="输入部分文字即可查询" style="width:150px" /> 
	       			<span>作者: </span>
	       			<input id="selAuthorName" class="easyui-textbox" prompt="输入部分文字即可查询" style="width:150px" /> 
	         			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="dosearch()">查询</a>
				</div>
			</div>	
			
			<div id="bookInfo" style="margin-top: 20px; display: none">
				<div class="easyui-panel" title="图书信息" style="width: 400px">
					<div style="padding: 10px 20px 10px 20px">
						<table>
							<tr>
								<td>类别:</td>
								<td><input id="classify" class="easyui-combobox" name="classify" style="width: 150px">
								</td>
							</tr>
							<tr>
								<td>书名:</td>
								<td><input id="name" name="name" class="easyui-textbox"
									style="width: 200px;"></input></td>
							</tr>
							<tr>
								<td>外文书名:</td>
								<td><input id="enname" name="enname" class="easyui-textbox"
									style="width: 200px;"></input></td>
							</tr>
							<tr>
								<td>作者:</td>
								<td><input id="author" name="author" class="easyui-textbox"
									style="width: 200px;"></input></td>
							</tr>
							<tr>
								<td>译者:</td>
								<td><input id="translator" name="translator" class="easyui-textbox"
									style="width: 200px;"></input></td>
							</tr>
							<tr>
								<td>地址:</td>
								<td><input id="link" name="link" class="easyui-textbox"
									style="width: 300px;"></input></td>
							</tr>
						</table>
						<div id="dlg-buttons" style="padding: 10px 60px 10px 60px">
							<a href="javascript:void(0)" class="easyui-linkbutton c6"
								iconCls="icon-ok" onclick="saveBook()" style="width: 90px">保存</a>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-cancel"
								onclick="clears()" style="width: 90px">清空</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/bookClassify.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		loadData();
		
	});
	var selbookName = "";
	var selbookAuthor = "";
	
	var bookurl ='../../book/selectBooks?selbookName='
		+ encodeURIComponent(selbookName) + '&selbookAuthor=' + encodeURIComponent(selbookAuthor)+'&source='+3;
	function loadData() {
		//datagrid初始化 
		$('#dg').datagrid({
			title : 'My EBooks',
			//iconCls : 'icon-edit',//图标 
			width : '60%',
			height : 430,
			url : bookurl,
				/* '../../book/selectBooks?selbookName='
					 + encodeURIComponent(selbookName) + '&selbookAuthor=' + encodeURIComponent(selbookAuthor)+'&source='+0,*/
			idField : 'fldId',
			singleSelect : true,//是否单选 
			pagination : true,//分页控件 
			rownumbers : true,//行号 
			fitColumns : true,
			toolbar : "#toolbar",
			pageSize : 10,//每页显示的记录条数，默认为10 
			pageList : [ 10, 15, 20 ]//可以设置每页记录条数的列表 
			
		});

		//设置分页控件 
		var p = $('#dg').datagrid('getPager');
		$(p).pagination({
			
			beforePageText : '第',//页数文本框前显示的汉字 
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
		
		$('#selclass').combobox({
			url : '../../book/getClassifyComeBox',
			valueField : 'id',
			textField : 'text',
			editable : false
		});

	}
	
	var edit=false;
	function newBook() {
		edit=false;
		$("#bookInfo").show();
		loadClassify();
		clears();
	}
	
	var classify="";
	function editBook() {
		edit=true;
		var row = $('#dg').datagrid('getSelected');
        if (row){
        	clears();
    		id = row.id;
    		classify = row.classify;
    		$('#classify').combobox({
    			url : '../../book/getClassifyComeBox',
    			valueField : 'id',
    			textField : 'text',
    			editable : false,
    			onLoadSuccess:function(){
					if(classify!=""){
						$("#classify").combobox('setValue',classify);
					}
				}
    		});
        	$('#name').textbox('setValue', row.name);
        	$('#enname').textbox('setValue', row.english_name);
        	$('#author').textbox('setValue', row.author);
        	$('#translator').textbox('setValue', row.translator);
        	$('#link').textbox('setValue', row.link);
        	
    		$("#bookInfo").show();
        }else{
        	$.messager.alert('提示信息','请选择一条数据','info');
        }
	}

	function saveBook() {
		var row = $('#dg').datagrid('getSelected');
		if (edit){
			 id=row.id;
		 }else{
			 id=0;
		 }
		var name = $("#name").val();
		if (name == null || name == "") {
			$.messager.alert("提示信息", "请输入书名", "info");
			return;
		}
		var classify = $('#classify').combobox('getValue');
		var enname=$('#enname').val();
		var author = $("#author").val();
		var translator = $("#translator").val();
		var link = $("#link").val();
	
		loadding();

		var url = "../../book/insertBook";
		$.post(url, {
			"id" : id,
			"name" : name,
			"english_name" : enname,
			"classifyid" : classify,
			"author" : author,
			"translator" : translator,
			"link" : link,
			"source" : 3
		}, function(data) {
			var result = $.parseJSON(data);
			if (result.success) {
				loadData();
				$("#bookInfo").hide();
			}
			removeLoad();
			$.messager.alert("提示信息", result.message, "info");
		});
				
	}
	
	function delInfo() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm("删除提示", "确定要删除吗？", function(data) {
				if (data) {
					bookid = row.id;
					bookname = row.name;
					var url = "../../book/deleteBook";
					$.post(url, {
						"id" : bookid,
						"name" : bookname
					}, function(result) {
						result = $.parseJSON(result);
						if (result.success) {
							loadData();
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
	
	function dosearch(){
		selbookName=$('#selBookName').val();
		selbookAuthor=$('#selAuthorName').val();
		var classifyid= $('#selclass').combobox('getValue');
		bookurl = '../../book/selectBooks?selbookAuthor='+selbookAuthor+
		'&classifyid='+classifyid+
		'&selbookName='+ selbookName+
		'&source='+3;

		$('#dg').datagrid('options').url = bookurl;
		$('#dg').datagrid('reload');
	}
	
	function cancel() {
		$("#bookInfo").hide();
		$("#dg").datagrid('unselectAll');
	}
	function clears() {
		$('#name').textbox('setValue', '');
		$('#enname').textbox('setValue', '');
		$('#author').textbox('setValue', '');
		$('#translator').textbox('setValue', '');
		$('#link').textbox('setValue', '');
		classify="";
	}
	
	
	function openbook(link){
		   window.open(link);
	   }
	
	function formatCheck(value,row,index){
		return "<a href=javascript:toReadnotes('"+row.id+"','"+row.name+"');>查看</a>";
	}
	function toReadnotes(id,name){
		window.location.href="readnote.jsp?bookid="+id+"&bookname="+name;
	}
	
</script>
</html>