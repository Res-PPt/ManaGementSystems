package dao;

import org.apache.ibatis.annotations.Param;

import entity.BackendUser;

/**
 * 后台用户接口
 * @author Administrator
 *
 */
public interface UserMapper {
	/**
	 * 判断用户名是否存在
	 * @return
	 */
	public BackendUser queryId(BackendUser backendUser);
	/**
	 * 根据用户名称判断用户是否存在
	 * @param backendUser
	 * @return
	 */
	public BackendUser queryName(@Param("userCode") String userCode);
	/**
	 * 根据密码判断用户是否存在
	 * @param backendUser
	 * @return
	 */
	public BackendUser queryPwd(@Param("userPassword") String userPassword);
}
