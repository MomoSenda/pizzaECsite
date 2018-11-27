package jp.co.rakus.ec2018c.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.domain.LoginUser;
import jp.co.rakus.ec2018c.domain.Order;
import jp.co.rakus.ec2018c.domain.User;
import jp.co.rakus.ec2018c.form.OrderDestinationForm;
import jp.co.rakus.ec2018c.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	//未注文のstatus
	public Integer UNORDERED_ID = 0;
	
	@ModelAttribute
	public OrderDestinationForm setUpForm() {
		return new OrderDestinationForm();
	}
	
	/**
	 * 注文情報を表示する.
	 * 注文情報をuseridとstatus(0:未注文)で調べてmodelに格納する
	 * 
	 * @param model　モデル
	 * @return 注文表示画面
	 */
	@RequestMapping("/orderconfirm")
	public String index(Model model,@AuthenticationPrincipal LoginUser loginUser){
		Integer status = UNORDERED_ID; 
		//ログイン中のユーザを取得する
		User user = loginUser.getUser();
		Integer userId = user.getId();
		
		Order order = orderService.findByUserIdAndStatus(userId, status);
		model.addAttribute("order", order);
		return "orderconfirm";
	}
	
	@RequestMapping("/order")
	public String order(@Validated OrderDestinationForm form,BindingResult result,@AuthenticationPrincipal LoginUser loginUser) {
		Integer status = UNORDERED_ID;
		//ログイン中のユーザを取得する
		User user = loginUser.getUser();
		Integer userId = user.getId();
		
		Order order = orderService.findByUserIdAndStatus(userId, status);

		//formの内容をorderに詰める
		order.setStatus(Integer.valueOf(form.getPaymentMethod()));
		order.setTotalPrice(order.getCalcTotalPrice());
		order.setOrderDate(new Date());
		order.setDestinationName(form.getDestinationName());
		order.setDestinationEmail(form.getDestinationEmail());
		order.setDestinationZipcode(form.getDestinationZipcode());
		order.setDestinationAddress(form.getDestinationAddress());
		order.setDestinationTel(form.getDestinationTel());
		order.setDeliveryTime(orderService.stringToTimestamp(form.getDeliveryTime()));
		order.setPaymentMethod(Integer.valueOf(form.getPaymentMethod()));
		
		orderService.update(order);
		
		//注文完了画面にリダイレクトで遷移
		return "redirect:/order/finish";
	}
	
	@RequestMapping("/finish")
	public String orderFinished() {
		return "orderfinished";
	}
}
