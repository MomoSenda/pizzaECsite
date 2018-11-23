package jp.co.rakus.ec2018c.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec2018c.domain.Order;

/**
 * ordersテーブルを操作するリポジトリ.
 * 
 * @author kento.uemura
 *
 */
@Repository
public class OrderRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	/** ordersのテーブル名の定数 */
	public static final String TABLE_NAME = "orders";
	
	//作成途中
	private static final ResultSetExtractor<Order> ORDER_RESULT_SET_EXTRACTOR = (rs) ->{
		Order order = new Order();
		while(rs.next()) {
			
		}
		return order;
	};
	
	//作成途中
	public Order findByUserIdAndStatus(Integer userId,Integer status){
		String sql = "SELECT o.id order_id,o.user_id user_id,o.status order_status,o.total_price order_total_price,"
				+ "o.order_date order_date,o.destination_name order_destination_name,o.destination_email order_destination_email,"
				+ "o.destination_zipcode order_destination_zipcode,o.destination_address order_destination_address,"
				+ "o.destination_tel order_destination_tel,o.delivery_time order_delivery_time,o.payment_method order_payment_method,"
				+ "oi.id order_item_id,oi.item_id item_id,oi.quantity order_item_quantity,oi.size order_item_size,i.name item_name,"
				+ "i.description item_description,i.price_m item_price_m,i.price_l item_price_l,i.image_path item_image_path,"
				+ "i.deleted item_deleted,ot.id order_topping_id,ot.topping_id topping_id,t.name topping_name,t.price_m topping_price_m,"
				+ "t.price_l topping_price_l FROM orders o JOIN order_items oi ON o.id = oi.order_id "
				+ "JOIN order_toppings ot ON oi.id = ot.order_item_id INNER JOIN items i ON oi.item_id = i.id "
				+ "INNER JOIN toppings t ON ot.topping_id = t.id ORDER BY i.name ,t.name; ";
		
		return null;
	}
	
	private SimpleJdbcInsert insert;
	
	/**
	 * initメソッド.
	 * 
	 * テーブルにinsertするためのメソッド
	 */
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate)template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName(TABLE_NAME);
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	/**
	 * saveメソッド.
	 * 
	 * @param order insert,updateするOrderドメイン.
	 * @return insertの場合は自動採番されたidが入ったOrderドメイン、updateの場合は引数をそのまま返す
	 */
	public Order save(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		if(order.getId() == null) {
			Number key = insert.executeAndReturnKey(param);
			order.setId(key.intValue());
		}else {
			String sql = "UPDATE "+TABLE_NAME+" SET user_id=:userId status=:status,total_price=:totalPrice,order_date=:orderDate,"
					+ "destination_name=:destinationName,destination_email=:destinationEmail,destination_zipcode=:destinationZipcode,"
					+ "destination_address=:destinationAddress,destination_tel=:destinationTel,delivery_time=:deliveryTime,"
					+ "payment_method=:paymentMethod WHERE id=:id;";
			template.update(sql, param);
		}
		return order;
	}
}
