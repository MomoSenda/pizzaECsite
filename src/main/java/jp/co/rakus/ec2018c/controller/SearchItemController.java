package jp.co.rakus.ec2018c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.domain.Item;
import jp.co.rakus.ec2018c.form.SearchItemForm;
import jp.co.rakus.ec2018c.service.ViewItemListService;

/**
 * 	商品の検索を行うコントローラー.
 * 
 * @author momo.senda
 *
 */
@Controller
@Transactional
@RequestMapping("/SearchItem")
public class SearchItemController {
	
	@Autowired
	private ViewItemListService viewItemListService;
	
	@ModelAttribute
	public SearchItemForm setUpForm() {
		return new SearchItemForm();
	}
	
	@RequestMapping("/search")
	public String searchItem(Model model,SearchItemForm searchItemForm) {
		List<Item> itemList =viewItemListService.findByName(searchItemForm.getName());
		model.addAttribute("itemList", itemList);
		return "itemList";
	}

}
