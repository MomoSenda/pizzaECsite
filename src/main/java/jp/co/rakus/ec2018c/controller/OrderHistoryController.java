package jp.co.rakus.ec2018c.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.domain.Order;
import jp.co.rakus.ec2018c.repository.OrderHistoryService;

@Controller
@RequestMapping("/orderhistory")
public class OrderHistoryController {
	public static final List<Integer> ORDERED_ID = Arrays.asList(1,2,3);
	@Autowired
	private OrderHistoryService orderHistoryService;
	
	@RequestMapping("/history")
	public String index(Model model) {
		//TODO:ログインフィルターを実装したらここでuserIdにログインユーザのidを入れる(仮で0を入れている)
		Integer userId = 1;

		List<Order> orders = orderHistoryService.findByUserIdAndStatusList(userId, ORDERED_ID);
		model.addAttribute("orders", orders);
		return "orderhistory";
	}
}
