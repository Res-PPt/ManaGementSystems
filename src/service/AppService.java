package service;

import java.util.List;

import entity.AppInfo;

public interface AppService {
	/**
	 * ����������ѯAPP��Ϣ
	 * @param appInfo
	 * @return
	 */
	public List<AppInfo> queryAPP(Object appInfo);
}
