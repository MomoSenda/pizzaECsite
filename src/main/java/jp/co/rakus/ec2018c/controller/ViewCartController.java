package jp.co.rakus.ec2018c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.domain.LoginUser;
import jp.co.rakus.ec2018c.domain.Order;
import jp.co.rakus.ec2018c.domain.User;
import jp.co.rakus.ec2018c.service.ViewCartService;

/**
 * ショッピングカートの中身を表示するコントローラー
 * 
 * @author risa.okumura
 *
 */
@Controller
@RequestMapping("/")
@Transactional
public class ViewCartController {
	
	@Autowired
	private ViewCartService service;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * ユーザーIDをもとに、ショッピングカートの中身（未購入状態のも）の一覧を表示する.
	 * 
	 * @param userId ユーザーＩＤ
	 * @param status　購入状態
	 * @param model　リクエストスコープ
	 * @return　ショッピングカートの中身を表示するJSP
	 */
	@RequestMapping("/viewCart")
	public String viewCart(@AuthenticationPrincipal LoginUser loginUser ,Integer status,Model model) {
		
		
		//ログイン認証からユーザー情報を取得し、ユーザーIDに代入.
		Integer userId;
		
		if(loginUser == null) {
			userId = session.getId().hashCode();
		}else {
			User user = loginUser.getUser();
			userId = user.getId();
		}
		
		//未購入の注文情報を指定.
		status = 0;
		
		Order order = service.viewCart(userId, status);
		model.addAttribute("order",order);
		
		return "cartList";
		
		
	}
	

}
