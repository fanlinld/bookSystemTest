<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	var _menus ="";
	var selectedPanelname="";
	$.ajax({
		async:false,
		type: "GET",
		url: "<%=basePath%>/menus?key="+new Date(),
		dataType: "json",
		success: function(data) {
			if(data.success){
				var menuslist=data.listp;
				_menus = {
					"menus":menuslist
				};
			}
		}
	});
	


	$(function(){
		 //$('#nav').accordion({
           // onSelect: function(title,index){
            	//delCookie("selectedPanelname");
            	//alert("onSelect="+title);
            	//addCookie("selectedPanelname",title,1);
            //}
        //});
		 
		InitLeftMenu();
	});

	//初始化左侧
	function InitLeftMenu() {
		$("#nav").accordion({animate:true,fit:true,border:false});
	    $.each(_menus.menus, function(i, n) {
			var menulist ='';
			menulist +='<ul class="navlist">';
	        $.each(n.menus, function(j, o) {
				menulist += '<li><div ><a pmenu="'+n.menuname+'" ref="'+o.menuid+'" href="<%=basePath%>' + o.url + 
					'" ><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' +
					o.menuname + '</span></a></div></li>';
	        });
			menulist += '</ul>';
			$('#nav').accordion('add', {
	            title: n.menuname,
	            content: menulist,
				border:false,
	            iconCls: 'icon ' + n.icon
	        });
			
			if(i==0){
				selectedPanelname =n.menuname;
			}
	    });
	    var cookie_selectedPanelname=getCookie("selectedPanelname");
	    if(cookie_selectedPanelname!=null && cookie_selectedPanelname!=""){
	    	selectedPanelname=cookie_selectedPanelname;
	    }
		$('#nav').accordion('select',selectedPanelname);
		$('.navlist li div').removeClass("selected");
		var menuid_ref=getCookie("menuid_ref");
		if(menuid_ref!=null && menuid_ref!=""){
			$("a[ref='"+menuid_ref+"']").parent().addClass("selected");
		}
		

		$('.navlist li a').click(function(){
			var tabTitle = $(this).children('.nav').text();

			var url = $(this).attr("rel");
			var menuid = $(this).attr("ref");
			var icon = $(this).find('.icon').attr('class');
			var pmenu=$(this).attr("pmenu");
        	addCookie("selectedPanelname",pmenu,1);
        	addCookie("menuid_ref",menuid,1);
			//$('.navlist li div').removeClass("selected");
			//$(this).parent().addClass("selected");
		}).hover(function(){
			$(this).parent().addClass("hover");
		},function(){
			$(this).parent().removeClass("hover");
		});
	}
</script>

<div region="west" split="true" title="导航菜单" style="width: 180px;" id="west">
	<div id="nav" class="easyui-accordion" style="width: 100%; height: 100%;">
	</div>
</div>