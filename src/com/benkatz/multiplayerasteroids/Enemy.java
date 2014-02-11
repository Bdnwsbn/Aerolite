package com.benkatz.multiplayerasteroids;

import android.graphics.Rect;
import android.util.Log;

public class Enemy {

	private int power, speedX, speedY, centerX, centerY;
	private Background bg = GameScreen.getBg1();
	private Spaceship spaceship = GameScreen.getSpaceship();
	
	public Rect r = new Rect(0, 0, 0, 0);
	public int health = 5;
	
	private int movementSpeed;
	

	// Behavioral Methods
	public void update() {
		follow();
		centerX += speedX;
		centerY += speedY;
		speedX = bg.getSpeedX() + movementSpeed;
		speedY = bg.getSpeedY() + movementSpeed;
		
		r.set(centerX + 1 , centerY + 5, 120, 120);
		
		if (Rect.intersects(r, Spaceship.rectNear)) {
			checkCollision();
		}
	}

	private void checkCollision() {
		if (Rect.intersects(r, Spaceship.rect)) {
			//System.out.println("collision!");
			Log.v("Enemy", "Collision!!!" );
		}
	}
	
	public void follow() {
		if (centerX < -95 || centerX > 810) {
			movementSpeed = 0;
		} else if (Math.abs(spaceship.getCENTER_X() - centerX) < 5) {
			movementSpeed = 0;
		} else {
			if (spaceship.getCENTER_X() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}
	}
	
	public void die() {

	}

	public void attack() {

	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}
	
	public int getSpeedY() {
		return speedY;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

	
}
