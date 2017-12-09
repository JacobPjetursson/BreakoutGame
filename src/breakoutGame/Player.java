package breakoutGame;

import java.awt.*;
import java.util.*;

public class Player extends GameObject {
	private Handler handler;
	private int width = 125;
	private int height = 20;
	private static double ballSpeedonPlayer=3;
	private Random r = new Random();
	private static Boolean weapons = false, moreBoardLength = false, lessBoardLength = false;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		x += velX;
		y += velY;
		if (!moreBoardLength && !lessBoardLength) {
			x = Game.clamp(x, 0, Game.WIDTH - 132);
		}
		else if (moreBoardLength) {
			x = Game.clamp(x, 30, Game.WIDTH - 162);
		}
		else if (lessBoardLength) {
			x = Game.clamp(x, -30, Game.WIDTH - 102);
		}


		collision();
	}

	public void render(Graphics g) {
		if (KeyInput.getParticleCount() == 0) {
			weapons = false;
		}
		if (weapons) {
			if (!moreBoardLength && !lessBoardLength) {
				g.setColor(Color.gray);
				g.fillRect((int) x + 5, (int) y - 25, width - 110, height + 8);
				g.fillOval((int) x + 5, (int) y - 32, width - 110, height - 12);
				g.setColor(Color.yellow);
				g.fillOval((int) x + 5, (int) y - 30, width - 110, height - 12);
				
				g.setColor(Color.gray);
				g.fillRect((int) x + 105, (int) y - 25, width - 110, height + 8);
				g.fillOval((int) x + 105, (int) y - 32, width - 110, height - 12);
				g.setColor(Color.yellow);
				g.fillOval((int) x + 105, (int) y - 30, width - 110, height - 12);		
			}
			else if (moreBoardLength) {
				g.setColor(Color.gray);
				g.fillRect((int) x + 5-30, (int) y - 25, width - 110, height + 8);
				g.fillOval((int) x + 5-30, (int) y - 32, width - 110, height - 12);
				g.setColor(Color.yellow);
				g.fillOval((int) x + 5-30, (int) y - 30, width - 110, height - 12);
				
				g.setColor(Color.gray);
				g.fillRect((int) x + 105+30, (int) y - 25, width - 110, height + 8);
				g.fillOval((int) x + 105+30, (int) y - 32, width - 110, height - 12);
				g.setColor(Color.yellow);
				g.fillOval((int) x + 105+30, (int) y - 30, width - 110, height - 12);
			}
			else if (lessBoardLength) {
				g.setColor(Color.gray);
				g.fillRect((int) x + 5+30, (int) y - 25, width - 110, height + 8);
				g.fillOval((int) x + 5+30, (int) y - 32, width - 110, height - 12);
				g.setColor(Color.yellow);
				g.fillOval((int) x + 5+30, (int) y - 30, width - 110, height - 12);
				
				g.setColor(Color.gray);
				g.fillRect((int) x + 105-30, (int) y - 25, width - 110, height + 8);
				g.fillOval((int) x + 105-30, (int) y - 32, width - 110, height - 12);
				g.setColor(Color.yellow);
				g.fillOval((int) x + 105-30, (int) y - 30, width - 110, height - 12);
			}
		}
		
		if (!moreBoardLength && !lessBoardLength) {
			g.setColor(Color.blue);
			g.fillRoundRect((int) x, (int) y, width, height, height, height);
			Color c = new Color(0, 150, 0);
			g.setColor(c);
			g.fillRect((int) x + 36, (int) y, width - 71, height);
		}
		else if (moreBoardLength) {
			g.setColor(Color.blue);
			g.fillRoundRect((int) x-30, (int) y, width+60, height, height, height);
			Color c = new Color(0, 150, 0);
			g.setColor(c);
			g.fillRect((int) x + 21, (int) y, width - 41, height);
		}
		
		else if (lessBoardLength) {
			g.setColor(Color.blue);
			g.fillRoundRect((int) x+30, (int) y, width-60, height, height, height);
			Color c = new Color(0, 150, 0);
			g.setColor(c);
			g.fillRect((int) x + 52, (int) y, width - 101, height);
		}
		/*
		 Graphics2D g2d = (Graphics2D) g; g.setColor(Color.RED);
		 g2d.draw(getBoundsTopLeft());
		 g2d.draw(getBoundsTopRight());
		 g2d.draw(getBoundsTopMiddle());
		 g2d.draw(getBoundsTopMostLeft());
		 g2d.draw(getBoundsTopMostRight());
		 g2d.draw(getBoundsLeft());
		 g2d.draw(getBoundsRight());
		 */
	}

	public Rectangle getBounds() {
		if (!moreBoardLength && !lessBoardLength) {
			return new Rectangle((int) x, (int) y, width, height);	
		}
		else if (moreBoardLength) {
			return new Rectangle((int) x-30, (int) y, width+60, height);	
		}
		else {
			return new Rectangle((int) x+30, (int) y, width-60, height);	
		}
	}

	// TOPSIDE
	
	public Rectangle getBoundsTopLeft() {
		if (!moreBoardLength && !lessBoardLength) {
			return new Rectangle((int) x + 7, (int) y, width - 100, height - 18);	
		}
		else if (moreBoardLength) {
			return new Rectangle((int) x + 7-30, (int) y, width - 100+15, height - 18);	
		}
		else {
			return new Rectangle((int) x + 7+30, (int) y, width - 100-15, height - 18);	
		}
	}

	public Rectangle getBoundsTopRight() {
		if (!moreBoardLength && !lessBoardLength) {
			return new Rectangle((int) x + 93, (int) y, width - 100, height - 18);
		}
		else if (moreBoardLength) {
			return new Rectangle((int) x + 93+15, (int) y, width - 100+15, height - 18);
		}
		else {
			return new Rectangle((int) x + 93-15, (int) y, width - 100-15, height - 18);
		}
	}

	public Rectangle getBoundsTopMiddle() {
		if (!moreBoardLength && !lessBoardLength) {
			return new Rectangle((int) x + 34, (int) y, width - 68, height - 18);
		}
		else if (moreBoardLength) {
			return new Rectangle((int) x + 34-15, (int) y, width - 68+30, height - 18);
		}
		else {
			return new Rectangle((int) x + 34+15, (int) y, width - 68-30, height - 18);	
		}
	}

	// CORNERS
	public Rectangle getBoundsTopMostLeft() {
		if (!moreBoardLength && !lessBoardLength) {
			return new Rectangle((int) x - 2, (int) y, width - 118, height - 15);
		}
		else if (moreBoardLength) {
			return new Rectangle((int) x - 2-30, (int) y, width - 118, height - 15);
		}
		else {
			return new Rectangle((int) x - 2+30, (int) y, width - 118, height - 15);		
		}
	}

	public Rectangle getBoundsTopMostRight() {
		if (!moreBoardLength && !lessBoardLength) {
			return new Rectangle((int) x + 120, (int) y, width - 118, height - 15);
		}
		else if (moreBoardLength) {
			return new Rectangle((int) x + 120+30, (int) y, width - 118, height - 15);
		}
		else {
			return new Rectangle((int) x + 120-30, (int) y, width - 118, height - 15);
		}
	}

	// SIDES
	public Rectangle getBoundsLeft() {
		if (!moreBoardLength && !lessBoardLength) {
			return new Rectangle((int) x, (int) y + 5, width - 123, height);
		}
		else if (moreBoardLength) {
			return new Rectangle((int) x-30, (int) y + 5, width - 123, height);
		}
		else {
			return new Rectangle((int) x+30, (int) y + 5, width - 123, height);
		}
		
	}

	public Rectangle getBoundsRight() {
		if (!moreBoardLength && !lessBoardLength) {
			return new Rectangle((int) x + 123, (int) y + 5, width - 123, height);
		}
		else if (moreBoardLength) {
			return new Rectangle((int) x + 123+30, (int) y + 5, width - 123, height);
		}
		else {
			return new Rectangle((int) x + 123-30, (int) y + 5, width - 123, height);
		}
	}

	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Ball) {
				// Top sides collision
				if (getBoundsTopLeft().intersects(tempObject.getBounds())
						|| ((getBoundsTopLeft().intersects(tempObject.getBounds())
								&& getBoundsTopMostLeft().intersects(tempObject.getBounds())))) {
					AudioPlayer.playSound("audioFiles/PlayerHit.wav");
					if (r.nextBoolean() == true) {
						
						tempObject.setVelX(-ballSpeedonPlayer);
					} else {
						
						tempObject.setVelX(-ballSpeedonPlayer*4/3);
					}
					
					tempObject.setVelY(-ballSpeedonPlayer*5/3);
				} else if (getBoundsTopRight().intersects(tempObject.getBounds())
						|| (getBoundsTopRight().intersects(tempObject.getBounds())
								&& getBoundsTopMostRight().intersects(tempObject.getBounds()))) {
					AudioPlayer.playSound("audioFiles/PlayerHit.wav");
					if (r.nextBoolean() == true) {
						
						tempObject.setVelX(ballSpeedonPlayer);
					} else {
						
						tempObject.setVelX(ballSpeedonPlayer*4/3);
					}
					tempObject.setVelY(-ballSpeedonPlayer*5/3);
					
				} else if (getBoundsTopMiddle().intersects(tempObject.getBounds())
						|| (getBoundsTopLeft().intersects(tempObject.getBounds())
								&& getBoundsTopMiddle().intersects(tempObject.getBounds()))
						|| (getBoundsTopRight().intersects(tempObject.getBounds())
								&& getBoundsTopMiddle().intersects(tempObject.getBounds()))) {
					AudioPlayer.playSound("audioFiles/PlayerHit.wav");
					if (tempObject.getVelX() > 0) {
						
						tempObject.setVelX(ballSpeedonPlayer/3);
					} else if (tempObject.getVelX() < 0) {
						
						tempObject.setVelX(-ballSpeedonPlayer/3);
					}
					tempObject.setVelY(-2*ballSpeedonPlayer);
				}
				// Corner collision
				else if (getBoundsTopMostLeft().intersects(tempObject.getBounds())
						|| (getBoundsLeft().intersects(tempObject.getBounds())
								&& getBoundsTopMostLeft().intersects(tempObject.getBounds()))) {
					AudioPlayer.playSound("audioFiles/PlayerHit.wav");
					tempObject.setVelX(-2*ballSpeedonPlayer);
					tempObject.setVelY(-ballSpeedonPlayer*2/3);
				} else if (getBoundsTopMostRight().intersects(tempObject.getBounds())
						|| (getBoundsTopMostRight().intersects(tempObject.getBounds())
								&& getBoundsRight().intersects(tempObject.getBounds()))) {
					
					AudioPlayer.playSound("audioFiles/PlayerHit.wav");
					tempObject.setVelX(2*ballSpeedonPlayer);
					tempObject.setVelY(-ballSpeedonPlayer*2/3);
				}
				// Side collision
				else if (getBoundsLeft().intersects(tempObject.getBounds())) {
					AudioPlayer.playSound("audioFiles/PlayerHit.wav");
					if (tempObject.getVelX() < 0) {
						tempObject.velX += 2;
					} else {
						tempObject.velX *= -1;
					}
				} else if (getBoundsRight().intersects(tempObject.getBounds())) {
					AudioPlayer.playSound("audioFiles/PlayerHit.wav");
					if (tempObject.getVelX() > 0) {
						tempObject.velX += 2;
					} else {
						tempObject.velX *= -1;
					}
				}

			}
		}
	}

	public static void setWeapons(Boolean newWeapons) {
		weapons = newWeapons;
	}

	public static Boolean getWeapons() {
		return weapons;
	}

	public static void setmoreBoardLength(Boolean newmoreBoardLength) {
		moreBoardLength = newmoreBoardLength;
	}

	public static Boolean getmoreBoardLength() {
		return moreBoardLength;
	}

	public static void setlessBoardLength(Boolean newlessBoardLength) {
		lessBoardLength = newlessBoardLength;
	}

	public static Boolean getlessBoardLength() {
		return lessBoardLength;
	}
	public static double getballSpeedonPlayer() {
		return ballSpeedonPlayer;
	}
	public static void setballSpeedonPlayer(double newballSpeedonPlayer) {
		ballSpeedonPlayer=newballSpeedonPlayer;
	}
}
