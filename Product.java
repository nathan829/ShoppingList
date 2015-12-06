public class Product {

	private String productName;
	private double productPrice;
	private int productQuantity;

	public Product() {
		productName = new String();
		productPrice = 0.00;
		productQuantity = 0;
	}	

	public Product(String productName) {
		this.productName = productName;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public String getProductName() {
		return productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

}