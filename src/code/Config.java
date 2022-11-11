package code;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import code.models.Agent;
import code.models.Session;

public class Config {

	private String pseudo;
	private List<Agent> AgentsActifs = new ArrayList<Agent>();
	private List<Session> sessions = new ArrayList<Session>();

	private Config() {
		
	}

	// Gestion du singleton

	private static class ConfigHolder {
		private final static Config instance = new Config();
	}

	public static Config getConfig() {
		return ConfigHolder.instance;
	}

	// Methodes

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String newPseudo) {
		this.pseudo = newPseudo;
	}

	public List<Agent> getAgents() {
		return this.AgentsActifs;
	}

	public void addAgent(Agent newAgent) {
		if (!this.AgentsActifs.contains(newAgent)) {
			this.AgentsActifs.add(newAgent);
		}
	}

	public void delAgent(Agent agent) {
		this.AgentsActifs.remove(agent);
	}

	public Agent findAgent(String adresse) {
		for (Agent a : this.AgentsActifs) {
			if (a.getAddress().equals(adresse)) {
				return a;
			}
		}
		return null;
	}

	public Session getSession(String id) {
		for (Session session : this.sessions) {
			if (session.getId().equals(id)) {
				return session;
			}
		}
		return null;
	}

	public void addSession(Session session) {
		this.sessions.add(session);
	}

	public void delSession(Session session) {
		this.sessions.remove(session);
	}

	public boolean checkPseudo(String pseudo) {
		for (Agent a : this.AgentsActifs) {
			if (a.getPseudo().equals(pseudo)) {
				return false;
			}
		}
		return true;
	}

	public Agent getAgentByAddress(String address) {
		for (Agent a : this.AgentsActifs) {
			if (a.getAddress().getHostAddress().equals(address)) {
				return a;
			}
		}
		return null;
	}


	////////////// methode de tests ///////////////

	public void showAgents() {
		for (Agent a : this.AgentsActifs) {
			System.out.println(a.getPseudo() + " : " + a.getAddress().getHostAddress());
		}
	}

	public void showSessions() {
		for (Session s : this.sessions) {
			System.out.println(s.getId() + " : ");

			for (Agent a : s.getMembres()) {
				System.out.println(a.getPseudo());
			}
		}
	}

	///////////////////////////////////////////////////
}
