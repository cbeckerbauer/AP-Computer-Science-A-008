import java.util.Random;
import java.util.Scanner;

public class TextAdventureGameLogic {

	// Attributes
	private Scanner scan;
	private TextAdventureRoom room;
	private String[] possibleActions = { "Go to next room", "Attack", "Open Chest", "Drink Potion", "Trade", "Level Up", "Get Inventory"};
	private String roomIIn;
	private TextAdventureCharacter character;
	private String finalRoomMessage;
	private Random rand;
	private int turn;

	// Constructor
	public TextAdventureGameLogic() {
		rand = new Random();
		finalRoomMessage = "";
		character = new TextAdventureCharacter(this);
		scan = new Scanner(System.in);
		roomIIn = "";
		introduction();
	}

	// Methods
	public void introduction() {
		System.out.println(
				"Welcome to a stupid text adventure that I made. You will enter rooms and the goal is to survive all rooms. Are you ready?");
		String answer = scan.nextLine();
		answer = answer.toLowerCase();
		if (answer.equals("yes")) {
			System.out.println("Great! Good luck! (You probably will survive)");
		} else {
			System.out.println("Well tough stuff. Just going to throw you in now!");
		}
		setRoomLayout();
	}

	public void end() {
		System.out.println("Thanks for playing! Goodbye!");
		System.exit(0);
	}

	public void setRoomLayout() {
		if(turn < 7) {
		int random = 0;
		for (int i = 0; i < 5; i++) {
			TextAdventureRoom currentRoom = new TextAdventureRoom(this);
			random = rand.nextInt(4);
			if (random == 0) {
				roomIIn = "Enemy Room";
			} else if (random == 1) {
				roomIIn = "Chest Room";
			} else if (random == 2) {
				roomIIn = "Trade Room";
			} else if (random == 3) {
				roomIIn = "Nothing Room";
			} else {
				roomIIn = "Level Up Room";
			}
			currentRoom.getMessage();
			answerAndQuestion(currentRoom);
		}
		}
		else {
			end();
		}
	}

	public void answerAndQuestion(TextAdventureRoom currentRoom) {
		for(int i = 0; i < 7; i++) {
			System.out.println("You have entered " + currentRoom.getRoomType() + ". What action would you like to do?");
			for (String action : possibleActions) {
				System.out.println(action);
			}	
			String answer = scan.nextLine();
			answer = answer.toLowerCase();
			character.setAction(answer);
			doAction();
			turn++;
			setRoomLayout();
		}
		doAction();
		end();
	}

	public String inRoom() {
		return roomIIn;
	}

	public void doAction() {
		if (character.getAction().equals("Next")) {

		} else if (character.getAction().equals("Attack")) {
			TextAdventureBadGuy badGuy = new TextAdventureBadGuy(this);
			for(int i = 0; i <= 3; i++) {
				boolean hurt = true;
				character.setAttack(hurt, badGuy);
			}
			finalRoomMessage = "You have defeated your enemy. Please move on.";
			getFinalRoomResponse();
		} else if (character.getAction().equals("Open")) {
			this.character.setOpen();
			finalRoomMessage = "You have opened a chest and got some items. Please move on.";
			getFinalRoomResponse();
		} else if (character.getAction().equals("Drink")) {
			character.setDrink();
			if(character.getNoPotion() == true) {
				finalRoomMessage = "You have drank a health potion and gained 15 HP. Please move on.";
				getFinalRoomResponse();
			}
			else {
				
			}
		} else if (character.getAction().equals("Trade")) {
			character.setTrade();
			finalRoomMessage = "You have traded with a merchant and got some items. Please move on.";
			getFinalRoomResponse();
		} else if (character.getAction().equals("Level")) {
			character.setLevel();
			finalRoomMessage = "You have leveled up. Please move on.";
			getFinalRoomResponse();
		}
		else if(character.getAction().equals("Inventory")) {
			character.setYourInventory();
			finalRoomMessage = "You have viewed your inventory for a long time. Please move on";
			getFinalRoomResponse();
		}
	}

	public void getFinalRoomResponse() {
		System.out.println(finalRoomMessage);
	}
}
