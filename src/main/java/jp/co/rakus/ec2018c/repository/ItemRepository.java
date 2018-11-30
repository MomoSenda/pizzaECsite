package jp.co.rakus.ec2018c.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec2018c.domain.Item;
import jp.co.rakus.ec2018c.domain.Topping;

/**
 * Itemテーブル操作用のリポジトリ.
 * 
 * @author momo.senda
 *
 */
@Repository
public class ItemRepository {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


	/**
	 * ResultSetオブジェクトからItemオブジェクトに変換するためのクラス実装&インスタンス化.
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
	Integer id = rs.getInt("id");
	String name = rs.getString("name");
	String description = rs.getString("description");
	Integer priceM = rs.getInt("price_m");
	Integer priceL = rs.getInt("price_l");
	String imagePath = rs.getString("image_path");
	Boolean deleted = rs.getBoolean("deleted");
	return new Item(id, name, description, priceM, priceL, imagePath, deleted);

};

	private static final ResultSetExtractor<List<Item>> ITEM_RESULT_SET_EXTRACTOR =(rs)->{
		List<Item> itemList = new LinkedList<Item>();
		Item item=null;
		List<Topping> toppingList = null;
		int beforeItemId=0;
		while(rs.next()) {
			if(rs.getInt("id")!=beforeItemId) {
				item= new Item();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				item.setPriceM(rs.getInt("price_m"));
				item.setPriceL(rs.getInt("price_l"));
				item.setImagePath(rs.getString("image_path"));
				item.setDeleted(rs.getBoolean("deleted"));
				toppingList= new ArrayList<Topping>();
				item.setToppingList(toppingList);
				itemList.add(item);
			}
			if(rs.getInt("top_id")!=0) {
				Topping topping= new Topping();
				topping.setId(rs.getInt("top_id"));
				topping.setName(rs.getString("top_name"));
				topping.setPriceM(rs.getInt("top_price_m"));
				topping.setPriceL(rs.getInt("top_price_l"));
				toppingList.add(topping);
			}
			beforeItemId = item.getId();
		}
		return itemList;
	};

	/**
	 * 商品の全情報を取得する
	 * 
	 * @return 取得したピザの情報
	 */
	public List<Item> findAll() {
		List<Item> items = namedParameterJdbcTemplate.query(
				"SELECT id,name,description,price_m,price_l,image_path,deleted FROM items ORDER BY price_m DESC",
				ITEM_ROW_MAPPER);
		return items;
	}

	/**
	 * 名前から商品を(曖昧)検索する.
	 * 
	 * @param name
	 *            商品の名前
	 * @return 検索された商品の情報
	 */
	public List<Item> findByName(String name) {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE name LIKE :name ORDER BY name DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");

		List<Item> itemList = namedParameterJdbcTemplate.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;

	}

	/**
	 * idから商品を1件検索する.
	 * 
	 * @param id
	 *            商品id
	 * @return
	 */
	public Item load(Integer id) {
		String sql="SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE id= :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Item> itemList=namedParameterJdbcTemplate.query(sql, param, ITEM_ROW_MAPPER);
		
		if(itemList.size()==0) {
			return null;
		}
		
		return itemList.get(0);
		
	}
	
	
	public List<Item> load2(Integer id) {
		String sql = "SELECT i.id,i.name,i.description,i.price_m,i.price_l,i.image_path,i.deleted,top.id AS top_id,top.name AS top_name, top.price_m AS top_price_m,top.price_l AS top_price_l FROM items AS i CROSS JOIN toppings AS top WHERE i.id=5";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
	
		List<Item> itemList = namedParameterJdbcTemplate.query(sql, param, ITEM_RESULT_SET_EXTRACTOR);
		return itemList;
	}

}
