package jp.co.rakus.ec2018c.domain;

import java.util.List;

/**
 * 注文商品の情報を表すドメイン.
 * 
 * @author risa.okumura
 *
 */
public class OrderItem {
	
	
	/** 注文商品のID	 */
	private Integer id;
	/** 商品のID	 */
	private Integer itemId;
	/** 注文のID	 */
	private Integer orderId;
	/** 数量	 */
	private Integer quantity;
	/** サイズ　*/
	private Character size;
	/** サイズ　*/
	private Item item;
	/** 商品　*/
	private List<Topping> orderToppingList;
	
	/**
	 * 合計金額を計算する.
	 * 
	 * @return 合計金額
	 */
	public int getSubTotal() {
		return 0;
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Character getSize() {
		return size;
	}
	public void setSize(Character size) {
		this.size = size;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public List<Topping> getOrderToppingList() {
		return orderToppingList;
	}
	public void setOrderToppingList(List<Topping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}
	
	
	
	

}
