package code.models;
import java.util.List;

public class Session {
	
	private List<Agent> membres;
	
	public Session(List<Agent> membres) {
		this.membres = membres;
	}
	
	public void send(String data) {
		// TODO
	}
	
	public void delAgent(Agent agent) {
		this.membres.remove(agent);
	}
}
