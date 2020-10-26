import java.awt.*;
import java.util.*;

public class PowerUp extends GameObject {
	private Handler handler;
	private int width = 20;
	private int height = 20;
	private Random r = new Random();
	private Boolean extraBall = false, weapons = false, slowerBalls = false, moreBoardLength = false,
			penetrateBlocks = false, lessBoardLength = false, fasterBalls = false, randomPowerup = false;
	private static int firstextraBall = 1;
	private static int slowBallLimit = 3, fastBallLimit = 3;

	public PowerUp(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velY = 5;
		int powerupType = r.nextInt(100) + 1;

		if (powerupType <= 11 || firstextraBall == 1) {
			extraBall = true;
			firstextraBall--;
		} else if (powerupType <= 22) {
			weapons = true;
		} else if (powerupType <= 33) {
			slowerBalls = true;
		} else if (powerupType <= 44) {
			moreBoardLength = true;
		} else if (powerupType <= 55) {
			penetrateBlocks = true;
		} else if (powerupType <= 70) {
			fasterBalls = true;
		} else if (powerupType <= 85) {
			randomPowerup = true;
		} else if (powerupType <= 100) {
			lessBoardLength = true;
		}
	}

	public void tick() {
		y += velY;
		collision();
		if (y >= Game.WIDTH) {
			handler.object.remove(this);
		}
	}

