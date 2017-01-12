package com.user.service;


import javax.servlet.http.HttpServletRequest;

import com.user.dto.User;

import net.sf.json.JSONObject;


public interface UserService {
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public JSONObject login(User user,HttpServletRequest request);
	
	
	/**
	 * 修改密码
	 * @param param
	 * @return
	 */
	public JSONObject editPassword(JSONObject param);
	
	/**
	 * 查询用户列表信息
	 * @param user
	 * @return
	 */
	public JSONObject selectUsers(User user);
	
	/**
	 * 重置用户密码
	 * @param param
	 * @return
	 */
	public JSONObject resetUserpass(User user);
	
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	public JSONObject insertUser(User user);
	
	/**
	 * 删除用户
	 * @param param
	 * @return
	 */
	public JSONObject deleteUser(User user);
	
	/**
	 * 用户信息注册
	 * @param user
	 * @return
	 */
	public JSONObject registerUser(User user);
	
	/**
	 * 用户信息审核
	 * @param user
	 * @return
	 */
	public JSONObject updateUser(User user);
	
}
