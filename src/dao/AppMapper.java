package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.AppCategory;
import entity.AppInfo;
import entity.AppVersion;
import entity.DataDictionary;

public interface AppMapper {
	/**
	 * ����������ѯAPP��Ϣ
	 * @param appInfo
	 * @return
	 */
	//public List<AppInfo> queryAPP(AppInfo appInfo,@Param("currentPageNo")Integer currentPageNo,@Param("num") Integer num);
	public List<AppInfo> queryAPP(AppInfo appInfo);
	/**
	 * ����������ѯAPP��Ϣ������
	 * @param appInfo
	 * @return
	 */
	public int queryAPP1(AppInfo appInfo);
	List<DataDictionary> queryTypes();
	/**
	 * ��ѯȫ��ƽ̨
	 * @return
	 */
	public List<DataDictionary> queryType();
	/**
	 * ��ѯһ������
	 * @return
	 */
	public List<AppCategory> queryApp1();
	/**
	 * ��ѯ������������
	 * @return
	 */
	public List<AppCategory> queryApp2(@Param("pid")Object pid);
	/**
	 * ��ѯ��������
	 * @return
	 */
	public List<AppCategory> queryApp3();
	/**
	 * ���ݰ汾id��ѯApp��Ϣ
	 * @return
	 */
	public AppInfo queryVersionid(Object versionid);
	/**
	 * ����id��ѯ�汾��Ϣ
	 * @param id
	 * @return
	 */
	public AppVersion queryVId(Object id);
	/**
	 * ���������޸�APP��Ϣ
	 * @param id
	 * @param status
	 * @return
	 */
	public int updapp(@Param("id")String id,@Param("status") String status);
}
