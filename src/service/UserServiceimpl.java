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
	 * ����������ѯ�û���Ϣ
	 */
	public BackendUser query1(BackendUser backendUser) {
		BackendUser sb= userMapper.queryId(backendUser);
		return sb;
	}
	/**
	 * �����û����Ʋ�ѯ��Ϣ
	 */
	public BackendUser queryName(String userCode) {
		BackendUser sb = userMapper.queryName(userCode);
		return sb;
	}
	/**
	 * ���������ѯ�û���Ϣ
	 */
	public BackendUser queryPwd(String userPassword) {
		BackendUser sb = userMapper.queryPwd(userPassword);
		return sb;
	}
}
