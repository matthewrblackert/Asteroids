import java.awt.*;
import javax.swing.*;
/**
 * @apiNote This is the main driver of the program.
 * @author Matthew Blackert
 */
public class SpriteMovement extends JFrame {
	
	// Serialization
	private static final long serialVersionUID = -1905941700044287568L;

	/**
	 * This is a constructor that calls the initUI method.
	 */
	public SpriteMovement() {
		initUI();
	}
	
	/**
	 * This method creates a board, sets the title and
	 * size, sets the location and resizability, and then
	 * close operation.
	 */
	private void initUI() {
		add(new Board());
		setTitle("Asteroids | Matthew Blackert");
		setSize(800, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * This is the main method that will invoke the
	 * game later instead of at start.
	 * @param args This is the command line arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SpriteMovement sm = new SpriteMovement();
			sm.setVisible(true);
		});
	}
	
}
