package dao;

import entity.BackendUser;

/**
 * ��̨�û��ӿ�
 * @author Administrator
 *
 */
public interface UserMapper {
	/**
	 * ����������ѯ��̨�û���Ϣ
	 * @return
	 */
	public BackendUser queryId(BackendUser backendUser);
}
