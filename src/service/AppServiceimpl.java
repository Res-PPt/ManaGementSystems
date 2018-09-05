package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AppMapper;
import entity.AppCategory;
import entity.AppInfo;
import entity.AppVersion;
import entity.DataDictionary;
@Service("AppMapper")
public class AppServiceimpl implements AppService{
	@Autowired
	private AppMapper appMapper;
	/**
	 * ����������ѯAPP��Ϣ
	 */
	public List<AppInfo> queryAPP(AppInfo appInfo) {
		List<AppInfo> list = appMapper.queryAPP(appInfo);
		return list;
	}
	/**
	 * ��ѯһ���˵�
	 */
	public List<AppCategory> queryApp1() {
		List<AppCategory> list = appMapper.queryApp1();
		return list;
	}
	/**
	 * ��ѯȫ��ƽ̨
	 */
	public List<DataDictionary> queryType() {
		List<DataDictionary> list = appMapper.queryType();
		return list;
	}
	/**
	 * ��ѯ����������
	 */
	public List<AppCategory> queryApp2(Object pid) {
		List<AppCategory> list = appMapper.queryApp2(pid);
		return list;
	}
	/**
	 * ����versionid��ѯAPP��Ϣ
	 */
	public AppInfo queryVersionid(Object versionid) {
		AppInfo app = appMapper.queryVersionid(versionid);
		return app;
	}
	/**
	 * ����id��ѯ�汾��Ϣ
	 */
	public AppVersion queryVId(Object id) {
		AppVersion app =appMapper.queryVId(id); 
		return app;
	}
	/**
	 * ����Id�޸�APP��Ϣ
	 */
	public int updapp(String id, String status) {
		int num = appMapper.updapp(id, status);
		return num;
	}

}
