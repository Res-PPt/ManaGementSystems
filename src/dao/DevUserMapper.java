package dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.AppInfo;
import entity.AppVersion;
import entity.BackendUser;
import entity.DataDictionary;
import entity.DevUser;

public interface DevUserMapper {
	/**
	 * 登陆
	 */
	public DevUser login(DevUser devUser);
	/**
	 * 根据用户名称判断用户是否存在
	 * @param backendUser
	 * @return
	 */
	public DevUser queryName(@Param("devCode") String userCode);
	
	
	List<AppInfo> Arrlist (AppInfo appInfo);
	/**
	 * 根据密码判断用户是否存在
	 * @param backendUser
	 * @return
	 */
	public DevUser queryPwd(@Param("devPassword") String userPassword);
	
	/**
	 * 模糊查询
	 * @param appInfo
	 * @return
	 */
	public List<AppInfo> ListAPP(Object appInfo);
	/**
	 * 查询app信息
	 */
	AppInfo queryid(@Param("id") String id);
	 /**
	  * 查询历史版本
	  * @param id
	  * @return
	  */
	 List<AppVersion> queryids(@Param("appId") String id);
	 
	 AppInfo queryidsf(@Param("id") String id);
	 
	 int delappinfo(@Param("id")String id);
	 /**
	  * 删除app应用信息
	  * @param appid
	  * @return
	  */
	 int delappidv(@Param("appid")String appid);
	 int quertinfoid(@Param("id")String id);
	 /**
	  * 根据类型编码查询信息
	  * @return
	  */
	 public List<DataDictionary> queryCode(@Param("typeCode")String typeCode);
	 /**
	  * 修改App信息
	  * @param appInfo
	  * @return
	  */
	 public int AppUpd(AppInfo appInfo);
	 int addinfo(AppInfo appInfo);
	 
	 AppInfo apkname(@Param("APKName")String APKName);
	 
	 /**
	  * 新增app版本
	  * @param appVersion
	  * @return
	  */
	 int appver(AppVersion appVersion);
	 /**
	  * 更新info表的vid版本
	  */
	 int infovid(AppInfo appInfo);
	 
	 AppVersion queryver(@Param("appid")String id);
	 
	 /**
	  * 查询版本表
	  */
	 AppVersion queryVersion(@Param("id")String id);
	 /**
	  * 更新数据
	  */
	 int moder(AppVersion appVersion);
	 /**
	  * 根据id修改App上下架状态
	  */
	 public boolean AppUpd1(AppInfo appInfo);

}
