package com.book.dto;

import com.util.SharePage;

public class Book extends SharePage{
	
	/**主键编号**/
	private Integer id;
	/**书籍名称**/
	private String name;
	private String english_name;
	/**出版社**/
	private String press;
	/**版次**/
	private String revison;
	/**类别**/
	private Integer classifyid;
	private String classify;
	/**作者**/
	private String author;
	/**译者**/
	private String translator;
	/**状态**/
	private Integer status;
	/**购买日期**/
	private String buydate;
	/**丛书**/
	private String listing;
	/**品牌**/
	private String brand;
	/**图片地址**/
	private String image;
	/**谁的**/
	private Integer source;
	
	/**页面排序**/
	private String order;
	private String sort; 
	
	/**电子书链接地址**/
	private String link;
	
	private String selbookAuthor;
	private String selclass;
	private String selbookName;
	private String seltranslator;
	private String selpress;
	private String sellisting;
	private String selbrand;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSelbookAuthor() {
		return selbookAuthor;
	}
	public void setSelbookAuthor(String selbookAuthor) {
		this.selbookAuthor = selbookAuthor;
	}
	public String getSelclass() {
		return selclass;
	}
	public void setSelclass(String selclass) {
		this.selclass = selclass;
	}
	public String getSelbookName() {
		return selbookName;
	}
	public void setSelbookName(String selbookName) {
		this.selbookName = selbookName;
	}
	public Integer getClassifyid() {
		return classifyid;
	}
	public void setClassifyid(Integer classifyid) {
		this.classifyid = classifyid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRevison() {
		return revison;
	}
	public void setRevison(String revison) {
		this.revison = revison;
	}
	public String getEnglish_name() {
		return english_name;
	}
	public void setEnglish_name(String english_name) {
		this.english_name = english_name;
	}
	public String getTranslator() {
		return translator;
	}
	public void setTranslator(String translator) {
		this.translator = translator;
	}
	public String getSeltranslator() {
		return seltranslator;
	}
	public void setSeltranslator(String seltranslator) {
		this.seltranslator = seltranslator;
	}
	public String getBuydate() {
		return buydate;
	}
	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}
	public String getListing() {
		return listing;
	}
	public void setListing(String listing) {
		this.listing = listing;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSelpress() {
		return selpress;
	}
	public void setSelpress(String selpress) {
		this.selpress = selpress;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSellisting() {
		return sellisting;
	}
	public void setSellisting(String sellisting) {
		this.sellisting = sellisting;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSelbrand() {
		return selbrand;
	}
	public void setSelbrand(String selbrand) {
		this.selbrand = selbrand;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}
