<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>书籍管理</title>
<link href="../../images/books.ico" rel="shortcut icon">
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
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
		<div id="p" class="easyui-panel" title="书籍管理>图书管理"
			style="width: 100%; height: 100%; padding: 10px;">
			<table id="dg">
				<thead>
					<tr>
						<th data-options="field:'book_name',width:80,formatter: formatName">书名</th>
						<th data-options="field:'borrow_person',width:50">借阅人</th>
						<th data-options="field:'borrow_date',width:40,sortable:true">借阅日期</th>
						
					</tr>
				</thead>
			</table>
			
			<div style="padding: 5px 0px 5px 0px" id="toolbar">
				
				<input id="ss" class="easyui-searchbox" style="width: 250px"></input>
				
			</div>

			<div id="mm" style="width: 120px">
				<div data-options="name:'bookName'">书名</div>
			</div>
				
			
		    	
		</div>
	</div>
</body>
<script type="text/javascript" src="js/imageView.js"></script>	
<script type="text/javascript" src="js/bookClassify.js"></script>	
<script type="text/javascript">
	
	$(document).ready(function() {
		loadData();
	});
	
	var selbookName = "";
	
	var url ='../../book/selectMyOutBooks?selbookName='+encodeURIComponent(selbookName);
	function loadData() {
		//datagrid初始化 
		$('#dg').datagrid({
			title : '未归还书籍',
			//iconCls : 'icon-edit',//图标 
			width : '40%',
			height : 630,
			url : url,
			idField : 'fldId',
			singleSelect : true,//是否单选 
			pagination : false,//分页控件 
			rownumbers : true,//行号 
			fitColumns : true,
			toolbar : "#toolbar"
		});

		// 设置搜索框
		$('#ss').searchbox({
			menu : '#mm',
			prompt : 'Please Input Value',
			value : ''
		});

	}
	
	$('#ss').searchbox({
		searcher : function(value, name) {
			if (name == 'bookName') {
				url = '../../book/selectMyOutBooks?selbookName='+ encodeURIComponent(value);
				selbookName = value;
			}
			$('#dg').datagrid('options').url = url;
			$('#dg').datagrid('reload');
		}
	});

	
	function formatName(value,row,index){
		return "<a href='borrowrecord.jsp?bookid="+row.book_id+"&bookname="+row.book_name+"'>"+value+"</a>";
	}
	
	
</script>
</html>