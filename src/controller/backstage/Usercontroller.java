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
 * ��̨�û���
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

	/**
	 * ��̨��¼
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/dologin")
	public String dologin(HttpServletRequest request) {
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
				request.setAttribute("error", "�û�����������");
			} else if (bts != null && btss == null) {
				request.setAttribute("error", "������������");
			} else {
				request.setAttribute("error", "�û��������붼������");
			}
			return "backendlogin";
		}
	}
	/**
	 * ��̨�˳���¼
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("userSession");
		return "backendlogin";
	}
}
