package com.gs.gscale.db;

public class DBAccess {
	public DBAccess(String path) {
		this.dbPath = path;
	}
	
	String dbPath = "";
	
	public int OpenDatabase() {
		return 0;
	}
	
	public int CloseDatabase() {
		return 0;
	}
}
