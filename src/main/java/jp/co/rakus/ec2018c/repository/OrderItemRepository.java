package jp.co.rakus.ec2018c.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec2018c.domain.OrderItem;

/**
 * 注文商品のリポジトリ.
 * 
 * @author risa.okumura
 *
 */
@Repository
public class OrderItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private SimpleJdbcInsert insert;
	
	/**
	 * 注文商品が登録されたときに採番されたIDを取得する.
	 */
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate)namedParameterJdbcTemplate.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_items");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	/**
	 * 注文商品の情報を登録する.
	 * 
	 * @param orderItem 注文商品の情報
	 * @return　IDを採番された注文商品の情報
	 */
	public OrderItem save (OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		Number key = insert.executeAndReturnKey(param);
		orderItem.setId(key.intValue());

		return orderItem;
	}
	
	/**
	 * 注文商品の情報を削除する.
	 * 
	 * @param OrderItemId 注文商品のID
	 */
	public void deleteById (Integer orderItemId) {
		String sql = "WITH deleted AS (DELETE FROM order_items WHERE id = :id RETURNING id)\r\n"
					+ "DELETE FROM order_toppings WHERE id IN (SELECT id FROM deleted);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderItemId);
		namedParameterJdbcTemplate.update(sql, param);
	}
}
