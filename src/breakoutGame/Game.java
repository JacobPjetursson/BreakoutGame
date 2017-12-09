package breakoutGame;
import java.awt.*;
import java.awt.image.*;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -5710040887476117394L;
	
	public static final int WIDTH=1200, HEIGHT = WIDTH/12 * 9;
	private Thread thread;
	private boolean running = false;
	private Levels levels;
	private Handler handler;
	private HUD hud;
	private Menu menu;
	private static int ballCount = 1;
	public enum STATE {
		Menu,
		Settings,
		Store,
		Help,
		Game,
		Nextlevel,
		End
	};
	public static int getBallCount() {
		return ballCount;
	}
	public static void setBallCount(int newBallCount) {
		ballCount=newBallCount;
	}
	public static STATE gameState = STATE.Menu;
	
	
	public Game() {
		new Window(WIDTH, HEIGHT, "Breakout!", this);
		
		handler = new Handler();
		hud = new HUD();
		levels = new Levels(handler,hud);
		menu = new Menu(this, handler, hud, levels);

		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running=true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running=false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta+=(now-lastTime) / ns;
			lastTime = now;
			while(delta>=1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer+= 1000;
				System.out.println("FPS: " + frames);
				frames=0;
			}
		}
		stop();
	}
	public static void reset() {
		Player.setWeapons(false);
		Player.setlessBoardLength(false);
		Player.setmoreBoardLength(false);
		BasicEnemy.setPenetration(false);
		Player.setballSpeedonPlayer(3);
		PowerUp.setfirstextraBall(1);
		PowerUp.setfastBallLimit(3);
		PowerUp.setslowBallLimit(3);
		BasicEnemy.setpenetrationCount(10);
		Game.setBallCount(1);
		AudioPlayer.stoploopSound();
	}
	private void tick() {
		if (gameState == STATE.Game) {
			handler.tick();
			levels.tick();
		}
		if (ballCount <=0) {
			gameState=STATE.End;
			reset();
			handler.object.clear();
			AudioPlayer.playSound("audioFiles/MarioLose.wav");
		}
		else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Settings || gameState == STATE.Help || gameState == STATE.Nextlevel || gameState == STATE.Store) {
			menu.tick();
			handler.tick();
		}
	}
	
	private void render(){
		final BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		handler.render(g);
		if (gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Settings || gameState == STATE.End || gameState == STATE.Help || gameState == STATE.Nextlevel || gameState == STATE.Store) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
		
	}
	
	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		}
		else if (var <= min) {
			return var = min;
		}
		else {
			return var;
		}
	}
}
