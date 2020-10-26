import java.awt.*;
import java.util.*;

public class BasicEnemy extends GameObject{
	private Handler handler;
	private HUD hud;
	private int width=80;
	private int height=25;
	private Color color;
	private Random r = new Random();
	private static Boolean penetration=false;
	private static int penetrationCount;
	public BasicEnemy(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler=handler;
		this.hud=hud;
		if (y<=50+height+1) {
			color = Color.magenta;
		}
		else if (y<=50+2*height+1) {
			color = Color.pink;
		}
		else if (y<=50+4*height+1) {
			color = Color.red;
		}
		else if (y<=50+6*height+1) {
			color = Color.yellow;
		}
		else if (y<=50+8*height+1) {
			color = Color.green;
		}
		else if (y<=50+10*height+1) {
			color = Color.blue;
		}
		else if (y<=50+12*height+1) {
			color = Color.cyan;
		}
		else if (y<=50+14*height+1) {
			color = Color.gray;
		} else {
			color = Color.darkGray;
		}
	}
	public static void setPenetration(Boolean newPenetration) {
		penetration=newPenetration;
	}
	public static boolean getPenetration() {
		return penetration;
	}
	public static void setpenetrationCount(int newPenetrationCount) {
		penetrationCount=newPenetrationCount;
	}
	public static int getpenetrationCount() {
		return penetrationCount;
	}
	
	public void tick() {
		if (penetrationCount<=0) {
			penetration=false;
			penetrationCount=10;
		}
		collision();
		
	}

	public void render(Graphics g) {
		
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
		/*
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsBot());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		*/
		
		// FIX THE COLLISION!
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x+3, (int)y, width-6, height-22);
	}
	public Rectangle getBoundsBot() {
		return new Rectangle((int)x+3, (int)y+22, width-6, height-22);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+2, width-76, height-4);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+76, (int)y+2, width-76, height-4);
	}
	
	private void collision() {
		for (int i=0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId()== ID.Ball) {
				// TOP AND BOTTOM WITH DOUBLE INTERSECTION (FOR SIDES AS WELL)
				if(getBoundsTop().intersects(tempObject.getBounds()) || getBoundsBot().intersects(tempObject.getBounds())) {
					// collision code
					if (r.nextInt(3)==0 || PowerUp.getfirstextraBall()==1) {
						handler.object.add(new PowerUp((int)x+40,(int) y+12, ID.PowerUp, handler));
					}
					handler.removeObject(this);
					hud.setScore(hud.getScore()+100);
					if (penetration) {
						penetrationCount--;
					}
					else if (!penetration) {
						AudioPlayer.playSound("audioFiles/BasicHit.wav");
						// Corners
						if (getBoundsTop().intersects(tempObject.getBounds()) && getBoundsLeft().intersects(tempObject.getBounds()) && tempObject.velX>0 && tempObject.velY<0) {
							tempObject.velX*=-1;	
						}
						else if (getBoundsBot().intersects(tempObject.getBounds()) && getBoundsLeft().intersects(tempObject.getBounds()) && tempObject.velX>0 && tempObject.velY>0) {
							tempObject.velX*=-1;	
						}
						else if (getBoundsTop().intersects(tempObject.getBounds()) && getBoundsRight().intersects(tempObject.getBounds()) && tempObject.velX<0 && tempObject.velY<0) {
							tempObject.velX*=-1;	
						}
						else if (getBoundsBot().intersects(tempObject.getBounds()) && getBoundsRight().intersects(tempObject.getBounds()) && tempObject.velX<0 && tempObject.velY>0) {
							tempObject.velX*=-1;		
						}
						else {
							tempObject.velY*=-1;
						}
						
					}
				}
				// SIDES
				else if (getBoundsLeft().intersects(tempObject.getBounds()) || getBoundsRight().intersects(tempObject.getBounds())) {
					
					hud.setScore(hud.getScore()+100);
					handler.removeObject(this);
					
					
					if (r.nextInt(3)==0) {
						handler.object.add(new PowerUp((int)x+40,(int) y+12, ID.PowerUp, handler));
					}
					if (penetration) {
						penetrationCount--;
					}
					else if (!penetration) {
						AudioPlayer.playSound("audioFiles/BasicHit.wav");
						tempObject.velX*=-1;
						
					}
				}
				
			}
			if (tempObject.getId()==ID.WeaponParticles) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
					hud.setScore(hud.getScore()+100);
					if (r.nextInt(4)==0) {
						handler.object.add(new PowerUp((int)x+40,(int) y+12, ID.PowerUp, handler));
					}
					if (penetration) {
						penetrationCount--;
					}
					
					else if (!penetration) {
						handler.removeObject(tempObject);	
					}
				}
			}
		}
	}
}
