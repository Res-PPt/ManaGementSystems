package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DevUserMapper;
import dao.UserMapper;
import entity.AppInfo;
import entity.AppVersion;
import entity.BackendUser;
import entity.DataDictionary;
import entity.DevUser;

@Service("devUserMappers")
public class DevUserServiceimpl implements DevUserService{
	@Autowired
	private DevUserMapper devUserMapper;

	@Override
	public DevUser login(DevUser devUser) {
		// TODO Auto-generated method stub
		return devUserMapper.login(devUser);
	}

	@Override
	public DevUser queryName(String userCode) {
		// TODO Auto-generated method stub
		return devUserMapper.queryName(userCode);
	}

	@Override
	public DevUser queryPwd(String userPassword) {
		// TODO Auto-generated method stub
		return devUserMapper.queryPwd(userPassword);
	}


	@Override
	public List<AppInfo> ListAPP(Object appInfo) {
		// TODO Auto-generated method stub
		List<AppInfo> list = devUserMapper.ListAPP(appInfo);
		return list;
	}



	@Override
	public int delappinfo(String id) {
		// TODO Auto-generated method stub
		return devUserMapper.delappinfo(id);
	}

	@Override
	public int delappidv(String appid) {
		// TODO Auto-generated method stub
		return devUserMapper.delappidv(appid);
	}

	@Override
	public int quertinfoid(String id) {
		// TODO Auto-generated method stub
		return devUserMapper.quertinfoid(id);
	}

	@Override
	public AppInfo queryid(String id) {
		// TODO Auto-generated method stub
		return devUserMapper.queryid(id);
	}

	@Override
	public List<AppVersion> queryids(String id) {
		// TODO Auto-generated method stub
		return devUserMapper.queryids(id);
	}

	@Override
	public List<DataDictionary> queryCode(String typeCode) {
		return devUserMapper.queryCode(typeCode);
	}

	/**
	 * �޸�APP��Ϣ
	 */
	public int AppUpd(AppInfo appInfo) {
		return devUserMapper.AppUpd(appInfo);
	}

	@Override
	public List<AppInfo> Arrlist(AppInfo appInfo) {
		// TODO Auto-generated method stub
		return devUserMapper.Arrlist(appInfo);
	}

	@Override
	public int addinfo(AppInfo appInfo) {
		// TODO Auto-generated method stub
		return devUserMapper.addinfo(appInfo);
	}

	@Override
	public AppInfo apkname(String apkname) {
		// TODO Auto-generated method stub
		return devUserMapper.apkname(apkname);
	}

	@Override
	public int appver(AppVersion appVersion) {
		// TODO Auto-generated method stub
		return devUserMapper.appver(appVersion);
	}

	

	@Override
	public AppInfo queryidsf(String id) {
		// TODO Auto-generated method stub
		return devUserMapper.queryidsf(id);
	}

	@Override
	public AppVersion queryver(String id) {
		// TODO Auto-generated method 
		return devUserMapper.queryver(id);
	}

	@Override
	public int infovid(AppInfo appInfo) {
		// TODO Auto-generated method stub
		return devUserMapper.infovid(appInfo);
	}

	@Override
	public AppVersion queryVersion(String id) {
		// TODO Auto-generated method stub
		return devUserMapper.queryVersion(id);
	}

	@Override
	public int moder(AppVersion appVersion) {
		// TODO Auto-generated method stub
		return devUserMapper.moder(appVersion);
	}

	@Override
	public boolean AppUpd1(AppInfo appInfo) {
		// TODO Auto-generated method stub
		return devUserMapper.AppUpd1(appInfo);
	}



	


	
}
