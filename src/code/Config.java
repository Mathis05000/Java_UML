package code;
import java.util.ArrayList;
import java.util.List;

public class Config {
	
	private String pseudo;
	private List<Agent> AgentsActifs = new ArrayList<Agent>();
	
	private Config() {}
	
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
	
	public boolean checkPseudo(String pseudo) {
		for (Agent a : this.AgentsActifs) {
			if (a.getPseudo().equals(pseudo)) {
				return false;
			}
		}
		return true;
	}
	
	public void connect() {
		// TODO
	}
	
}
