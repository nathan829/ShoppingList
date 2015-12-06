import java.util.*;

public class Menu {

	private final String MENU_OPTIONS = "[1: Add Item] [2: View Items] [3: Edit Items] [4: Exit]";
	private final String EDIT_OPTIONS = "[1: Remove Item] [2: Modify Price] [3: Back To Menu]";

	Scanner scanner;

	private InventoryManager inventoryManager = null;

	private boolean running;
	private boolean editing;
	private int userSelection;

	public Menu() {
		running = true;
		editing = false;
		scanner =  new Scanner(System.in);

		inventoryManager = new InventoryManager();
	}

	public void startMenu() {
		while(running) {
			getUserInput();
			handleMenuInput();
		}
	}

	private void editMenu() {
		editing = true;
		while(editing) {
			getUserInput();
			handleEditInput();
		}
	}

	private void getUserInput() {
		boolean valid = false;
		userSelection = 0;

		while(!valid) {
			generateMenu();
			try {
				if(scanner.hasNextInt()){
					userSelection = scanner.nextInt();
				}
				else {
					throw new Exception("ERROR: Please enter a number");
				}

				if(editing){
					if(userSelection < 1 || userSelection > 3) {
						throw new Exception("ERROR: Please enter a valid number");
					}
				}
				else {
					if(userSelection < 1 || userSelection > 4) {
						throw new Exception("ERROR: Please enter a valid number");
					}
				}

				valid = true;
			} 
			catch(Exception error) {
				System.out.println(error.getMessage());
				resetScanner();
			}
		}
	}

	private void generateMenu() {
		if(editing){
			System.out.println(EDIT_OPTIONS);
		}
		else{
			System.out.println(MENU_OPTIONS);
		}
	}

	private void handleMenuInput() {
		switch(userSelection) {
			case 1:
				addItem();
				break;
			case 2:
				viewItems();
				break;
			case 3:
				editMenu();
				break;
			case 4:
				terminate();
				break;
			default:

		}
	}

	private void handleEditInput() {
		switch(userSelection) {
			case 1:
				removeItem();
				break;
			case 2:
				modifyPrice();
				break;
			case 3:
				returnToMenu();
				break;
			default:

		}
	}

	private void presentationHeader() {
		line(40);
		System.out.println("\t     SHOPPING LIST");
		line(40);
		System.out.printf("%8s\t%11s%s\n", "ITEM", "PRICE", "   QUANTITY");
		line(40);
	}

	private void line(int num) {
		for(int i = 0; i < num; i++) {
			System.out.printf("-");
		}
		System.out.printf("\n");
	}

	private void subtotal() {
		double subtotal = inventoryManager.calculateSubtotal();
		int quantity = inventoryManager.calculateQuantity();
		System.out.printf("\t%s%.2f%5s%d%s\n", "   SUBTOTAL   $", subtotal, " ", quantity, " items total");
	}

	private void removeItem() {
		inventoryManager.removeItem(scanner);
	}

	private void modifyPrice() {
		inventoryManager.modifyPrice(scanner);
	}

	private void addItem() {
		inventoryManager.addItem(scanner);
	}

	private void viewItems() {
		presentationHeader();
		printItems();
		line(40);
		subtotal();
		line(40);
	}

	private void printItems() {
		String productName;
		double productPrice;
		int productQuantity;
		int length = inventoryManager.getLength();
		for(int i = 0; i < length; i++) {
			productName = inventoryManager.getName(i);
			productPrice = inventoryManager.getPrice(i);
			productQuantity = inventoryManager.getQuantity(i);
			System.out.printf("%2d. %-17s $%.2f%3s%3d\n", i+1, productName, productPrice, " ", productQuantity);
		}
	}

	private void returnToMenu() {
		editing = false;
	}

	private void resetScanner() {
		scanner.nextLine();
	}

	private void terminate() {
		System.out.println("Terminating Program");
		running = false;
	}

}