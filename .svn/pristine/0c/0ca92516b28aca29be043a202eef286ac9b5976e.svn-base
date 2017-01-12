<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>菜单管理</title>
<link href="../../images/favicon.ico" rel="shortcut icon">
<style type="text/css">
#operate {
	float: left
}

#menuinfo {
	margin-left: 40px
}
</style>

</head>
<body class="easyui-layout" style="overflow-y: hidden" fit="true"
	scroll="no">
	<jsp:include page="../common/top.jsp"></jsp:include>
	<jsp:include page="../common/foot.jsp"></jsp:include>
	<jsp:include page="../common/left.jsp"></jsp:include>
	<div id="rolePanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="p" class="easyui-panel" title="系统管理>菜单管理"
			style="width: 100%; height: 100%; padding: 10px">

			<div style="margin: 20px 0;"></div>
			<div id="operate">
				<div class="easyui-panel" title="菜单列表"
					style="width: 180px; float: left">
					<div style="padding: 10px 20px 20px 10px">
						<form id="ff">
							<div id="toolbar">
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-add" plain="true" onclick="addTreeNode()">添加</a>
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-remove" plain="true" onclick="removeTreeNode()">删除</a>
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-undo" plain="true" onclick="cancel()">取消</a>
									<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editMenus()">修改</a>
							</div>
						</form>
						<div>
							<ul id="tree" class="ztree"></ul>
						</div>
					</div>
				</div>
			</div>
			<div id="menuInfo"
				style="float: left; margin-top: 20px; margin-left: 20px; display: none">
				<div class="easyui-panel" title="菜单信息" style="width: 450px">
					<div style="padding: 10px 20px 10px 20px">
						<form id="fm">
							<table cellpadding="3" cellspacing="3">
								<tr>
									<td>父级ID:</td>
									<td><input id="parentSid" class="easyui-textbox"
										name="parentSid" style="width: 200px;"></input></td>
								</tr>
								
								<tr>
									<td>菜单名称:</td>
									<td><input id="caidanName" class="easyui-textbox"
										name="caidanName" style="width: 200px"></td>
								</tr>
								<tr>
									<td>菜单图标:</td>
									<td><input id="iconName" class="easyui-textbox"
										name="iconName" style="width: 200px"></td>
								</tr>
								<tr>
									<td>JSP路径:</td>
									<td><input id="jspUrl" class="easyui-textbox"
										name="jspUrl" style="width: 200px"></td>
								</tr>				
								<tr>
									<td>ORDER顺序:</td>
									<td><input id="order" class="easyui-textbox" name="order"
										style="width: 200px;"></input></td>
								</tr>
							</table>
							<div id="operate-buttons" style="padding: 10px">
								<a href="javascript:void(0)" class="easyui-linkbutton c6"
									iconCls="icon-ok" onclick="saveMenus()" style="width: 90px">提交</a>
								<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-cancel" onclick="cancel()"
									style="width: 90px; margin-left: 50px">取消</a>
							</div>
						</form>
					</div>
				</div>
			</div>


		</div>
	</div>

	<script type="text/javascript">
	var rMenu;
	$(document).ready(function() {
			initTree();		
		});
		
		//设置Ztree
		var setting = {
			check : {
				enable : false
			},
			data : {
				simpleData : {
					enable : true,
				},
				keep : {
					parent : true
				/*如果设置为 true，则所有 isParent = true 的节点，即使该节点的子节点被全部删除或移走，依旧保持父节点状态 */
				}
			},
			callback : {
				//用于捕获勾选或取消勾选之前的事件回调函数,并且根据返回值确定是否允许勾选或取消勾选 
				beforeCheck : zTreeBeforeCheck,
				//用于捕获 checkbox/radio 被勾选 或 取消勾选的事件回调函数
				onCheck : oncheck,
				//用于捕获勾选或取消勾选之前的事件回调函数,并且根据返回值确定是否允许勾选或取消勾选
				//onExpand : onExpand,
				onClick : onClick,
			},
		};
		
		function expandAll(expandSign) {
			zTree.expandAll(expandSign);
		}
		 var addCount = 1;
		function addTreeNode() {
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var sNodes = treeObj.getSelectedNodes();
			
			if(sNodes.length > 0){
				treeObj.cancelSelectedNode(sNodes[0]);
				if(sNodes[0].pId==null){
					var node_pid = sNodes[0].id;
					$('#menuInfo').form('clear');
					$("#parentSid").textbox('setValue',node_pid);
					$("#menuInfo").show();
				}else{
					var node_pid = sNodes[0].pId;
					$('#menuInfo').form('clear');
					$("#parentSid").textbox('setValue',node_pid);
					$("#menuInfo").show();
				}
			}else{
				$('#menuInfo').form('clear');
				$("#menuInfo").show();
				$("#parentSid").textbox('setValue',0);
				//$.messager.alert('提示信息','请选择一个菜单节点进行添加','info');
			}			
		}
		
		function removeTreeNode() {
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var sNodes = treeObj.getSelectedNodes();
			if(sNodes.length > 0){
				$.messager.confirm("提示信息", "确定要删除吗？", function (data) {
					if(data){
						treeNode=sNodes[0];
						var url = "../../menus/deleteMenus";
						$.post(url, {"id" : treeNode.id}, function(result) {
							result = $.parseJSON(result);
							if (result.success) {
								$('#menuInfo').attr("style", "display:none");
								initTree();	
								$.messager.alert('提示信息',result.message,'info');
							}
						});
					}
				});				
			}else{
					$.messager.alert('提示信息','请选择一个菜单','info');
			}
		} 

	    function zTreeBeforeCheck(treeId, treeNode) {
			return true;
		}
		//用于捕获 checkbox/radio 被勾选 或 取消勾选的事件回调函数,获取选中复选框的值
		function oncheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			var nodes = zTree.getCheckedNodes(true);
			var areaNo = "";
			for (var i = 0; i < nodes.length; i++) {
				areaNo += nodes[i].id + ",";
			}
			areaNo = areaNo.substr(0, areaNo.length - 1);
			return areaNo;
		}
		function onClick(e, treeId, treeNode) {
			//console.log("treeNode",treeNode);
			$('#caidanName').textbox('setValue',treeNode.name);
			$("#iconName").textbox('setValue',treeNode.menuIcon);
			$("#jspUrl").textbox('setValue',treeNode.menuUrl);
			if(treeNode.pId!=null){
				$("#parentSid").textbox('setValue',treeNode.pId);
			}else{
				$("#parentSid").textbox('setValue',0);
			}
			$("#order").textbox('setValue',treeNode.menuOrder);
			$("#menuInfo").show();
		}

		function initTree() {
			var url = "../../roles/getmenustree";
			$.getJSON(url, function(data) {
				treeNodes = data.list;
				var t = $("#tree");
				$.fn.zTree.init(t, setting, treeNodes);
			});

		}

		var treeNodes = "";
		function editMenus() {
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var sNodes = treeObj.getSelectedNodes();
			if(sNodes.length > 0){
				if(sNodes[0].isParent){
					$.messager.confirm("这是一个父节点菜单", "确定要修改吗？", function (data) {
						if(data){
							$("#menuInfo").show();
						}else{
						   $.messager.alert('提示信息','请选择一条数据','info');
						}
					});				
				}else{
					$("#menuInfo").show();
				}
			}else{
				$.messager.alert('提示信息','请选择一个菜单节点进行修改','info');
			}
		}

		function saveMenus() {
			var menuId="";
			var caidaname ="";
			var iconname ="";
			var jspurl ="";
			var order ="";
			var parentsid = "";
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var sNodes = treeObj.getSelectedNodes();
			if(sNodes.length > 0){
				menuId=sNodes[0].id;
			}else{
				menuId=0;
			}
			caidaname = $("#caidanName").val();
			if(caidaname ==null || caidaname==""){
				$.messager.alert("提示信息", "请输入新增菜单名", "info");
				return;
			}
			iconname = $("#iconName").val();
			jspurl = $("#jspUrl").val();
			order = $("#order").val();
			parentsid = $("#parentSid").val();
			
		   	var url = "../../menus/updateMenus";
			$.post(url, {
					"name" : caidaname,
					"id":menuId,
                    "pid" : parentsid,
                    "icon" : iconname,
                    "url" : jspurl,                  
					"sort" : order
					}, 
					function(result) {
					result = $.parseJSON(result);
					if (result.success) {
						$('#menuInfo').attr("style", "display:none");
						initTree();		
						alert(result.message);
					}
				});
		}
		

		function cancel() {
			$('#menuInfo').hide();
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var sNodes = treeObj.getSelectedNodes();
			if(sNodes.length > 0){
				node=sNodes[0];
				treeObj.cancelSelectedNode(node);
			}
		}
	</script>

</body>
</html>