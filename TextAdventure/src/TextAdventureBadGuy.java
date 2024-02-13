import java.util.Random;

public class TextAdventureBadGuy {

	// Attributes
	private boolean hurt;
	private int health;
	private boolean isDead;
	private String deathMessage;
	private int damage;
	private String action;
	private TextAdventureGameLogic gameLogic;
	private Random rand;

	// Constructor
	public TextAdventureBadGuy(TextAdventureGameLogic logic) {
		rand = new Random();
		gameLogic = logic;
		deathMessage = "";
		action = "";
		damage = 0;
		health = 100;
		isDead = false;
	}

	// Methods
	public boolean getHurt(int random, TextAdventureCharacter character) {
		health = health - random;
		hurt = true;
		amIDead();
		if (!isDead) {
			setAttack(hurt, character);
			return hurt;
		}
		else {
			return isDead;
		}
	}

	public boolean amIDead() {
		if (health <= 10) {
			deathMessage = "Bad Guy died.";
			isDead = true;
		}
		return isDead;
	}

	public int getHealth() {
		return health;
	}

	public void setAttack(boolean hurt, TextAdventureCharacter character) {
		this.action = ("Attack");
		character.getHurt(this);
		character.amIDead();
		if (character.amIDead() == false) {
			character.setAttack(hurt, this);
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
}
