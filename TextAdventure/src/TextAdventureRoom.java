
public class TextAdventureRoom {

	// Attributes
	private String message;
	private TextAdventureGameLogic gameLogic;
	private String roomType;

	// Constructor
	public TextAdventureRoom(TextAdventureGameLogic logic) { 
		gameLogic = logic;
	}

	// Methods
	public void getMessage() {
		if (gameLogic.inRoom().equals("Enemy Room")) {
			roomType = "a room with an enemy";
			message = "You have entered a room with an enemy. Do you wish to fight?";
		} else if (gameLogic.inRoom().equals("Chest Room")) {
			roomType = "a room with a chest";
			message = "You have entered a room with a chest. Do you wish to open it?";
		} else if (gameLogic.inRoom().equals("Trade Room")) {
			roomType = "a room with a merchant";
			message = "You have entered a room with a merchant. Do you wish to trade with them?";
		} else if (gameLogic.inRoom().equals("Nothing Room")) {
			roomType = "an empty room";
			message = "You have entered a room with nothing in it. What do you wish to do?";
		} else if (gameLogic.inRoom().equals("Level Up Room")) {
			roomType = "a room where you can level up";
			message = "You have entered a room where you can level up. Do you wish to level up?";
		}
	}

	public String getRoomType() {
		return roomType;
	}
}
