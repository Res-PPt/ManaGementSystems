package dao;

import java.util.List;

import entity.AppCategory;
import entity.AppInfo;
import entity.DataDictionary;

public interface AppMapper {
	/**
	 * ����������ѯAPP��Ϣ
	 * @param appInfo
	 * @return
	 */
	public List<AppInfo> queryAPP(Object appInfo);
	/**
	 * ��ѯƽ̨��Ϣ
	 * @return
	 */
	public List<DataDictionary> queryData();
	/**
	 * ��ѯһ������
	 * @return
	 */
	public List<AppCategory> queryApp();
}
