package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.persistence.*;



public class DatabaseConnector {
	
    private String serverName = "jdbc:sqlserver://hanzeltog.database.windows.net:1433;database=testDataBase;user=hanzeltog@hanzeltog;password=!H4Nz3lT0@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private Connection conn;
    
    //Loads correct drivers, needed for cloud implementation
    static {
    	try {
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		Class.forName("org.eclipse.persistence.jpa.PersistenceProvider");
    	} catch(ClassNotFoundException ex) {
    		ex.printStackTrace();
    	}
    }

    
    //Constructor for database Connector
    public DatabaseConnector() {
    	try {
    		conn = getConnection();
    	} catch (SQLException ex) {
    		ex.printStackTrace();
    	}
    }
    
    //Sets up the connection to the database
    private Connection getConnection() throws SQLException {
        if (conn == null) {
            Properties connectionProps = new Properties();
            connectionProps.put("user", "hanzeltog@hanzeltog");
            connectionProps.put("password", "!H4Nz3lT0@");

            conn = DriverManager.getConnection(serverName);
        }
        return conn;
    }
    
    //Method to add competence to competences table
    public boolean addCompetence(Competences competence){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	
    	em.persist(competence);
    	tx.commit();
    	em.close();
 	
    	return true;    	
    }
    
    //Method to add person to persons table
    public boolean addPerson(Persons person){
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	
    	em.persist(person);
    	tx.commit();
    	em.close();

    	return true;
    }

    //Method to get Person from table by user id
    public Persons getPerson(String userId){
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	System.out.println("GO");
    	@SuppressWarnings("unchecked")
    	Persons person = (Persons) em.createQuery("SELECT p FROM Persons p WHERE p.user_id =:userId")
    												.setParameter("userId", userId)
    												.getSingleResult();
    	System.out.println(person.getFirstName());
    	tx.commit();
    	em.close();

    	return person;
    }
    
    //Method to get personal profiles from table
    public List<Personal_profile> getPersonalProfiles(){
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	@SuppressWarnings("unchecked")
    	List<Personal_profile> personalProfiles = (List<Personal_profile>) em.createQuery("SELECT p FROM Personal_profile p").getResultList();

    	tx.commit();
    	em.close();

    	return personalProfiles;
    }
    
    //Method to get Profile from table by id
    public Profiles getProfile(int id){
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();

    	@SuppressWarnings("unchecked")
    	Profiles profile = (Profiles) em.createQuery("SELECT p FROM Profiles p WHERE p.prof_comp_table_id =:id")
    												.setParameter("id", id)
    												.getSingleResult();
    	tx.commit();
    	em.close();
    	System.out.println(profile.getName());

    	return profile;
    }
    
    //Method to get profiles from user
    public List<Profiles> getUserProfiles(String userId){
    	List<Personal_profile> personalProfiles = getPersonalProfiles();
    	List<Profiles> personProfiles = new ArrayList<Profiles>();
    	Iterator<Personal_profile> i = personalProfiles.iterator();
    	while(i.hasNext()){
    		Personal_profile pp = (Personal_profile) i.next();
    		System.out.println(pp.getUserId());
    		if(pp.getUserId().equals(userId)){
    			System.out.println(pp.getProfileId());
    			personProfiles.add(getProfile(pp.getProfileId()));
    		}
    	}
    	return personProfiles;
    }
    
