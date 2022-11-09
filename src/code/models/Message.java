package code.models;

import java.net.InetAddress;
import java.util.Date;

public class Message {

	private String data;
	private InetAddress src;

	public Message(String data) {
		this.data = data;
	}

	public String getData() {
		return this.data;
	}

	public InetAddress getSource() {
		return this.src;
	}

	public void setSource(InetAddress address) {
		this.src = address;
	}
}
