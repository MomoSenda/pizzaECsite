package jp.co.rakus.ec2018c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.domain.Item;
import jp.co.rakus.ec2018c.domain.Topping;
import jp.co.rakus.ec2018c.service.ShowItemDetailService;

/**
 * 商品の詳細画面を表示するコントローラ.
 * 
 * @author momo.senda
 *
 */
@Controller
@RequestMapping("/ShowItemDetail")
public class ShowItemDetailController {
	@Autowired
	private ShowItemDetailService  showItemDetailService;

	/**
	 * 商品の詳細情報を取得して、画面に表示する.
	 * 
	 * @param id 商品のid
	 * @param model モデル
	 * @return　商品の詳細情報
	 */
	@RequestMapping("/detail/{id}")
	public String index(@PathVariable("id") int id,Model model) {
		Item item=showItemDetailService.load(id);
		List<Topping> toppingList=showItemDetailService.findAll();
		
		model.addAttribute("item",item);
		model.addAttribute("toppingList",toppingList);
		return "itemDetail";
	}
}
