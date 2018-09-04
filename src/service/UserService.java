package service;


import org.apache.ibatis.annotations.Param;

import dao.UserMapper;
import entity.BackendUser;
public interface UserService {
	/**
	 * ����������ѯ�û���Ϣ
	 * @param backendUser
	 * @return
	 */
	public BackendUser query1(BackendUser backendUser);
	/**
	 * �����û������ж��û��Ƿ����
	 * @param backendUser
	 * @return
	 */
	public BackendUser queryName(String userCode);
	/**
	 * ���������ж��û��Ƿ����
	 * @param backendUser
	 * @return
	 */
	public BackendUser queryPwd(String userPassword);
}
