package service;


import org.apache.ibatis.annotations.Param;

import dao.UserMapper;
import entity.BackendUser;
public interface UserService {
	/**
	 * 根据条件查询用户信息
	 * @param backendUser
	 * @return
	 */
	public BackendUser query1(BackendUser backendUser);
	/**
	 * 根据用户名称判断用户是否存在
	 * @param backendUser
	 * @return
	 */
	public BackendUser queryName(String userCode);
	/**
	 * 根据密码判断用户是否存在
	 * @param backendUser
	 * @return
	 */
	public BackendUser queryPwd(String userPassword);
}
