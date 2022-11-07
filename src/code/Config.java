package code;

import java.util.ArrayList;
import java.util.List;

import code.models.Agent;

public class Config {

	private String pseudo;
	private List<Agent> AgentsActifs = new ArrayList<Agent>();

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

	public void addAgent(Agent newAgent) {
		this.AgentsActifs.add(newAgent);
	}

	public void delAgent(Agent agent) {
		this.AgentsActifs.remove(agent);
	}

	public Agent findAgent(String adresse) {
		for (Agent a : this.AgentsActifs) {
			if (a.getAdresse().equals(adresse)) {
				return a;
			}
		}
		return null;
	}

	public boolean checkPseudo(String pseudo) {
		for (Agent a : this.AgentsActifs) {
			if (a.getPseudo().equals(pseudo)) {
				return false;
			}
		}
		return true;
	}
}
