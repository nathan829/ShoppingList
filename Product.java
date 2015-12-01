public class Product {

	private String productName;
	private double productPrice;

	public Product() {
		productName = new String();
		productPrice = 0.00;
	}	

	public Product(String productName) {
		this.productName = productName;
	}

	//mutator function
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	//accessor function
	public String getProductName() {
		return productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

}