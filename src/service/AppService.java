package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.AppCategory;
import entity.AppInfo;
import entity.AppVersion;
import entity.DataDictionary;
import entity.pages;

public interface AppService {
	/**
	 * ����������ѯAPP��Ϣ
	 * @param appInfo
	 * @return
	 */
	//public List<AppInfo> queryAPP(AppInfo appInfo,Integer currentPageNo,Integer num);
	public List<AppInfo> queryAPP(AppInfo appInfo);
	/**
	 * ����������ѯAPP��Ϣ������
	 * @param appInfo
	 * @return
	 */
	public int queryAPP1(AppInfo appInfo);
	List<DataDictionary> queryTypes();
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
	 * ��ѯ��������
	 * @return
	 */
	public List<AppCategory> queryApp3();
	/**
	 * ���ݰ汾id��ѯApp��Ϣ
	 * @return
	 */
	public AppInfo queryVersionid(Object id);
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
	/**
	 * ��ѯ���ж���ҳ
	 * @param pages
	 * @return
	 */
	public int AppYe(int result,int num1);
}
