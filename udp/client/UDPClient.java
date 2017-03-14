package udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class UDPClient {

	private int clientID;
	
	private byte[] buffer;
	private int clientPort;
	private int serverPort;
	private InetAddress serverAddress;	
	private DatagramSocket datagramSocket;
	DatagramPacket datagramPacket;
	
	public UDPClient(int clientID, String hostname, int clientPort, int serverPort){
		this.clientID = clientID;
		buffer = null;
		this.clientPort = clientPort;
		this.serverPort = serverPort;
		
		try {
			this.serverAddress = InetAddress.getByName(hostname);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			
		}
		
		try {
			this.datagramSocket = new DatagramSocket(clientPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}		
		
		datagramPacket = null;
	}
	
	public void send(String message){
		buffer = new byte[65508]; 
		//buffer = (clientID + message).getBytes();
		
		buffer = ByteBuffer.allocate(4).putInt(Integer.parseInt(message)).array();
		datagramPacket = new DatagramPacket(buffer, buffer.length ,serverAddress, serverPort);
		
		try {
			datagramSocket.send(datagramPacket);
			System.out.println("send");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String receive(){
		buffer = new byte[65508]; 
		datagramPacket = new DatagramPacket(buffer, buffer.length);
		
		try {
			datagramSocket.receive(datagramPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}			
			
		return new String(datagramPacket.getData()).substring(0, datagramPacket.getLength());
	}
	
	public void print(){
		System.out.println(serverAddress);
	}
	
}
