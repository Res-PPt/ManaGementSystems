package dao;

import java.util.List;

import entity.AppCategory;
import entity.AppInfo;
import entity.DataDictionary;

public interface AppMapper {
	/**
	 * 根据条件查询APP信息
	 * @param appInfo
	 * @return
	 */
	public List<AppInfo> queryAPP(Object appInfo);
	/**
	 * 查询平台信息
	 * @return
	 */
	public List<DataDictionary> queryData();
	/**
	 * 查询一级名称
	 * @return
	 */
	public List<AppCategory> queryApp();
}
