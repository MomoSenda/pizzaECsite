package jp.co.rakus.ec2018c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ec2018c.domain.User;

@Controller
@RequestMapping("/")
@SessionAttributes( types = {User.class})
public class LoginUserController {

	
	@RequestMapping("/")
	public String index( Model model,
			@RequestParam(required = false) String error) {
		System.err.println("login error:" + error);
		if (error != null) {
			System.err.println("user: login failed");
			model.addAttribute("loginError","メールアドレスまたはパスワードが不正です");
		}
		return "login";
	}
	
	
}
