package com.benkatz.multiplayerasteroids;

public class Background {
	
	// Constants - (2160/2) = 1080
//	final int CENTER_X = 1080;
//	final int CENTER_Y = 1080;
	
	private int bgX, bgY, speedX, speedY;

	
	public Background(int x, int y) {
		bgX = x;
		bgY = y;
		speedX = 0;
		speedY = 0;
	}
	
	public void update() {
		bgX += speedX;
		bgY += speedY;
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public int getSpeedX() {
		return speedX;
	}
	
	public int getSpeedY() {
		return speedY;
	}
	
//	public int getCENTER_X() {
//		return CENTER_X;
//	}
//	
//	public int getCENTER_Y() {
//		return CENTER_Y;
//	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
}
