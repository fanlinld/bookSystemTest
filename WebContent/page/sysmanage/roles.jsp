<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>角色管理</title>
<link href="../../images/favicon.ico" rel="shortcut icon">
<style type="text/css">
	#角色管理
		{border:1px dashed #99BBE8;
		background:#E0ECFF;
		color:#416AA3; 
		}

</style>
</head>
<body class="easyui-layout" style="overflow-y: hidden" fit="true" scroll="no">
	<jsp:include page="../common/top.jsp"></jsp:include>
	<jsp:include page="../common/foot.jsp"></jsp:include>
	<jsp:include page="../common/left.jsp"></jsp:include>
	<div id="rolePanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="p" class="easyui-panel" title="系统管理>角色管理"
			style="width: 100%; height: 100%; padding: 10px">
			 <table id="rolestable" title="角色管理" class="easyui-datagrid" style="width:700px;height:300px;margin:auto"
            		 rownumbers="true" toolbar="#toolbar" 
             		fitColumns="true" singleSelect="true">
        		<thead>
            		<tr>
                		<th field="title" width="50">角色名称</th>
            		</tr>
        		</thead>
    		</table>
    		<div id="toolbar">
        		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRole()">添加</a>
        		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRole()">修改</a>
    		</div>
    		
    		<div style="margin:20px 0;"></div>
    		<div id="operate" style="display:none;">
    		<div  class="easyui-panel" title="编辑角色" style="width:400px">
        		<div style="padding:10px 60px 20px 60px">
        			<form id="ff" >
            		<table cellpadding="5">
                	<tr>
                    	<td>角色名称:</td>
                    	<td><input id="text_id" class="easyui-textbox" type="text" style="width:120px" name="name" data-options="required:true"></input></td>
                	</tr>
                	<tr>
                    	<td>权限设置:</td>
                	</tr>
            		</table>
       		 		</form>
       		 		<div>	
       		 			<ul id="tree" class="ztree"></ul>
       		 		</div>
       		 		<div id="operate-buttons" style="padding:10px">
        				<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRole()" style="width:90px">提交</a>
        				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()" style="width:90px">取消</a>
    				</div>
    			</div>
   			</div>
   			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		loadroleinfo();
		initTree();
	});
	
	var roleslist ="";
	function loadroleinfo(){
		$.ajax({
			async:false,
			type: "GET",
			url: "../../roles/getroles",
			dataType: "json",
			success: function(data) {
				if(data.success){
					roleslist=data.list;
				}
			}
		});
		$('#rolestable').datagrid({
			//pagination : true,//分页控件 
		 	nowrap: false,
			data:roleslist
		});
		
	}
	
	//设置Ztree
     var setting = {
			 check:{
	                enable:true
	            },
	         data:{
	                simpleData:{
	                  	enable:true
	                }
	            },
	         callback: {
	        		 //用于捕获勾选或取消勾选之前的事件回调函数,并且根据返回值确定是否允许勾选或取消勾选 
	          		beforeCheck: zTreeBeforeCheck,
	            	  //用于捕获 checkbox/radio 被勾选 或 取消勾选的事件回调函数
	          		onCheck: oncheck
	           }
		};
   		//用于捕获勾选或取消勾选之前的事件回调函数,并且根据返回值确定是否允许勾选或取消勾选 
     function zTreeBeforeCheck(treeId,treeNode) {
          return true;
     }
    	//用于捕获 checkbox/radio 被勾选 或 取消勾选的事件回调函数,获取选中复选框的值
     function oncheck(e, treeId, treeNode) {
    	var zTree= $.fn.zTree.getZTreeObj("tree");
        var nodes=zTree.getCheckedNodes(true);
        var areaNo="";
       	for(var i=0;i<nodes.length;i++){
       		areaNo+=nodes[i].id+",";
        }
       	areaNo=areaNo.substr(0,areaNo.length-1);
       	return areaNo;
	} 
     
     function initTree(){
    	 var url="../../roles/getmenustree";
         $.getJSON(url,function(data){
        	 treeNodes=data.list;
        	 var t= $("#tree");
 			$.fn.zTree.init(t, setting, treeNodes);
         });
    	
     }
     
     function newRole(){
    	 $('#ff').form('clear');
    	 $('input').attr("readOnly",false);
    	 for(var i=0;i<treeNodes.length;i++){
        	treeNodes[i].checked=false;
         }
    	 var t1 = $("#tree");
    	 $.fn.zTree.init(t1, setting, treeNodes);
             $('#operate').attr("style","display:block;margin:20px 0");
             
     }
     
     var treeNodes="";
     function editRole(){
         var row = $('#rolestable').datagrid('getSelected');
         $('input').attr("readOnly",true); //将input元素设置为readonly
         if (row){
             $('#ff').form('load',{
                 name:row.title
             });
             var permission=row.permission;
             if(permission!=""&&permission!=null){
            	 var arrayperm=permission.split(",");
                 for(var i=0;i<treeNodes.length;i++){
                	 if(arrayperm.in_array(treeNodes[i].id)){
                		 treeNodes[i].checked=true;
                	 }
                	 if(!arrayperm.in_array(treeNodes[i].id)){
                		 treeNodes[i].checked=false;
                	 }
                 }
             }
        	 var t2 = $("#tree");
        	 $.fn.zTree.init(t2, setting, treeNodes);
        	 $('#operate').attr("style","display:block;margin: 20px 0");
         }else{
        	 alert("请选择一条数据");
         }
     }
     
     function saveRole(){
    	 var rolename = $("#text_id").val(); 
    	 if(rolename!=""&&rolename!=null){
    		var s = oncheck();
    		//console.log(s);
    			
             var url="../../roles/updateroles";
             $.post(url,{"title":rolename,"permission":s},function(result){
            	 result=$.parseJSON(result);
    			if(result.success){
    				loadroleinfo();
    				$('#operate').attr("style","display:none");
    				alert(result.message);
    			}
    		});
    	 }else{
    		 alert("角色名称不能为空");
    	 }
   	}
     
     function cancel(){
    	 $('#operate').hide();
     }

	</script>
</body>
</html>