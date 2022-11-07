package code;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import code.models.Message;
import code.models.MessageConnect;
import code.models.MessageConnectAck;
import code.tools.UDPSender;
import code.tools.UDPServer;

public class Canal {

	private UDPServer UDPServ;
	private UDPSender UDPClient;
	private InetAddress broadcast;
	private int portUDP;
	private Service service;

	public Canal() throws UnknownHostException, SocketException {

		this.broadcast = InetAddress.getByName("255.255.255.255");
		this.portUDP = 1234;
		this.UDPServ = new UDPServer(this);
		this.UDPServ.start();
		this.UDPClient = new UDPSender();
		this.service = Service.getService();
	}

	public int getPortUDP() {
		return this.portUDP;
	}
	public void sendUDP(Message data, InetAddress address) throws IOException {
		this.UDPClient.send(data, address, this.portUDP);
	}

	public void sendConnect(String pseudo) throws IOException {
		Message message = new MessageConnect(pseudo);
		this.sendUDP(message, this.broadcast);
	}

	public void sendConnectAck(String pseudo, boolean valide, InetAddress address) throws IOException {
		Message message = new MessageConnectAck(pseudo, valide);
		this.sendUDP(message, address);
	}

	public void messageHandler(Message m) {

		if (m instanceof MessageConnect) {
			
		}

		if (m instanceof MessageConnectAck) {

		}
	}

}
