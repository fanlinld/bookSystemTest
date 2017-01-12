package com.menus.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.menus.dto.Menus;
/**
 *	菜单dao层实现类
 * @author 
 *
 */
@Repository
public class MenusDaoImpl implements MenusDao{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 查询所有子菜单信息 
	 * @param menus
	 * @return
	 */
	public List<Menus> selectChildmenusInfo(Menus menus) {
		return this.sqlSessionTemplate.selectList("selectChildmenusInfo", menus);
	}

	/**
	 * 查询所有父菜单信息 
	 * @param menus
	 * @return
	 */
	public List<Menus> selectParsmenusInfo(Menus menus) {
		return this.sqlSessionTemplate.selectList("selectParsmenusInfo", menus);
	}

	/**
	 * 查询所有菜单信息 
	 * @param menus
	 * @return
	 */
	public List<Menus> selectAllmenusInfo(Menus menus) {
		return this.sqlSessionTemplate.selectList("selectAllmenusInfo", menus);
	}
	
	public Menus selectMenus(Menus menus){
		return this.sqlSessionTemplate.selectOne("selectMenus", menus);
	}
	
	/**
	 * 新增菜单信息 
	 * @param menus
	 * @return
	 */
	public int insertMenus(Menus menus){
		return this.sqlSessionTemplate.insert("insertMenus", menus);
	}
	
	/**
	 * 修改菜单信息 
	 * @param menus
	 * @return
	 */
	public int updateMenus(Menus menus){
		return this.sqlSessionTemplate.update("updateMenus", menus);
	}

	/**
	 * 删除菜单信息 
	 * @param menus
	 * @return
	 */
	public int deleteMenus(Menus menus){
		return this.sqlSessionTemplate.delete("deleteMenus", menus);
	}
	
	

}
