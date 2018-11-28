package jp.co.rakus.ec2018c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/yetlogin")
public class LoginController {
	
	@RequestMapping("/login")
	public String login() {
		return "/login";
	}
}
