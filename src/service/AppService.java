package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.AppCategory;
import entity.AppInfo;
import entity.AppVersion;
import entity.DataDictionary;

public interface AppService {
	/**
	 * ����������ѯAPP��Ϣ
	 * @param appInfo
	 * @return
	 */
	public List<AppInfo> queryAPP(AppInfo appInfo);
	/**
	 * ��ѯһ������
	 * @return
	 */
	public List<AppCategory> queryApp1();
	
	/**
	 * ��ѯȫ��ƽ̨
	 * @return
	 */
	public List<DataDictionary> queryType();
	/**
	 * ��ѯ������������
	 * @return
	 */
	public List<AppCategory> queryApp2(Object pid);
	/**
	 * ���ݰ汾id��ѯApp��Ϣ
	 * @return
	 */
	public AppInfo queryVersionid(Object versionid);
	/**
	 * ����id��ѯ�汾��Ϣ
	 * @return
	 */
	public AppVersion queryVId(Object id);
	/**
	 * ���������޸�APP��Ϣ
	 * @param id
	 * @param status
	 * @return
	 */
	public int updapp(String id,String status);
}
