//获取Request参数
function getArgs(strParame) {
	var args = new Object();
	var query = location.search.substring(1);
	var pairs = query.split("&");
	for(var i = 0; i < pairs.length; i++) {
		var pos = pairs[i].indexOf("=");
		if (pos == -1) continue;
		var argname = pairs[i].substring(0,pos);
		var value = pairs[i].substring(pos+1);
		value = decodeURIComponent(value);
		args[argname] = value;
	}
	return args[strParame];
}



function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null;
}




var imgURL="http://lftbjb.52fdw.com";
function pathReplace(pathstr){
	if(pathstr!=null && pathstr!=undefined){
		pathstr=pathstr.replace(new RegExp("<p>&nbsp;</p>", 'g'),"");
		pathstr=pathstr.replace(new RegExp("quiz/images/", 'g'),imgURL+"/quiz/images/");
		pathstr=pathstr.replace(new RegExp("/STSource/", 'g'),imgURL+"/STSource/");
		pathstr=pathstr.replace(new RegExp("lftbbgxx/", 'g'),imgURL+"/lftbbgxx/");
		pathstr=pathstr.replace(new RegExp("lftbookCutImgs/", 'g'),imgURL+"/lftbookCutImgs/");
		pathstr=pathstr.replace(new RegExp("doakimages/", 'g'),imgURL+"/doakimages/");
		return pathstr.replace(new RegExp("/Attachments/", 'g'),imgURL+"/Attachments/");
	}else{
		return "";
	}
}


function addCookie(objName,objValue,objHours){//添加cookie
	var str = objName + "=" + escape(objValue);
	if(objHours > 0){//为0时不设定过期时间，浏览器关闭时cookie自动消失
		var date = new Date();
		var ms = objHours*3600*1000;
		date.setTime(date.getTime() + ms);
		str += "; expires=" + date.toGMTString()+";path=/BookManageSystem";
	}
	document.cookie = str;
}
function getCookie(objName){//获取指定名称的cookie的值
	var arrStr = document.cookie.split("; ");
	for(var i = 0;i < arrStr.length;i ++){
		var temp = arrStr[i].split("=");
		if(temp[0] == objName) return unescape(temp[1]);
	}
}
function delCookie(name){//为了删除指定名称的cookie，可以将其过期时间设定为一个过去的时间
	var date = new Date();
	date.setTime(date.getTime() - 10000);
	document.cookie = name + "= ; expires=" + date.toGMTString()+";path=/BookManageSystem";
} 

//判断数组中是否存在某字符串
Array.prototype.in_array = function(e){
	for(var i=0;i<this.length;i++ ){
		if(this[i] == e)
			return true;
	}
	return false;
};

//比较日期大小
function dateCompare(startdate,enddate){   
	var arr=startdate.split("/");    
	var starttime=new Date(arr[2],arr[0],arr[1]);    
	var starttimes=starttime.getTime();   	
	alert(starttimes);
	var arrs=enddate.split("/");    
	var lktime=new Date(arrs[2],arrs[0],arrs[1]);    
	var lktimes=lktime.getTime();   
	alert(lktimes);
	if(starttime>lktime)    
	{   
		return false;   
	}   
	else  
		return true;   
}  

//比较日期大小
function dateCompare2(startdate,enddate){   
	var arr=startdate.split("-");    
	var starttime=new Date(arr[0],arr[1],arr[2]);    
	var starttimes=starttime.getTime();   

	var arrs=enddate.split("-");    
	var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
	var lktimes=lktime.getTime();   

	if(starttimes>lktimes)    
	{   
		return false;   
	}   
	else  
		return true;   
}
//日期格式转换 mm/dd/yyyy转换为 yyyy-mm-dd
function dateConvert(date){   
	if((date.getMonth()+1)<10){
		month="0"+(date.getMonth()+1);
	}else{
		month=date.getMonth()+1;
	}
	if(date.getDate()<10){
		dat="0"+(date.getDate());
	}else{
		dat=date.getDate();
	}
	newdate=date.getFullYear()+"-"+month+"-"+dat; 
	return newdate;
}

// 日期作差(相差几天)
function DateDiff(sDate1, sDate2) {     //sDate1和sDate2是2004-10-18格式  
    var aDate = sDate1.split("-");
    var oDate1 = new Date(aDate[0], aDate[1]-1, aDate[2]);     //转换为10-18-2004格式

    aDate = sDate2.split("-");
    var oDate2 = new Date(aDate[0], aDate[1]-1, aDate[2]);

    var iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24);    //把相差的毫秒数转换为天数
    return iDays;
}

function loadding(){
	$("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body"); 
	$("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2}); 
}

function removeLoad(){
	$("body").children("div.datagrid-mask-msg").remove();
	$("body").children("div.datagrid-mask").remove();
}


