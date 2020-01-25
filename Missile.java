/**
 * @apiNote This class allows the missiles to be created.
 * @author matthewblackert
 */
public class Missile extends Sprite {
	
	// Instance variables
	private final int BOARD_WIDTH = 800;
	private int missile_speed = 3;
	
	/**
	 * This is the missile constructor that has
	 * an x and y parameter, both of type int.
	 * @param x This is the current x position of the missile.
	 * @param y This is the current y position of the missile.
	 */
	public Missile(int x, int y) {
		super(x, y);
		initMissile();
	}
	
	/**
	 * This method allows the missile to be initialized
	 * by declaring the asset location and then getting
	 * the dimensions.
	 */
	private void initMissile() {
		loadImage("src/assets/missile_0.png");
		getImageDimensions();
	}
	
	/**
	 * This method allows the missile to move across the
	 * screen and will disappear once the x position is 
	 * larger than the board width.
	 */
	public void move() {
		x += missile_speed;
		if (x > BOARD_WIDTH)
			setVisible(false);
	}
	
}
