import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * @apiNote This is the board class that handles a lot of the drawing functionalities.
 * @author Matthew Blackert
 */
public class Board extends JPanel implements ActionListener {

	// Instance Variables
	private static final long serialVersionUID = -6473120678919312177L;
	private int currLevel = 1;
	private int aliensInLevel = 20;
	private List<Alien> alienP;
	private int[][] aliens = new int[aliensInLevel][aliensInLevel];
	private final int ship_x = 50;
	private final int ship_y = 50;
	private boolean ingame;
	private final int boardHeight = 800;
	private final int boardWidth = 800;
	private Timer timer;
	private SpaceShip spaceShip;
	private final int DELAY = 10;
	
	/**
	 * This is a no argument constructor that 
	 * initializes the board.
	 */
	public Board() {
		initBoard();
	}
	
	/**
	 * This is the method that sets up the board
	 * and then creates the timer.
	 */
	public void initBoard() {
		addKeyListener(new TAdapter());
		setBackground(Color.BLACK);
		setFocusable(true);
		setPreferredSize(new Dimension(boardWidth, boardHeight));
		ingame = true;
		genAliens();
		spaceShip = new SpaceShip(ship_x, ship_y);
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void genAliens() {
		for (int i = 0; i < aliensInLevel; i++) {
			int randX = randX(800, 3000);
			int randY = randY(30, 770);
			aliens[i][0] = randX;
			aliens[i][1] = randY;
		}
		alienP = new ArrayList<>();
        for (int[] p : aliens) {
            alienP.add(new Alien(p[0], p[1]));
        }
	}
	
	public int randX(int min, int max) {
		return new Random().nextInt(max-min+1) + min;
	}
	
	public int randY(int min, int max) {
		return new Random().nextInt(max-min+1) + min;
	}
	
	/**
	 * This is the painting aspect of the program.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (ingame == true)
			doDrawing(g);
		else 
			drawGameOver(g);
		Toolkit.getDefaultToolkit().sync();
	}
	
	/**
	 * This is where the program does most of the drawing.
	 * @param g This is the graphics element from the paintComponent method.
	 */
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
		List<Missile> missiles = spaceShip.getMissiles();
        for (Missile missile : missiles) {
            g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
        }
        for (Alien alien : alienP) {
            if (alien.getVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }
        }
        g.setColor(Color.WHITE);
        g.drawString("Aliens left: " + alienP.size(), 5, 15);
	}
	
	private void drawGameOver(Graphics g) {
        String msg = "Game Over, Thanks for Playing";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (boardWidth - fm.stringWidth(msg)) / 2,
                boardHeight / 2);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ingame();
		updateMissiles();
        updateSpaceShip();
        updateAliens();
        checkCollisions();
        repaint();
	}
	
	/**
	 * This method keeps track of whether you
	 * are ingame or not.
	 */
	public void ingame() {
		if (ingame == false) {
			timer.stop();
		}
	}
	
	/**
	 * This method initializes a list of missiles and then determines whether or not the missile will move
	 * or be removed.
	 */
    private void updateMissiles() {
        List<Missile> missiles = spaceShip.getMissiles();
        for (int i = 0; i < missiles.size(); i++) {
            Missile missile = missiles.get(i);
            if (missile.getVisible()) {
                missile.move();
            } else {
                missiles.remove(i);
            }
        }
    }
    
    private void updateAliens() {
    	if (alienP.isEmpty()) {
            ingame = false;
            return;
        }
        for (int i = 0; i < alienP.size(); i++) {
            Alien a = alienP.get(i);
            if (a.getVisible()) {
                a.move();
            } else {
                alienP.remove(i);
            }
        }
    }

    /**
     * Allows the spaceship to move on the screen.
     */
    private void updateSpaceShip() {
    	if (spaceShip.getVisible() == true) 
    		spaceShip.move();
    }
    
    public void checkCollisions() {
        Rectangle r3 = spaceShip.getBounds();
        for (Alien alien : alienP) {         
            Rectangle r2 = alien.getBounds();
            if (r3.intersects(r2)) {     
                spaceShip.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        }

        List<Missile> ms = spaceShip.getMissiles();
        for (Missile m : ms) {
            Rectangle r1 = m.getBounds();
            for (Alien alien : alienP) {
                Rectangle r2 = alien.getBounds();
                if (r1.intersects(r2)) {   
                    m.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }
    }
	
	/**
	 * @author Matthew Blackert
	 * @apiNote This was done in order to repaint only part of the screen
	 * at a time.
	 */
	private class TAdapter extends KeyAdapter {

	    @Override
	    public void keyReleased(KeyEvent e) {
	        spaceShip.keyReleased(e);
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	        spaceShip.keyPressed(e);
	    }
	    
	}
	
	

}
