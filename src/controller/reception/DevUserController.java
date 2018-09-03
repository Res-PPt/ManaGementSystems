package controller.reception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 开发者后台
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/dev")
public class DevUserController {
@RequestMapping("/login")
public String login(){
	return "devlogin";
}
}
