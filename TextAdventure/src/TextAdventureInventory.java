import java.util.ArrayList;
import java.util.Random;

public class TextAdventureInventory {
	
	//Attributes
		private ArrayList<String> items;
		private String[] possibleItems = {"Sword", "Wand", "Knife", "Bow", "Arrows", "Shield", "Health Potion", "Cloak", "Poison", "Gold"};
		private String[] possibleChestItems = {"Arrows", "Knife", "Health Potion", "Gold"};
		private String[] possibleTradeItems = {"Sword", "Wand", "Arrows", "Bow", "Shield", "Cloak", "Poison"};
		private Random rand;
	//Constructor
		public TextAdventureInventory() {
			items = new ArrayList<String>();
			rand = new Random();
			items = getItems();
		}
	//Methods
		public ArrayList<String> getItems() {
			return items;
		}
		public void getInventory() {
			System.out.println("Here is your inventory:");
			for(String item : items) {
				System.out.println(item);
			}
		}
		public void setInventory() {
			String newItems = "";
			for(int i = 0; i < 5; i++) {
				newItems = possibleItems[rand.nextInt(9)];
				items.add(newItems);
			}
		}
		public void addToInventory(int num) {
			String newItem = "";
			if(num == 1) {
				for(int i = 0; i < 2; i++) { 
					newItem = possibleChestItems[rand.nextInt(3)];
					this.items.add(newItem);
				}
			}
			else if(num == 2) {
				for(int i = 0; i <= 1; i++) {
					newItem = possibleTradeItems[rand.nextInt(6)];
					this.items.add(newItem);
				}
			}
			getInventory();
		}
		public void removeFromInventory(String removeItem) {
			this.items.remove(removeItem);
		}
		public boolean doIHaveAPotion() {
			boolean yes;
			if(items.contains("Health Potion")) {
				yes = true;
			}
			else {
				yes = false;
			}
			return yes;
		}
}