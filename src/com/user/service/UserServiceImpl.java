package com.user.service;




import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.dao.UserDao;
import com.user.dto.User;
import com.util.CommonUtil;
import com.util.MD5;
import com.util.SharePage;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;


	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public JSONObject login(User user,HttpServletRequest request){
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try{
			if(user.getUser_name()!=null && !user.getUser_name().equals("")
					&& user.getPassword()!=null && !user.getPassword().equals("")){
				String loginPassWord=user.getPassword();
				user.setNumber(user.getUser_name());
				user=userDao.login(user);
				if(user==null){
					message="用户名不存在";
				}else{ 
					if(user.getPassword().equals(loginPassWord)){
						//登录成功				
						root=JSONObject.fromObject(user);
						request.getSession().setAttribute("BookManageSystem_User", root);
						message="登录成功";
						success=true;
					}else{
						//密码错误
						message="用户名或者密码错误";
					}
				}
			}else{
				message="参数信息异常";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			root.put("message", message);
			root.put("success", success);
		}
		return root;
	}
	
	/**
	 * 修改密码
	 * @param param
	 * @return
	 */
	public JSONObject editPassword(JSONObject param) {
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try{
			Integer id=param.getInt("id");
			String passWord=param.getString("oldPass");
			String newpassWord=param.getString("newPass");
			User user=new User();
			user.setId(id);
			user.setPassword(passWord);
			user.setNewpassWord(newpassWord);
			success=userDao.updateUserPassWordById(user)>0;
			if(success){
				message="修改密码成功";
			}else{
				success=false;
				message="原密码输入错误";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}
	
	/**
	 * 查询用户列表信息
	 * @param user
	 * @return
	 */
	public JSONObject selectUsers(User user){
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try {
			user.setIsShare(1);//需要分页
			Integer maxCount=userDao.selectUsersCount(user);
			user.setMaxCount(maxCount);
			root=SharePage.getSharePageContext(userDao.selectUsers(user),user);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}
	
	/**
	 * 重置用户密码
	 * @param param
	 * @return
	 */
	public JSONObject resetUserpass(User user) {
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try{
			if(user.getId()!=null && user.getId()!=0){
				user.setPassword(MD5.MD5Encode("666666"));
				if(success=userDao.updateUserById(user)>0){
					success=true;
					message="重置密码成功";
				};
			}else{
				message="数据不存在";
			}
		} catch (Exception e) {
			message="程序处理异常";
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}
	
	/**
	 * 添加用户信息(后台注册)
	 * @param user
	 * @return
	 */
	public JSONObject insertUser(User user){
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try {
			User ckUser=new User();
			ckUser.setNumber(user.getUser_name());
			if(userDao.login(ckUser)==null){
				user.setCreate_time(CommonUtil.getDateDayTime());
				Integer userId=userDao.insertUser(user);
				if(userId>0){
					success=true;
				}
				message=success?"用户添加成功":"程序处理异常";
			}else{
				message="用户名已存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}
	
	/**
	 * 删除用户
	 * @param param
	 * @return
	 */
	public JSONObject deleteUser(User user) {
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try{
			if(user.getId()!=null && user.getId()!=0){
				if(userDao.deleteUser(user)>0){
					success=true;
					message=success?"删除成功":"程序处理异常";
				}else{
					message="删除失败";
				}
			}else{
				message="数据不存在";
			}

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}

	@Override
	public JSONObject registerUser(User user) {
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try {
			User ckUser=new User();
			ckUser.setNumber(user.getUser_name());
			if(userDao.login(ckUser)==null){
				user.setPower(0);
				user.setCreate_time(CommonUtil.getDateDayTime());
				Integer userId=userDao.insertUser(user);
				if(userId>0){
					success=true;
				}
				message=success?"用户注册成功，请等待管理员审核":"程序处理异常";
			}else{
				message="用户名已存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}

	@Override
	public JSONObject updateUser(User user) {
		JSONObject root=new JSONObject();
		String message="程序处理异常";
		boolean success=false;
		try {
			User ckUser=new User();
			ckUser.setNumber(user.getUser_name());			
			Integer userId=userDao.updateUserById(user);
			if(userId>0){
				success=true;
			}
			message=success?"修改成功":"程序处理异常";			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			root.put("success", success);
			root.put("message", message);
		}
		return root;
	}
	
	
}
