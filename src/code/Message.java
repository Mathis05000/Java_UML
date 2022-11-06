package code;
import java.util.Date;

public class Message {
	
	private String data;
	private Date date;
	
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
}
