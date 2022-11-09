package code;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import code.models.Message;
import code.models.MessageChat;
import code.models.MessageConnect;
import code.models.MessageConnectAck;
import code.models.MessageDisconnect;
import code.models.MessageSession;
import code.tools.UDPSender;
import code.tools.UDPServer;

public class Canal {

	private UDPServer UDPServ;
	private UDPSender UDPClient;
	private InetAddress broadcast;
	private int portUDP;
	private int portTCP;
	private Service service;

	public Canal() throws UnknownHostException, SocketException {

		this.broadcast = InetAddress.getByName("255.255.255.255");
		this.portUDP = 1234;
		this.portTCP = 15000;
		this.UDPServ = new UDPServer(this);
		this.UDPServ.start();
		this.UDPClient = new UDPSender();
		this.service = new Service();
	}

	public int getPortUDP() {
		return this.portUDP;
	}

	public int getPortTCP() {
		return this.portTCP;
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

	public void messageHandler(Message m) throws IOException {

		if (m instanceof MessageConnect) {
			this.service.processMessageConnect((MessageConnect) m);
		}

		if (m instanceof MessageConnectAck) {
			this.service.processMessageConnectAck((MessageConnectAck) m);
		}

		if (m instanceof MessageSession) {
			this.service.processMessageSession((MessageSession) m);
		}

		if (m instanceof MessageDisconnect) {
			this.service.processMessageSession((MessageDisconnect) m);
		}
	}

}
