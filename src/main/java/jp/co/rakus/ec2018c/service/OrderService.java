package jp.co.rakus.ec2018c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ec2018c.domain.Order;
import jp.co.rakus.ec2018c.repository.OrderRepository;

/**
 * 注文情報を表示するためのサービス.
 * 
 * @author kento.uemura
 *
 */
@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * UserId,Statusを指定して注文情報を取得する.
	 * 
	 * @param userId ログインユーザのID
	 * @param status 注文の状態 (0:未注文,1:未入金,2:入金済み,3:発送済み,9:キャンセル)
	 * @return 取得した注文情報
	 */
	public Order findByUserIdAndStatus(Integer userId,Integer status) {
		return orderRepository.findByUserIdAndStatus(userId, status);
	}
}
