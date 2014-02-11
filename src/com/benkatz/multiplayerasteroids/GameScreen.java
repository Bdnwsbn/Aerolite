package com.benkatz.multiplayerasteroids;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.benkatz.framework.Game;
import com.benkatz.framework.Graphics;
import com.benkatz.framework.Image;
import com.benkatz.framework.Input.TouchEvent;
import com.benkatz.framework.Screen;
import com.benkatz.net.GameClient;
import com.benkatz.net.packets.Packet00Login;
import com.benkatz.net.packets.Packet01Disconnect;
import com.benkatz.net.packets.Packet02Move;

public class GameScreen extends Screen {
	enum GameState {
		READY, RUNNING, PAUSED, GAMEOVER
	}

	protected static final String TAG = "GameScreen";
	// GameClient for network
	private GameClient socketClient;
	
	GameState state = GameState.READY;
	
	// Variable Setup
	private static Background bg1;
	private static Spaceship spaceship;
	public static Asteroid asteroid1, asteroid2;
	public static PlayerMP player;
	public static List<PlayerMP> players = new ArrayList<PlayerMP>();
	
	public Image currentSprite;
	private Image 
	characterUp, characterUp2, characterUp3, characterUp4,
	characterDown, characterDown2, characterDown3, characterDown4,
	characterLeft, characterLeft2, characterLeft3, characterLeft4,
	characterRight, characterRight2, characterRight3, characterRight4,
	//characterUpRight, characterUpRight2, characterUpRight3, 
	//characterUpLeft, characterUpLeft2, characterUpLeft3, 
	//characterDownRight, characterDownRight2, characterDownRight3,
	//characterDownLeft, characterDownLeft2, characterDownLeft3, 
	 asteroidBig, asteroidBig2, asteroidBig3, asteroidBig4, asteroidBig5,
	 asteroidBig6, asteroidBig7, asteroidBig8, asteroidBig9, asteroidBig10,
	 asteroidBig11, asteroidBig12, asteroidBig13, asteroidBig14, asteroidBig15,
	 asteroidBig16, asteroidBig17;
	
	private Animation anim, animIdleUp, animMoveUp, animIdleDown, animMoveDown,
	animIdleLeft, animMoveLeft, animIdleRight, animMoveRight, 
	animAsteroid;
	
	int livesLeft = 1;
	Paint paint, paint2;
	
