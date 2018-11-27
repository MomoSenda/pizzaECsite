package jp.co.rakus.ec2018c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec2018c.domain.LoginUser;
import jp.co.rakus.ec2018c.domain.User;
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
	
//	@Autowired
//	private HttpSession session;
	
	@ModelAttribute
	public AddCartForm setUpForm() {
		return new AddCartForm();
	}
	
	/**
	 * ショッピングカートに商品を追加する.
	 * 
	 * @param userId ユーザーのID
	 * @param addcartForm　注文する商品のリクエストパラメータが入ったフォーム
	 * @return ショッピングカートリストを表示するコントローラー.
	 */
	@RequestMapping("/addCart")
	public String addCart(@AuthenticationPrincipal LoginUser loginUser ,AddCartForm addCartForm) {
		
		//ログイン認証からユーザー情報を取得し、ユーザーIDに代入.
		//もしログインしていなかったらログイン画面へ遷移.
		
//		Integer userId;
		
//		if(loginUser == null) {
//			userId = Integer.parseInt(session.getId().replaceAll("[A-Z]+", "").substring(0, 8));
//		}else {
			User user = loginUser.getUser();
			Integer userId = user.getId();
//			Integer sessionId;
//			sessionId = Integer.parseInt(session.getId().replaceAll("[A-Z]+", "").substring(0, 8));
//			System.out.println(sessionId);
//		}
		
//		System.out.println(userId);
		
		//フォームで受け取ったリクエストパラメータを対応するデータ型に変換する.
		Integer itemId = addCartForm.getIntItemId();
		Integer quantity = addCartForm.getIntQuantity();
		Character size = addCartForm.getCharSize();
		List<Integer> toppingIdList = addCartForm.getToppingIdList();

//		userId = 1;
//		Integer itemId = 1;
//		Integer quantity = 1;
//		Character size = 'M';
//		Integer toppingId = 1;
//		List<Integer> toppingIdList = new ArrayList<>();
//		toppingIdList.add(toppingId);
		
		service.addCart(itemId, userId, quantity, size, toppingIdList);
		
		return "redirect:/viewCart";
	}
	
	

}
