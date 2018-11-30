package jp.co.rakus.ec2018c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.domain.Item;
import jp.co.rakus.ec2018c.service.RecommendService;

@Controller
@RequestMapping("/")
@Transactional
//FIXME:javadoc漏れ
public class Extra505Controller {

	@Autowired
	private RecommendService recommendService;
	
	@RequestMapping("/500")
	
	public String index(Model model) {
		List<Item> itemRecommendList = recommendService.recommend();
		model.addAttribute("itemRecommendList",itemRecommendList);
		
		return "500";
	}
}
