package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.AppInfo;
import entity.AppVersion;
import entity.BackendUser;
import entity.DataDictionary;
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
	public List<AppInfo> ListAPP(Object appInfo);
	/**
	 * ��ѯapp��Ϣ
	 */
	AppInfo queryid(@Param("id") String id);
	 /**
	  * ��ѯ��ʷ�汾
	  * @param id
	  * @return
	  */
	 List<AppVersion> queryids(@Param("appId") String id);
	 
	 
	 int delappinfo(@Param("id")String id);
	 /**
	  * ɾ��appӦ����Ϣ
	  * @param appid
	  * @return
	  */
	 int delappidv(@Param("appid")String appid);
	 
	 int quertinfoid(@Param("id")String id);
	 /**
	  * �������ͱ����ѯ��Ϣ
	  * @return
	  */
	 public List<DataDictionary> queryCode(String typeCode);
	 /**
	  * �޸�App��Ϣ
	  * @param appInfo
	  * @return
	  */
	 public int AppUpd(AppInfo appInfo);
}	
