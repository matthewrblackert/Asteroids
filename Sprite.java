import java.awt.*;
import javax.swing.*;
/**
 * @apiNote This is the class that controls the individual sprites.
 * @author matthewblackert
 */
public class Sprite {
	
	// Instance Variables
	protected int x, y, width, height;
	protected boolean visible;
	protected Image image;
	
	/**
	 * This is two argument constructor that 
	 * x and y has been passed through.
	 * @param x This is the x location of the sprite.
	 * @param y This is the y location of the sprite.
	 */
	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		visible = true;
	}
	
	/**
	 * This method allows for the loading of the character
	 * onto the screen.
	 * @param imageName This is the file path to the character asset image.
	 */
	protected void loadImage(String imageName) {
		ImageIcon ic = new ImageIcon(imageName);
		image = ic.getImage();
	}
	
	/**
	 * This method determines what the image dimensions
	 * are based on the get method for the image class.
	 */
	protected void getImageDimensions() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	/**
	 * @return The current x position of the sprite.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return The current y position of the sprite.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @return The width of the sprite.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @return The height of the sprite.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @return The image for the sprite.
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * @return True: visible, False: not visible
	 */
	public boolean getVisible() {
		return visible;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * This method allows the sprite to have 
	 * alterable visibility.
	 * @param visible This is the state in 
	 * which the sprite exists.
	 */
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

}