    //Method to get list of all Profiles
    public ArrayList<Profiles> getProfiles(){
    	ArrayList<Profiles> profiles = new ArrayList<Profiles>();
    	
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
    			
    			profiles.add(new Profiles(id, name, profileCompetenceId, description));    			
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
    public List<Competences> getCompetences(){
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	
    	@SuppressWarnings("unchecked")
		List<Competences> competences = (List<Competences>) em.createQuery("SELECT c FROM Competences c").getResultList();
    	tx.commit();
    	em.close();

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
    
    //Method to change a competence profile
    public boolean changeCompetenceProfile(int competenceid, int profileid){
    	PreparedStatement pstmt = null;
    	String query = "UPDATE Profile_competence_table SET profile_id = ? WHERE competences = ?";
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		pstmt.setInt(1, profileid);
    		pstmt.setInt(2, competenceid);
    		
    		pstmt.executeUpdate();
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    		return false;
    	}
    	return true;
    }
    
    //Method to change competence name
    public boolean changeCompetenceName(int competenceId, String competenceName){
    	PreparedStatement pstmt = null;
    	String query = "UPDATE Competences SET name = ? WHERE id = ?";
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		pstmt.setString(1, competenceName);
    		pstmt.setInt(2, competenceId);
    		
    		pstmt.executeUpdate();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    		return false;
    	}
    	return true;
    }
    
    //Method to change competence description
    public boolean changeCompetenceDescription(int competenceId, String competenceDescription){
    	PreparedStatement pstmt = null;
    	String query = "UPDATE Competences SET description = ? WHERE id = ?";
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		pstmt.setString(1, competenceDescription);
    		pstmt.setInt(2, competenceId);
    		
    		pstmt.executeUpdate();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    		return false;
    	}
    	return true;
    }
    
    //Method to get all users
    public ArrayList<Persons> getUsers(){
    	ArrayList<Persons> userlist = new ArrayList<Persons>();
    	PreparedStatement pstmt = null;
    	String query = "SELECT * FROM Persons";
    	ResultSet rs = null;
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()){
    			int id = rs.getInt("id");
    			String firstname = rs.getString("firstname");
    			String lastname = rs.getString("lastname");
    			String userId = rs.getString("userid");
    			int profileId = rs.getInt("profile_id");
    			
    			userlist.add(new Persons(id, userId, firstname, lastname, profileId));
    		}
    	}
    	catch (SQLException e){
    		return userlist;
    	}
    	return userlist;
    }
   
    //Method to get all user competences for manager dashboard
    public ArrayList<PersonalCompetenceLevel> getAllUserCompetences(){
    	ArrayList<PersonalCompetenceLevel> usercomplist = new ArrayList<PersonalCompetenceLevel>();
    	PreparedStatement pstmt = null;
    	String query = "SELECT * FROM Personal_comp_level";
    	ResultSet rs = null;
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()){
    			int id = rs.getInt("id");
    			int profileId = rs.getInt("comp_id");
    			int level = rs.getInt("comp_level");
    			String userId = rs.getString("user_id");
    			
    			usercomplist.add(new PersonalCompetenceLevel(
    					id, profileId, level, userId));
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}
    	return usercomplist;
    }
    
    public boolean setAvailable(String date, String userid){  
    	
    	String dateString = date.toString();
    	
    	PreparedStatement pstmt = null;
    	String query = "UPDATE Person_available SET available = ?, available_date = ? WHERE user_id LIKE ?";
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		pstmt.setInt(1, 1);
    		pstmt.setString(2, dateString);
    		pstmt.setString(3, userid);
    		System.out.println(userid + dateString);
    		pstmt.executeUpdate();
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}
    	return true;
    }
    
    public ArrayList<Available> getAllAvailable(){
    	ArrayList<Available> list = new ArrayList<Available>();
    	
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String query = "SELECT * FROM Person_available";
    	
    	try {
    		pstmt = conn.prepareStatement(query);
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()){
    			int id = rs.getInt("id");
    			int available = rs.getInt("available");
    			String userId = rs.getString("user_id");
    			String dateString = rs.getString("available_date");
    			
    			list.add(new Available(id, available, userId, dateString));
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    public static void main(String[] args) throws SQLException{
    	DatabaseConnector dbc = new DatabaseConnector();
    	ArrayList<PersonalCompetenceLevel> arr = dbc.getUserCompetences("hli24213");
    	
    	for (int i = 0; i < arr.size(); i++){
    		System.out.println(arr.get(i).getCompetenceLevel() + arr.get(i).getPersonId() + arr.get(i).getCompetenceId());
    	}
    }
    
}
