package com.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 分页
 * @author ydd
 *
 */
public class SharePage {
	/**当前页**/
	private Integer page=0;
	/**总页数**/
	private Integer maxPage=1;
	/**分页总条数**/
	private Integer maxCount=0;
	/**分页开始位置**/
	private Integer startIndex=0;
	/**分页结束位置**/
	private Integer endIndex=0;
	/**页面分页条数**/
	private Integer rows=10;
	/**是否采用分页   0不分页   1分页**/
	private Integer isShare=0;
	
	/**
	 * 封装分页内容
	 * @param maxCount 分页总条数
	 * @param list 分页当前条数
	 * @return
	 */
	public static JSONObject getSharePageContext(List<?> list,SharePage sharePage) throws Exception{
		JSONObject resultJsonObject=new JSONObject();
		if(list!=null && list.size()>0){
			resultJsonObject.put("success", true);
			//总页数
			resultJsonObject.put(SysConstant.SHAREPAGE_MAXPAGE,sharePage.getMaxPage());
			//当前页
			resultJsonObject.put(SysConstant.SHAREPAGE_PAGEINDEX,sharePage.getPage());
			//总条数
			resultJsonObject.put(SysConstant.SHAREPAGE_MAXCOUNT, sharePage.getMaxCount());
			//页面大小
			resultJsonObject.put(SysConstant.SHAREPAGE_PAGESIZE, sharePage.getRows());
			//分页当前条数
			JSONArray array=JSONArray.fromObject(list);
			for(int i=0;i<array.size();i++){
				JSONObject item=(JSONObject) array.get(i);
				item.remove(SysConstant.SHAREPAGE_MAXPAGE);
				item.remove(SysConstant.SHAREPAGE_PAGEINDEX);
				item.remove(SysConstant.SHAREPAGE_MAXCOUNT);
				item.remove(SysConstant.SHAREPAGE_PAGESIZE);
				item.remove(SysConstant.SHAREPAGE_ISSHARE);
				item.remove(SysConstant.SHAREPAGE_ENDINDEX);
				item.remove(SysConstant.SHAREPAGE_STARTINDEX);
			}
			resultJsonObject.put(SysConstant.LISTCONTEXT_ITEM,array);
		}else{
			resultJsonObject.put("success", false);
			resultJsonObject.put("message", "没有要查询的数据");
			resultJsonObject.put("rows", "[]");
			resultJsonObject.put("total", 0);
		}
		return resultJsonObject;
	}
	
	/**
	 * 去除查询结果中分页字段
	 * @param param
	 * @return
	 */
	public static JSONObject clearSharePageCloumn(JSONObject root){
		if(root!=null){
			root.remove(SysConstant.SHAREPAGE_MAXPAGE);
			root.remove(SysConstant.SHAREPAGE_PAGEINDEX);
			root.remove(SysConstant.SHAREPAGE_MAXCOUNT);
			root.remove(SysConstant.SHAREPAGE_PAGESIZE);
			root.remove(SysConstant.SHAREPAGE_ISSHARE);
			root.remove(SysConstant.SHAREPAGE_ENDINDEX);
			root.remove(SysConstant.SHAREPAGE_STARTINDEX);
		}
		return root;
	}
	
	
	public Integer getPage() {
		page=(page<1)?1:(page>maxPage)?maxPage:page;
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getMaxPage() {
		maxPage=(maxCount+rows-1)/rows;
		//maxPage=(maxCount%rows==0)?maxCount/rows:maxCount/rows+1;
		return maxPage;
	}
	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}
	public Integer getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(Integer maxCount) {
		maxPage=(maxCount+rows-1)/rows;
		page=(page<1)?1:(page>maxPage)?maxPage:page;
		this.maxCount = maxCount;
	}
	public Integer getStartIndex() {
		startIndex=(this.page-1)*this.rows;
		startIndex=startIndex<0?0:startIndex;
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getEndIndex() {
		endIndex=this.startIndex+this.rows;
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getIsShare() {
		return isShare;
	}
	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
	}
}
