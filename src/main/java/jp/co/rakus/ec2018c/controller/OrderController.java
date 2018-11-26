package jp.co.rakus.ec2018c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.domain.Order;
import jp.co.rakus.ec2018c.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	//未注文のstatus
	public Integer UNORDERED_ID = 0;
	
	/**
	 * 注文情報を表示する.
	 * 注文情報をuseridとstatus(0:未注文)で調べてmodelに格納する
	 * 
	 * @param model　モデル
	 * @return 注文表示画面
	 */
	@RequestMapping("/orderconfirm")
	public String index(Model model){
		Integer status = UNORDERED_ID; 
		
		//TODO:ログインフィルターを実装したらここでuserIdにログインユーザのidを入れる(仮で0を入れている)
		Integer userId = 1;
		
		Order order = orderService.findByUserIdAndStatus(userId, status);
		model.addAttribute("orderitems", order);
		return "orderconfirm";
	}
}
