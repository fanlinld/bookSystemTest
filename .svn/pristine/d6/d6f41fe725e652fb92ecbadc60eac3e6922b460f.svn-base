package com.roles.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.menus.dto.Menus;
import com.roles.dto.Roles;
import com.roles.service.RolesService;
import com.util.BaseController;
import com.util.JsonUtil;

@Component("roles")
public class RolesController extends HttpServlet{
	private static final long serialVersionUID = 6251810705530373791L;
	@Autowired
	private RolesService rolesService;
	private String SERVERNAME="roles";
	
	/**
	 * 查询所有角色信息
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject root=new JSONObject();
		String subService=BaseController.getUriNode(SERVERNAME, 0, request);
		//设置查询参数
		JSONObject param=BaseController.setQueryParams(request);
		Roles roles=(Roles)JsonUtil.getJsonToObject(param.toString(), Roles.class);
		Menus menus=(Menus)JsonUtil.getJsonToObject(param.toString(), Menus.class);
		if(subService.equals("getroles")){
			root=rolesService.getRoles(roles);
		}
		if(subService.equals("getmenustree")){
			root=rolesService.getPermissonsTree(menus);
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
		Roles roles=(Roles)JsonUtil.getJsonToObject(param.toString(), Roles.class);
		if(subService.equals("updateroles")){
			root=rolesService.updateRoles(roles);
			BaseController.responseJson(root, response);
		}else if(subService.equals("getRolesComeBox")){
			JSONArray array=new JSONArray();
			array=rolesService.getRolesComeBox(roles);
			BaseController.responseJson(array, response);
		}
	}
}
