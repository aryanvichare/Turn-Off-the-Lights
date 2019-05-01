import processing.core.*;

public class RunMe extends PApplet {
	int c;
	TurnOffTheLights game;
	Display display;

	public void setup() {
		size(420, 420); // set the size of the screen.

		// Create a game object
		game = new TurnOffTheLights(3, 3);

		// Create the display
		// parameters: (10,10) is upper left of display
		// (300, 300) is the width and height
		display = new Display(this, 10, 10, 400, 400);

		display.setColor(1, 0xFF3399FF); // SET COLORS FOR PLAYER 1 & 2
		display.setColor(2, 0xFF888888);

		// You can use images instead if you'd like.
		// d.setImage(1, "c:/data/ball.jpg");
		// d.setImage(2, "c:/data/cone.jpg");

		display.initializeWithGame(game);
		c = 0;
	}

	public void draw() {
		background(200);
		display.drawGrid(game.getGrid()); // display the game
	}

	public void mouseClicked() {
		Location loc = display.gridLocationAt(mouseX, mouseY);
		game.move(loc.getRow(), loc.getCol());
		if (game.isGameOver()) {
			System.out.println("You win!");
		}
	}

	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] { "RunMe" });
	}
}