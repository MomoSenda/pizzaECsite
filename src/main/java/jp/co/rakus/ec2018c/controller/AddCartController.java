package jp.co.rakus.ec2018c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.form.AddCartForm;
import jp.co.rakus.ec2018c.service.AddCartService;

/**
 * ショッピングカートに商品を追加するコントローラー.
 * 
 * @author risa.okumura
 *
 */
@Controller
@RequestMapping("/")
@Transactional
public class AddCartController {
	
	@Autowired
	private AddCartService service;
	
	@ModelAttribute
	public AddCartForm setUpAddCartForm() {
		return new AddCartForm();
	}
	
	/**
	 * ショッピングカートに商品を追加する.
	 * 
	 * @param userId ユーザーのID
	 * @param addcartForm　注文する商品のリクエストパラメータが入ったフォーム
	 * @return ショッピングカートリストを表示するコントローラー.
	 */
	@RequestMapping("/addcart")
	public String addCart(Integer userId ,AddCartForm addcartForm) {
		
		//フォームで受け取ったリクエストパラメータを対応するデータ型に変換する.
		Integer itemId = addcartForm.getIntItemId();
		Integer quantity = addcartForm.getIntQuantity();
		Character size = addcartForm.getCharSize();
		List<Integer> toppingIdList = addcartForm.getToppingList();

//		userId = 1;
//		Integer itemId = 1;
//		Integer quantity = 1;
//		Character size = 'M';
//		Integer toppingId = 1;
//		List<Integer> toppingIdList = new ArrayList<>();
//		toppingIdList.add(toppingId);
		
		service.addCart(itemId, userId, quantity, size, toppingIdList);
		
		return "redirect:";
	}
	
	

}
