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
	 * 根据条件查询APP信息
	 * @param appInfo
	 * @return
	 */
	//public List<AppInfo> queryAPP(AppInfo appInfo,Integer currentPageNo,Integer num);
	public List<AppInfo> queryAPP(AppInfo appInfo);
	/**
	 * 根据条件查询APP信息的条数
	 * @param appInfo
	 * @return
	 */
	public int queryAPP1(AppInfo appInfo);
	List<DataDictionary> queryTypes();
	/**
	 * 查询一级名称
	 * @return
	 */
	public List<AppCategory> queryApp1();
	
	/**
	 * 查询全部平台
	 * @return
	 */
	public List<DataDictionary> queryType();
	/**
	 * 查询二，三级名称
	 * @return
	 */
	public List<AppCategory> queryApp2(Object pid);
	/**
	 * 查询级别名称
	 * @return
	 */
	public List<AppCategory> queryApp3();
	/**
	 * 根据版本id查询App信息
	 * @return
	 */
	public AppInfo queryVersionid(Object id);
	/**
	 * 根据id查询版本信息
	 * @return
	 */
	public AppVersion queryVId(Object id);
	/**
	 * 根据条件修改APP信息
	 * @param id
	 * @param status
	 * @return
	 */
	public int updapp(String id,String status);
	/**
	 * 查询共有多少页
	 * @param pages
	 * @return
	 */
	public int AppYe(int result,int num1);
}
