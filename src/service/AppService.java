package service;

import java.util.List;

import entity.AppInfo;

public interface AppService {
	/**
	 * 根据条件查询APP信息
	 * @param appInfo
	 * @return
	 */
	public List<AppInfo> queryAPP(Object appInfo);
}