	public GameScreen(Game game) {
		super(game);
		
		// Get InetAddress of Android Device (For multiplayer)
		//InetAddress ipAddress;
		//try {
			//ipAddress = InetAddress.getLocalHost();
			//ipAddress.
			 //For multiplayer - InetAddress.getByName converts ipAddress.getHostAddress() into InetAdress obj giving us the IP of the device
			//InetAddress.getByName(ipAddress.getHostAddress()).toString();
			//player = new PlayerMP(468, 208, "1", null, -1);
		    //spaceship = new PlayerMP(368, 208, "1", null, -1);
			//Packet00Login loginPacket = new Packet00Login(player.getUsername());
			//loginPacket.writeData(socketClient);
		//} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		// Initialize game objects here
		bg1 = new Background(0, 0);
		spaceship = new Spaceship();
		asteroid1 = new Asteroid(500, 0);
		asteroid2 = new Asteroid(500, 280);
		
		
		// Start GameClient thread and initialize MP spaceship instance
		//socketClient = new GameClient(this, "128.239.146.41"); // Server IP (my Laptop's subnet IP on WM network)
//		socketClient = new GameClient(this, "10.0.1.40"); // Laptop IP at home
		socketClient = new GameClient(this, "128.239.24.118");	
		socketClient.start();
		
		Random rand = new Random();
		int id = rand.nextInt(1000000);
		String str = Integer.toString(id);		
		player = new PlayerMP(468, 208, "Player" + id, null, -1);
		players.add(player);
		Packet00Login loginPacket = new Packet00Login(player.getUsername(), player.x, player.y);
		loginPacket.writeData(socketClient);
		
	
		// Images
		characterUp = Assets.characterUp; characterUp2 = Assets.characterUp2;
		characterUp3 = Assets.characterUp3; characterUp4 = Assets.characterUp4;
		characterDown = Assets.characterDown; characterDown2 = Assets.characterDown2;
		characterDown3 = Assets.characterDown3; characterDown4 = Assets.characterDown4;
		characterLeft = Assets.characterLeft; characterLeft2 = Assets.characterLeft2;
		characterLeft3 = Assets.characterLeft3; characterLeft4 = Assets.characterLeft4;
		characterRight = Assets.characterRight; characterRight2 = Assets.characterRight2;
		characterRight3 = Assets.characterRight3; characterRight4 = Assets.characterRight4;
		
		asteroidBig = Assets.asteroidBig;
		asteroidBig2 = Assets.asteroidBig2;
		asteroidBig3 = Assets.asteroidBig3;
		asteroidBig4 = Assets.asteroidBig4;
		asteroidBig5 = Assets.asteroidBig5;
		asteroidBig6 = Assets.asteroidBig6;
		asteroidBig7 = Assets.asteroidBig7;
		asteroidBig8 = Assets.asteroidBig8;
		asteroidBig9 = Assets.asteroidBig9;
		asteroidBig10 = Assets.asteroidBig10;
		asteroidBig11 = Assets.asteroidBig11;
		asteroidBig12 = Assets.asteroidBig12;
		asteroidBig13 = Assets.asteroidBig13;
		asteroidBig14 = Assets.asteroidBig14;
		asteroidBig15 = Assets.asteroidBig15;
		asteroidBig16 = Assets.asteroidBig16;
		asteroidBig17 = Assets.asteroidBig17;
		
		// Animations
		
		// Stationary Spaceship Animation
		animIdleUp = new Animation();
		animIdleUp.addFrame(characterUp, 150);
		animIdleUp.addFrame(characterUp2, 150);

		animIdleDown = new Animation();
		animIdleDown.addFrame(characterDown, 150);
		animIdleDown.addFrame(characterDown2, 150);
		
		animIdleRight = new Animation();
		animIdleRight.addFrame(characterRight, 150);
		animIdleRight.addFrame(characterRight2, 150);
		
		animIdleLeft = new Animation();
		animIdleLeft.addFrame(characterLeft, 150);
		animIdleLeft.addFrame(characterLeft2, 150);
		
		// Moving Spaceship Animation
		animMoveUp = new Animation();
		animMoveUp.addFrame(characterUp3, 150);
		animMoveUp.addFrame(characterUp4, 150);

		animMoveDown = new Animation();
		animMoveDown.addFrame(characterDown3, 150);
		animMoveDown.addFrame(characterDown4, 150);
		
		animMoveRight = new Animation();
		animMoveRight.addFrame(characterRight3, 150);
		animMoveRight.addFrame(characterRight4, 150);
		
		animMoveLeft = new Animation();
		animMoveLeft.addFrame(characterLeft3, 150);
		animMoveLeft.addFrame(characterLeft4, 150);
		
		// Asteroid Animation
		animAsteroid = new Animation();
		animAsteroid.addFrame(asteroidBig, 150);
		animAsteroid.addFrame(asteroidBig2, 150);
		animAsteroid.addFrame(asteroidBig3, 150);
		animAsteroid.addFrame(asteroidBig4, 150);
		animAsteroid.addFrame(asteroidBig5, 150);
		animAsteroid.addFrame(asteroidBig6, 150);
		animAsteroid.addFrame(asteroidBig7, 150);
		animAsteroid.addFrame(asteroidBig8, 150);
		animAsteroid.addFrame(asteroidBig9, 150);
		animAsteroid.addFrame(asteroidBig10, 150);
		animAsteroid.addFrame(asteroidBig11, 150);
		animAsteroid.addFrame(asteroidBig12, 150);
		animAsteroid.addFrame(asteroidBig13, 150);
		animAsteroid.addFrame(asteroidBig14, 150);
		animAsteroid.addFrame(asteroidBig15, 150);
		animAsteroid.addFrame(asteroidBig16, 150);
		animAsteroid.addFrame(asteroidBig17, 150);
		
		// Default Animation for Spaceship
		currentSprite = animIdleUp.getImage();
		
		
		// Defining a Paint Object
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		
		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);
	}
	
	@Override
	public void update(float deltaTime) {
		List touchEvents = game.getInput().getTouchEvents();
		
		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.READY)
			updateReady(touchEvents);
		if (state == GameState.RUNNING)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.PAUSED)
			updatePaused(touchEvents);
		if (state == GameState.GAMEOVER)
			updateGameOver(touchEvents);
	}
	
	private void updateReady(List touchEvents) {
		
		// This example starts with a "Ready" screen
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!
		
		if (touchEvents.size() > 0)
			state = GameState.RUNNING;
	}
	
	// Identical to run() from JavaMA StartingClass()
	private void updateRunning(List touchEvents, float deltaTime) {
		
		// 1. All Touch input handled here:
			// Modified for 4-directional movement, will just add left right buttons
		int len = touchEvents.size();
		for (int i=0; i<len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
	
				// For spaceship move everything over 65 pixels to account for
				// L and R buttons
				
				// TODO: Idle animation
				
				// Move up
				if (inBounds(event, 65, 285, 65, 65)) {
					spaceship.moveUp();
					spaceship.setMovingUp(true);
				} 
				
				//Move down
				else if (inBounds(event, 65, 415, 65, 65)) {
					spaceship.moveDown();
					spaceship.setMovingDown(true);
				}

				//Move Left
				else if (inBounds(event, 0, 350, 65, 65)) {
					spaceship.moveLeft();
					spaceship.setMovingLeft(true);
				}
				
				//Move Right
				else if (inBounds(event, 130, 350, 65, 65)) {
					spaceship.moveRight();
					spaceship.setMovingRight(true);
				}
				
				//Shoot
					//try event.x>400 if this doesnt work
//					if (!(inBounds(event, 65, 350, 200, 200))) { //this would allow me to tap in bad places and shoot
				if(event.x>400) {
					if (spaceship.isFacingUp()) {
						spaceship.shoot("up");
					} else if (spaceship.isFacingDown()) {
						spaceship.shoot("down");
					} else if (spaceship.isFacingLeft()) {
						spaceship.shoot("left");
					} else if (spaceship.isFacingRight()) {
						spaceship.shoot("right");
					}					
				}
			}
			
			if (event.type == TouchEvent.TOUCH_UP) {
				
				//Stop Move Up 
				if (inBounds(event, 65, 285, 65, 65)) {
					spaceship.stopUp();
				} 
				
				//Stop Move down
				else if (inBounds(event, 65, 415, 65, 65)) {
					spaceship.stopDown();
				}

				//Stop Move Left
				else if (inBounds(event, 0, 350, 65, 65)) {
					spaceship.stopLeft();
				}
				
				//Stop Move Right
				else if (inBounds(event, 130, 350, 65, 65)) {
					spaceship.stopRight();
				}
				
				//Pause
				if (inBounds(event, 0, 0, 35, 35)) {
					pause();
				}
			}
		}
		
		// 2. Check miscellaneous events like death:
		
		if (livesLeft == 0) {
			state = GameState.GAMEOVER;
		}
		
		// 3. Call individual update() methods here.
		// This is where all the game updates happen.
		// For example, spaceship.update():
		
		spaceship.update(); // maybe put this after checking anim like in javaMA
		
		// Setting correct animation for Spaceship
		// Check to see if Spaceship is moving
		if (spaceship.isMovingUp()) {
			anim = animMoveUp;
			currentSprite = anim.getImage();
		} else if (spaceship.isMovingDown()) {
			anim = animMoveDown;
			currentSprite = anim.getImage();
		} else if (spaceship.isMovingLeft()) {
			anim = animMoveLeft;
			currentSprite = anim.getImage();
		} else if (spaceship.isMovingRight()) {
			anim = animMoveRight;
			currentSprite = anim.getImage();
		} else {
		// Otherwise Spaceship is stationary
			if (spaceship.isFacingUp()) {
				anim = animIdleUp;
				currentSprite = anim.getImage();
			} else if (spaceship.isFacingDown()) {
				anim = animIdleDown;
				currentSprite = anim.getImage();
			} else if (spaceship.isFacingLeft()) {
				anim = animIdleLeft;
				currentSprite = anim.getImage();
			} else {
				anim = animIdleRight;
				currentSprite = anim.getImage();
			}
		}
		
		// Setting multiplayer spaceships
		if (players != null) {
			for (PlayerMP p : getPlayers()) {
				if (!p.getUsername().equals(player.getUsername())) {  // Don't change client player
					if (p.isMovingUp()) {
						
						Log.v(TAG, "Moving UP");
						
						p.anim = animMoveUp;
						p.currentSprite = p.anim.getImage();
					} else if (p.isMovingDown()) {
						
						Log.v(TAG, "Moving Down");
						
						p.anim = animMoveDown;
						p.currentSprite = p.anim.getImage();
					} else if (p.isMovingLeft()) {
						
						Log.v(TAG, "Moving Left");
						
						p.anim = animMoveLeft;
						p.currentSprite = p.anim.getImage();
					} else if (p.isMovingRight()) {
						
						Log.v(TAG, "Moving Right");
						
						p.anim = animMoveRight;
						p.currentSprite = p.anim.getImage();
					} else {
						if (p.isFacingUp()) {
							
							Log.v(TAG, "Facing UP");
							
							p.anim = animIdleUp;
							p.currentSprite = p.anim.getImage();
						} else if (p.isFacingDown()) {

							Log.v(TAG, "Facing Down");

							p.anim = animIdleDown;
							p.currentSprite = p.anim.getImage();
						} else if (p.isFacingLeft()) {
							
							Log.v(TAG, "Facing Left");
							
							p.anim = animIdleLeft;
							p.currentSprite = p.anim.getImage();
						} else {
							
							Log.v(TAG, "Facing Right");
							
							p.anim = animIdleRight;
							p.currentSprite = p.anim.getImage();
						}
					}
					p.anim.update(10);
				}
			}
		}
		
		// Bullets
		ArrayList projectiles = spaceship.getProjectiles();
		ArrayList projectiles2 = spaceship.getProjectiles2();
		for (int i=0; i<projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			Projectile p2 = (Projectile) projectiles2.get(i);
			if (p.isVisible() == true) {
				p.update();
				p2.update();
			} else {
				projectiles.remove(i);
				projectiles2.remove(i);
			}
		}
		
		// Send data to server
		//socketClient.sendData2(bg1.getBgX(), bg1.getBgY());
		//Log.v(TAG, "CenterX, Y: " + bg1.getBgX() + ", " + bg1.getBgY());
		Packet02Move packet = new Packet02Move(player.getUsername(), -bg1.getBgX(), -bg1.getBgY(), 
//				player.isMovingLeft(), player.isMovingRight(), player.isMovingUp(), player.isMovingDown());
				spaceship.isMovingLeft(), spaceship.isMovingRight(), spaceship.isMovingUp(), spaceship.isMovingDown());
		packet.writeData(socketClient);
		
		spaceship.update();
		asteroid1.update();
		asteroid2.update();
		bg1.update();
		animate();

		
		
		// If Spaceship collides with an Asteroid, set GameState to DEAD 
		//if (asteroid1.r.intersects(r, Spaceship.rect) || asteroid2.r.intersects(Spaceship.rect)) {
		if (Rect.intersects(asteroid1.r, spaceship.rect) || Rect.intersects(asteroid2.r, spaceship.rect)) {
			Log.v(TAG, "GAMEEEEE OVERRRR");
			state = GameState.GAMEOVER;
		}
	}
	
	// Checking to see if touching buttons
	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}
	
	// If paused...Resume or Menu
	private void updatePaused(List touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}
	
	// GameOver
	private void updateGameOver(List touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 800, 480)) {
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}
	}
	
	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
		
		ArrayList<Projectile> projectiles = spaceship.getProjectiles();
		ArrayList<Projectile> projectiles2 = spaceship.getProjectiles2();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			Projectile p2 = projectiles2.get(i);
			if (p.getDirection() == "right" || p.getDirection() == "left") {
				g.drawRect(p.getX(), p.getY(), 10, 5, Color.YELLOW);
				g.drawRect(p2.getX(), p2.getY(), 10, 5, Color.YELLOW);
			} else {
				g.drawRect(p.getX(), p.getY(), 5, 10, Color.YELLOW);
				g.drawRect(p2.getX(), p2.getY(), 5, 10, Color.YELLOW);
			}
		}
		
		// First draw the game elements.

