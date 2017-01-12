package com.util;

/**
 * 系统常量
 * @author ydd
 *
 */
public class SysConstant {
	public static final String USER_REGURL_SERVER="http://www.52fdw.com/new/user_saveforgsyd.asp";
	
	
	/**用户状态0正常 **/
	public static final Integer USER_STATE_NORMAL=0;
	/**用户状态 1帐号已过期(未激活)**/
	public static final Integer USER_STATE_DISABLE=1;
	
	/**订单状态 0:未处理 */
	public static final Integer PLANS_EFFECT_UNTREATED=0;
	/**订单状态  1：已生效（正在使用）*/
	public static final Integer PLANS_EFFECT_BEINGUSED=1;
	/**订单状态  2：已过期*/
	public static final Integer PLANS_EFFECT_EXPIRED=2;
	
	
	/**查询集合内容**/
	public static final String LISTCONTEXT_ITEM="rows";
	/**分页：当前页 pageIndex***/
	public static final String SHAREPAGE_PAGEINDEX="page";
	/**分页：总页数maxPage***/
	public static final String SHAREPAGE_MAXPAGE="maxPage";
	/**分页：总条数maxCount***/
	public static final String SHAREPAGE_MAXCOUNT="total";
	/**分页条数**/
	public static final String SHAREPAGE_PAGESIZE="rows";
	/**分页开始位置**/
	public static final String SHAREPAGE_STARTINDEX="startIndex";
	/**分页结束位置**/
	public static final String SHAREPAGE_ENDINDEX="endIndex";
	/**是否采用分页   0不分页   1分页**/
	public static final String SHAREPAGE_ISSHARE="isShare";
	/**lft系统默认套餐编号设置 注册体验套餐**/
	public static final String LFT_REG_PLANSTAG="002";
	/**lft系统默认套餐编号设置 免费默认套餐**/
	public static final String LFT_DEFAULT_PLANSTAG="001";
	/**lft用户标识**/
	public static final String USER_LFT_FLAG="lftznjxpt";
	/**注册用户订购套餐享受优惠价天数限制  **/
	public static final Integer REGUSER_DISCOUNT_PRICE_DAY=31;
	
	/*-----------------------------乐辅通智能教学平台------------------------------------*/
	/**智能教学平台用户标识**/
	public static final String USER_LFTZNJXPT_FLAG="lftznjxpt";
	/**人人通**/
	public static final String USER_RRT_FLAG="rrt";
	/**--应用密钥(app_secret)**/
	public static final String APP_SECRET = "lft-znjxpt-"; //app_Secret
	/*-----------------------------乐辅通智能教学平台------------------------------------*/
	
	
	
	
	
	
}
