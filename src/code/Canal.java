package code;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import code.models.Message;
import code.models.MessageChat;
import code.models.MessageConnect;
import code.models.MessageConnectAck;
import code.models.MessageDisconnect;
import code.models.MessageSession;
import code.models.Session;
import code.tools.TCPSender;
import code.tools.TCPServer;
import code.tools.UDPSender;
import code.tools.UDPServer;

public class Canal {

	private UDPServer UDPServ;
	private UDPSender UDPClient;
	private TCPServer TCPServ;
	private InetAddress broadcast;
	private int portUDP;
	private int portTCP;
	private Service service;

	public Canal(Service service) throws IOException {

		System.out.println("Canal");
		this.broadcast = InetAddress.getByName("255.255.255.255");
		this.portUDP = 1500;
		this.UDPServ = new UDPServer(this);
		this.UDPServ.start();
		//this.TCPServ = new TCPServer(this);
		//this.TCPServ.start();
		this.UDPClient = new UDPSender();
		this.service = service;
	}

	public int getPortUDP() {
		return this.portUDP;
	}

	public int getPortTCP() {
		return this.portTCP;
	}


	/////////////// UDP //////////////////

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

	public void sendSession(Session session, String addr) throws UnknownHostException, IOException {
		MessageSession message = new MessageSession(session.getId(), session.getAddrMembres());
		this.sendUDP(message, InetAddress.getByName(addr));
	}

	///////////////////////////////////////////////////

	//////////////// TCP ///////////////////////

	public void TCPsend(Message message, InetAddress address) throws IOException {
		TCPSender sender = new TCPSender(address, this.portTCP);
		sender.send(message);
	} 

	public void sendChat(String data, String idSession, InetAddress address) throws IOException {
		MessageChat message = new MessageChat(data, idSession);
		this.TCPsend(message, address);
	}

	////////////////////////////////////////////


	public void messageHandler(Message m) throws IOException {

		if (!m.getSource().getHostAddress().equals(InetAddress.getLocalHost().getHostAddress())) {

			if (m instanceof MessageConnect) {
				this.service.processMessageConnect((MessageConnect) m);
			}
	
			if (m instanceof MessageConnectAck) {
				this.service.processMessageConnectAck((MessageConnectAck) m);
			}
	
			if (m instanceof MessageDisconnect) {
				this.service.processMessageDisconnect((MessageDisconnect) m);
			}

			if (m instanceof MessageSession) {
				this.service.processMessageSession((MessageSession) m);
			}
	
			if (m instanceof MessageChat) {
				this.service.processMessageChat((MessageChat) m);
			}
		}
	}
}
