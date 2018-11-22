package jp.co.rakus.ec2018c.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.domain.User;
import jp.co.rakus.ec2018c.form.RegisterUserForm;
import jp.co.rakus.ec2018c.repository.UserRepository;
import jp.co.rakus.ec2018c.service.RegisterUserService;

@Controller
@RequestMapping("/registeruser")
public class RegisterUserController {

	@Autowired
	private RegisterUserService registerUserService;
	@Autowired
	private UserRepository userRepository;
	
	
	
	/**
	 * フォームを初期化する.
	 * @return　フォーム
	 */
	@ModelAttribute
	public RegisterUserForm setUpForm() {
		return new RegisterUserForm();
	}
	
	
	/**
	 * メンバー情報登録画面を表示する.
	 * @return　メンバー情報登録画面
	 */
	@RequestMapping("/form")
	public String form() {
		return "";
	}
	
	
	
	/**
	 * ユーザー情報を登録する.
	 * @param form　　　フォーム
	 * @param result　リザルト
	 * @param model　　モデル
	 * @return　　　　　　ログイン画面
	 */
	@RequestMapping("/create")
	public String create(@Validated RegisterUserForm form,
			BindingResult result,
			Model model) {
		
		String email = form.getEmail();
		User user = userRepository.findByEmail(email);
		if(user != null) {
			result.rejectValue("email",null ,"このメールアドレスは既に使われております");
			return form();
		}
		
		String password = form.getPassword();
		String checkPassword = form.getCheckPassword();
		if(!(password.equals(checkPassword))) {
			result.rejectValue("password",null,"確認用パスワードと入力が異なります");
			return form();
		}
		
		if(result.hasErrors()) {
			return form();
		}
		
		
		User registeredUser = new User();
		BeanUtils.copyProperties(form, registeredUser);
		registerUserService.save(registeredUser);
		return "redirect";
	}
	

	
}
