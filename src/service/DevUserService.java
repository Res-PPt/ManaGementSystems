package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.AppInfo;
import entity.BackendUser;
import entity.DevUser;

public interface DevUserService {
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
	public List<AppInfo> Arlist(Object appInfo);
}	
