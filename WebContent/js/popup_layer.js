Function.prototype.binding = function() {
    if (arguments.length < 2 && typeof arguments[0] == "undefined") return this;
    var __method = this, args = jQuery.makeArray(arguments), object = args.shift();
    return function() {
        return __method.apply(object, args.concat(jQuery.makeArray(arguments)));
    };
};

var Class = function(subclass){
	subclass.setOptions = function(options){
		this.options = jQuery.extend({}, this.options,options);
		for(var key in options){
			if(/^on[A-Z][A-Za-z]*$/.test(key)){
				$(this).bind(key,options[key]);
			}
		}
	};
    var fn =  function(){
        if(subclass._init && typeof subclass._init == 'function'){
            this._init.apply(this,arguments);
        }
    };
    if(typeof subclass == 'object'){
        fn.prototype = subclass;
    }
    return fn;
};

var PopupLayer = new Class({
	options:{
		trigger:null,                            //瑙﹀彂鐨勫厓绱犳垨id,蹇呭～鍙傛暟
		popupBlk:null,                           //寮瑰嚭鍐呭灞傚厓绱犳垨id,蹇呭～鍙傛暟
		closeBtn:null,                           //鍏抽棴寮瑰嚭灞傜殑鍏冪礌鎴杋d
		popupLayerClass:"popupLayer",            //寮瑰嚭灞傚鍣ㄧ殑class鍚嶇О
		eventType:"click",                       //瑙﹀彂浜嬩欢鐨勭被鍨�
		offsets:{                                //寮瑰嚭灞傚鍣ㄤ綅缃殑璋冩暣鍊�
			x:0,
			y:0
		},
		useFx:false,                             //鏄惁浣跨敤鐗规晥
		useOverlay:false,                        //鏄惁浣跨敤鍏ㄥ眬閬僵
		usePopupIframe:true,                     //鏄惁浣跨敤瀹瑰櫒閬僵
		isresize:true,                           //鏄惁缁戝畾window瀵硅薄鐨剅esize浜嬩欢
		onBeforeStart:function(){}            //鑷畾涔変簨浠�
	},
	_init:function(options){
		this.setOptions(options);                //杞藉叆璁剧疆
		this.isSetPosition = this.isDoPopup = this.isOverlay = true;    //瀹氫箟涓€浜涘紑鍏�
		this.popupLayer = $(document.createElement("div")).addClass(this.options.popupLayerClass);     //鍒濆鍖栨渶澶栧眰瀹瑰櫒
		this.popupIframe = $(document.createElement("iframe")).attr({border:0,frameborder:0});         //瀹瑰櫒閬僵,鐢ㄤ簬灞忚斀ie6涓嬬殑select
		this.trigger = $(this.options.trigger);                         //鎶婅Е鍙戝厓绱犲皝瑁呮垚瀹炰緥鐨勪竴涓睘鎬э紝鏂逛究浣跨敤鍙婄悊瑙�
		this.popupBlk = $(this.options.popupBlk);                       //鎶婂脊鍑哄唴瀹瑰眰鍏冪礌灏佽鎴愬疄渚嬬殑涓€涓睘鎬�
		this.closeBtn = $(this.options.closeBtn);                       //鎶婂叧闂寜閽礌灏佽鎴愬疄渚嬬殑涓€涓睘鎬�
		$(this).trigger("onBeforeStart");                               //鎵ц鑷畾涔変簨浠躲€�
		this._construct();                                               //閫氳繃寮瑰嚭鍐呭灞傦紝鏋勯€犲脊鍑哄眰锛屽嵆涓哄叾娣诲姞澶栧眰瀹瑰櫒鍙婂簳灞俰frame
		this.trigger.bind(this.options.eventType,function(){            //缁欒Е鍙戝厓绱犵粦瀹氳Е鍙戜簨浠�
			if(this.isSetPosition){
				this.setPosition(this.trigger.offset().left + this.options.offsets.x, this.trigger.offset().top + this.trigger.get(0).offsetHeight + this.options.offsets.y);
			}
			this.options.useOverlay?this._loadOverlay():null;               //濡傛灉浣跨敤閬僵鍒欏姞杞介伄缃╁厓绱 
			(this.isOverlay && this.options.useOverlay)?this.overlay.show():null;
			if(this.isDoPopup && (this.popupLayer.css("display")== "none")){
				this.options.useFx?this.doEffects("open"):this.popupLayer.show();
			}							 
		}.binding(this));
		this.isresize?$(window).bind("resize",this.doresize.binding(this)):null;
		this.options.closeBtn?this.closeBtn.bind("click",this.close.binding(this)):null;   //濡傛灉鏈夊叧闂寜閽紝鍒欑粰鍏抽棴鎸夐挳缁戝畾鍏抽棴浜嬩欢
	},
	_construct:function(){                  //鏋勯€犲脊鍑哄眰
		this.popupBlk.show();
		this.popupLayer.append(this.popupBlk.css({opacity:1})).appendTo($(document.body)).css({position:"absolute",'z-index':2,width:this.popupBlk.get(0).offsetWidth,height:this.popupBlk.get(0).offsetHeight});
		this.options.usePopupIframe?this.popupLayer.append(this.popupIframe):null;
		this.recalculatePopupIframe();
		this.popupLayer.hide();
	},
	_loadOverlay:function(){                //鍔犺浇閬僵
		pageWidth = ($.browser.version=="6.0")?$(document).width()-21:$(document).width();
		this.overlay?this.overlay.remove():null;
		this.overlay = $(document.createElement("div"));
		this.overlay.css({position:"absolute","z-index":1,left:0,top:0,zoom:1,display:"none",width:pageWidth,height:$(document).height()}).appendTo($(document.body)).append("<div style='position:absolute;z-index:2;width:100%;height:100%;left:0;top:0;opacity:0.3;filter:Alpha(opacity=30);background:#000'></div><iframe frameborder='0' border='0' style='width:100%;height:100%;position:absolute;z-index:1;left:0;top:0;filter:Alpha(opacity=0);'></iframe>");
	},
	doresize:function(){
		this.overlay?this.overlay.css({width:($.browser.version=="6.0")?$(document).width()-21:$(document).width(),height:($.browser.version=="6.0")?$(document).height()-4:$(document).height()}):null;
		if(this.isSetPosition){
			this.setPosition(this.trigger.offset().left + this.options.offsets.x, this.trigger.offset().top + this.trigger.get(0).offsetHeight + this.options.offsets.y);
		}
	},
	setPosition:function(left,top){          //閫氳繃浼犲叆鐨勫弬鏁板€兼敼鍙樺脊鍑哄眰鐨勪綅缃�
		this.popupLayer.css({left:left,top:top});
	},
	doEffects:function(way){                //鍋氱壒鏁�
		way == "open"?this.popupLayer.show("slow"):this.popupLayer.hide("slow");
		
	},
	recalculatePopupIframe:function(){     //閲嶇粯popupIframe,杩欎釜涓嶇煡鎬庝箞鏀硅繘锛屽彧濂藉厛鐢ㄨ繖涓鍔炴硶銆�
		this.popupIframe.css({position:"absolute",'z-index':-1,left:0,top:0,opacity:0,width:this.popupBlk.get(0).offsetWidth,height:this.popupBlk.get(0).offsetHeight});
	},
	close:function(){                      //鍏抽棴鏂规硶
		this.options.useOverlay?this.overlay.hide():null;
		this.options.useFx?this.doEffects("close"):this.popupLayer.hide();
	}
});