package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.BackendUser;
import entity.DevUser;
import service.AppServiceimpl;
import service.DevUserServiceimpl;
import service.UserServiceimpl;

@Controller
@RequestMapping("/user")
public class Logindein {
	@Autowired
	private DevUserServiceimpl DevUserService;
	
	
	@Autowired
	private UserServiceimpl userServiceimpl;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "backendlogin";
	}
	
	@RequestMapping("/logins")
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
		DevUser bcct =(DevUser) request.getSession().getAttribute("devUserSession");
		if(bcct!=null){
			return "developer/main";
		}
		if(bss!=null){
		request.getSession().setAttribute("devUserSession",bss);
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
	 * 后台退出登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("userSession");
		return "backendlogin";
	}
	/**
	 * 后台登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/dologins")
	public String dologins(HttpServletRequest request) {
		System.out.println("2222222222222222222");
		BackendUser bct=(BackendUser)request.getSession().getAttribute("userSession");
		System.out.println("bct="+bct);
		if(bct!=null){
			return "backend/main";
		}
		String userCode = request.getParameter("userCode");
		String userPassword = request.getParameter("userPassword");
		BackendUser ba = new BackendUser();
		ba.setUserCode(userCode);
		ba.setUserPassword(userPassword);
		BackendUser bss = userServiceimpl.query1(ba);
		
		if (bss != null) {
			request.getSession().setAttribute("userSession", bss);
			return "backend/main";
		} else {
			BackendUser bts = userServiceimpl.queryName(userCode);
			BackendUser btss = userServiceimpl.queryPwd(userPassword);
			if (bts == null && btss != null) {
				request.setAttribute("error", "用户名输入有误");
			} else if (bts != null && btss == null) {
				request.setAttribute("error", "密码输入有误");
			} else {
				request.setAttribute("error", "用户名或密码都不存在");
			}
			return "backendlogin";
		}
	}
}
