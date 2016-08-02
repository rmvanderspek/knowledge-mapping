package backend;

import java.sql.*;

import java.util.Properties;


public class DatabaseConnector {
	
    private String serverName = "jdbc:sqlserver://hanzeltog.database.windows.net:1433;database=testDataBase;user=hanzeltog@hanzeltog;password=!H4Nz3lT0@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private Connection conn;
    
    //Loads correct drivers, needed for cloud implementation
    static {
    	try {
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	} catch(ClassNotFoundException ex) {
    		ex.printStackTrace();
    	}
    }
    
    //Constructor for database Connector
    public DatabaseConnector() {
    	try {
    		conn = getConnection();
    		System.out.println(conn);
    	} catch (SQLException ex) {
    		ex.printStackTrace();
    		System.out.println("Connection is: " +conn);
    	}
    }
    
    //Sets up the connection to the database
    private Connection getConnection() throws SQLException {
        if (conn == null) {
            Properties connectionProps = new Properties();
            connectionProps.put("user", "hanzeltog@hanzeltog");
            connectionProps.put("password", "!H4Nz3lT0@");

            conn = DriverManager.getConnection(serverName);

            System.out.println("Connected to database");
        }
        return conn;
    }
    
    //Method to add competence to competences table
    public boolean addCompetence(Competence competence){
    	
    	String name = competence.getName();
    	PreparedStatement pstmt = null;
    	String query = "INSERT Competences (name) VALUES (?);";
    	
    	try {
    		pstmt = conn.prepareStatement(query);   		
    		pstmt.setString(1, name); 
    		pstmt.executeUpdate();
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    		return false;
    	}    	
    	return true;    	
    }
    
    //Method to add person to persons table
    public boolean addPerson(Person person){
    	
    	int userId = person.getUserId();
    	String firstName = person.getFirstName();
    	String lastName = person.getLastName();
    	int personalProfileId = person.getPersonalProfileId();
    	
    	PreparedStatement pstmt = null;
    	String query = "INSERT Persons (userId, firstname, lastname, personalprofileid) VALUES (?,?,?,?)";
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		pstmt.setInt(1, userId);
    		pstmt.setString(2, firstName);
    		pstmt.setString(3, lastName);
    		pstmt.setInt(4, personalProfileId);
    		pstmt.executeUpdate();
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    		return false;
    	}
    	return true;
    }

    public static void main(String[] args) throws SQLException{
    	DatabaseConnector dbc = new DatabaseConnector();

    	
    }
    
}