//		g.drawRect(spaceship.getCENTER_X(), spaceship.getCENTER_Y(), 
//				(int)spaceship.rect.width(), (int)spaceship.rect.height(), Color.RED);
//		g.drawRect(spaceship.getCENTER_X(), spaceship.getCENTER_Y(), 
	//			25, 25, Color.RED);
		
		if (players != null) {
			for (PlayerMP p : getPlayers()) {
				if (!p.getUsername().equals(player.getUsername())) {  //Because I had to add this instance to multiplayer list
					
					if (p.currentSprite == null) {
						Log.v(TAG, "p.curSpri == NULL");
						p.currentSprite = animIdleUp.getImage();
					}

					g.drawImage(p.getCurrentSprite(), p.getX() + spaceship.getBgX() + 368, p.getY() + spaceship.getBgY() + 208);

				}
			}
		}
		

				
		g.drawImage(currentSprite, spaceship.getCENTER_X(), spaceship.getCENTER_Y());
		g.drawImage(animAsteroid.getImage(), asteroid1.getCenterX(), asteroid1.getCenterY());
		g.drawImage(animAsteroid.getImage(), asteroid2.getCenterX(), asteroid2.getCenterY());
		

		// Example:
		// g.drawImage(Assets.background, 0, 0);
		// g.drawImage(Assets.character, characterX, characterY);

		// Secondly, draw the UI above the game elements.
		if (state == GameState.READY)
			drawReadyUI();
		if (state == GameState.RUNNING)
			drawRunningUI();
		if (state == GameState.PAUSED)
			drawPausedUI();
		if (state == GameState.GAMEOVER)
			drawGameOverUI();

	}
	
	public void animate() {
		anim.update(10);
		animAsteroid.update(50);
	}
	
	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		paint = null;
		bg1 = null;
		spaceship = null; //player = null; This makes game crash if you pause and then go to menu
		asteroid1 = null; asteroid2 = null;
		currentSprite = null;
		characterUp= null; characterUp2= null; characterUp3= null; characterUp4= null;
		characterDown= null; characterDown2= null; characterDown3= null; characterDown4= null;
		characterLeft= null; characterLeft2= null; characterLeft3= null; characterLeft4= null;
		characterRight= null; characterRight2= null; characterRight3= null; characterRight4= null;
		//characterUpRight, characterUpRight2, characterUpRight3, 
		//characterUpLeft, characterUpLeft2, characterUpLeft3, 
		//characterDownRight, characterDownRight2, characterDownRight3,
		//characterDownLeft, characterDownLeft2, characterDownLeft3, 
		 asteroidBig= null; asteroidBig2= null; asteroidBig3= null; asteroidBig4= null;
		 asteroidBig5= null; asteroidBig6= null; asteroidBig7= null; asteroidBig8= null; 
		 asteroidBig9= null; asteroidBig10= null; asteroidBig11= null; asteroidBig12= null; 
		 asteroidBig13= null; asteroidBig14= null; asteroidBig15= null; asteroidBig16= null; 
		 asteroidBig17= null;
		
		 anim = null; animIdleUp= null; animMoveUp= null;animIdleDown= null; animMoveDown= null;
		 animIdleLeft= null; animMoveLeft= null; animIdleRight= null; animMoveRight= null; 
		 animAsteroid= null;


		// Call garbage collector to clean up memory.
		System.gc();

	}
	
	private void drawReadyUI() {
		Graphics g = game.getGraphics();
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 400, 240, paint);

	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.button, 65, 285, 0, 0, 65, 65); //Up
		//g.drawImage(Assets.button, 0, 350, 0, 65, 65, 65); //Shoot button
		g.drawImage(Assets.button, 65, 415, 0, 130, 65, 65); //Down
		g.drawImage(Assets.button, 0, 350, 0, 65, 65, 65); //Left
		g.drawImage(Assets.button, 130, 350, 0, 65, 65, 65); //Right
		g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35); //Pause
		
	}
	
	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, 360, paint2);

	}
	
	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		g.drawString("GAME OVER.", 400, 240, paint2);
		g.drawString("Tap to return.", 400, 290, paint);

	}
	
	@Override
	public void pause() {
		if (state == GameState.RUNNING)
			state = GameState.PAUSED;

	}

	@Override
	public void resume() {
		if (state == GameState.PAUSED)
			state = GameState.RUNNING;
	}

	@Override
	public void dispose() {
		Packet01Disconnect packet = new Packet01Disconnect(GameScreen.player.getUsername());
		packet.writeData(this.socketClient);
	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

	public static Background getBg1() {
		return bg1;
	}
	
	public static Spaceship getSpaceship() {
		return spaceship;
	}
	
	public synchronized List<PlayerMP> getPlayers() {
		return GameScreen.players;
	}
	
	public void addPlayer(PlayerMP player) {
		getPlayers().add(player);
		Log.v(TAG, "Added " + player.getUsername() + " to list. Now Size = " + getPlayers().size());
	}
	
	public void removePlayer(String username) {
		int index = 0; 
		for (PlayerMP p : getPlayers()) {
			if (p.getUsername().equalsIgnoreCase(username)) {
				break;
			}
			index++;
		}
		getPlayers().remove(index);
	}
	
	private int getPlayerIndex(String username) {
		int index = 0;
		for (PlayerMP p : getPlayers()) {
			if (p.getUsername().equalsIgnoreCase(username)) {
				//Log.v(TAG, "username equals");
				//Log.v(TAG, "p.user : " + p.getUsername() + ", str username:" + username);

				break;
			}
			//Log.v(TAG, "p.user : " + p.getUsername() + ", str username:" + username);

			index++;
		}
		//Log.v(TAG, "pIndex - index: " + index + " , size= "+ players.size());
				
		return index;
	}
	
	public void movePlayer(String username, int x, int y, boolean isMovingLeft, boolean isMovingRight,
	boolean isMovingUp, boolean isMovingDown ){
		if (!players.isEmpty()) {
			int index = getPlayerIndex(username);
			
			PlayerMP player = (PlayerMP) getPlayers().get(index);
			
			//Log.v(TAG, "username: " + username +", index: " + index + ", size= "+ players.size());
			
			player.x = x;
			player.y = y;
			player.setMovingLeft(isMovingLeft);
			player.setMovingRight(isMovingRight);
			player.setMovingUp(isMovingUp);
			player.setMovingDown(isMovingDown);
		}
	}
	
//	private void playersUpdate() {
//		for (PlayerMP p : players) {
//			Packet02Move packet = new Packet02Move(p.getUsername(), p.g)
//		}		
//	}
	
}