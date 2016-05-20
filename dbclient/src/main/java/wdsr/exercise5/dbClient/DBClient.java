package wdsr.exercise5.dbClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wdsr.exercise5.Main;

public class DBClient {
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	
	private Connection connection = null;
	
	private static final String URL = "jdbc:hsqldb:mem:test-db";
	private static final String User = "SA";
	
	public DBClient(){
		try {
			connection = DriverManager.getConnection(URL, User, "");
			log.info("Connected");
		} catch (SQLException e) {
			log.error("Error: ", e);
		}
	}
	
	public void createTabels(){
		PreparedStatement preparedStatement = null;
		String query = null;
		try {
			query = "CREATE TABLE Student ("
					+ "pkey INT NOT NULL GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1),"
					+ "name VARCHAR(255) NOT NULL,"
					+ "sex VARCHAR(6) NOT NULL,"
					+ "age INT NULL,"
					+ "level INT NULL,"
					+ "PRIMARY KEY (pkey));";
			preparedStatement = connection.prepareStatement(query);
			log.info("Table 'Student' has been created",preparedStatement.executeUpdate());
			
			query = "CREATE TABLE Faculty ("
					+ "pkey INT NOT NULL GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1),"
					+ "name VARCHAR(255) NOT NULL,"
					+ "PRIMARY KEY (pkey));";
			preparedStatement = connection.prepareStatement(query);
			log.info("Table 'Faculty' has been created",preparedStatement.executeUpdate());

			query = "CREATE TABLE Class ("
					+ "pkey INT NOT NULL GENERATED BY DEFAULT AS IDENTITY(START WITH 1000, INCREMENT BY 1),"
					+ "name VARCHAR(255) NOT NULL,"
					+ "fkey_faculty INT,"
					+ "PRIMARY KEY (pkey),"
					+ "FOREIGN KEY (fkey_faculty) REFERENCES Faculty(pkey));";
			preparedStatement = connection.prepareStatement(query);
			log.info("Table 'Class' has been created",preparedStatement.executeUpdate());

			query = "CREATE TABLE Enrollment ("
					+ "fkey_student INT,"
					+ "fkey_class INT,"
					+ "FOREIGN KEY (fkey_student) REFERENCES Student(pkey),"
					+ "FOREIGN KEY (fkey_class) REFERENCES Class(pkey));";
			preparedStatement = connection.prepareStatement(query);
			log.info("Table 'Enrollment' has been created",preparedStatement.executeUpdate());

		} catch (SQLException e) {
			log.error("Error: ", e);
		}
	}
	
	public void insertValues(){
		PreparedStatement preparedStatement = null;
		String query = null;
		try {
			query = "INSERT INTO Student (name, sex, age, level) VALUES (?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, "John Smith");
			preparedStatement.setString(2, "male");
			preparedStatement.setInt(3, 23);
			preparedStatement.setInt(4, 2);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }else{
	        	log.info("Student 'John Smith' has been added");
	        }

			preparedStatement.setString(1, "Rebecca Milson");
			preparedStatement.setString(2, "female");
			preparedStatement.setInt(3, 27);
			preparedStatement.setInt(4, 3);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }else{
	        	log.info("Student 'Rebecca Milson' has been added");
	        }
		
