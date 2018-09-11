package dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.AppInfo;
import entity.AppVersion;
import entity.BackendUser;
import entity.DataDictionary;
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
	
	
	List<AppInfo> Arrlist (AppInfo appInfo);
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
	 
	 AppInfo queryidsf(@Param("id") String id);
	 
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
	 public List<DataDictionary> queryCode(@Param("typeCode")String typeCode);
	 /**
	  * �޸�App��Ϣ
	  * @param appInfo
	  * @return
	  */
	 public int AppUpd(AppInfo appInfo);
	 int addinfo(AppInfo appInfo);
	 
	 AppInfo apkname(@Param("APKName")String APKName);
	 
	 /**
	  * ����app�汾
	  * @param appVersion
	  * @return
	  */
	 int appver(AppVersion appVersion);
	 /**
	  * ����info���vid�汾
	  */
	 int infovid(AppInfo appInfo);
	 
	 AppVersion queryver(@Param("appid")String id);
	 
	 /**
	  * ��ѯ�汾��
	  */
	 AppVersion queryVersion(@Param("id")String id);
	 /**
	  * ��������
	  */
	 int moder(AppVersion appVersion);
	 /**
	  * ����id�޸�App���¼�״̬
	  */
	 public boolean AppUpd1(AppInfo appInfo);

}
