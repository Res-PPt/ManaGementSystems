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
	 * 根据条件查询APP信息
	 */
	public List<AppInfo> queryAPP(AppInfo appInfo) {
		List<AppInfo> list = appMapper.queryAPP(appInfo);
		return list;
	}
	/**
	 * 查询一级菜单
	 */
	public List<AppCategory> queryApp1() {
		List<AppCategory> list = appMapper.queryApp1();
		return list;
	}
	/**
	 * 查询全部平台
	 */
	public List<DataDictionary> queryType() {
		List<DataDictionary> list = appMapper.queryType();
		return list;
	}
	/**
	 * 查询二三级名称
	 */
	public List<AppCategory> queryApp2(Object pid) {
		List<AppCategory> list = appMapper.queryApp2(pid);
		return list;
	}
	/**
	 * 根据versionid查询APP信息
	 */
	public AppInfo queryVersionid(Object versionid) {
		AppInfo app = appMapper.queryVersionid(versionid);
		return app;
	}
	/**
	 * 根据id查询版本信息
	 */
	public AppVersion queryVId(Object id) {
		AppVersion app =appMapper.queryVId(id); 
		return app;
	}
	/**
	 * 根据Id修改APP信息
	 */
	public int updapp(String id, String status) {
		int num = appMapper.updapp(id, status);
		return num;
	}

}
