package jp.co.rakus.ec2018c.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.domain.LoginUser;
import jp.co.rakus.ec2018c.domain.Order;
import jp.co.rakus.ec2018c.domain.User;
import jp.co.rakus.ec2018c.repository.OrderHistoryService;
import jp.co.rakus.ec2018c.service.OrderService;
import jp.co.rakus.ec2018c.service.ShoppingCartBadgeService;

@Controller
@RequestMapping("/orderhistory")
public class OrderHistoryController {
	public static final List<Integer> ORDERED_ID = Arrays.asList(1,2,3);
	public static final Integer UNOURDERD_ID = 0;
	@Autowired
	private OrderHistoryService orderHistoryService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ShoppingCartBadgeService shoppingCartBadgeService;
	
	@RequestMapping("/history")
	public String index(Model model,@AuthenticationPrincipal LoginUser loginUser) {
		User user = loginUser.getUser();
		//ログイン中のユーザを取得する
		Integer userId = user.getId();

		List<Order> orders = orderHistoryService.findByUserIdAndStatusList(userId, ORDERED_ID);
		Order order = orderService.findByUserIdAndStatus(userId, UNOURDERD_ID);
		if(order != null) {
			Integer cartCount = shoppingCartBadgeService.countByOrderId(order.getId());
			model.addAttribute("cartCount", cartCount);
		}
		model.addAttribute("orders", orders);
		return "orderhistory";
	}
}
