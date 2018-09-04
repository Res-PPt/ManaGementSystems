package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserMapper;
import entity.BackendUser;
@Service("UserMapper")
public class UserServiceimpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	/**
	 * 根据条件查询用户信息
	 */
	public BackendUser query1(BackendUser backendUser) {
		BackendUser sb= userMapper.queryId(backendUser);
		return sb;
	}
	/**
	 * 根据用户名称查询信息
	 */
	public BackendUser queryName(String userCode) {
		BackendUser sb = userMapper.queryName(userCode);
		return sb;
	}
	/**
	 * 根据密码查询用户信息
	 */
	public BackendUser queryPwd(String userPassword) {
		BackendUser sb = userMapper.queryPwd(userPassword);
		return sb;
	}
}
