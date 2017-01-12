package com.roles.service;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.menus.dao.MenusDao;
import com.menus.dto.Menus;
import com.roles.dao.RolesDao;
import com.roles.dto.Roles;


@Service
@Transactional
public class RolesServiceImpl implements RolesService {
	@Autowired(required=true)
	private RolesDao rolesDao;
	@Autowired(required=true)
	private MenusDao menusDao;
	
	/**
	 * 查询所有角色信息 
	 * @param roles
	 * @return
	 */
	public JSONObject getRoles(Roles roles){
		JSONObject root=new JSONObject();
		try{
			List<Roles> list=rolesDao.selectAllRolesInfo(roles);
			if(list!=null && list.size()>0){
				root.put("list", JSONArray.fromObject(list));
				root.put("success", true);
			}else{
				root.put("success", false);
				root.put("message", "没有要查询的数据");
			}
		}catch(Exception e) {
			e.printStackTrace();
			root.put("success", false);
			root.put("message", "服务端处理异常");
		}
		return root;
	}
	
	
	/**
	 * 查询所有角色信息 
	 * @param roles
	 * @return
	 */
	public JSONArray getRolesComeBox(Roles roles){
		JSONArray array=new JSONArray();
		try {
			List<Roles> list=rolesDao.selectAllRolesInfo(roles);
			if(list!=null && list.size()>0){
				for(int i=0;i<(list.size());i++){
					JSONObject item=new JSONObject();
					if(i==0){
						item.put("selected", true);
					}
					item.put("id", list.get(i).getId());
					item.put("title", list.get(i).getTitle());
					array.add(item);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return array;
		
	}
	/**
	 * 菜单数据
	 * @param menus
	 * @return
	 */
	public JSONObject getPermissonsTree(Menus menus) {
		JSONObject root=new JSONObject();
		try {
			List<Menus> menuslist=menusDao.selectAllmenusInfo(menus);
			JSONArray array=new JSONArray();
			if(menuslist!=null && menuslist.size()>0){
				for(int i=0;i<menuslist.size();i++){
					menus=menuslist.get(i);
					JSONObject item=new JSONObject();
					item.put("id", menus.getId());
					item.put("pId", menus.getPid());
					item.put("name", menus.getName());
					item.put("menuUrl", menus.getUrl());
					item.put("menuIcon", menus.getIcon());
					item.put("menuOrder", menus.getSort());
					item.put("open",true);
					array.add(item);	
				}
				root.put("list", array);
				root.put("success", true);
			}else{
				root.put("success", false);
				root.put("message", "没有要查询的数据");
			}
		}catch (Exception e) {
			e.printStackTrace();
			root.put("success", false);
			root.put("message", "服务端处理异常");
		}
		return root;
	}

	/**
	 * 更新角色信息表
	 * @param menus
	 * @return
	 */
	public JSONObject updateRoles(Roles roles) {
		JSONObject root=new JSONObject();
		try{
			String perm=roles.getPermission();
			 roles.setPermission(perm);
			if(rolesDao.updateRoleInfo(roles)>0){
				root.put("success", true);
				root.put("message", "修改成功");
			}else{
				Roles newRole=new Roles();
				newRole.setTitle(roles.getTitle());
				newRole.setPermission(roles.getPermission());
				if(rolesDao.insertRoleInfo(newRole)>0){
					root.put("success", true);
					root.put("message", "添加成功");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			root.put("success", false);
			root.put("message", "服务端处理异常");
		}
		return root;
	}
}  

