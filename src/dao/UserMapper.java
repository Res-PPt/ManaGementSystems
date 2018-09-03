package dao;

import entity.BackendUser;

/**
 * 后台用户接口
 * @author Administrator
 *
 */
public interface UserMapper {
	/**
	 * 根据条件查询后台用户信息
	 * @return
	 */
	public BackendUser queryId(BackendUser backendUser);
}
