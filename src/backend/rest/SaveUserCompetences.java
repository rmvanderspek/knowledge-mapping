package backend.rest;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import backend.DatabaseConnector;
import backend.SaveCompetences;

@Path("saveusercompetences")
public class SaveUserCompetences{
	@QueryParam("userid") String userId;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveUserCompetences(String jseon) {
		System.out.println(jseon);
		JSONObject obj;
		try {
			obj = new JSONObject(jseon);
		
			ArrayList<JSONObject> list = new ArrayList<JSONObject>();     
			ArrayList<SaveCompetences> saveList = new ArrayList<SaveCompetences>();
			JSONArray jsonArray = obj.getJSONArray("data"); 
			
			   int len = jsonArray.length();
			   
			   for (int i=0;i<len;i++){ 
				   
			    list.add(new JSONObject(jsonArray.get(i).toString()));
			    
			    System.out.println(list.get(i));
			    System.out.println(list.get(i).getString("name"));
			    saveList.add(new SaveCompetences(list.get(i).getString("name"), 
			    		list.get(i).getString("description"), 
			    		list.get(i).getInt("level"), 
			    		list.get(i).getInt("id")));
			   };
			   
			System.out.println(saveList); 
			
			
			
			DatabaseConnector db = new DatabaseConnector();
			System.out.println("De db connector: " + db);
			System.out.println(jseon);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200)
				.entity("email was successfully added!")
				.build();
	}
}