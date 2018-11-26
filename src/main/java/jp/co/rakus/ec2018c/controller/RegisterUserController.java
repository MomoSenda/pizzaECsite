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
import jp.co.rakus.ec2018c.service.RegisterUserService;

@Controller
@RequestMapping("/registeruser")
public class RegisterUserController {

	@Autowired
	private RegisterUserService registerUserService;

	
	
	
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
	 * @param model 
	 * @return　メンバー情報登録画面
	 */
	@RequestMapping("/form")
	public String form(Model model) {
		return "registeruser";
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
		
		
		
		
		//パスワード確認
		if(!form.getPassword().equals(form.getCheckPassword())) {
			result.rejectValue("checkPassword", "","パスワードと入力が異なります");
		}
		
		
		//メールアドレスが重複している場合の処理
		User checkUser = registerUserService.findByEmail(form.getEmail());
		if(checkUser != null) {
			result.rejectValue("email", "","すでにメールアドレスが登録されています");
		}
		
		
		if(result.hasErrors()) {
			return form(model);
		}
		
		//フォームの内容をドメインに格納
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user = registerUserService.save(user);
		
		return "redirect:/";
	}
	

	
}
