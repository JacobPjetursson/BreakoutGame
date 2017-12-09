package breakoutGame;

import java.awt.*;

public class HUD {
	private int score = 0;
	private int level = 1;
	
	public void render(Graphics g) {
		Font fnt = new Font("serif",1,15);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Score:" + score,  15, 30);
		g.drawString("Level:" + level,  15, 55);
		if (BasicEnemy.getPenetration()) {
			g.setColor(Color.white);
			g.drawString("Penetration:", 15, 850);
			g.setColor(Color.green);
			g.fillRect(100, 835, BasicEnemy.getpenetrationCount()*15, 20);
			g.setColor(Color.black);
			for (int i=0;i<14;i++) {
				g.drawLine(100+15*i, 835, 100+15*i, 855);
			}
		}
		if (Player.getWeapons()) {
			g.setColor(Color.white);
			g.drawString("Weapon Shots:", 920, 850);
			g.setColor(Color.green);
			g.fillRect(1025, 835, KeyInput.getParticleCount()*15, 20);
			g.setColor(Color.black);
			for (int i=0;i<14;i++) {
				g.drawLine(1025+15*i, 835, 1025+15*i, 855);
			}
		}
		if (PowerUp.getslowBallLimit()<3) {
			g.setColor(Color.white);
			g.drawString("Slowball level:  "+(PowerUp.getslowBallLimit()*(-1)+3), 320, 850);
			g.setColor(Color.green);
		}
		if (PowerUp.getfastBallLimit()<3) {
			g.setColor(Color.white);
			g.drawString("Fastball level:  "+(PowerUp.getfastBallLimit()*(-1)+3), 720, 850);
			g.setColor(Color.green);
		}
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level=level;
	}
}
