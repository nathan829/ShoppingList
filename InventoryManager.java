import java.util.Scanner;
import java.text.DecimalFormat;

public class InventoryManager {

	private Product[] product;
	private int lastIndex;

	private DecimalFormat df;

	public InventoryManager() {
		product = new Product[5];
		lastIndex = 0;

		df = new DecimalFormat("#.##");
	}

	public void addItem(Scanner scanner) {
		scanner = new Scanner(System.in);

		System.out.println("Enter the name of the product: ");
		String productName = scanner.nextLine();
		addItem(productName);
		
		System.out.println("Enter the price of " + productName);
		double productPrice = scanner.nextDouble();
		addPrice(productPrice);
	}

	private void addItem(String productName) {
		if(lastIndex >= product.length-1){
			expandArray(5);
		}

		product[lastIndex] = new Product(productName);
	}

	public void removeItem(Scanner scanner) {
		scanner = new Scanner(System.in);

		System.out.println("Enter the name of product you want to remove");
		String removeRequest = scanner.nextLine();
		int position = checkPresent(removeRequest);

		if(position >= 0) {
			removeAndShift(position);
			lastIndex--;
			System.out.println("item removed!");
		}
		else {
			System.out.println("item not in list");
			//do something here, consider a while loop
		}
	}

	private int checkPresent(String itemName) {
		for(int i = 0; i < lastIndex; i++) {
			if(itemName.equals(product[i].getProductName())) {
				return i;
			}
			
		}
		return -1;
	}

	public void modifyPrice(Scanner scanner) {
		scanner  = new Scanner(System.in);

		System.out.println("Enter the name of the product you want to modify");
		String modifyRequest = scanner.nextLine();
		int position = checkPresent(modifyRequest);
		if(position >= 0) {
			System.out.println("Enter the new price of " + modifyRequest);
			double newPrice = scanner.nextDouble();
			changeProductPrice(position, newPrice);
			System.out.println("Item price changed");
		}
		else {
			System.out.println("item not in list");
			//do something here, consider a while loop
		}
	}

	private void changeProductPrice(int position, double newPrice) {
		product[position].setProductPrice(newPrice);
	}

	private void removeAndShift(int position) {
		for(int i = position + 1; i < product.length; i++) {
			product[i-1] = product[i];
		}
	}

	private void expandArray(int expandBy) {
		Product[] temp = new Product[product.length + expandBy];
		for(int i = 0; i < product.length; i++) {
			temp[i] = product[i];
		}
		//pointer reference back to original array
		product = temp;
	}

	public String getName(int num) {
		return product[num].getProductName();
	}

	public double getPrice(int num) {
		return product[num].getProductPrice();
	}

	public int getLength() {
		int count = 0;
		for(int i = 0; i < product.length; i++) {
			if(product[i] != null){
				count++;
			}
		}
		return count;
	}

	private void addPrice(double productPrice) {
		product[lastIndex++].setProductPrice(productPrice);
	}

	public double calculateSubtotal() {
		double total = 0.00;
		for(int i = 0; i < lastIndex; i++) {
			total += product[i].getProductPrice();
		}
		return total;
	}

}