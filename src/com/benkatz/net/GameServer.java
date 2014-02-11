package com.benkatz.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

import com.benkatz.framework.Game;

public class GameServer extends Thread {
	
protected static final String TAG = "Server";
	
	private DatagramSocket socket;
//	private Game game;
	
	//public GameServer(Game game, String ipAddress) {
	public GameServer() {
		//this.game = game;
		try {
			this.socket = new DatagramSocket(5000);    // Listens to Port 5000
		} catch (SocketException e) {
			e.printStackTrace();
		} 
		
	}

	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Log.v(TAG, "Received " + new String(packet.getData()).trim());
			
			String message = new String(packet.getData());
			if (message.trim().equalsIgnoreCase("ping")); {
				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
			}  
		}
	}
	
	public void sendData(byte[] data, InetAddress ipAddress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Log.v(TAG, "Sent " + new String(packet.getData()));
	}

}
