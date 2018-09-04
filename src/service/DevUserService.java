package service;

import org.apache.ibatis.annotations.Param;

import entity.BackendUser;
import entity.DevUser;

public interface DevUserService {
	public DevUser login(DevUser devUser);
	
	/**
	 * 根据用户名称判断用户是否存在
	 * @param backendUser
	 * @return
	 */
	public DevUser queryName(@Param("devCode") String userCode);
	/**
	 * 根据密码判断用户是否存在
	 * @param backendUser
	 * @return
	 */
	public DevUser queryPwd(@Param("devPassword") String userPassword);
}	
