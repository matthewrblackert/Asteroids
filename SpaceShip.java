import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.List;
import java.util.*;
/**
 * @apiNote This is a spin-off of space invaders. The program was made for my Object Oriented Programming class.
 * @author Matthew Blackert (matthewrblackert@gmail.com)
 */
public class SpaceShip extends Sprite {
	
	// Instance Variables
	private int deltaX, deltaY;
	private List<Missile> missiles;
	
	/**
	 * This is a one argument constructor that calls
	 * a method to load the player.
	 */
	public SpaceShip(int x, int y) {
		super(x, y);
		loadPlayer();
	}
	
	/**
	 * This method creates an image icon that will 
	 * represent the player. Then sets the width
	 * and height.
	 */
	private void loadPlayer() {
		missiles = new ArrayList<>();
		loadImage("src/assets/player_plane.png");
		getImageDimensions();
	}
	
	/**
	 * This is the method that allows the player
	 * to move on the screen.
	 */
	public void move() {
		x += deltaX;
		y += deltaY;
		if (x < 1)
			x = 1;
		if (y < 1) 
			y = 1;
	}
	
	/**
	 * @return A list of missiles that are on the screen.
	 */
	public List<Missile> getMissiles() {
        return missiles;
    }
	
	/**
	 * This method changes the value of delta x & y
	 * based on what key was pressed. This simulates
	 * movement.
	 * @param e This is the event handler passed when
	 * the user presses any key.
	 */
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_SPACE) 
			fire();
		else if (key == KeyEvent.VK_LEFT)
			deltaX = -2;
		else if (key == KeyEvent.VK_RIGHT)
			deltaX = 2;
		else if (key == KeyEvent.VK_UP) 
			deltaY = -2;
		else if (key == KeyEvent.VK_DOWN)
			deltaY = 2;
	}
	
	/**
	 * Method that creates a missile that is fired onto
	 * the screen.
	 */
	public void fire() {
		missiles.add(new Missile(x + width, y + height / 2));
	}
	
	/**
	 * This method changes the value of delta x & y
	 * based on what key was released. This resets 
	 * the value.
	 * @param e This is the event handler passed when
	 * the user releases any key.
	 */
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT) 
			deltaX = 0;
		else if (key == KeyEvent.VK_RIGHT)
			deltaX = 0;
		else if (key == KeyEvent.VK_UP) 
			deltaY = 0;
		else if (key == KeyEvent.VK_DOWN)
			deltaY = 0;
		
	}
	
}
