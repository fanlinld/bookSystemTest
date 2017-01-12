package com.menus.dto;

/**
 * 导航菜单
 * @author fl
 *
 */

public class Menus {
	/**主键ID**/
	private Integer id;
	/**父级ID**/
	private Integer pid;
	/**菜单名称**/
	private String name;
	/**菜单图标**/
	private String icon;
	/**路径**/
	private String url;
	/**状态**/
	private String state;
	/**菜单权限**/
	private String permission;
	private Integer sort;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}

