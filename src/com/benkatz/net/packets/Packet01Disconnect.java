package com.benkatz.net.packets;

import com.benkatz.net.GameClient;

public class Packet01Disconnect extends Packet {

	private String username;
	
	// For retrieving data
	public Packet01Disconnect(byte[] data) {
		super(01);
		this.username = readData(data);
	}
	
	// For sending data from client
	public Packet01Disconnect(String username) {
		super(01);
		this.username = username;
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}

	@Override
	public byte[] getData() {
		return ("01" + this.username).getBytes();
	}
	
	public String getUsername() {
		return username;
	}
	
	
}
