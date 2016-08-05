package backend;

import java.sql.*;
import java.util.ArrayList;
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
    		//System.out.println(conn);
    	} catch (SQLException ex) {
    		ex.printStackTrace();
    		//System.out.println("Connection is: " +conn);
    	}
    }
    
    //Sets up the connection to the database
    private Connection getConnection() throws SQLException {
        if (conn == null) {
            Properties connectionProps = new Properties();
            connectionProps.put("user", "hanzeltog@hanzeltog");
            connectionProps.put("password", "!H4Nz3lT0@");

            conn = DriverManager.getConnection(serverName);

            //System.out.println("Connected to database");
        }
        return conn;
    }
    
    //Method to add competence to competences table
    public boolean addCompetence(Competence competence){
    	
    	String name = competence.getName();
    	String description = competence.getDescription();
    	PreparedStatement pstmt = null;
    	String query = "INSERT Competences (name, description) VALUES (?,?);";
    	
    	try {
    		pstmt = conn.prepareStatement(query);   		
    		pstmt.setString(1, name); 
    		pstmt.setString(2, description);
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
    	
    	String userId = person.getUserId();
    	String firstName = person.getFirstName();
    	String lastName = person.getLastName();
    	int personalProfileId = person.getPersonalProfileId();
    	
    	PreparedStatement pstmt = null;
    	String query = "INSERT Persons (userId, firstname, lastname, personalprofileid) VALUES (?,?,?,?)";
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		pstmt.setString(1, userId);
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

    //Method to get Person from table by user id
    public Person getPerson(String userId){
    	Person person = null;
    	
    	PreparedStatement pstmt = null;
    	String query = "SELECT * FROM Persons WHERE userId LIKE ?";
    	ResultSet rs = null;
    	try {
    		pstmt = conn.prepareStatement(query);
    		pstmt.setString(1,  userId);
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()){
    			int id = rs.getInt(1);
    			String firstname = rs.getString(2);
    			String lastname = rs.getString(3);
    			String userid = rs.getString(4);
    			int profileId = rs.getInt(5);
    			
    			person = new Person(id, firstname, lastname, userid, profileId);
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    		return person;
    	}
    	return person;
    }
    
    //Method to get personal profiles from table
    public ArrayList<PersonalProfile> getPersonalProfiles(){
    	ArrayList<PersonalProfile> personalProfiles = new ArrayList<PersonalProfile>();
    	
    	PreparedStatement pstmt = null;
    	String query = "SELECT * FROM Personal_profile";
    	ResultSet rs = null;
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()){
    			int id = rs.getInt(1);
    			int profileId = rs.getInt(2);
    			String userId = rs.getString(3);
    			
    			personalProfiles.add(new PersonalProfile(id, profileId, userId));
    		}
    	} 
    	catch(SQLException e){
    		e.printStackTrace();
    		return personalProfiles;
    	}
    	return personalProfiles;
    }
    
    //Method to get Profile from table by id
    public Profile getProfile(int id){
    	Profile profile = null;
    	
    	PreparedStatement pstmt = null;
    	String query = "SELECT * FROM Profiles WHERE prof_comp_table_id LIKE ?";
    	ResultSet rs = null;
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		pstmt.setInt(1, id);
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()){
    			String name = rs.getString(2);
    			int profileCompetenceId = rs.getInt(3);
    			String description = rs.getString(4);
    			
    			profile = new Profile(id, name, profileCompetenceId, description);
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    		return profile;
    	}
    	return profile;
    }
    
    //Method to get profiles from user
    public ArrayList<Profile> getUserProfiles(String userId){
    	ArrayList<PersonalProfile> personalProfiles = getPersonalProfiles();
    	ArrayList<Profile> personProfiles = new ArrayList<Profile>();
    	for(int i = 0; i < personalProfiles.size(); i++){
    		PersonalProfile pp = personalProfiles.get(i);
    		if(pp.getUserId().equals(userId)){
    			System.out.println(pp.getUserId());
    			personProfiles.add(getProfile(pp.getProfileId()));
    		}
    	}
    	return personProfiles;
    }
    
    //Method to get list of all Profiles
    public ArrayList<Profile> getProfiles(){
    	ArrayList<Profile> profiles = new ArrayList<Profile>();
    	
    	PreparedStatement pstmt = null;
    	String query = "SELECT * FROM Profiles";
    	ResultSet rs = null;
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()){
    			int id = rs.getInt(1);
    			String name = rs.getString(2);
    			int profileCompetenceId = rs.getInt(3);
    			String description = rs.getString(4);
    			
    			profiles.add(new Profile(id, name, profileCompetenceId, description));
    			System.out.println(profiles);
    			
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    		return profiles;
    	}
    	return profiles;
    }
    
    //Method to get competences from user
    public ArrayList<PersonalCompetenceLevel> getUserCompetences(String userId){
    	ArrayList<PersonalCompetenceLevel> competences = new ArrayList<PersonalCompetenceLevel>();
    	
    	PreparedStatement pstmt = null;
    	String query = "SELECT * FROM Personal_comp_level where user_id LIKE ?";
    	ResultSet rs = null;
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		pstmt.setString(1, userId);
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()){
    			int id = rs.getInt(1);
    			int competenceId = rs.getInt(2);
    			int competenceLevel = rs.getInt(3);
    			String personId = rs.getString(4);
    			
    			competences.add(new PersonalCompetenceLevel(id, competenceId, competenceLevel, personId));
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    		return competences;
    	}
    	return competences;
    }
    
    //Method to get all profile competences
    public ArrayList<ProfileCompetences> getProfileCompetences(){
    	ArrayList<ProfileCompetences> profileCompetences = new ArrayList<ProfileCompetences>();
    	
    	PreparedStatement pstmt = null;
    	String query = "SELECT * FROM Profile_competence_table";
    	ResultSet rs = null;
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()){
    			int id = rs.getInt(1);
    			int profileId = rs.getInt(2);
    			int competence = rs.getInt(3);
    			
    			profileCompetences.add(new ProfileCompetences(id, profileId, competence));
    		}
    	} 
    	catch(SQLException e){
    		e.printStackTrace();
    		return profileCompetences;
    	}
    	return profileCompetences;
    }
    
    //Method to get all competences
    public ArrayList<Competence> getCompetences(){
    	ArrayList<Competence> competences = new ArrayList<Competence>();
    	
    	PreparedStatement pstmt = null;
    	String query = "SELECT * FROM Competences";
    	ResultSet rs = null;
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()){
    			int id = rs.getInt(1);
    			String name = rs.getString(2);
    			String description = rs.getString(3);
    			System.out.println(description + name);
    			competences.add(new Competence(id, name, description));
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    		return competences;
    	}
    	return competences;
    }
    
    //Method for updating the copmetences in the database
    public boolean updateCompetences(ArrayList<SaveCompetences> list, String userid){
    	PreparedStatement pstmt = null;
    	String query = "UPDATE Personal_comp_level SET comp_level = ? WHERE user_id LIKE ? and comp_id = ?";
    	int updated = 0;
    	for(int i = 0; i < list.size(); i++){
    		SaveCompetences sc = list.get(i);
    		try {
    			pstmt = conn.prepareStatement(query);
    			pstmt.setInt(1, sc.getLevel());
    			pstmt.setString(2, userid);
    			pstmt.setInt(3, sc.getId());
    			
    			updated = updated + pstmt.executeUpdate();
    		}
    		catch(SQLException e){
    			e.printStackTrace();
    			return false;
    		}
       	}
    	//TODO add logic for everything update
    	System.out.println("Total of " + updated + " rows updated");
    	return true;
    }
    
    //Method to add competence to user
    public boolean addCompetenceToUser(SaveCompetences saveCompetences, String userid){
    	PreparedStatement pstmt = null;
    	String query = "INSERT Personal_comp_level (comp_id, comp_level, user_id) VALUES (?,?,?)";
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		pstmt.setInt(1,  saveCompetences.getId());
    		pstmt.setInt(2, 0);
    		pstmt.setString(3, userid);
    		
    		pstmt.executeUpdate();
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    		return false;
    	}
    	return true;
    }
    
    //Method to set new competence to profile 'overige'
    public boolean addCompetenceToProfile(ProfileCompetences pc){
    	PreparedStatement pstmt = null;
    	String query = "INSERT Profile_competence_table (profile_id, competences) VALUES (?,?)";
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		pstmt.setInt(1, pc.getProfileId());
    		pstmt.setInt(2, pc.getCompetenceId());
    		
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
    	//System.out.println(dbc.getUserProfiles("hli24213"));
    	//System.out.println(dbc.getProfileCompetences());
    	//System.out.println(dbc.getCompetences());
    	System.out.println(dbc.getUserCompetences("hli24213"));
    	ArrayList<PersonalCompetenceLevel> arr = dbc.getUserCompetences("hli24213");
    	for (int i = 0; i < arr.size(); i++){
    		System.out.println(arr.get(i).getCompetenceLevel() + arr.get(i).getPersonId() + arr.get(i).getCompetenceId());
    	}
    }
    
}
