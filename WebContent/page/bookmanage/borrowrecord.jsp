<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>借阅记录</title>
<link href="../../images/favicon.ico" rel="shortcut icon">
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 10px;
}
#p {
  background-image: url(../../images/leafbk.png);
  background-position: 0px 0px;
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
		<div id="p" class="easyui-panel" title="书籍管理>借阅记录"
			style="width: 100%; height: 100%; padding: 10px; ">
			<input type="hidden" id="power" value="${BookManageSystem_User.power}">
			<div  style="float: left;overflow: visible;">
			<table id="dg">
				<thead>
					<tr>
						<th data-options="field:'borrow_person',width:80,sortable:true">借阅人</th>
						<th data-options="field:'borrow_date',width:120,sortable:true">借阅时间</th>
						<th data-options="field:'return_date',width:120,sortable:true">归还时间</th>
					</tr>
				</thead>
			</table>
			</div>
			<div id="toolbar">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-borrow" plain="true" onclick="borrowBook()">借书</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-returnbook" plain="true" onclick="returnBook()">还书</a>
					
				 <a	id="deleter"  style="display: none;"  href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true" onclick="delInfo()">删除</a>
					
				 <a	href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-undo" plain="true" onclick="cancel()">取消</a> 
			</div>
			
			<div id="recordInfo" style="float: left; margin-left: 20px; display: none">
				<div class="easyui-panel" title="图书信息" style="width: 360px">
					<div style="padding: 10px 20px 10px 20px">
						<table cellpadding="3" cellspacing="3">
							<tr class="borrowb">
								<td>借阅人:</td>
								<td><input id="borrower" name="borrower" class="easyui-textbox"
									style="width: 200px;"></input></td>
							</tr>
							<tr class="borrowb">
								<td>借阅日期:</td>
								<td><input id="borrowdate" name=""borrowdate"" class="easyui-datebox"
									style="width: 200px;"
									maxlength="20"></input></td>
							</tr>
							<tr class="returnb">
								<td>归还日期:</td>
								<td><input id="returndate" name="returndate" class="easyui-datebox"
									 style="width: 200px;"
									maxlength="20"></input></td>
							</tr>
						</table>
						<div id="dlg-buttons" style="padding: 10px 80px 10px 80px">
							<a href="javascript:void(0)" class="easyui-linkbutton c6"
								iconCls="icon-ok" onclick="saveRecord()" style="width: 70px">确定</a>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</body>
