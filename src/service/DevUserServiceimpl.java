package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DevUserMapper;
import dao.UserMapper;
import entity.AppInfo;
import entity.BackendUser;
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
	public AppInfo queryid(String id) {
		// TODO Auto-generated method stub
		return devUserMapper.queryid(id);
	}

	@Override
	public List<AppInfo> queryids(String id) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
