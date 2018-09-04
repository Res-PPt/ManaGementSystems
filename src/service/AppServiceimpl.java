package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AppMapper;
import entity.AppInfo;
@Service("AppMapper")
public class AppServiceimpl implements AppService{
	@Autowired
	private AppMapper appMapper;
	/**
	 * ����������ѯAPP��Ϣ
	 */
	public List<AppInfo> queryAPP(Object appInfo) {
		List<AppInfo> list = appMapper.queryAPP(appInfo);
		return list;
	}

}
