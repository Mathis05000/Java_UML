package code.models;

import java.net.InetAddress;
import java.util.Date;

public class Message {

	private String data;
	private Date date;
	private InetAddress src;

	public Message(String data) {
		this.data = data;
		this.date = new Date();
	}

	public String getData() {
		return this.data;
	}

	public Date getDate() {
		return this.date;
	}

	public void setSource(InetAddress address) {
		this.src = address;
	}
}
