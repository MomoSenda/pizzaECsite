package jp.co.rakus.ec2018c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
	@RequestMapping("/orderconfirm")
	public String index(Model model){
		//注文情報をOrderに詰めてモデルにセットする
		return "orderconfirm";
	}
}
