package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Backend_userMapper;
@Service("userMapper")
public class UserServiceimpl implements UserService{
	@Autowired
	private Backend_userMapper userMapper;

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return userMapper.count();
	}
	
	
}
