import java.awt.event.*;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private static int weaponParticleCount=0;
	private boolean[] keyDown = new boolean[2];
	private long end = System.currentTimeMillis();
	Game game;

	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;

		keyDown[0] = false;
		keyDown[1] = false;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				// Key events player
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(8);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-8);
					keyDown[1] = true;
				}
			}
		}
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		if (key == KeyEvent.VK_SPACE) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Ball && tempObject.getVelY()==0) {
					tempObject.setVelY(6);
				}
			}
			
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Player) {
					if (Player.getWeapons() && weaponParticleCount!=0) {
						
						if (System.currentTimeMillis()>=end) {
							end =System.currentTimeMillis()+120;
							AudioPlayer.playSound("audioFiles/WeaponZap.wav");
							weaponParticleCount--;
							if (!Player.getmoreBoardLength() && !Player.getlessBoardLength()) {
								handler.object.add(new WeaponParticles((int)tempObject.getX()+7, (int)tempObject.getY()-25, ID.WeaponParticles,handler));
								handler.object.add(new WeaponParticles((int)tempObject.getX()+107, (int)tempObject.getY()-25, ID.WeaponParticles,handler));
							}
							else if (Player.getmoreBoardLength()) {
								handler.object.add(new WeaponParticles((int)tempObject.getX()+7-30, (int)tempObject.getY()-25, ID.WeaponParticles,handler));
								handler.object.add(new WeaponParticles((int)tempObject.getX()+107+30, (int)tempObject.getY()-25, ID.WeaponParticles,handler));
							}
							else if (Player.getlessBoardLength()) {
								handler.object.add(new WeaponParticles((int)tempObject.getX()+7+30, (int)tempObject.getY()-25, ID.WeaponParticles,handler));
								handler.object.add(new WeaponParticles((int)tempObject.getX()+107-30, (int)tempObject.getY()-25, ID.WeaponParticles,handler));
							
						}
		
						}
					}
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				// Key events player
				if (key == KeyEvent.VK_D) {
					keyDown[0] = false;

				}
				if (key == KeyEvent.VK_A) {
					keyDown[1] = false;

				}
				if (!keyDown[0] && !keyDown[1]) {
					tempObject.setVelX(0);
				}
			}
		}
	}
	public static void setParticleCount(int newParticleCount) {
		weaponParticleCount=newParticleCount;
	}
	public static int getParticleCount() {
		return weaponParticleCount;
	}
}