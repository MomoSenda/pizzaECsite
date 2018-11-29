package jp.co.rakus.ec2018c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ec2018c.repository.OrderItemRepository;

@Service
public class ShoppingCartBadgeService {
	@Autowired
	private OrderItemRepository orederItemRepository;
	
	public Integer countByOrderId(Integer orderId) {
		return orederItemRepository.countByOrderId(orderId);
	}
}