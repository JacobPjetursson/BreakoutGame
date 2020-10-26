
// 80 PIXELS EACH SIDE FOR X-AXIS
public class Levels {
	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private int enemySpaceX =81;
	private int enemySpaceY = 26;

	public Levels(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}

	public void setScoreKeep(int scorekeep) {
		this.scoreKeep = scorekeep;
	}

	public void tick() {

		if (hud.getScore() == scoreKeep * 100) {
			
			for (int i=0; i<handler.object.size();i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Ball) {
					if (tempObject.getVelY()!=0) {
						Game.gameState = Game.STATE.Nextlevel;
						hud.setLevel(hud.getLevel() + 1);
						handler.object.clear();
						Game.reset();
						AudioPlayer.playSound("audioFiles/MarioNextLevel.wav");
					}
				}
			}
			if (hud.getLevel() == 1 && Game.gameState== Game.STATE.Game) {
				for (int i = 0; i < 13; i++) {
					handler.addObject(new BasicEnemy(80 + i * enemySpaceX, 50, ID.BasicEnemy, handler, hud));
					scoreKeep++;
				}
				for (int i = 0; i < 13; i++) {
					handler.addObject(new BasicEnemy(80 + i * enemySpaceX, 50+enemySpaceY, ID.BasicEnemy, handler, hud));
					scoreKeep++;
				}
			}
			else if (hud.getLevel() == 2 && Game.gameState == Game.STATE.Game) {
				for (int i=0;i<5;i++) {
					for (int j=0;j<13;j++) {
						if ((j % 2 != 0 && i == 2) || (j % 2 != 0 && i == 1)) {
							handler.addObject(new ImmuneEnemy(80+j*enemySpaceX, 50+i*enemySpaceY, ID.ImmuneEnemy, handler, hud));
						} else {
							
							handler.addObject(new BasicEnemy(80+j*enemySpaceX, 50+i*enemySpaceY, ID.BasicEnemy, handler, hud));
							scoreKeep++;
						}
							
							
						
					}
				}
				for (int i=0; i<13; i++) {
					handler.addObject(new ToughEnemy(80+i*enemySpaceX, 50+5*enemySpaceY, ID.ToughEnemy, handler, hud,false));
					scoreKeep+=2;
				}
			}
			else if (hud.getLevel() == 3 && Game.gameState == Game.STATE.Game) {
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 13; j++) {
						if (i%2 == 0) {
							handler.addObject(new ToughEnemy(80 + j * enemySpaceX, 50 + i * enemySpaceY, ID.ToughEnemy, handler, hud,false));
							scoreKeep+=2;
						}
						else {
							handler.addObject(new BasicEnemy(80 + j * enemySpaceX, 50 + i * enemySpaceY, ID.BasicEnemy, handler, hud));
							scoreKeep++;
						}
					}
				}
			}
			else if (hud.getLevel() == 4 && Game.gameState == Game.STATE.Game) {
				for (int i = 0; i < 10; i++) {
					for (int j=0;j<13;j++) {
							handler.addObject(new ToughEnemy(80 + j * enemySpaceX, 50 + i * enemySpaceY, ID.ToughEnemy, handler, hud,true));
							scoreKeep += 3;
					}
				}
			}
			else if (hud.getLevel() == 5 && Game.gameState == Game.STATE.Game) {
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 13; j++) {
						if ((i <= 6 && i >= 3 && j <= 7 && j >= 5)) {
							handler.addObject(new ImmuneEnemy(80 + j * enemySpaceX, 50 + i * enemySpaceY, ID.ImmuneEnemy, handler, hud));
						}
						else {	
							handler.addObject(new ToughEnemy(80 + j * enemySpaceX, 50 + i * enemySpaceY, ID.ToughEnemy, handler, hud,true));
							scoreKeep += 3;
						}
					
					}
				}
			}
			else if (hud.getLevel() == 6 && Game.gameState== Game.STATE.Game) {
				for (int i = 0; i < 16; i++) {
					for (int j=0;j<13;j++) {
						if (j % 3 == 0) {
						handler.addObject(new BasicEnemy(80+j*enemySpaceX, 50+i*enemySpaceY, ID.BasicEnemy, handler, hud));
						scoreKeep++;
						}
						else if (j % 3 == 2) {
							handler.addObject(new ToughEnemy(80+j*enemySpaceX, 50+i*enemySpaceY, ID.ToughEnemy, handler, hud, true));
							scoreKeep+=3;
						}
						else {
							handler.addObject(new ToughEnemy(80+j*enemySpaceX, 50+i*enemySpaceY, ID.ToughEnemy, handler, hud, false));
							scoreKeep+=2;
						}
					}	
				}
				for (int i =0; i<10; i++) {
					handler.addObject(new ImmuneEnemy(i * 1120, 600, ID.ImmuneEnemy, handler, hud));
				}
				
			}
			else if (hud.getLevel() == 7 && Game.gameState== Game.STATE.Game) {
				for (int i=0;i<16;i++) {
					for (int j=0;j<13;j++) {
						handler.addObject(new ToughEnemy(80+j*enemySpaceX, 50+i*enemySpaceY, ID.ToughEnemy, handler, hud, true));
						scoreKeep+=3;
					}
				}
				handler.addObject(new ImmuneEnemy(0,475, ID.ImmuneEnemy,handler,hud));
				handler.addObject(new ImmuneEnemy(1120,475, ID.ImmuneEnemy,handler,hud));
				for (int i=0;i<3;i++) {
					handler.addObject(new ImmuneEnemy(300+i*275,600, ID.ImmuneEnemy,handler,hud));
				}
			}

		}
	}
}
