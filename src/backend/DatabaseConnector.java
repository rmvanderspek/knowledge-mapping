package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    	
    	Persons person = (Persons) em.createQuery("SELECT p FROM Persons p WHERE p.user_id =:userId")
    												.setParameter("userId", userId)
    												.getSingleResult();
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

    	Profiles profile = (Profiles) em.createQuery("SELECT p FROM Profiles p WHERE p.prof_comp_table_id =:id")
    												.setParameter("id", id)
    												.getSingleResult();
    	tx.commit();
    	em.close();

    	return profile;
    }
    
    //Method to get profiles from user
    public List<Profiles> getUserProfiles(String userId){
    	List<Personal_profile> personalProfiles = getPersonalProfiles();
    	List<Profiles> personProfiles = new ArrayList<Profiles>();
    	Iterator<Personal_profile> i = personalProfiles.iterator();
    	while(i.hasNext()){
    		Personal_profile pp = (Personal_profile) i.next();
    		if(pp.getUserId().equals(userId)){
    			personProfiles.add(getProfile(pp.getProfileId()));
    		}
    	}
    	return personProfiles;
    }
    
    //Method to get list of all Profiles
    public List<Profiles> getProfiles(){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();

    	@SuppressWarnings("unchecked")
    	List<Profiles> profiles = (List<Profiles>) em.createQuery("SELECT p FROM Profiles p")
    												.getResultList();
    	tx.commit();
    	em.close();

    	return profiles;
    }
    
    //Method to get competences from user
    public List<Personal_comp_level> getUserCompetences(String userId){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();

    	@SuppressWarnings("unchecked")
    	List<Personal_comp_level> personalCopmetenceLevel = (List<Personal_comp_level>) em.createQuery("SELECT p FROM Personal_comp_level p WHERE p.user_id LIKE :id")
    												.setParameter("id", userId)
    												.getResultList();
    	tx.commit();
    	em.close();

    	return personalCopmetenceLevel;
    }
    
    //Method to get all profile competences
    public List<Profile_competence_table> getProfileCompetences(){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();

    	@SuppressWarnings("unchecked")
    	List<Profile_competence_table> profileCompetences = (List<Profile_competence_table>) em.createQuery("SELECT p FROM Profile_competence_table p")
    												.getResultList();
    	tx.commit();
    	em.close();
    	
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
    
    //Method for updating the competences in the database
    public boolean updateCompetences(ArrayList<SaveCompetences> list, String userid){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	
    	@SuppressWarnings("unchecked")
		List<Personal_comp_level> results = em.createQuery("SELECT p FROM Personal_comp_level p")
    															.getResultList();
    	for(Personal_comp_level pcl : results){
    		for(int i = 0; i < list.size(); i++){
    			if(((Personal_comp_level)pcl).getPersonId().equals(userid) && ((Personal_comp_level)pcl).getCompetenceId() == list.get(i).getId()){
        			((Personal_comp_level)pcl).setCompetenceLevel(list.get(i).getLevel());
        			em.persist(pcl);
        		}
    		}
    		
    	}
    	tx.commit();
    	em.close();
    	
    	return true;
    }
    
    //Method to add competence to user
    public boolean addCompetenceToUser(SaveCompetences saveCompetences, String userid){
    	Personal_comp_level pcl = new Personal_comp_level(saveCompetences.getId(), 0, userid);
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	
    	em.persist(pcl);
    	tx.commit();
    	em.close();
    	
    	return true;
    }
    
    //Method to set new competence to profile 'overige'
    public boolean addCompetenceToProfile(Profile_competence_table pc){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	
    	em.persist(pc);
    	tx.commit();
    	em.close();

    	return true;
    }
    
    //Method to change a competence profile
    public boolean changeCompetenceProfile(int competenceid, int profileid){

    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	Profile_competence_table pct = (Profile_competence_table) em.createQuery("SELECT p FROM Profile_competence_table p where p.competences =:competenceid")
    			.setParameter("competenceid", competenceid)
    			.getSingleResult();

    	pct.setProfileId(profileid);
    	em.persist(pct);
    	tx.commit();
    	em.close();

    	return true;
    }
    
    //Method to change competence name
    public boolean changeCompetenceName(int competenceId, String competenceName){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	Competences c = (Competences) em.createQuery("SELECT c FROM Competences c where c.id =:id")
    			.setParameter("id", competenceId)
    			.getSingleResult();

    	c.setName(competenceName);
    	em.persist(c);
    	tx.commit();
    	em.close();

    	return true;
    }
    
    //Method to change competence description
    public boolean changeCompetenceDescription(int competenceId, String competenceDescription){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	Competences c = (Competences) em.createQuery("SELECT c FROM Competences c where c.id =:id")
    			.setParameter("id", competenceId)
    			.getSingleResult();

    	c.setDescription(competenceDescription);
    	em.persist(c);
    	tx.commit();
    	em.close();

    	return true;
    }
    
    //Method to get all users
    public List<Persons> getUsers(){
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	
    	@SuppressWarnings("unchecked")
		List<Persons> userlist = (List<Persons>) em.createQuery("SELECT p FROM Persons p").getResultList();
    	tx.commit();
    	em.close();
    	
    	return userlist;
    }
   
    //Method to get all user competences for manager dashboard
    public List<Personal_comp_level> getAllUserCompetences(){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	
    	@SuppressWarnings("unchecked")
    	List<Personal_comp_level> usercomplist = (List<Personal_comp_level>) em.createQuery("SELECT p FROM Personal_comp_level p").getResultList();
    	tx.commit();
    	em.close();

    	return usercomplist;
    }
    
    public boolean setAvailable(String date, String userid){  
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	Person_available pa = (Person_available) em.createQuery("SELECT p FROM Person_available p where p.user_id LIKE :id")
    			.setParameter("id", userid)
    			.getSingleResult();

    	pa.setDateString(date.toString());
    	em.persist(pa);
    	tx.commit();
    	em.close();

    	return true;
    }
    
    public List<Person_available> getAllAvailable(){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("testDataBase");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	
    	@SuppressWarnings("unchecked")
    	List<Person_available> list = (List<Person_available>) em.createQuery("SELECT p FROM Person_available p").getResultList();
    	tx.commit();
    	em.close();
    	
    	return list;
    }
    
    public static void main(String[] args) throws SQLException{
    	DatabaseConnector dbc = new DatabaseConnector();
    	List<Personal_comp_level> arr = dbc.getUserCompetences("hli24213");
    	
    	for (int i = 0; i < arr.size(); i++){
    		System.out.println(arr.get(i).getCompetenceLevel() + arr.get(i).getPersonId() + arr.get(i).getCompetenceId());
    	}
    }
    
}
