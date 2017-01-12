package com.roles.dto;

/**
 * 角色
 * @author fl
 *
 */
public class Roles {
	/**角色主键ID**/
	private Integer id;
	/**角色名称**/
	private String title;
	/**角色权限**/
	private String permission;
	
	private String ids;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
}