<script type="text/javascript" src="../../js/datetime.js"></script>	
<script type="text/javascript">
	
	var bookid=getArgs("bookid");
	var bookname=getArgs("bookname");
	var power=0;
	$(document).ready(function() {
		$('#p').panel('setTitle','借阅记录>'+bookname);
		power = $("#power").val();
		if (power == 1) {
			$("#deleter").show();
		}
		loadData();
	});
	

	function loadData() {
		
		$('#dg').datagrid({
			title : bookname,
			//iconCls : 'icon-edit',//图标 
			width : 500,
			height : 500,
			url : '../../book/selectBorrowRecords?bookid='+bookid,
			idField : 'fldId',
			singleSelect : true,//是否单选 
			rownumbers : false,//行号 
			fitColumns : true,
			toolbar : "#toolbar"
			
			
		});	
			
		
	}
	
	var id=0;
	var borrowdate = "";
	var returndate = "";
	function borrowBook (){
		$('#recordInfo').form('clear');
		var rows = $('#dg').datagrid('getRows');
		row=rows[0];
		if(row){
			if(row.return_date==''||row.return_date==null){
				$.messager.alert('提示信息','此书已被借走','info');
			}else{
				id=0;
				borrowdate = new Date().format("yyyy-MM-dd");
				$("#recordInfo").show();
				$(".borrowb").show();
				$(".returnb").hide();
				$("#borrowdate").datebox({  
		    	     onSelect: function(date){
		    	    	sdate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
		    	        if(dateCompare2(sdate,borrowdate)){
		    	        	$("#recordInfo").show();
		    				$(".returnb").hide();
		    				borrowdate=sdate;
		    	    	 }else{
		    	    	        $.messager.alert('提示信息','借书日期应小于或等于今天日期！','info');
		    	    	        borrowdate='';
		    	    	 }
		    	     }
	   			});
				$('#borrowdate').datebox('setValue',borrowdate);
			}
		}else{
			id=0;
			borrowdate = new Date().format("yyyy-MM-dd");
			$("#recordInfo").show();
			$(".returnb").hide();
			$("#borrowdate").datebox({  
	    	     onSelect: function(date){
	    	    	sdate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	    	        if(dateCompare2(sdate,borrowdate)){
	    	        	$("#recordInfo").show();
	    				$(".returnb").hide();
	    				borrowdate=sdate;
	    	    	 }else{
	    	    	        $.messager.alert('提示信息','借书日期应小于或等于今天日期！','info');
	    	    	        borrowdate='';
	    	    	}
	    	     }
   			});
			$('#borrowdate').datebox('setValue',borrowdate);
		}
	}
	
	
	function returnBook(){
		$('#recordInfo').form('clear');
		var rows = $('#dg').datagrid('getRows');
		row=rows[0];
		if(row){
			if(row.return_date==''||row.return_date==null){
				id=row.id;
				/* $("#borrower").textbox('setValue',row.borrow_person);
				$("#borrower").attr('disabled','disabled'); */
				/* $("#borrowdate").datebox('setValue',row.borrow_date);
				$("#borrowdate").attr('readonly','readonly'); */
				
				returndate = new Date().format("yyyy-MM-dd");
				$("#recordInfo").show();
				$(".returnb").show();
				$(".borrowb").hide();
				$("#returndate").datebox({  
		    	     onSelect: function(date){
		    	    	sdate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
		    	        if(dateCompare2(sdate,returnDate)){
		    	        	returndate=sdate;
		    	    	 }else{
		    	    	        $.messager.alert('提示信息','还书日期应小于或等于今天日期！','info');
		    	    	        returndate='';
		    	    	    }
		    	       
		    	     }
      			});
				$('#returndate').datebox('setValue',returndate);
				
			}else{
				$.messager.alert('提示信息','此书已还','info');
			}
		}else{
        	$.messager.alert('提示信息','没有借阅数据','info');
        }
		
	}
	
	function saveRecord(){
		var borrower = "";
		if(id==0){	//添加借阅记录 借书
			borrower = $("#borrower").val();
			if (borrower == null || borrower == "") {
				$.messager.alert("提示信息", "请输入借阅人", "info");
				return;
			}	
			if (borrowdate == null || borrowdate == "") {
				$.messager.alert("提示信息", "请输入借书日期", "info");
				return;
			}	
		}
		
		if(id!=0){
			if (returndate == null || returndate == "") {
				$.messager.alert("提示信息", "请输入还书日期", "info");
				return;
			}	
		}
		var url = "../../book/saveBorrowRecord";
		loadding();
		$.post(url, {
			"borrow_person" : borrower,
			"borrow_date" : borrowdate,
			"return_date" : returndate,
			"id" : id,
			"bookid" : bookid
		}, function(result) {
			result = $.parseJSON(result);
			if (result.success) {
				$("#recordInfo").hide();
				loadData();
			}else{
				$.messager.alert("提示信息", result.message, "info");
			}
			removeLoad();
			
		});
	}
		
	
	function delInfo(){
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm("删除提示", "确定要删除吗？", function(data) {
				if (data) {
					var rid = row.id;
					var bookid= row.bookid;
					var url = "../../book/deleteBorrowRecord";
					$.post(url, {
						"id" :rid,
						"bookid":bookid
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
	
	function cancel() {
		$("#recordInfo").hide();
		$("#dg").datagrid('unselectAll');
	}
	
	
	
	
	
</script>
</html>