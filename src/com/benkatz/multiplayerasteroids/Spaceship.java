package com.benkatz.multiplayerasteroids;

import java.util.ArrayList;

import android.graphics.Rect;

public class Spaceship {
	
	// Constants here
	final int MOVESPEED = 5;
	
	// Coordinates for Spaceship's center
	final int CENTER_X = 368;
	final int CENTER_Y = 208;
	
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingDown = false;
	private boolean movingUp = false;
	
	// Default facingUp 
	private boolean facingUp = true;
	private boolean facingDown = false;
	private boolean facingLeft = false;
	private boolean facingRight = false;
	
	// Rate at which Spaceship's coordinates change
	private int speedX = 0;
	private int speedY = 0;
	
	// Collision Rectangles
	public static Rect rect = new Rect(0, 0, 0, 0);
	public static Rect rectNear = new Rect(0, 0, 0, 0);
	
	private Background bg1 = GameScreen.getBg1();
	
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private ArrayList<Projectile> projectiles2 = new ArrayList<Projectile>();

	public void update() {

		// Moves Background with respect to X direction character appears to "move"
		// 400 is half the Applet screen width. 
		if (speedX == 0) {
			bg1.setSpeedX(0);
		} else if (speedX < 0) {
			if (bg1.getBgX() + 400 >= 400){
				bg1.setSpeedX(0);
			} else {
				bg1.setSpeedX(MOVESPEED);
			}
		} else {
			if (bg1.getBgX() - 400 <= -1760){
				bg1.setSpeedX(0);
			} else {
				bg1.setSpeedX(-MOVESPEED);
			}
		}
		
		// Moves Background with respect to Y direction character appears to "move"
		if (speedY == 0) {
			bg1.setSpeedY(0);
		} else if (speedY < 0) {
			if (bg1.getBgY() + 400 >= 400){
				bg1.setSpeedY(0);
			} else {
				bg1.setSpeedY(MOVESPEED);
			}
		} else {
			if (bg1.getBgY() - 400 <= -1760){
				bg1.setSpeedY(0);
			} else {
				bg1.setSpeedY(-MOVESPEED);
			}
		}
		
		rect.set(CENTER_X + 11, CENTER_Y + 23, 40, 30);
		rectNear.set(CENTER_X - 45, CENTER_Y - 40, 150, 150);
		
	}
	

	public void moveRight() {
		setFacingUp(false);
		setFacingDown(false);
		setFacingLeft(false);
		setFacingRight(true);
		speedX = MOVESPEED;
	}

	public void moveLeft() {
		setFacingUp(false);
		setFacingDown(false);
		setFacingLeft(true);
		setFacingRight(false);
		speedX = -MOVESPEED;
	}

	public void moveDown() {
		setFacingUp(false);
		setFacingDown(true);
		setFacingLeft(false);
		setFacingRight(false);
		speedY = MOVESPEED;
	}
	
	public void moveUp() {
		setFacingUp(true);
		setFacingDown(false);
		setFacingLeft(false);
		setFacingRight(false);
		speedY = -MOVESPEED;
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}
	
	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}
	
	public void stopDown() {
		setMovingDown(false);
		stop();
	}
	
	public void stopUp() {
		setMovingUp(false);
		stop();
	}
	
	public void stop() {
		// Left and Right
        if (isMovingRight() == false && isMovingLeft() == false) {
            speedX = 0;
        }

        if (isMovingRight() == false && isMovingLeft() == true) {
            moveLeft();
        }

        if (isMovingRight() == true && isMovingLeft() == false) {
            moveRight();
        }
        
        // Up and Down
        if (isMovingUp() == false && isMovingDown() == false) {
            speedY = 0;
        }

        if (isMovingUp() == false && isMovingDown() == true) {
            moveDown();
        }

        if (isMovingUp() == true && isMovingDown() == false) {
            moveUp();
        }
	}

	// Eventually add diagonal shooting
	public void shoot(String s) {
		if (s == "up") {
			Projectile p = new Projectile(CENTER_X + 12, CENTER_Y + 28, "up");
			Projectile p2 = new Projectile(CENTER_X + 47, CENTER_Y + 28, "up");
			projectiles.add(p);
			projectiles2.add(p2);
		}
		
		if (s == "down") {
			Projectile p = new Projectile(CENTER_X + 12, CENTER_Y + 26, "down");
			Projectile p2 = new Projectile(CENTER_X + 47, CENTER_Y + 26, "down");
			projectiles.add(p);
			projectiles2.add(p2);
		}
		
		if (s == "right") {
			Projectile p = new Projectile(CENTER_X + 25, CENTER_Y + 12, "right");
			Projectile p2 = new Projectile(CENTER_X + 25, CENTER_Y + 47, "right");
			projectiles.add(p);
			projectiles2.add(p2);
		}
		
		if (s == "left") {
			Projectile p = new Projectile(CENTER_X + 28, CENTER_Y + 12, "left");
			Projectile p2 = new Projectile(CENTER_X + 28, CENTER_Y + 47, "left");
			projectiles.add(p);
			projectiles2.add(p2);
		}

	}
	
// Getters & Setters
	public int getCENTER_X() {
		return CENTER_X;
	}

	public int getCENTER_Y() {
		return CENTER_Y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}


	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}


	public boolean isMovingLeft() {
		return movingLeft;
	}


	public boolean isMovingRight() {
		return movingRight;
	}


	public boolean isMovingDown() {
		return movingDown;
	}


	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}


	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}


	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}


	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public ArrayList<Projectile> getProjectiles2() {
		return projectiles2;
	}


	public boolean isFacingUp() {
		return facingUp;
	}


	public boolean isFacingDown() {
		return facingDown;
	}


	public boolean isFacingLeft() {
		return facingLeft;
	}


	public boolean isFacingRight() {
		return facingRight;
	}


	public void setFacingUp(boolean facingUp) {
		this.facingUp = facingUp;
	}


	public void setFacingDown(boolean facingDown) {
		this.facingDown = facingDown;
	}


	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}


	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}

	public int getBgX() {
		return bg1.getBgX();
	}
	
	public int getBgY() {
		return bg1.getBgY();
	}

}

