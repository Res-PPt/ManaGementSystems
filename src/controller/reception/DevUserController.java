package controller.reception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.DevUser;
import service.DevUserServiceimpl;
/**
 * �����ߺ�̨
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/dev")
public class DevUserController {
	@Autowired
	private DevUserServiceimpl DevUserService;
	
	@RequestMapping("/login")
	public String login(){
		return "devlogin";
	}

	/**
	 * ��֤��½
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
			request.setAttribute("error","�û�����������");
		}else if(bts!=null && btss==null){
			request.setAttribute("error","������������");
		}else{
		request.setAttribute("error","�û��������붼������");
		}
		return "devlogin";
	}
}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		return "developer/appinfolist";
	}
}
