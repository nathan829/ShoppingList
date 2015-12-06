import java.util.Scanner;
import java.text.DecimalFormat;

public class InventoryManager {

	private Product[] product;
	private int lastIndex;

	public InventoryManager() {
		product = new Product[5];
		lastIndex = 0;
	}

	public void addItem(Scanner scanner) {
		scanner = new Scanner(System.in);

		System.out.println("Enter the name of the product: ");
		String productName = scanner.nextLine();
		int index = checkPresent(productName);

		boolean present = false;

		if(index >= 0)
			present = true;

		System.out.println("Enter the quantity of " + productName + "'s");
		int productQuantity = inputReady(scanner);

		if(present) {
			addToExisting(index, productQuantity);
			System.out.println("Quantity added to existing product");
		}
		else {
			System.out.println("Enter the price of each " + productName);
			double productPrice = scanner.nextDouble();

			addNewItem(productName);
			addQuantity(productQuantity);
			addPrice(productPrice);
		}

	}

	private int inputReady(Scanner scanner) {
		boolean valid = false;
		int productQuantity = 0;
		while(!valid) {
			try {
				if(scanner.hasNextInt()) {
					productQuantity = scanner.nextInt();
					valid = true;
				}
				else {
					throw new Exception("ERROR: Please enter a number");
				}
			}
			catch(Exception error) {
				System.out.println(error.getMessage());
				resetScanner(scanner);
			}
		}
		return productQuantity;
	}


	// public void addItem(Scanner scanner) {
	// 	scanner = new Scanner(System.in);

	// 	System.out.println("Enter the name of the product: ");
	// 	String productName = scanner.nextLine();
	// 	int index = checkPresent(productName);

	// 	boolean present = false;
	// 	boolean valid = false;

	// 	if(index >= 0)
	// 		present = true;

	// 	System.out.println("Enter the quantity of " + productName + "'s");
	// 	int productQuantity = scanner.nextInt();


	// 	if(present) {
	// 		addToExisting(index, productQuantity);
	// 		System.out.println("Quantity added to existing product");
	// 	}
	// 	else {
	// 		System.out.println("Enter the price of each " + productName);
	// 		double productPrice = scanner.nextDouble();

	// 		addNewItem(productName);
	// 		addQuantity(productQuantity);
	// 		addPrice(productPrice);
	// 	}

	// }

	

	private void addNewItem(String productName) {
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
			System.out.println("Item not in list");
			//do something here, consider a while loop
		}
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
			System.out.println("Item not in list");
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
		product = temp;
	}

	private void changeProductPrice(int position, double newPrice) {
		product[position].setProductPrice(newPrice);
	}

	private void addQuantity(int productQuantity) {
		product[lastIndex].setProductQuantity(productQuantity);
	}

	private void addToExisting(int index, int quantityToBeAdded) {
		int existingQuantity = getQuantity(index);
		product[index].setProductQuantity(existingQuantity + quantityToBeAdded);
	}

	private void addPrice(double productPrice) {
		product[lastIndex++].setProductPrice(productPrice);
	}

	public String getName(int num) {
		return product[num].getProductName();
	}

	public double getPrice(int num) {
		return product[num].getProductPrice();
	}

	public int getQuantity(int num) {
		return product[num].getProductQuantity();
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

	public double calculateSubtotal() {
		double total = 0.00;
		for(int i = 0; i < lastIndex; i++) {
			total += getPrice(i);
		}
		return total;
	}

	public int calculateQuantity() {
		int total = 0;
		for(int i = 0; i < lastIndex; i++) {
			total += getQuantity(i);
		}
		return total;
	}

	private void resetScanner(Scanner scanner) {
		scanner.nextLine();
	}
}