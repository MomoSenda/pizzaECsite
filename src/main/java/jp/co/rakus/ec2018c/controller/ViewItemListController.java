package jp.co.rakus.ec2018c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.domain.Item;
import jp.co.rakus.ec2018c.form.RegisterUserForm;
import jp.co.rakus.ec2018c.form.SearchItemForm;
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
	public String list(Model model) {
		List<Item> itemList=viewItemListService.findAll();
		model.addAttribute("itemList", itemList);
		
		return "itemList";
	}

}
