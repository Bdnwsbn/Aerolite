package com.benkatz.net;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

import com.benkatz.multiplayerasteroids.GameScreen;
import com.benkatz.multiplayerasteroids.PlayerMP;
import com.benkatz.net.packets.Packet;
import com.benkatz.net.packets.Packet01Disconnect;
import com.benkatz.net.packets.Packet.PacketTypes;
import com.benkatz.net.packets.Packet00Login;
import com.benkatz.net.packets.Packet02Move;

public class GameClient extends Thread {
	
	protected static final String TAG = "GameClient";
	
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private GameScreen gameScreen;
	
	public GameClient(GameScreen gameScreen, String ipAddress) {
		this.gameScreen = gameScreen;
		try {
			this.socket = new DatagramSocket();    // Listen to Port 5000 if you set (5000)
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		
	}

	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
				
				Log.v(TAG, "Received from InetAddr(" + ipAddress + "): " + new String(packet.getData()).trim());
			} catch (IOException e) {
				e.printStackTrace();
			}

			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		Packet packet = null;
		switch(type) {
			default:
			case INVALID:
				Log.v(TAG, "Invalid for device: " + address);
				break;
				
			case LOGIN:
				packet = new Packet00Login(data);
				handleLogin((Packet00Login) packet, address, port);
				break;
				
			case DISCONNECT:
				packet = new Packet01Disconnect(data);
				handleDisconnect((Packet01Disconnect) packet, address, port);
				//Log.v(TAG, "["+address.getHostAddress()+":"+port+"] " + ((Packet01Disconnect)packet).getUsername()+ " has left Space...");
				//gameScreen.removePlayer(((Packet01Disconnect) packet).getUsername());
				break;
				
			case MOVE:
				packet = new Packet02Move(data);
				handleMove((Packet02Move) packet);
		}
	}
	
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 5000);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Log.v(TAG, "Sent to InetAddr(" + ipAddress + "): " + new String(packet.getData()));
	}
	
	public void sendData2(int x, int y) {		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);

			dos.writeInt(x);
			dos.writeInt(y);
			
			byte[] data = baos.toByteArray();
			DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 5000);
			
			socket.send(packet);
			baos.close();
			dos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		Log.v(TAG, "Sent to InetAddr(" + ipAddress + "): " + x +","+ y);
	}

	public void handleLogin(Packet00Login packet, InetAddress address, int port) {
		Log.v(TAG, "["+address.getHostAddress() + ":" + port + "] " + packet.getUsername() + " has joined game...");

		PlayerMP player = new PlayerMP(packet.getX(), packet.getY(), packet.getUsername(), address, port);
		gameScreen.addPlayer(player);

	}
	
	public void handleDisconnect(Packet01Disconnect packet, InetAddress address, int port) {
		Log.v(TAG, "["+address.getHostAddress()+":"+port+"] " + ((Packet01Disconnect)packet).getUsername()+ " has left Space...");
		
		gameScreen.removePlayer(((Packet01Disconnect) packet).getUsername());
		
	}
	
	private void handleMove(Packet02Move packet) {
		gameScreen.movePlayer(packet.getUsername(), packet.getX(), packet.getY(), 
				packet.isMovingLeft(), packet.isMovingRight(), packet.isMovingUp(), packet.isMovingDown());
	}
}
