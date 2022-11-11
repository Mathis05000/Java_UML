package code.models;

import java.io.IOException;
import java.net.InetAddress;

import code.tools.TCPSender;

public class Agent {

	private String pseudo;
	private InetAddress address;
	private TCPSender socket;

	public Agent(String pseudo, InetAddress address) throws IOException {
		this.pseudo = pseudo;
		this.address = address;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public InetAddress getAddress() {
		return this.address;
	}

}