			preparedStatement.setString(1, "George Heartbreaker");
			preparedStatement.setString(2, "male");
			preparedStatement.setInt(3, 19);
			preparedStatement.setInt(4, 1);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }else{
	        	log.info("Student 'George Heartbreaker' has been added");
	        }
		
			preparedStatement.setString(1, "Deepika Chopra");
			preparedStatement.setString(2, "female");
			preparedStatement.setInt(3, 25);
			preparedStatement.setInt(4, 3);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }else{
	        	log.info("Student 'Deepika Chopra' has been added");
	        }
		
			preparedStatement = connection.prepareStatement("INSERT INTO Faculty (name) VALUES (?)");
			preparedStatement.setString(1, "Engineering");
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating faculty failed, no rows affected.");
	        }else{
	        	log.info("Faculty 'Engineering' has been added");
	        }
			
			preparedStatement.setString(1, "Philosophy");
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating faculty failed, no rows affected.");
	        }else{
	        	log.info("Faculty 'Philosophy' has been added");
	        }

			preparedStatement.setString(1, "Law and administration");
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating faculty failed, no rows affected.");
	        }else{
	        	log.info("Faculty 'Law and administration' has been added");
	        }
			
			preparedStatement.setString(1, "Languages");
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating faculty failed, no rows affected.");
	        }else{
	        	log.info("Faculty 'Languages' has been added");
	        }
			
			preparedStatement = connection.prepareStatement("INSERT INTO Class (name, fkey_faculty) VALUES (?,?)");
			preparedStatement.setString(1, "Introduction to labour law");
			preparedStatement.setInt(2, 102);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating class failed, no rows affected.");
	        }else{
	        	log.info("Class 'Introduction to labour law' has been added");
	        }

			preparedStatement.setString(1, "Graph algorithms");
			preparedStatement.setInt(2, 100);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating class failed, no rows affected.");
	        }else{
	        	log.info("Class 'Graph algorithms' has been added");
	        }
			
			preparedStatement.setString(1, "Existentialism in 20th century");
			preparedStatement.setInt(2, 101);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating class failed, no rows affected.");
	        }else{
	        	log.info("Class 'Existentialism in 20th century' has been added");
	        }
			
			preparedStatement.setString(1, "English grammar");
			preparedStatement.setInt(2, 103);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating class failed, no rows affected.");
	        }else{
	        	log.info("Class 'English grammar' has been added");
	        }
			
			preparedStatement.setString(1, "From Plato to Kant");
			preparedStatement.setInt(2, 101);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating class failed, no rows affected.");
	        }else{
	        	log.info("Class 'From Plato to Kant' has been added");
	        }
			
			preparedStatement = connection.prepareStatement("INSERT INTO Enrollment (fkey_student, fkey_class) VALUES (?,?)");
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, 1000);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating enrollment failed, no rows affected.");
	        }else{
	        	log.info("Enrollment 1-1000 has been added");
	        }
		
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, 1002);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating enrollment failed, no rows affected.");
	        }else{
	        	log.info("Enrollment 1-1002 has been added");
	        }
			
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, 1003);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating enrollment failed, no rows affected.");
	        }else{
	        	log.info("Enrollment 1-1003 has been added");
	        }
			
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, 1004);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating enrollment failed, no rows affected.");
	        }else{
	        	log.info("Enrollment 1-1004 has been added");
	        }
			
			preparedStatement.setInt(1, 2);
			preparedStatement.setInt(2, 1002);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating enrollment failed, no rows affected.");
	        }else{
	        	log.info("Enrollment 2-1002 has been added");
	        }
			
			preparedStatement.setInt(1, 2);
			preparedStatement.setInt(2, 1003);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating enrollment failed, no rows affected.");
	        }else{
	        	log.info("Enrollment 2-1003 has been added");
	        }
			
			preparedStatement.setInt(1, 4);
			preparedStatement.setInt(2, 1000);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating enrollment failed, no rows affected.");
	        }else{
	        	log.info("Enrollment 4-1000 has been added");
	        }
			
			preparedStatement.setInt(1, 4);
			preparedStatement.setInt(2, 1002);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating enrollment failed, no rows affected.");
	        }else{
	        	log.info("Enrollment 4-1002 has been added");
	        }
			
			preparedStatement.setInt(1, 4);
			preparedStatement.setInt(2, 1003);
			if (preparedStatement.executeUpdate() == 0) {
	            throw new SQLException("Creating enrollment failed, no rows affected.");
	        }else{
	        	log.info("Enrollment 4-1003 has been added");
	        }
			
		} catch (SQLException e) {
			log.error("Error: ", e);
		}
	}
	
	
	public void select1(){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String query = "SELECT pkey, name FROM Student";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			log.info("Wynik zapytania 1:");
			while(resultSet.next()){
				log.info("pkey: {} name: {}",resultSet.getInt("pkey"),resultSet.getString("name"));
			}
		} catch (SQLException e) {
			log.error("Error when executing DB query: ", e);
		}
	}
	
	public void select2(){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String query = "Select s.pkey, s.name FROM Student s WHERE NOT EXISTS (SELECT s1.pkey, s1.name FROM Student s1 JOIN Enrollment e ON s.pkey=e.fkey_student WHERE s.pkey=s1.pkey)";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			log.info("Wynik zapytania 2:");
			while(resultSet.next()){
				log.info("pkey: {} name: {}",resultSet.getInt("pkey"),resultSet.getString("name"));
			}
		} catch (SQLException e) {
			log.error("Error when executing DB query: ", e);
		}
	}
	
	public void select3(){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String query = "SELECT s.pkey, s.name FROM Student s JOIN Enrollment e ON s.pkey=e.fkey_student JOIN Class c ON e.fkey_class=c.pkey WHERE sex='female' AND c.name='Existentialism in 20th century'";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			log.info("Wynik zapytania 3:");
			while(resultSet.next()){
				log.info("pkey: {} name: {}",resultSet.getInt("pkey"),resultSet.getString("name"));
			}
		} catch (SQLException e) {
			log.error("Error when executing DB query: ", e);
		}
	}
	
	public void select4(){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String query = "SELECT f.name FROM Faculty f WHERE NOT EXISTS (SELECT f1.name FROM Faculty f1 JOIN Class c ON f1.pkey=c.fkey_faculty JOIN Enrollment e ON c.pkey=e.fkey_class WHERE f.name=f1.name)";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			log.info("Wynik zapytania 4:");
			while(resultSet.next()){
				log.info("name: {}",resultSet.getString("name"));
			}
		} catch (SQLException e) {
			log.error("Error when executing DB query: ", e);
		}
	}
	
	public void select5(){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String query = "SELECT MAX(s1.age) age FROM Student s1 JOIN Enrollment e ON s1.pkey=e.fkey_student JOIN Class c ON e.fkey_class=c.pkey WHERE c.name='Introduction to labour law'";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			log.info("Wynik zapytania 5:");
			while(resultSet.next()){
				log.info("age: {}",resultSet.getInt("age"));
			}
		} catch (SQLException e) {
			log.error("Error when executing DB query: ", e);
		}
	}
	
	public void select6(){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String query = "SELECT c.name FROM Student s JOIN Enrollment e ON s.pkey=e.fkey_student JOIN Class c ON e.fkey_class=c.pkey GROUP BY c.name HAVING COUNT(c.name)>1";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			log.info("Wynik zapytania 6:");
			while(resultSet.next()){
				log.info("name: {}",resultSet.getString("name"));
			}
		} catch (SQLException e) {
			log.error("Error when executing DB query: ", e);
		}
	}
	
	public void select7(){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String query = "SELECT level, AVG(age) average FROM Student GROUP BY level";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			log.info("Wynik zapytania 7:");
			while(resultSet.next()){
				log.info("level: {} average: {}",resultSet.getString("level"),resultSet.getInt("average"));
			}
		} catch (SQLException e) {
			log.error("Error when executing DB query: ", e);
		}
	}
	
	public void closeConnection(){
		try{
			if(connection!=null)
				connection.close();
		}catch(SQLException e){
			log.error("Error when closing connection: ", e);
		}
	}
}