	public void render(Graphics g) {
		Color darkgreen = new Color(0, 75, 0);
		Color darkred = new Color(75, 0, 0);
		Color darkyellow = new Color(255, 150, 0);
		Font fnt4 = new Font("serif", 1, 13);

		if (extraBall) {
			g.setFont(fnt4);
			g.setColor(darkgreen);
			g.fillRect((int) x, (int) y, width, height);
			g.setColor(Color.white);
			g.drawString("B", (int) x + 6, (int) y + 14);
		} else if (weapons) {
			g.setColor(darkgreen);
			g.fillRect((int) x, (int) y, width, height);
			g.setColor(Color.white);
			g.drawString("W", (int) x + 4, (int) y + 14);
		} else if (slowerBalls) {
			g.setColor(darkgreen);
			g.fillRect((int) x, (int) y, width, height);
			g.setColor(Color.white);
			g.drawString("S", (int) x + 6, (int) y + 14);
		} else if (moreBoardLength) {
			g.setColor(darkgreen);
			g.fillRect((int) x, (int) y, width, height);
			g.setColor(Color.white);
			g.drawString("M", (int) x + 5, (int) y + 14);
		} else if (penetrateBlocks) {
			g.setColor(darkgreen);
			g.fillRect((int) x, (int) y, width, height);
			g.setColor(Color.white);
			g.drawString("P", (int) x + 7, (int) y + 14);
		} else if (fasterBalls) {
			g.setColor(darkred);
			g.fillRect((int) x, (int) y, width, height);
			g.setColor(Color.white);
			g.drawString("F", (int) x + 6, (int) y + 14);
		} else if (lessBoardLength) {
			g.setColor(darkred);
			g.fillRect((int) x, (int) y, width, height);
			g.setColor(Color.white);
			g.drawString("L", (int) x + 6, (int) y + 14);
		} else if (randomPowerup) {
			g.setColor(darkyellow);
			g.fillRect((int) x, (int) y, width, height);
			g.setColor(Color.white);
			g.drawString("R", (int) x + 5, (int) y + 14);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (extraBall) {
						AudioPlayer.playSound("audioFiles/PositivePowerup.wav");
						handler.addObject(new Ball((int) tempObject.getX() + 60, (int) tempObject.getY() - 12, ID.Ball, handler));
						Game.setBallCount(Game.getBallCount() + 1);
						handler.removeObject(this);
					} else if (weapons) {
						AudioPlayer.playSound("audioFiles/PositivePowerup.wav");
						Player.setWeapons(true);
						KeyInput.setParticleCount(10);
						handler.object.remove(this);
					} else if (slowerBalls) {
						AudioPlayer.playSound("audioFiles/PositivePowerup.wav");
						handler.object.remove(this);
						if (fastBallLimit != 6) {
							fastBallLimit++;
						}
						if (slowBallLimit > 0) {
							slowBallLimit--;
							Player.setballSpeedonPlayer(Player.getballSpeedonPlayer() - 0.3);
							for (int j = 0; j < handler.object.size(); j++) {
								GameObject tempObjectBall = handler.object.get(j);
								if (tempObjectBall.getId() == ID.Ball) {
									// PERCENTAGE
									tempObjectBall.setVelX(tempObjectBall.getVelX() * 0.91);
									tempObjectBall.setVelY(tempObjectBall.getVelY() * 0.91);
								}
							}
						}

					} else if (moreBoardLength) {
						AudioPlayer.playSound("audioFiles/PositivePowerup.wav");
						handler.object.remove(this);
						if (Player.getlessBoardLength()) {
							Player.setlessBoardLength(false);
						} else {
							Player.setmoreBoardLength(true);
						}
					} else if (penetrateBlocks) {
						AudioPlayer.playSound("audioFiles/PositivePowerup.wav");
						handler.object.remove(this);
						BasicEnemy.setPenetration(true);
						BasicEnemy.setpenetrationCount(10);
					} else if (fasterBalls) {
						AudioPlayer.playSound("audioFiles/NegativePowerup.wav");
						handler.object.remove(this);
						if (slowBallLimit != 6) {
							slowBallLimit++;
						}
						if (fastBallLimit > 0) {
							fastBallLimit--;
							Player.setballSpeedonPlayer(Player.getballSpeedonPlayer() + 0.3);
							for (int j = 0; j < handler.object.size(); j++) {
								GameObject tempObjectBall = handler.object.get(j);
								if (tempObjectBall.getId() == ID.Ball) {
									// PERCENTAGE
									tempObjectBall.setVelX(tempObjectBall.getVelX() * 1.09);
									tempObjectBall.setVelY(tempObjectBall.getVelY() * 1.09);
								}
							}
						}
					} else if (lessBoardLength) {
						AudioPlayer.playSound("audioFiles/NegativePowerup.wav");
						handler.object.remove(this);
						if (Player.getmoreBoardLength()) {
							Player.setmoreBoardLength(false);
						} else {
							Player.setlessBoardLength(true);
						}
					} else if (randomPowerup) {
						handler.object.remove(this);
						int powerupType = r.nextInt(100) + 1;

						if (powerupType <= 13) {
							// EXTRA BALL
							AudioPlayer.playSound("audioFiles/PositivePowerup.wav");
							handler.addObject(new Ball((int) tempObject.getX() + 60, (int) tempObject.getY() - 12,
									ID.Ball, handler));
							Game.setBallCount(Game.getBallCount() + 1);
						} else if (powerupType <= 26) {
							// WEAPONS
							AudioPlayer.playSound("audioFiles/PositivePowerup.wav");
							Player.setWeapons(true);
							KeyInput.setParticleCount(10);
						} else if (powerupType <= 39) {
							// SLOWER BALLS
							AudioPlayer.playSound("audioFiles/PositivePowerup.wav");
							handler.object.remove(this);
							if (fastBallLimit != 6) {
								fastBallLimit++;
							}
							if (slowBallLimit > 0) {
								slowBallLimit--;
								Player.setballSpeedonPlayer(Player.getballSpeedonPlayer() - 0.3);
								for (int j = 0; j < handler.object.size(); j++) {
									GameObject tempObjectBall = handler.object.get(j);
									if (tempObjectBall.getId() == ID.Ball) {
										// PERCENTAGE
										tempObjectBall.setVelX(tempObjectBall.getVelX() * 0.91);
										tempObjectBall.setVelY(tempObjectBall.getVelY() * 0.91);
									}
								}
							}
						} else if (powerupType <= 52) {
							// MORE BOARD LENGTH
							AudioPlayer.playSound("audioFiles/PositivePowerup.wav");
							if (Player.getlessBoardLength()) {
								Player.setlessBoardLength(false);
							} else {
								Player.setmoreBoardLength(true);
							}
						} else if (powerupType <= 65) {
							// PENETRATION
							AudioPlayer.playSound("audioFiles/PositivePowerup.wav");
							BasicEnemy.setPenetration(true);
						} else if (powerupType <= 82) {
							// FASTER BALLS
							AudioPlayer.playSound("audioFiles/NegativePowerup.wav");
							handler.object.remove(this);
							if (slowBallLimit != 6) {
								slowBallLimit++;
							}
							if (fastBallLimit > 0) {
								fastBallLimit--;
								Player.setballSpeedonPlayer(Player.getballSpeedonPlayer() + 0.3);
								for (int j = 0; j < handler.object.size(); j++) {
									GameObject tempObjectBall = handler.object.get(j);
									if (tempObjectBall.getId() == ID.Ball) {
										// PERCENTAGE
										tempObjectBall.setVelX(tempObjectBall.getVelX() * 1.09);
										tempObjectBall.setVelY(tempObjectBall.getVelY() * 1.09);
									}
								}
							}
						} else if (powerupType <= 100) {
							// LESS BOARD LENGTH
							AudioPlayer.playSound("audioFiles/NegativePowerup.wav");
							if (Player.getmoreBoardLength()) {
								Player.setmoreBoardLength(false);
							} else {
								Player.setlessBoardLength(true);
							}
						}
					}
				}
			}
		}
	}

	public static void setfirstextraBall(int newfirstExtraBall) {
		firstextraBall = newfirstExtraBall;
	}

	public static int getfirstextraBall() {
		return firstextraBall;
	}
	public static void setfastBallLimit(int newfastBallLimit) {
		fastBallLimit=newfastBallLimit;
	}
	public static int getfastBallLimit() {
		return fastBallLimit;
	}
	public static void setslowBallLimit(int newslowBallLimit) {
		slowBallLimit=newslowBallLimit;
	}
	public static int getslowBallLimit() {
		return slowBallLimit;
	}

}