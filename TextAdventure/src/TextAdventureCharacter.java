import java.util.Random;

public class TextAdventureCharacter {

	// Attributes
	private boolean hurt;
	private int damage;
	private int health;
	private boolean isDead;
	private String action;
	private String deathMessage;
	private TextAdventureGameLogic gameLogic;
	private TextAdventureInventory inventory;
	private Random rand;
	private boolean havePotion;

	// Constructor
	public TextAdventureCharacter(TextAdventureGameLogic logic) {
		inventory = new TextAdventureInventory();
		rand = new Random();
		isDead = false;
		health = 200;
		deathMessage = "";
		action = "";
		damage = 0;
		gameLogic = logic;
		inventory.setInventory();
	}

	// Methods
	public boolean getHurt(TextAdventureBadGuy badGuy) {
		damage -= rand.nextInt(50) + 40;
		health = health - damage;
		hurt = true;
		amIDead();
		if (!isDead) {
			setAttack(hurt, badGuy);
		}
		return hurt;
	}

	public boolean amIDead() {
		if (health == 0) {
			deathMessage = "You died.";
			isDead = true;
			gameLogic.end();
		}
		return isDead;
	}

	public void setAttack(boolean hurt, TextAdventureBadGuy badGuy) {
		if(hurt == true) {
			int random = rand.nextInt(50) + 40;
			badGuy.getHurt(random, this);
			if(!badGuy.amIDead()) {
				badGuy.setAttack(hurt, this);
			}
		}
		else {
			int random = rand.nextInt(100) + 50;
			badGuy.getHurt(random, this);
			if (badGuy.amIDead() == false) {
				badGuy.setAttack(hurt, this);
		}
		}
	}

	public String getAttack() {
		return this.action;
	}

	public String getDeathMessage() {
		return deathMessage;
	}

	public int getDamage() {
		return damage;
	}

	public String getAction() {
		return action;
	}

	public int getHealth() {
		return health;
	}

	public void setAction(String answer) {
		if (answer.equals("go to next room")) {
			action = "Next";
			getNext();
		} else if (answer.equalsIgnoreCase("attack")) {
			action = "Attack";
			getAttack();
		} else if (answer.equals("open chest")) {
			action = "Open";
			getOpen();
		} else if (answer.equals("drink potion")) {
			action = "Drink";
			getDrink();
		} else if (answer.equals("trade")) {
			action = "Trade";
			getTrade();
		} else if (answer.equals("level up")) {
			action = "Level";
			getLevel();
		}
		else if(answer.equals("get inventory")) {
			action = "Inventory";
			getYourInventory();
		}
	}
	public String getYourInventory() {
		return action;
	}
	public void setYourInventory() {
		inventory.getInventory();
	}
	public String getNext() {
		return action;
	}
	public String getOpen() {
		return action;
	}
	public String getDrink() {
		return action;
	}
	public String getTrade() {
		return action;
	}
	public String getLevel() {
		return action;
	}
	public void setTrade() {
		int num = 2;
		inventory.addToInventory(num);
	}
	public void setOpen() {
		int num = 1;
		this.inventory.addToInventory(num);
	}
	public void setLevel() {
		health += 50;
	}
	public void setDrink() {
		if(inventory.doIHaveAPotion() == true) {
			havePotion = true;
			inventory.removeFromInventory("Health Potion");
			health += 15;
			inventory.getInventory();
			getHealth();
			System.out.println("Your health is " + health + " HP.");
		}
		else {
			havePotion = false;
			System.out.println("You do not have a potion. Please go on");
		}
	}	
	public boolean getNoPotion() {
		return havePotion;
	}
}
