//添加类型
	function addClassify(e){
		$.messager.prompt('添加类型', '请输入类型名称', function(addtype){
			if (addtype){
				if(addtype==null || addtype==''){
					alert('请填写要添加类型');
				}else{
					var url ='../../book/insertClassify';
					$.post(url,{'name':addtype},
						function(data){
							data=$.parseJSON(data);
							if(data.success){
								$.messager.alert('提示信息', '添加成功', 'info');
								loadClassify();
							}else{
								$.messager.alert('提示信息', data.message, 'info');
							}
					});
				}
			}
		});
	}
	//删除类型
	function removeClassify(e){
		$.messager.confirm('删除确认', '是否删除当前类型?', function(r){
			if (r){
				var id = $("#classify").combobox('getValue');
			    if(id != 0 && id != null){
		        	var url ='../../book/deleteClassify';
		    		$.post(url,{'id':id},
		    			function(data){
		    				data=$.parseJSON(data);
		    				if(data.success){
		    					$.messager.alert('提示信息', data.message, 'info');
		    					loadClassify();
		    				}else{
		    					$.messager.alert('提示信息', data.message, 'info');
		    				}
		    			}
		    		);
		        }else{
		        	$.messager.alert('提示信息', '没有选中要删除的类型', 'info');
		        }
			}else{
				//$(e.data.target).textbox('clear');
				//var v = $(e.data.target).textbox('getValue');
				//alert('The inputed value is ' + (v ? v : 'empty'));
			}
		});
	}
	
	function loadClassify(){
		$('#classify').combobox({
			url : '../../book/getClassifyComeBox',
			valueField : 'id',
			textField : 'text',
			editable : false
		});
	}