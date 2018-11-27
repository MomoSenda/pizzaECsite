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

@Controller
@RequestMapping("/orderhistory")
public class OrderHistoryController {
	public static final List<Integer> ORDERED_ID = Arrays.asList(1,2,3);
	@Autowired
	private OrderHistoryService orderHistoryService;
	
	@RequestMapping("/history")
	public String index(Model model,@AuthenticationPrincipal LoginUser loginUser) {
		User user = loginUser.getUser();
		//ログイン中のユーザを取得する
		Integer userId = user.getId();

		List<Order> orders = orderHistoryService.findByUserIdAndStatusList(userId, ORDERED_ID);
		model.addAttribute("orders", orders);
		return "orderhistory";
	}
}
