package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.AppCategory;
import entity.AppInfo;
import entity.AppVersion;
import entity.DataDictionary;

public interface AppMapper {
	/**
	 * 根据条件查询APP信息
	 * @param appInfo
	 * @return
	 */
	//public List<AppInfo> queryAPP(AppInfo appInfo,@Param("currentPageNo")Integer currentPageNo,@Param("num") Integer num);
	public List<AppInfo> queryAPP(AppInfo appInfo);
	/**
	 * 根据条件查询APP信息的条数
	 * @param appInfo
	 * @return
	 */
	public int queryAPP1(AppInfo appInfo);
	List<DataDictionary> queryTypes();
	/**
	 * 查询全部平台
	 * @return
	 */
	public List<DataDictionary> queryType();
	/**
	 * 查询一级名称
	 * @return
	 */
	public List<AppCategory> queryApp1();
	/**
	 * 查询二，三级名称
	 * @return
	 */
	public List<AppCategory> queryApp2(@Param("pid")Object pid);
	/**
	 * 查询级别名称
	 * @return
	 */
	public List<AppCategory> queryApp3();
	/**
	 * 根据版本id查询App信息
	 * @return
	 */
	public AppInfo queryVersionid(Object versionid);
	/**
	 * 根据id查询版本信息
	 * @param id
	 * @return
	 */
	public AppVersion queryVId(Object id);
	/**
	 * 根据条件修改APP信息
	 * @param id
	 * @param status
	 * @return
	 */
	public int updapp(@Param("id")String id,@Param("status") String status);
}
