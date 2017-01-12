package com.menus.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
public class MenusServiceImpl implements MenusService {
	@Autowired(required=true)
	private MenusDao menusDao;
	@Autowired
	private RolesDao rolesDao;

	/**
	 * 查询所有菜单信息 
	 * @param menus
	 * @return
	 */
	public JSONObject selectmenusInfo(Menus menus,HttpServletRequest request) {
		JSONObject root=new JSONObject();
		try {
			JSONObject user=(JSONObject) request.getSession().getAttribute("BookManageSystem_User");
			Integer roleId=user.getInt("power");
			Roles roles=new Roles();
			roles.setId(roleId);
			String permission= rolesDao.selectRolesInfo(roles).getPermission();
			menus.setPermission(permission);
			List<Menus> listc=menusDao.selectChildmenusInfo(menus);
			List<Menus> listp=menusDao.selectParsmenusInfo(menus);
			JSONArray arrayp=new JSONArray();
			if(listp!=null && listp.size()>0){
				for(int i=0;i<listp.size();i++){
					Menus menusp=new Menus();
					menusp=listp.get(i);
					JSONObject itemp=new JSONObject();
					itemp.put("menuid", menusp.getId());
					itemp.put("menuname", menusp.getName());
					itemp.put("icon", menusp.getIcon());

					if(listc!=null && listc.size()>0){
						JSONArray arrayc=new JSONArray();
						for(int j=0;j<listc.size();j++){
							Menus menusc=new Menus();
							menusc=listc.get(j);
							JSONObject itemc=new JSONObject();
							if(menusc.getPid()==menusp.getId()){
								itemc.put("menuid", menusc.getId());
								itemc.put("paId", menusc.getPid());
								itemc.put("menuname", menusc.getName());
								itemc.put("icon", menusc.getIcon());
								itemc.put("url", menusc.getUrl());
								arrayc.add(itemc);									
							}
						}
						itemp.put("menus",arrayc);
						arrayp.add(itemp);
					}
				}
				root.put("listp", arrayp);
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

	public JSONObject selectParsmenusInfo(Menus menus) {
		return null;
	}

	public JSONObject updateMenus(Menus menus){
		JSONObject root=new JSONObject();
		try {
			if(menus.getId()==null||menus.getId()==0){	
				Menus m=new Menus();
				m.setName(menus.getName());
				m.setId(0);
				m.setPid(menus.getPid());
				m=menusDao.selectMenus(m);
				if(m!=null){
					root.put("success", false);
					root.put("message", "菜单已存在");
				}else{
					if(menusDao.insertMenus(menus)>0){
						root.put("success", true);
						root.put("message", "新增成功");
					}
				}
			}else{	
				if(menusDao.updateMenus(menus)>0){
					root.put("success", true);
					root.put("message", "修改成功");
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
			root.put("success", false);
			root.put("message", "服务端处理异常");
		}
		return root;
	}

	/**
	 * 删除学校信息 
	 * @param school
	 * @return
	 */
	public JSONObject deleteMenus(Menus menus){
		JSONObject root=new JSONObject();
		try{
			Menus m=new Menus();
			boolean flag=false;
			if(menus.getPid()==null||menus.getPid()==0){
				m.setPid(menus.getId());
				List<Menus> list=menusDao.selectAllmenusInfo(m);
				if(list!=null && list.size()>0){
					for(int i=0;i<list.size();i++){
						Menus menusc=new Menus();
						menusc=list.get(i);
						if(menusDao.deleteMenus(menusc)<0){
							root.put("success", false);
							root.put("message", "删除失败");
						}
					}
					flag=true;
				}else{
					flag=true;
				}
				if((menusDao.deleteMenus(menus)>0)&&flag){
					root.put("success", true);
					root.put("message", "删除成功");
				}else{
					root.put("success", false);
					root.put("message", "删除失败");
				}
			}else{
				if(menusDao.deleteMenus(menus)>0){
					root.put("success", true);
					root.put("message", "删除成功");
				}else{
					root.put("success", false);
					root.put("message", "删除失败");
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
