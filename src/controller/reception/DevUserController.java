package controller.reception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.AppInfo;
import entity.DevUser;
import service.DevUserServiceimpl;
/**
 * 开发者后台
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/dev")
public class DevUserController {
	@Autowired
	private DevUserServiceimpl DevUserService;
	
//	private AppInfo appInfo;
	
	@RequestMapping("/login")
	public String login(){
		return "devlogin";
	}

	/**
	 * 验证登陆
	 */
	@RequestMapping("/dologin")
	public String dologin(HttpServletRequest request){
		String devCode = request.getParameter("devCode");
		String devPassword = request.getParameter("devPassword");
		DevUser devUser =new DevUser();
		devUser.setDevCode(devCode);
		devUser.setDevPassword(devPassword);
		DevUser bss = DevUserService.login(devUser);
		
		if(bss!=null){
		request.setAttribute("devUserSession",bss);
		return "developer/main";
	}else{ 
		DevUser bts = DevUserService.queryName(devCode);
		DevUser btss = DevUserService.queryPwd(devPassword);
		if(bts==null && btss!=null){
			request.setAttribute("error","用户名输入有误");
		}else if(bts!=null && btss==null){
			request.setAttribute("error","密码输入有误");
		}else{
		request.setAttribute("error","用户名或密码都不存在");
		}
		return "devlogin";
	}
}
	/**
	 * 第一次进入页面先把下拉框添加进去
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(AppInfo appInfo,HttpServletRequest request){
		/**
		 * 首先判断是否为空如果为空那就先把他给添加下拉框和分页
		 */
		List<AppInfo> list = DevUserService.ListAPP(appInfo);
		request.setAttribute("appInfoList", list);
		return "developer/appinfolist";
	}
	
	/**
	 * 跳转到查询查看页面
	 */
	@RequestMapping("/appview/{id}")
	public String appview(@PathVariable String id,HttpServletRequest resquest) {
		AppInfo sa=DevUserService.queryid(id);
		AppInfo appInfo=new AppInfo();
		appInfo.setId(Integer.valueOf(id));
		List<AppInfo> list=DevUserService.queryids(id);
		resquest.setAttribute("appInfo", sa);
		resquest.setAttribute("appVersionList", list);
		return "developer/appinfoview";
	}
	
	/**
	 * 退出系统
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		
		
		request.getSession().removeAttribute("devUserSession");
		return "devlogin";
	}
	
	
	/**
	 * 删除
	 */
	
	@RequestMapping(value="paginquery",produces = "text/html;charset=UTF-8")
	   @ResponseBody
	   public String Delid(HttpServletRequest reques,
			   HttpServletResponse response) {
				return null;
	}

}
