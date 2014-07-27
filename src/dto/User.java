package dto;

public class User {
	private int UID;
	private int GID;
	private int DBID;
	
	public User() {
		
	}
	
	public User(int UID, int GID, int DBID){
		this.UID = UID;
		this.GID = GID;
		this.DBID = DBID;
	}
	
	public int getUID() {
		return UID;
	}
	public void setUID(int uID) {
		UID = uID;
	}
	public int getGID() {
		return GID;
	}
	public void setGID(int gID) {
		GID = gID;
	}
	public int getDBID() {
		return DBID;
	}
	public void setDBID(int dBID) {
		DBID = dBID;
	}
}
