package com.util;

import java.util.Properties;

public class SearchUtil {
	
	    //知识点搜索接口
	    public static String defaultUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewzsd";
	    public static String czsxUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewzsd";
	    public static String czwlUrl="http://114.215.149.216:9095/lftzsdServer/searchNewWuli";
		public static String czhxUrl="http://114.215.149.216:9095/lftzsdServer/searchNewHuaxue";
		public static String czswUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewMiddleBiology";
		public static String czyyUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewMiddleEnglish";
		public static String czlsUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewMiddleHistory";
		public static String czzzUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewMiddlePolitics";
		public static String gzsxUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewSeniorMath";
		public static String gzwlUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewSeniorPhysics";
		public static String gzhxUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewSeniorChemistry";
		public static String gzswUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewSeniorBiology";
		public static String gzyyUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewSeniorEnglish";
		public static String gzlsUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewSeniorHistory";
		public static String gzzzUrl="http://lftbjb.52fdw.com:9095/lftzsdServer/searchNewSeniorPolitics";
		
		//题目搜索接口
		public static String searchQuestUrl="http://search-slb.daoxuehao.com:9095/SearchClient/search";
		
	public static void loadProperties() {
		try {
			Properties props = new Properties();
			props.load(SearchUtil.class
					.getResourceAsStream("/search.properties"));
			defaultUrl = props.getProperty("defaultUrl");
			czsxUrl = props.getProperty("czsxUrl");
			czwlUrl = props.getProperty("czwlUrl");
			czhxUrl = props.getProperty("czhxUrl");
			czswUrl = props.getProperty("czswUrl");
			czyyUrl = props.getProperty("czyyUrl");
			czlsUrl = props.getProperty("czlsUrl");
			czzzUrl = props.getProperty("czzzUrl");	
			gzsxUrl = props.getProperty("gzsxUrl");
			gzwlUrl = props.getProperty("gzwlUrl");
			gzhxUrl = props.getProperty("gzhxUrl");
			gzswUrl = props.getProperty("gzswUrl");
			gzyyUrl = props.getProperty("gzyyUrl");
			gzlsUrl = props.getProperty("gzlsUrl");
			gzzzUrl = props.getProperty("gzzzUrl");
			searchQuestUrl = props.getProperty("searchQuestUrl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static {
		loadProperties();
	}
	
	public static String getSearchZsdUrl(int section,int subject){
		if(section==1){
				return defaultUrl;	
		}else if(section==2){
            if(subject==1){
				return czsxUrl;
			}else if(subject==2){
				return czwlUrl;
			}else if(subject==3){
				return czhxUrl;
			}else if(subject==4){
				return czswUrl;
			}else if(subject==6){
				return czyyUrl;
			}else if(subject==7){
				return czlsUrl;
			}else if(subject==8){
				return czzzUrl;
			}else{
				return defaultUrl;	
			}		
		}else if(section==3){
			if(subject==1){
				return gzsxUrl;
			}else if(subject==2){
				return gzwlUrl;
			}else if(subject==3){
				return gzhxUrl;
			}else if(subject==4){
				return gzswUrl;
			}else if(subject==6){
				return gzyyUrl;
			}else if(subject==7){
				return gzlsUrl;
			}else if(subject==8){
				return gzzzUrl;
			}else{
				return defaultUrl;	
			}
		}else{
			return defaultUrl;
		}		
	}
}
