package wdsr.exercise5;

import wdsr.exercise5.dbClient.DBClient;

public class Main {
	
	public static void main(String[] args) {
		DBClient dbClient = new DBClient();
		dbClient.createTabels();
		dbClient.insertValues();
		dbClient.select1();
		dbClient.select2();
		dbClient.select3();
		dbClient.select4();
		dbClient.select5();
		dbClient.select6();
		dbClient.select7();
		dbClient.closeConnection();
	}

}
