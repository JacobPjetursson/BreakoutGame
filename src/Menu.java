import java.awt.*;
import java.awt.event.*;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private HUD hud;
	private Levels level;
	
	private Font fnt = new Font("serif", 1, 50);
	private Font fnt2 = new Font("serif", 1, 30);
	private Font fnt3 = new Font("serif", 1, 20);
	private Font fnt4 = new Font("serif", 1, 13);
	
	private Color darkgreen = new Color(0,75,0);
	private Color darkred = new Color(75,0,0);
	private Color darkyellow = new Color(255,150,0);
	
	public Menu(Game game, Handler handler, HUD hud, Levels level) {
		this.game = game;
		this.handler = handler;
		this.hud=hud;
		this.level=level;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == Game.STATE.Menu) {

			// play button
			if (mouseOver(mx, my, 485, 210, 200, 64)) {
				AudioPlayer.playSound("audioFiles/click.wav");
				AudioPlayer.lowVolumeloopSound("audioFiles/floodcut.wav");
				game.gameState = Game.STATE.Game;
				handler.addObject(new Player((Game.WIDTH-115)/2, 800, ID.Player, handler));
				hud.setScore(0);
				level.setScoreKeep(0);
				
				for (int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					if (tempObject.getId() == ID.Player) {
						handler.addObject(new Ball((int)tempObject.getX()+60,(int)tempObject.getY()-12, ID.Ball, handler));
					}
				}
			}
			
			// Settings button
			if (mouseOver(mx, my, 485, 410, 200, 64)) {
				AudioPlayer.playSound("audioFiles/click.wav");
				game.gameState = Game.STATE.Settings;
				
				
			}
			// Store Button
			if (mouseOver(mx,my,800, 410,200,64)) {
				AudioPlayer.playSound("audioFiles/click.wav");
				game.gameState = Game.STATE.Store;
			}
			// Help Button
			if (mouseOver(mx,my,170,410,200,64)) {
				AudioPlayer.playSound("audioFiles/click.wav");
				game.gameState = Game.STATE.Help;
			}
			// Quit button
			if (mouseOver(mx, my, 485, 610, 200, 64)) {
				System.exit(1);
				
			}
		}
		// Buttons for Settings
		if (game.gameState == Game.STATE.Settings) {
			if (mouseOver(mx, my, 485, 750, 200, 64)) {
				AudioPlayer.playSound("audioFiles/click.wav");
				game.gameState = Game.STATE.Menu;
				return;
			}
		}
		
		// back button for End-screen
				if (game.gameState == Game.STATE.End) {
					if (mouseOver(mx, my, 485, 450, 200, 64)) {
						AudioPlayer.playSound("audioFiles/click.wav");
						game.gameState = Game.STATE.Menu;
						hud.setScore(0);
						level.setScoreKeep(0);
					}
				}
		// back button for help-screen
				if (game.gameState == Game.STATE.Help) {
					if(mouseOver(mx,my,485, 750, 200, 64)) {
						AudioPlayer.playSound("audioFiles/click.wav");
						game.gameState = Game.STATE.Menu;
						return;
					}
				}
		// Buttons for store-screen
				if (game.gameState == Game.STATE.Store) {
					if(mouseOver(mx,my,485, 750, 200, 64)) {
						AudioPlayer.playSound("audioFiles/click.wav");
						game.gameState = Game.STATE.Menu;
						return;
					}
				}
		// Buttons for nextlevel-screen
				if (game.gameState == Game.STATE.Nextlevel) {
					if (mouseOver(mx,my,485, 250, 200, 64)) {
						AudioPlayer.lowVolumeloopSound("audioFiles/floodcut.wav");
						game.gameState = Game.STATE.Game;
						handler.addObject(new Player((Game.WIDTH-115)/2, 800, ID.Player, handler));
						// Ball spawn
						for (int i = 0; i < handler.object.size(); i++) {
							GameObject tempObject = handler.object.get(i);
							if (tempObject.getId() == ID.Player) {
								handler.addObject(new Ball((int)tempObject.getX()+60,(int)tempObject.getY()-12, ID.Ball, handler));
								
							}
						}
						hud.setScore(0);
						level.setScoreKeep(0);
					}
					if (mouseOver(mx,my,485, 600, 200, 64)) {
						AudioPlayer.playSound("audioFiles/click.wav");
						game.gameState = Game.STATE.Menu;
					}
				}
	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;

	}

	public void tick() {

	}

	public void render(Graphics g) {
		if (game.gameState == Game.STATE.Menu) {
			
			// Title
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Breakout!", 485, 70);
			// Play Button
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawRect(485, 210, 200, 64);
			g.drawString("Play", 510, 250);
			// Settings Button
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawRect(485, 410, 200, 64);
			g.drawString("Settings", 510, 450);
			// Store Button
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawRect(800, 410,200, 64);
			g.drawString("Store", 825, 450);
			// Help Button
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawRect(170, 410,200, 64);
			g.drawString("Help", 195, 450);
			// Quit Button
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawRect(485, 610, 200, 64);
			g.drawString("Quit", 510, 650);

		} else if (game.gameState == Game.STATE.Settings) {
			// Settings Title
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Settings", 485, 70);
			// Back Button
			g.setFont(fnt2);
			g.drawRect(485, 750, 200, 64);
			g.drawString("Back", 510, 792);

		} else if (game.gameState == Game.STATE.End) {
			// EndScreen Title	
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 485, 70);
			// EndScreen Message
			g.setFont(fnt2);
			g.drawString("You lost with a score of: "+ hud.getScore(), 425,300);
			// EndScreen Button
			g.setFont(fnt2);
			g.drawRect(485, 450, 200, 64);
			g.drawString("Try Again", 510, 490);
			
		} else if (game.gameState == Game.STATE.Help) {
			// Help Title
				g.setFont(fnt);
				g.setColor(Color.WHITE);
				g.drawString("Helping Tips", 450, 70);
			// Help Messages
				g.setFont(fnt3);
				g.setColor(Color.white);
				g.drawString("- Use the A and D keys to move the board", 140, 200);
				g.drawString("- Hit the space bar to release the ball", 140, 275);
				g.drawString("- Hit the P key to pause the game", 140, 350);
				g.drawString("- These are the different types of blocks:", 140, 500);
			// Blocks:
				Color c = Color.green;
				Color darkc = c.darker();
				Color darkerc = darkc.darker();
				Color darkererc = darkerc.darker();
				Color darkestc = darkererc.darker();
				g.setColor(c);
				g.fillRect(670, 478, 80,25);
				g.setFont(fnt4);
				g.setColor(Color.white);
				g.drawString("Ordinary", 680, 468);
				
				g.setColor(Color.white);
				g.fillRect(550, 478, 80,25);
				g.setFont(fnt4);
				g.drawString("Immune", 560, 468);
				
				g.setColor(darkerc);
				g.fillRect(790, 478, 80,25);
				g.setColor(Color.white);
				g.setFont(fnt4);
				g.drawString("Takes two hits", 790, 468);
				
				g.setColor(darkestc);
				g.fillRect(910, 478, 80,25);
				g.setColor(Color.white);
				g.setFont(fnt4);
				g.drawString("Takes three hits", 910, 468);
				
				g.setFont(fnt3);
				g.drawString("- These are the different types of powerups:", 140, 600);
			// Powerups
				g.setFont(fnt4);
				g.setColor(darkgreen);
				g.fillRect(550, 585, 20,20);
				g.setColor(Color.white);
				g.drawString("B", 556, 599);
				g.drawString("Extra Ball", 530,577);
				
				g.setColor(darkgreen);
				g.fillRect(650, 585, 20,20);
				g.setColor(Color.white);
				g.drawString("W", 654, 599);
				g.drawString("Weapons", 635,577);
				
				g.setColor(darkgreen);
				g.fillRect(750, 585, 20,20);
				g.setColor(Color.white);
				g.drawString("S", 756, 599);
				g.drawString("Slower Balls", 728,577);
				
				g.setColor(darkgreen);
				g.fillRect(875, 585, 20,20);
				g.setColor(Color.white);
				g.drawString("M", 880, 599);
				g.drawString("More Boardlength", 838,577);
				
				g.setColor(darkgreen);
				g.fillRect(1004, 585, 20,20);
				g.setColor(Color.white);
				g.drawString("P", 1011, 599);
				g.drawString("Penetrate Blocks", 967,577);
				
				g.setColor(darkred);	
				g.fillRect(750, 645, 20,20);
				g.setColor(Color.white);
				g.drawString("F", 756, 659);
				g.drawString("Faster Balls", 728,637);
				
				g.setColor(darkred);	
				g.fillRect(875, 645, 20,20);
				g.setColor(Color.white);
				g.drawString("L", 881, 659);
				g.drawString("Less Boardlength", 838,637);
				
				g.setColor(darkyellow);	
				g.fillRect(1004, 645, 20,20);
				g.setColor(Color.white);
				g.drawString("R", 1009, 659);
				g.drawString("Random", 990,637);
				
			// HelpScreen button
				g.setFont(fnt2);
				g.setColor(Color.white);
				g.drawRect(485, 750, 200, 64);
				g.drawString("Back", 510, 790);
				
		} else if (game.gameState == Game.STATE.Store) {
			// Store Title
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Store", 525, 70);
			//StoreScreen button
			g.setFont(fnt2);
			g.drawRect(485, 750, 200, 64);
			g.drawString("Back", 510, 790);
			
		} else if (game.gameState == Game.STATE.Nextlevel) {
			// Nextlevel title
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Congratulations!", 400, 70);
			// Nextlevel message
			g.setFont(fnt3);
			g.drawString("You beat level "+(hud.getLevel()-1)+" with a score of "+hud.getScore()+". This gives you XX coins", 330,145);
			//Nextlevel buttons
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawRect(485, 250, 200, 64);
			g.drawString("Next level", 510, 290);
			
			g.setFont(fnt2);
			g.drawRect(485, 600, 200, 64);
			g.drawString("Menu", 510, 640);
		}

	}
}