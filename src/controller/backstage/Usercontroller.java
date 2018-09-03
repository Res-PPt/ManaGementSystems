package controller.backstage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.BackendUser;
import service.UserService;
import service.UserServiceimpl;
/**
 * 后台用户表
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/manager")
public class Usercontroller {
	@Autowired
	@Qualifier("UserMapper")
	private UserServiceimpl userServiceimpl;
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "backendlogin";
	}
	@RequestMapping("/dologin")
	public String dologin(HttpServletRequest request){
		String userCode = request.getParameter("userCode");
		String userPassword = request.getParameter("userPassword");
		BackendUser ba = new BackendUser();
		ba.setUserCode(userCode);
		ba.setUserPassword(userPassword);
		BackendUser bss = userServiceimpl.query1(ba);
		if(bss!=null){
		request.setAttribute("userSession",bss);
		return "backend/main";
	}
		return null;
}
}
