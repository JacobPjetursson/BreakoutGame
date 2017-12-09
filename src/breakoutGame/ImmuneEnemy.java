package breakoutGame;

import java.awt.*;

public class ImmuneEnemy extends GameObject{
	private Handler handler;
	private HUD hud;
	
	private int width=80;
	private int height=25;
	public ImmuneEnemy(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler=handler;
		this.hud=hud;
	}
	
	public void tick() {
		
		collision();
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
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
					if (BasicEnemy.getPenetration()) {
						BasicEnemy.setpenetrationCount(BasicEnemy.getpenetrationCount()-1);
						handler.object.remove(this);
					}
					else if (!BasicEnemy.getPenetration()) {
						AudioPlayer.playSound("audioFiles/ImmuneHit.wav");
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
					
					if (BasicEnemy.getPenetration()) {
						BasicEnemy.setpenetrationCount(BasicEnemy.getpenetrationCount()-1);
						handler.object.remove(this);
					}
					else if (!BasicEnemy.getPenetration()) {
						AudioPlayer.playSound("audioFiles/ImmuneHit.wav");
						tempObject.velX*=-1;
						
					}
					
				}
				
			}
			if (tempObject.getId()==ID.WeaponParticles) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (BasicEnemy.getPenetration()) {
						BasicEnemy.setpenetrationCount(BasicEnemy.getpenetrationCount()-1);
						handler.removeObject(this);
					}
					else if (!BasicEnemy.getPenetration()) {
						handler.removeObject(tempObject);	
					}
				}
			}
		}
	}
}
