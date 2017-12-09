package breakoutGame;

import java.awt.*;
import java.util.*;

public class ToughEnemy extends GameObject{
	private Handler handler;
	private HUD hud;
	private int width=80;
	private int height=25;
	private Color color;
	private Boolean isTough=true;
	private Boolean isSuperTough;
	private Random r = new Random();
	public ToughEnemy(int x, int y, ID id, Handler handler, HUD hud, Boolean isSuperTough) {
		super(x, y, id);
		this.handler=handler;
		this.hud=hud;
		this.isSuperTough=isSuperTough;
	}
	
	public void tick() {
		if (BasicEnemy.getpenetrationCount()<=0) {
			BasicEnemy.setPenetration(false);
			BasicEnemy.setpenetrationCount(10);
		}
		collision();
		
	}

	public void render(Graphics g) {
		// COLORS
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
		
		if (isSuperTough) {
			color = color.darker();
			color = color.darker();
			color = color.darker();
			color = color.darker();
		}
		else if (!isSuperTough && isTough) {
			color = color.darker();
			color = color.darker();
		}
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
					if (r.nextInt(3)==0 || PowerUp.getfirstextraBall()==1) {
						handler.object.add(new PowerUp((int)x+40,(int) y+12, ID.PowerUp, handler));
					}
					if (!isTough && !isSuperTough) {
						hud.setScore(hud.getScore()+100);
						handler.object.remove(this);
						if (BasicEnemy.getPenetration()) {
							BasicEnemy.setpenetrationCount(BasicEnemy.getpenetrationCount()-1);
						}
					}
					else if (!isSuperTough) {
						hud.setScore(hud.getScore()+100);
						isTough=false;
						if (BasicEnemy.getPenetration()) {
							BasicEnemy.setpenetrationCount(BasicEnemy.getpenetrationCount()-1);
						}
					}
					else {
						hud.setScore(hud.getScore()+100);
						isSuperTough=false;
						if (BasicEnemy.getPenetration()) {
							BasicEnemy.setpenetrationCount(BasicEnemy.getpenetrationCount()-1);
						}
					}
					if (!BasicEnemy.getPenetration()) {
						AudioPlayer.playSound("audioFiles/BasicHit.wav");
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
					if (r.nextInt(3)==0) {
						handler.object.add(new PowerUp((int)x+40,(int) y+12, ID.PowerUp, handler));
					}
					if (!isTough && !isSuperTough) {
						hud.setScore(hud.getScore()+100);
						handler.object.remove(this);
						if (BasicEnemy.getPenetration()) {
							BasicEnemy.setpenetrationCount(BasicEnemy.getpenetrationCount()-1);
						}
					}
					else if (!isSuperTough) {
						hud.setScore(hud.getScore()+100);
						isTough=false;
						if (BasicEnemy.getPenetration()) {
							BasicEnemy.setpenetrationCount(BasicEnemy.getpenetrationCount()-1);
						}
					}
					else {
						hud.setScore(hud.getScore()+100);
						isSuperTough=false;
						if (BasicEnemy.getPenetration()) {
							BasicEnemy.setpenetrationCount(BasicEnemy.getpenetrationCount()-1);
						}
					}
					
					if (!BasicEnemy.getPenetration()) {
						AudioPlayer.playSound("audioFiles/BasicHit.wav");
						tempObject.velX*=-1;
						
					}
					
				}
				
			}
			if (tempObject.getId()==ID.WeaponParticles) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (r.nextInt(4)==0) {
						handler.object.add(new PowerUp((int)x+40,(int) y+12, ID.PowerUp, handler));
					}
					if (!BasicEnemy.getPenetration()) {
						handler.removeObject(tempObject);	
					}
					
					if (!isTough && !isSuperTough) {
						hud.setScore(hud.getScore()+100);
						handler.object.remove(this);
						if (BasicEnemy.getPenetration()) {
							BasicEnemy.setpenetrationCount(BasicEnemy.getpenetrationCount()-1);
						}
					}
					else if (!isSuperTough) {
						hud.setScore(hud.getScore()+100);
						isTough=false;
						if (BasicEnemy.getPenetration()) {
							BasicEnemy.setpenetrationCount(BasicEnemy.getpenetrationCount()-1);
						}
					}
					else {
						hud.setScore(hud.getScore()+100);
						isSuperTough=false;
						if (BasicEnemy.getPenetration()) {
							BasicEnemy.setpenetrationCount(BasicEnemy.getpenetrationCount()-1);
						}
					}
				}
			}
		}
	}
}