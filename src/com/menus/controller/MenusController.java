package com.menus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.util.BaseController;
import com.util.JsonUtil;

import com.menus.dto.Menus;
import com.menus.service.MenusService;

@Component("menus")
public class MenusController extends HttpServlet{
	
	private static final long serialVersionUID = -318704016538898634L;
	@Autowired
	private MenusService menusService;
	private String SERVERNAME="menus";
	
	/**
	 * 查询所有菜单信息
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject root=new JSONObject();
		String subService=BaseController.getUriNode(SERVERNAME, 0, request);
		//设置查询参数
		JSONObject param=BaseController.setQueryParams(request);
		Menus menus=(Menus) JsonUtil.getJsonToObject(param.toString(), Menus.class);
		if(subService==null){
			root=menusService.selectmenusInfo(menus,request);
		}
		BaseController.responseJson(root, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject root=new JSONObject();
		String subService=BaseController.getUriNode(SERVERNAME, 0, request);
		//设置查询参数
		JSONObject param=BaseController.setQueryParams(request);
		Menus menus=(Menus) JsonUtil.getJsonToObject(param.toString(), Menus.class);
		if(subService.equals("updateMenus")){
			root=menusService.updateMenus(menus);
		}else if(subService.equals("deleteMenus")){
			root=menusService.deleteMenus(menus);
		}
		BaseController.responseJson(root, response);
	}

}
