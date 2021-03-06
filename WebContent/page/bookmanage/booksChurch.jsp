<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教会书籍目录</title>
<link href="../../images/fish.ico" rel="shortcut icon">
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
		<div id="p" class="easyui-panel" title="书籍管理>教会图书"
			style="width: 100%; height: 100%; padding: 10px;">
			<input type="hidden" id="power" value="${BookManageSystem_User.power}">
			<div style="float: right; width: 20%; height: 20%; padding: 0px 0px 0px 0px;">
				<img src="../../images/books03.png"   />
			</div>
			
			<table id="dg">
				<thead>
					<tr>
						<th data-options="field:'name',width:90,sortable:true">书名</th>
						<th data-options="field:'author',width:90,sortable:true">作者</th>
						<th data-options="field:'press',width:110,sortable:true">出版社</th>
						<th data-options="field:'classify',width:45,sortable:true">类别</th>
						<th data-options="field:'status',width:25,sortable:true,
							formatter: function(value,row,index){
								if (value==0){
									return '借出';
								}else if(value==1){
									return '馆藏';
								}
							},
							styler: function(value,row,index){
								if (value == 0){
									return 'color:red;';
									// the function can return predefined css class and inline style
									// return {class:'c1',style:'color:red'}
								}
							}
							">状态</th>
						
						<th data-options="field:'buydate',width:30,sortable:true,order:'desc'">购买日期</th>
						<th data-options="field:'a',width:40,align:'center',
							formatter: formatCheck">借阅记录</th>
					</tr>
				</thead>
			</table>

			<div id="bookInfo" style="margin-top: 20px; display: none">
				<div style="float: right; width: 30%; padding: 0px 0px 0px 0px;">
					<img src="../../images/books04.png"   />
				</div>
				<div class="easyui-panel" title="图书信息" style="width: 800px">
					<div style="padding: 10px 20px 10px 20px">
						<table cellpadding="3" cellspacing="3">
							<tr>
								<td>类别:</td>
								<td><input id="classify" class="easyui-combobox" name="classify" style="width: 150px" data-options="
									iconWidth: 22,
									icons: [{
										iconCls:'icon-add',
										handler: function(e){
											addClassify(e);
										}
									},{
										iconCls:'icon-remove',
										handler: function(e){
											removeClassify(e);
										}
									}]
									">
								</td>
								<td rowspan="10">
									<div id="localImag" style="margin-left: 20px;">
										<%--预览，默认图片--%>
										<img id="preview" alt="预览图片"
											src="../../images/20150709165046.png" onerror="javascript:defaultImg();"
											style="width: 130px; height: 200px;" />
									</div>
									<form id="fm" action="../../bookupload/uploadcover" method="post" enctype="multipart/form-data">
										<div style="margin-left: 20px;">
											选择图片:<input id="idFile" runat="server" name="pic"
												onchange="javascript:setImagePreview(this,localImag,preview);"
												type="file" />
										</div>
									</form>
									<div style="margin-left: 20px;">
										<span>支持：gif, jpeg, png, jpg, bmp</span>
									</div>
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
								<td>出版社:</td>
								<td><input id="press" name="press" class="easyui-textbox"
									prompt="" style="width: 200px;"></input></td>
							</tr>
							<tr>
								<td>版次印次:</td>
								<td><input id="revison" name="rev" class="easyui-textbox"
									prompt="" style="width: 200px;"></input></td>
							</tr>
							<tr>
								<td>丛书名:</td>
								<td><input id="listing" name="listing" class="easyui-textbox"
									style="width: 200px;"
									maxlength="20"></input></td>
							</tr>
							<tr>
								<td>品牌:</td>
								<td><input id="brand" name="brand" class="easyui-textbox"
									style="width: 200px;"
									maxlength="20"></input></td>
							</tr>
							<tr>
								<td>购买日期:</td>
								<td><input id="buydate" name="buydate" class="easyui-textbox"
									prompt="如：2015-08-08或2015-08" style="width: 200px;"
									maxlength="20"></input></td>
							</tr>
							<!-- <tr>
								<td style="font-size: 14px;font-weight: bold;">是否借出</td>
								<td>
									<input type="radio" id="status0" name="status" value="0"  />借出
									<input type="radio" id="status1" name="status" value="1"  checked />馆藏
								 </td>
							</tr> -->
						</table>
						<div id="dlg-buttons" style="padding: 0px 120px 20px 120px">
							<a href="javascript:void(0)" class="easyui-linkbutton c6"
								iconCls="icon-ok" onclick="saveBook()" style="width: 90px">保存</a>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-cancel"
								onclick="clears()" style="width: 90px">清空</a>
						</div>
					</div>
				</div>
			</div>

			<div id="toolbar">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" onclick="newBook()">添加</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" onclick="editBook()">修改</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-copy" plain="true" onclick="copyBook()">复制</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true" onclick="delInfo()">删除</a>
				 <a	href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-undo" plain="true" onclick="cancel()">取消</a> 
				 <a	href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" plain="true" onclick="advancedSearch()">高级查询</a> 
					
				<input id="ss" class="easyui-searchbox" style="width: 250px"></input>
				
			</div>

			<div id="mm" style="width: 120px">
				<div data-options="name:'bookName'">书名</div>
				<div data-options="name:'bookAuthor'">作者</div>
			</div>
				
			<div id="advancedQuery" style="margin-top: 20px; display: none">
				<!-- 高级查询-弹框 start -->
				<div id="queryView" class="easyui-window" title="高级查询" style="width: 30%" data-options="modal:true,closed:true,iconCls:'icon-search02'">
					<div style="padding: 10px 20px 10px 20px">
						<table cellpadding="3" cellspacing="3">
							<tr>
								<td>类别:</td>
								<td><input id="selclass" class="easyui-combobox" name="selclass" style="width: 150px"></input></td>
							</tr>
							<tr>
								<td>书名:</td>
								<td><input id="selBookName" class="easyui-textbox" prompt="输入部分文字即可查询" style="width:150px" /></td>
							</tr>
							<tr>
								<td>作者:</td>
								<td><input id="selAuthorName" name="selAuthorName" class="easyui-textbox" prompt="" style="width: 300px;"></input></td>
							</tr>
							<tr>
								<td>丛书:</td>
								<td><input id="selListing" name="selListing" class="easyui-textbox" prompt="" style="width: 300px;"></input></td>
							</tr>
							<tr>
								<td>出版社:</td>
								<td><input id="selPressName" name="selPressName" class="easyui-textbox" prompt="" style="width: 300px;"></input></td>
							</tr>
							<tr>
								<td>品牌:</td>
								<td><input id="selBrand" name="selBrand" class="easyui-textbox" prompt="" style="width: 300px;"></input></td>
							</tr>
						</table>
	        			<div id="advancedsearch" style="padding: 5px 120px 20px 140px">
							<a href="javascript:void(0)" class="easyui-linkbutton c6"
								iconCls="icon-search" onclick="advanced()" style="width: 90px">查询</a>
						</div>
	    			</div>
	    		</div>
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
	var selbookAuthor = "";
	function loadData() {
		//datagrid初始化 
		$('#dg').datagrid({
			title : 'Church Books',
			//iconCls : 'icon-edit',//图标 
			width : 950,
			height : 500,
			url : '../../book/selectBooks?selbookName='
					+ encodeURIComponent(selbookName) + '&selbookAuthor=' + encodeURIComponent(selbookAuthor)+'&source='+1,
			idField : 'fldId',
			singleSelect : true,//是否单选 
			pagination : true,//分页控件 
			rownumbers : true,//行号 
			fitColumns : true,
			toolbar : "#toolbar",
			pageSize : 20,//每页显示的记录条数，默认为10 
			pageList : [ 20, 15, 25 ],//可以设置每页记录条数的列表 
			onDblClickRow :function(rowIndex,rowData){
				
			}
		});

		//设置分页控件 
		var p = $('#dg').datagrid('getPager');
		$(p).pagination({
			
			beforePageText : '第',//页数文本框前显示的汉字 
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
		// 设置搜索框
		$('#ss').searchbox({
			menu : '#mm',
			prompt : 'Please Input Value',
			value : ''
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
    		//document.getElementById("status"+row.status).checked=true;
        	$('#name').textbox('setValue', row.name);
        	$('#enname').textbox('setValue', row.english_name);
        	$('#press').textbox('setValue', row.press);
        	$('#author').textbox('setValue', row.author);
        	$('#translator').textbox('setValue', row.translator);
        	$('#revison').textbox('setValue', row.revison);
        	$('#listing').textbox('setValue', row.listing);
        	$('#brand').textbox('setValue', row.brand);
        	$('#buydate').textbox('setValue', row.buydate);
        	
        	if(row.image!=null && row.image!=""){
        		$('#preview').attr('src','http://172.16.1.92:8080/bookManage/cover/'+row.image);
        	}else{
        		$("#preview").attr("src","../../images/20150709165046.png");
        	}
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
		//var status = $("input[name='status']:checked").val();
		var classify = $('#classify').combobox('getValue');
		var enname=$('#enname').val();
		var press = $("#press").val();
		var author = $("#author").val();
		var translator = $("#translator").val();
		var rev = $("#revison").val();
		var listing=$("#listing").val();
		var brand = $("#brand").val();
		var buydate=$("#buydate").val();
	
		loadding();
		
		var imgpath=$("#idFile").val();
		if(imgpath!=null && imgpath!=""){
			$('#fm').ajaxForm({success :function(data){
				var result = $.parseJSON(data);
				if(result.success){
					var url = "../../book/insertBook";
					var image=result.image;
					$.post(url, {
						"id" : id,
						"name" : name,
						"english_name" : enname,
						"classifyid" : classify,
						"press" : press,
						"author" : author,
						"translator" : translator,
						"revison" : rev,
						"listing":listing,
						"brand" : brand,
						"image" : image,
						"status" : 1,
						"buydate" : buydate,
						"source" : 1
					}, function(data1) {
						var result1 = $.parseJSON(data1);
						if (result1.success) {
							loadData();
							$("#bookInfo").hide();
						}
						removeLoad();
						$.messager.alert("提示信息", result1.message, "info");
					});
				}
			}}).submit();
		}else{
			var url = "../../book/insertBook";
			$.post(url, {
				"id" : id,
				"name" : name,
				"english_name" : enname,
				"classifyid" : classify,
				"press" : press,
				"author" : author,
				"translator" : translator,
				"revison" : rev,
				"listing":listing,
				"brand" : brand,
				"status" : 1,
				"buydate" : buydate,
				"source" : 1
			}, function(data1) {
				var result1 = $.parseJSON(data1);
				if (result1.success) {
					loadData();
					$("#bookInfo").hide();
				}
				removeLoad();
				$.messager.alert("提示信息", result1.message, "info");
			});
		}
	}
	
	function copyBook(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
		 $.messager.confirm("提示", "复制后自动添加，确定吗？", function(data) {
			if (data) {
				var url = "../../book/insertBook";
				$.post(url, {
					"id" : 0,
					"name" : row.name,
					"english_name" : row.english_name,
					"classifyid" : row.classifyid,
					"press" : row.press,
					"author" : row.author,
					"translator" : row.translator,
					"revison" : row.revison,
					"listing":row.listing,
					"brand" : brand,
					"status" : row.status,
					"buydate" : row.buydate,
					"image" : row.image,
					"source" : 1
				}, function(data1) {
					var result1 = $.parseJSON(data1);
					if (result1.success) {
						loadData();
					}
					$.messager.alert("提示信息", result1.message, "info");
				});
			}
		});
	 }else{
		 $.messager.alert('提示信息','请选择一条要复制的数据','info'); 
	 }
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
	
	function cancel() {
		$("#bookInfo").hide();
		$("#dg").datagrid('unselectAll');
	}
	function clears() {
		$("#fm").form('clear');
		$('#name').textbox('setValue', '');
		$('#enname').textbox('setValue', '');
		$('#press').textbox('setValue', '');
		$('#revison').textbox('setValue', '');
		$('#listing').textbox('setValue', '');
		$('#brand').textbox('setValue', '');
		$('#buydate').textbox('setValue', '');
		$('#author').textbox('setValue', '');
		$('#translator').textbox('setValue', '');
		classify="";
		$("#preview").attr("src","../../images/20150709165046.png");
		//document.getElementById("status1").checked=true;
	}
	
	
	$('#ss').searchbox({
		searcher : function(value, name) {
			//alert(value + "," + name);
			var url = '../../book/selectBooks';
			if (name == 'bookName') {
				url = '../../book/selectBooks?selbookName='
						+ encodeURIComponent(value)+"&source="+1;
				selbookName = value;
				selbookAuthor = "";
			}
			if (name == 'bookAuthor') {
				url = '../../book/selectBooks?selbookAuthor='+value+"&source="+1;
				selbookAuthor = value;
				selbookName = "";
			}
			
			$('#dg').datagrid('options').url = url;
			$('#dg').datagrid('reload');
		}
	});

	
	function advancedSearch() {
		$('#queryView').window('open');
		$('#selclass').combobox({
			url : '../../book/getClassifyComeBox',
			valueField : 'id',
			textField : 'text',
			editable : false
		});
		$('#selBookName').textbox('setValue', '');
		$('#selAuthorName').textbox('setValue', '');
		$('#selTranslator').textbox('setValue', '');
		$('#selListing').textbox('setValue', '');
		$('#selPressName').textbox('setValue', '');
		$('#selBrand').textbox('setValue', '');
		$("#advancedQuery").show();
		$(document).keypress(function(e) {
	    	// 回车键事件
	       if(e.which == 13) {
	    	   advanced();
	       }
	   });
	}
	
	function advanced(){
		selbookName=$('#selBookName').val();
		selbookAuthor=$('#selAuthorName').val();
		var selListing=$('#selListing').val();
		var classifyid= $('#selclass').combobox('getValue');
		var selpress= $('#selPressName').val();
		var selbrand= $('#selBrand').val();
		var url = '../../book/selectBooks?selbookAuthor='+selbookAuthor+
				'&classifyid='+classifyid+
				'&sellisting='+selListing+
				'&selbookName='+ selbookName+
				'&selpress='+selpress+
				'&selbrand='+selbrand+
				'&source='+1;
		$("#advancedQuery").hide();
		$('#queryView').window('close');
		$('#dg').datagrid('options').url = url;
		$('#dg').datagrid('reload');
	}
	
	
	
	function formatCheck(value,row,index){
		return "<a href=javascript:toBorrowRecord('"+row.id+"','"+row.name+"');>查看</a>";
	}
	function toBorrowRecord(id,name){
		window.location.href="borrowrecord.jsp?bookid="+id+"&bookname="+name;
	}
	
</script>
</html>