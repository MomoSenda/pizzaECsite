package jp.co.rakus.ec2018c.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.domain.Item;
import jp.co.rakus.ec2018c.domain.LoginUser;
import jp.co.rakus.ec2018c.domain.Order;
import jp.co.rakus.ec2018c.domain.User;
import jp.co.rakus.ec2018c.form.SearchItemForm;
import jp.co.rakus.ec2018c.service.ShoppingCartBadgeService;
import jp.co.rakus.ec2018c.service.ViewCartService;
import jp.co.rakus.ec2018c.service.ViewItemListService;

/**
 * itemの一覧表示を行うコントローラー
 * 
 * @author momo.senda
 *
 */
@Controller
@RequestMapping("/viewItemList")
@Transactional
public class ViewItemListController {

	@Autowired
	private  ViewItemListService viewItemListService;
	
	@Autowired
	private ViewCartService service;
	
	@Autowired
	private ShoppingCartBadgeService shoppingCartBadgeService;
	
	@Autowired
	private HttpSession session;

	@ModelAttribute
	public SearchItemForm setUpForm() {
		return new SearchItemForm();
	}

	/**
	 * itemの全情報を取得し、画面に表示する.
	 * @param model モデル
	 * @return item情報表示画面
	 */
	@RequestMapping("/list")
	public String list(Model model,@AuthenticationPrincipal LoginUser loginUser) {
		List<Item> itemList=viewItemListService.findAll();
		model.addAttribute("itemList", itemList);
		Integer userId;
		
		if(loginUser == null) {
			userId = session.getId().hashCode();
		}else {
			User user = loginUser.getUser();
			userId = user.getId();
		}
		
		//未購入の注文情報を指定.
		Integer status = 0;
		
		Order order = service.viewCart(userId, status);
		if(order != null) {
			Integer cartCount = shoppingCartBadgeService.countByOrderId(order.getId());
			model.addAttribute("cartCount", cartCount);
		}
	
		return "itemList";
	}

}
