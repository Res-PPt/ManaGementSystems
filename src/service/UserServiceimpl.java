package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserMapper;
import entity.BackendUser;
@Service("UserMapper")
public class UserServiceimpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	public BackendUser query1(BackendUser backendUser) {
		BackendUser sb= userMapper.queryId(backendUser);
		return sb;
	}
}
