package controller.backstage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * ������
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