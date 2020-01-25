
public class Alien extends Sprite {

	private final int OFF_BOARD = 850;
	
	public Alien(int x, int y) {
		super(x, y);
		initAlien();
	}
	
	private void initAlien() {
		loadImage("src/assets/ships_asteroids.png");
		getImageDimensions();
	}
	
	/**
	 * This allows the asteroid to move on the screen.
	 * If the x position is larger than the width of the
	 * board, the asteroid is sent back to the other side
	 * of the frame.
	 */
	public void move() {
		if (x < 0) {
			x = OFF_BOARD;
		}
		x -= 1;
	}
	
}
