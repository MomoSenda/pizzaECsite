package jp.co.rakus.ec2018c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ec2018c.domain.Order;
import jp.co.rakus.ec2018c.domain.OrderItem;
import jp.co.rakus.ec2018c.domain.OrderTopping;
import jp.co.rakus.ec2018c.repository.OrderItemRepository;
import jp.co.rakus.ec2018c.repository.OrderRepository;
import jp.co.rakus.ec2018c.repository.OrderToppingRepository;

@Service
public class AddCartService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * ショッピングカートに商品を追加するサービスクラス.
	 * 
	 * @param itemId
	 *            商品のID
	 * @param userId
	 *            ユーザーのID
	 * @param quantity
	 *            注文商品の数量
	 * @param size
	 *            注文商品のサイズ
	 * @param toppingIdList
	 *            注文トッピングのIDの詰まったリスト
	 */
	public void addCart(Integer itemId, Integer userId, Integer quantity, Character size, List<Integer> toppingIdList) {

		Integer status = 0;
		//Integer orderId = 1;
		
		// ユーザーIDと注文ステータスが未購入のものを検索
		Integer orderId = null;
		
		Order order = orderRepository.findByUserIdAndStatus(userId, status);
		//Order order = null;
		
		int totalPrice = 0;

		// 注文情報がない場合は、新しい注文クラスを作成し、採番された注文のＩＤを取得する.
		// 注文情報がある場合は、すで採番されている注文情報のＩＤを取得する.
		if (order == null) {
			Order newOrder = new Order();
			newOrder.setUserId(userId);
			newOrder.setStatus(status);
			newOrder.setTotalPrice(totalPrice);
			orderId = orderRepository.save(newOrder).getId();
		} else {
			orderId = order.getId();
		}

		// 注文商品の情報を登録し、採番された注文情報のＩＤを取得する.
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(itemId);
		orderItem.setOrderId(orderId);
		orderItem.setQuantity(quantity);
		orderItem.setSize(size);
		Integer orderItemId = orderItemRepository.save(orderItem).getId();

		// 注文トッピングの情報を登録する.
		for (Integer toppingId : toppingIdList) {
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setToppingId(toppingId);
			orderTopping.setOrderItemId(orderItemId);
			orderToppingRepository.save(orderTopping);
		}

	}

}