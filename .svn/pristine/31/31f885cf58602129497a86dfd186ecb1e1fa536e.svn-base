package com.user.dao;



import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.dto.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user){
		return this.sqlSessionTemplate.selectOne("login", user);
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public Integer updateUserPassWordById(User user){
		return this.sqlSessionTemplate.update("updateUserPassWordById", user);
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public Integer updateUserById(User user){
		return this.sqlSessionTemplate.update("updateUserById", user);
	}
	
	/**
	 * 查询用户列表信息
	 * @param user
	 * @return
	 */
	public List<User> selectUsers(User user){
		return this.sqlSessionTemplate.selectList("selectUsers", user);
	}
	
	
	/**
	 * 查询用户信息Count
	 * @param user
	 * @return
	 */
	public Integer selectUsersCount(User user){
		return this.sqlSessionTemplate.selectOne("selectUsersCount", user);
	}
	
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	public Integer insertUser(User user){
		this.sqlSessionTemplate.insert("insertUser", user);
		return user.getId();
	}
	
	/**
	 * 删除用户信息
	 * @param user
	 * @return
	 */
	public Integer deleteUser(User user){
		return this.sqlSessionTemplate.delete("deleteUser", user);
	}

	
	@Override
	public User selectUser(User user) {
		return this.sqlSessionTemplate.selectOne("selectUser", user);
	}
}
