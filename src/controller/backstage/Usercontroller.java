package controller.backstage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.UserService;
@Controller
public class Usercontroller {
	@Autowired
	private UserService userService;
	
	@RequestMapping("s")
	public String ps() {
		System.out.println(userService.count());
		return "";
	}
}
