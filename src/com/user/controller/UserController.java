package com.user.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.dto.User;
import com.user.service.UserService;
import com.util.BaseController;
import com.util.JsonUtil;

/**
 * 用户
 * @author ydd
 *
 */
@Component("user")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 9130858646519749765L;
	@Autowired
	private UserService userService;
	private String SERVERNAME="user";

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject root=null;
		String subService =BaseController.getUriNode(SERVERNAME, 0, request);
		JSONObject param=BaseController.setQueryParams(request);
		User user=(User)JsonUtil.getJsonToObject(param.toString(), User.class);
		if(subService.equals("login")){
			root=userService.login(user,request);
		}else if(subService.equals("editpass")){
			JSONObject nuser= (JSONObject) request.getSession().getAttribute("BookManageSystem_User");
			param.put("id", nuser.getInt("id"));
			root=userService.editPassword(param);
		}
		BaseController.responseJson(root, response);
	}
	
	/**
	 * POST
	 */	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject root=null;
		String subService =BaseController.getUriNode(SERVERNAME, 0, request);
		JSONObject param=BaseController.setQueryParams(request);
		if(subService.equals("loginOut")){
			//安全退出
			request.getSession().invalidate();
		}else if(subService.equals("selectUsers")){
			User user=(User)JsonUtil.getJsonToObject(param.toString(), User.class);
			root=userService.selectUsers(user);
			BaseController.responseJson(root, response);
		}else if(subService.equals("resetUserpass")){
			User user=(User)JsonUtil.getJsonToObject(param.toString(), User.class);
			root=userService.resetUserpass(user);
			BaseController.responseJson(root, response);
		}else if(subService.equals("insertUser")){
			User user=(User)JsonUtil.getJsonToObject(param.toString(), User.class);
			root=userService.insertUser(user);
			BaseController.responseJson(root, response);
		}else if(subService.equals("deleteUser")){
			User user=(User)JsonUtil.getJsonToObject(param.toString(), User.class);
			root=userService.deleteUser(user);
			BaseController.responseJson(root, response);
		}else if(subService.equals("registerUser")){
			//注册账号
			User user=(User)JsonUtil.getJsonToObject(param.toString(), User.class);
			root=userService.registerUser(user);
			BaseController.responseJson(root, response);
		}else if(subService.equals("checkUser")){
			//审核账号
			User user=(User)JsonUtil.getJsonToObject(param.toString(), User.class);
			root=userService.updateUser(user);
			BaseController.responseJson(root, response);
		}		
	}
}
