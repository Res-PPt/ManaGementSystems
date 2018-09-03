package controller.backstage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.UserService;
@Controller
@RequestMapping("/manager")
public class Usercontroller {
	@Autowired
	
	@RequestMapping("/login")
	public String login() {
		return "backendlogin";
	}
}
