package code.models;

import java.net.InetAddress;

public class Agent {

	private String pseudo;
	private InetAddress address;

	public Agent(String pseudo, InetAddress address) {
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
