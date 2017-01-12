package com.menus.service;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.menus.dto.Menus;

public interface MenusService {

	public JSONObject selectmenusInfo(Menus menus,HttpServletRequest request);

	public JSONObject updateMenus(Menus menus);
	
	public JSONObject deleteMenus(Menus menus);
	
}
