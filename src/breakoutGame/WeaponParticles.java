package breakoutGame;

import java.awt.*;

public class WeaponParticles extends GameObject {
	private Handler handler;
	private int width=9;
	private int height=9;
	
	public WeaponParticles(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velY=-7;
	}

	public void tick() {
		y+=velY;
		handler.addObject(new Trail(x,y, ID.Trail, Color.yellow,9,9,0.12f, handler));
		collision();
		if (y<=0) {
			handler.object.remove(this);
		}
	}

	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval((int)x,(int)y,width,height);

	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,width,height);
	}
	/*
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x+3,(int)y,width-6,height-11);
	}
	public Rectangle getBoundsBot() {
		return new Rectangle((int)x+3,(int)y+11,width-6,height-11);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x,(int)y+3,width-11,height-6);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+11,(int)y+3,width-11,height-6);
	}
	*/
	private void collision() {
		for (int i=0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
		}
	}

	
}