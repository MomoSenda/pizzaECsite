package jp.co.rakus.ec2018c.form;


/**
 * 送り先情報が格納されるフォーム.
 * 
 * @author kento.uemura
 *
 */
public class OrderDestinationForm {
	/** 送り先名前 */
	private String destinationName;
	/** 送り先メールアドレス */
	private String destinationEmail;
	/** 送り先郵便番号 */
	private String destinationZipcode;
	/** 送り先住所 */
	private String destinationAddress;
	/** 送り先電話番号 */
	private String destinationTel;
	/** 配達時間 */
	private String deliveryTime;
	/** 支払い方法 */
	private String paymentMethod;

	//getter,setter
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getDestinationEmail() {
		return destinationEmail;
	}
	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}
	public String getDestinationZipcode() {
		return destinationZipcode;
	}
	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public String getDestinationTel() {
		return destinationTel;
	}
	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
