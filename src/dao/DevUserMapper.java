package dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.AppInfo;
import entity.BackendUser;
import entity.DevUser;

public interface DevUserMapper {
	/**
	 * ��½
	 */
	public DevUser login(DevUser devUser);
	/**
	 * �����û������ж��û��Ƿ����
	 * @param backendUser
	 * @return
	 */
	public DevUser queryName(@Param("devCode") String userCode);
	/**
	 * ���������ж��û��Ƿ����
	 * @param backendUser
	 * @return
	 */
	public DevUser queryPwd(@Param("devPassword") String userPassword);
	
	/**
	 * ģ����ѯ
	 * @param appInfo
	 * @return
	 */
	public List<AppInfo> Arrlist(Object appInfo);
}
