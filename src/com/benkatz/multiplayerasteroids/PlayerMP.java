package com.benkatz.multiplayerasteroids;

import java.net.InetAddress;

import com.benkatz.framework.Image;

public class PlayerMP extends Spaceship {
	
	public String username;
	public InetAddress ipAddress;
	public int port;
	public int x;
	public int y;
	public Image currentSprite;
	public Animation anim;
	
	public PlayerMP(int x, int y, String username, InetAddress ipAddress, int port) {
		super();
		this.x = x;
		this.y = y;
		this.username = username;
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	public InetAddress getInetAddress () {
		return this.ipAddress;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Image getCurrentSprite() {
		return currentSprite;
	}
	
	public Animation getAnim() {
		return anim;
	}

}
