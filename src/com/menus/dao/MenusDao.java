package com.menus.dao;

import java.util.List;

import com.menus.dto.Menus;

public interface MenusDao {
	/**
	 * 查询所有子菜单信息 
	 * @param menus
	 * @return
	 */
	public List<Menus> selectChildmenusInfo(Menus menus);
	
	/**
	 * 查询所有父菜单信息 
	 * @param menus
	 * @return
	 */
	public List<Menus> selectParsmenusInfo(Menus menus);
	
	/**
	 * 查询所有菜单信息 
	 * @param menus
	 * @return
	 */
	public List<Menus> selectAllmenusInfo(Menus menus);

	/**
	 * 查询菜单信息 
	 * @param menus
	 * @return
	 */
	public Menus selectMenus(Menus menus);
	
	/**
	 * 新增菜单信息 
	 * @param menus
	 * @return
	 */
	public int insertMenus(Menus menus);
	
	/**
	 * 修改菜单信息 
	 * @param menus
	 * @return
	 */
	public int updateMenus(Menus menus);
	
	/**
	 * 删除菜单信息 
	 * @param menus
	 * @return
	 */
	public int deleteMenus(Menus menus);
	

}
