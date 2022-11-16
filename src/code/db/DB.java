package code.db;

public class DB {
	
	// private Connexion connexion;
	
	private DB() {}
	
	private static class DBHolder {
		private final static DB instance = new DB();
	}
	
	public DB getDB() {
		return DBHolder.instance;
	}
	
	// public boolean push();
	// public boolean pull();
}
