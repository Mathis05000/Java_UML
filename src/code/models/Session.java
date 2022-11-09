package code.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import code.Config;

public class Session {

	private List<Agent> membres;
	private String id;

	public Session(List<Agent> membres) {
		this.membres = membres;
		this.id = UUID.randomUUID().toString();
	}

	public void delAgent(Agent agent) {
		this.membres.remove(agent);
	}

	public String getId() {
		return this.id;
	}

	public static Session createByMessage(MessageSession message, Config config) {
		List<Agent> agents = new ArrayList<Agent>();

		for (String address : message.getAddrMembres()) {
			agents.add(config.getAgentByAddress(address));
		}

		return new Session(agents);
	}

	public void send(String data) {
		// TODO
	}
}
