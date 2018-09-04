package dao;

import org.apache.ibatis.annotations.Param;

import entity.BackendUser;

/**
 * ��̨�û��ӿ�
 * @author Administrator
 *
 */
public interface UserMapper {
	/**
	 * �ж��û����Ƿ����
	 * @return
	 */
	public BackendUser queryId(BackendUser backendUser);
	/**
	 * �����û������ж��û��Ƿ����
	 * @param backendUser
	 * @return
	 */
	public BackendUser queryName(@Param("userCode") String userCode);
	/**
	 * ���������ж��û��Ƿ����
	 * @param backendUser
	 * @return
	 */
	public BackendUser queryPwd(@Param("userPassword") String userPassword);
}
