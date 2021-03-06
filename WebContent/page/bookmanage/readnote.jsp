<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>读书记录</title>
<link href="../../images/favicon.ico" rel="shortcut icon">
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 10px;
}
/* #p {
  background-image: url(../../images/background.jpg);
  background-position: 0px 0px;
} */        
</style>
<link href="../../ueditor1_4_3/themes/default/css/ueditor.css" type="text/css" rel="stylesheet">
</head>

<body class="easyui-layout" style="overflow-y: hidden" fit="true"
	scroll="no">
	<jsp:include page="../common/top.jsp"></jsp:include>
	<jsp:include page="../common/foot.jsp"></jsp:include>
	<jsp:include page="../common/left.jsp"></jsp:include>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="p" class="easyui-panel" title="书籍管理>读书记录"
			style="width: 100%; height: 100%; padding: 10px; ">
			<input type="hidden" id="power" value="${lftGuidanceLearnManage_user.power}">
			<div  style="float: left;overflow: visible;">
			<table id="dg">
				<thead>
					<tr>
						<th data-options="field:'createdate',width:80,sortable:true">创建日期</th>
						<th data-options="field:'title',width:120,sortable:true">标题</th>
					</tr>
				</thead>
			</table>

			<div id="toolbar">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" onclick="newNote()">添加</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" onclick="editNote()">修改</a>
				 <a	href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true" onclick="delInfo()">删除</a>
				 <a	href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-undo" plain="true" onclick="cancel()">取消</a> 
			</div>
			</div>
			
			<div id="noteInfo" style="float: left; margin-left: 20px; display: none">
				<div class="easyui-panel" title="笔记"
					style="width: 940px; height: 600px;">
					<div style="padding: 10px 20px 10px 20px">
						<form id="fm">
							
							<div style="padding: 5px ">标题:
							<input id="title" class="easyui-textbox" name="title"
								style="width: 150px"></div>
						
							<div style="padding: 5px ">最后修改时间:
							<span id="modifydate"></span></div>
						
							<div style="padding: 5px ">
								<!--style给定宽度可以影响编辑器的最终宽度--> 
								<script type="text/plain"
									id="myEditor" style="width:850px;height:500px;">
								</script>
							</div>
						
						
							<div align="center" id="td_btn" style="padding: 10px ">
								<a href="javascript:void(0)" class="easyui-linkbutton c6"
								iconCls="icon-ok" onclick="saveBookNote()"
								style="width: 90px">提交</a>
								 <a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-cancel"
								onclick="cancel()" style="width: 90px; margin-left: 20px;">关闭</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../../ueditor1_4_3/ueditor.all.min.js"></script>
<script type="text/javascript" src="../../ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>
<!-- <script type="text/javascript" src="../../js/autoBackgroundScroll.js"></script>  -->
<script type="text/javascript">
	
	var bookid=getArgs("bookid");
	var bookname=getArgs("bookname");
	$(document).ready(function() {
		$('#p').panel('setTitle','读书记录>'+bookname);
		loadData();
		//实例化编辑器
		UE.getEditor('myEditor');
		
		/* $('#p').autoBackgroundScroll({
            speed: 0.3,
            direction1: 'bottom',
            direction2: 'right',
            imageWidth: 2000,
            imageHeight: 2000
        }); */
	});
	

	function loadData() {
		var url='../../book/selectReadNotes?bookid='+bookid;
		$.getJSON(url,function(result){
			if(result.success){
				//datagrid初始化 
				$('#dg').datagrid({
					title : result.name,
					//iconCls : 'icon-edit',//图标 
					width : 250,
					height : 500,
					data : result.rows,
					idField : 'fldId',
					singleSelect : true,//是否单选 
					rownumbers : false,//行号 
					fitColumns : true,
					toolbar : "#toolbar",
					scrollbarSize: 0,
					onClickRow: function(index,row){
						editNote();
					}
					
				});	
			}else{
				$.messager.alert("提示信息", result.message, "info");
			}
		
		});
	}
	
	var id=0;
	function newNote(){
		$('#noteInfo').form('clear');
		UE.getEditor('myEditor').setContent("");//清空文本框
		$("#noteInfo").fadeIn("slow");
		id=0;
	}
	
	function editNote(){
		$('#noteInfo').form('clear');
		UE.getEditor('myEditor').setContent("");//清空文本框
		var row = $('#dg').datagrid('getSelected');
		if(row){
			id=row.id;
			$('#title').textbox('setValue',row.title);
			$('#modifydate').html(row.modifydate);
			UE.getEditor('myEditor').setContent(row.content);
		}else{
        	$.messager.alert('提示信息','请选择一条数据','info');
        }
		
		$("#noteInfo").fadeIn("slow");
	}
	
	function saveBookNote(){
		var title = "";
		var content = "";
		title = $("#title").val();
		if (title == null || title == "") {
			$.messager.alert("提示信息", "请输入标题", "info");
			return;
		}	
		
		content=UE.getEditor('myEditor').getContent();
		
		var url = "../../book/saveBookNote";
		loadding();
		$.post(url, {
			"title" : title,
			"content" : content,
			"id" : id,
			"bookid" : bookid
		}, function(result) {
			result = $.parseJSON(result);
			if (result.success) {
				loadData();
				$("#noteInfo").fadeOut("slow");
			}
			removeLoad();
			$.messager.alert("提示信息", result.message, "info");
		});
	}
		
		
	function cancel() {
		$("#noteInfo").hide();
	}
	
	function delInfo(){
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm("删除提示", "确定要删除吗？", function(data) {
				if (data) {
					id = row.id;
					var url = "../../book/deleteBookNote";
					$.post(url, {
						"id" : id
					}, function(result) {
						result = $.parseJSON(result);
						if (result.success) {
							$("#noteInfo").hide();
							var url2='../../book/selectReadNotes?bookid='+bookid;
							$('#dg').datagrid('options').url = url2;
							$('#dg').datagrid('reload');
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
	
	
	
</script>
</html>