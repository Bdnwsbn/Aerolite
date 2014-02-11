package com.benkatz.multiplayerasteroids;

import android.graphics.Rect;

public class Projectile {

	private int x, y, speedX, speedY;
	private boolean visible;
	private String direction;
	private Rect r;
	
	// X, Y represent top left corner of each bullet
	public Projectile(int startX, int startY, String s){
		x = startX;
		y = startY;
		direction = s;
		if (direction == "up" ) {
			speedY = -7;
		} else if (direction == "down") {
			speedY = 7;
		} else if (direction == "left") {
			speedX = -7;
		} else {
			speedX = 7;
		}
		visible = true;
		
		r = new Rect(0, 0, 0, 0);
	}
	
	public void update(){
		x += speedX;
		y += speedY;
		
 		r.set(x, y, 10, 5);
 		
		// If bullet goes off screen, make it invisible
		if (x > 800 || y > 480){
			visible = false;
			r = null;
		}
		
		if (x < 800 && x > 0 && y < 480 && y > 0) {
			checkCollision();
		}
	}
	
	private void checkCollision() {

		if (Rect.intersects(r, GameScreen.asteroid1.r)) {
			visible = false;
			
			if (GameScreen.asteroid1.health > 0) {
				GameScreen.asteroid1.health -= 1;
			}
			
			if (GameScreen.asteroid1.health <= 0) {
				GameScreen.asteroid1.setCenterX(-500);
				//GameScreen.score += 5;
			}
		}
		
		if (Rect.intersects(r, GameScreen.asteroid2.r)) {
			visible = false;
			
			if (GameScreen.asteroid2.health > 0) {
				GameScreen.asteroid2.health -= 1;
			}
			
			if (GameScreen.asteroid2.health <= 0) {
				GameScreen.asteroid2.setCenterX(-500);
				//GameScreen.score += 5;
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}
	
	public int getSpeedY() {
		return speedY;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
}

