package com.roles.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roles.dto.Roles;


@Repository
public class RolesDaoImpl implements RolesDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 查询所有角色信息 
	 * @param roles
	 * @return
	 */
	public List<Roles> selectAllRolesInfo(Roles roles) {
		return this.sqlSessionTemplate.selectList("selectAllRolesInfo", roles);
	}

	/**
	 * 根据title查询角色信息 
	 * @param roles
	 * @return
	 */
	public Roles selectRolesInfo(Roles roles) {
		return this.sqlSessionTemplate.selectOne("selectRoleInfo", roles);
	}

	/**
	 * 更新角色信息 
	 * @param roles
	 * @return
	 */
	public Integer updateRoleInfo(Roles roles) {
		return this.sqlSessionTemplate.update("updateRoleInfo",roles);
	}

	/**
	 * 新增角色信息 
	 * @param roles
	 * @return
	 */
	public Integer insertRoleInfo(Roles roles) {
		return this.sqlSessionTemplate.insert("insertRoleInfo", roles);
	}

}
