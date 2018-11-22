package jp.co.rakus.ec2018c.repository;

import org.springframework.stereotype.Repository;

import jp.co.rakus.ec2018c.domain.Order;

@Repository
public class OrderRepository {
	public Order findByUserIdAndStatus(Integer userId,Integer status){
		return null;
	}
}
