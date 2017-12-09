package breakoutGame;

import java.awt.*;


public class Ball extends GameObject {
	private Handler handler;
	private int width=12;
	private int height=12;
	
	public Ball(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		velX=0;
		velY=0;
	}

	public void tick() {
		x+= velX;
		y+=velY;
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Ball) {
				if (tempObject.getY()>Game.HEIGHT ) {
					handler.object.remove(tempObject);
					Game.setBallCount(Game.getBallCount()-1);
				}
			}
		}
		
		for (int i=0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId()==ID.Player && velY==0) {
				velX=tempObject.getVelX();
				if (!Player.getmoreBoardLength() && !Player.getlessBoardLength()) {
					x=Game.clamp(x, 60, Game.WIDTH-72);
				}
				else if (Player.getmoreBoardLength()) {
					x=Game.clamp(x, 90, Game.WIDTH-102);
				}
				else if (Player.getlessBoardLength()) {
					x=Game.clamp(x, 30, Game.WIDTH-42);
				}
			}
		}
		
		if (x <= 0|| x>=Game.WIDTH -18 ) {
			velX*=-1;
		}
		if (y <= 0) {
			velY*=-1;
		}
		collision();
	}

	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((int)x,(int)y,width,height);
		
		g.setColor(Color.white);
		g.drawOval((int)x,(int)y,width,height);
		/*
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.gray);
		g2d.draw(getBounds());
		*/
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