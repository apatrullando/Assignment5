package test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Assignment 5!
 *
 */

@SpringBootApplication
@RestController
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
    
    @RequestMapping(value="/{name}", method = RequestMethod.GET)
    public JSONObject getValue(@PathVariable String name, ModelMap model){
    	
    	return getPerson(name);
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public JSONObject getList(ModelMap model){
    	return getList();
    }
    
    private JSONObject getPerson( String number ){
    	JSONParser parser = new JSONParser();
    	Object obj;
    	JSONObject person = null;
		try {
			obj = parser.parse(new FileReader("./database.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray people = (JSONArray) jsonObject.get( "people" );
			
			person = (JSONObject) people.get( Integer.valueOf(number) - 1 );
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		return person;
    	
    }
    
    private JSONObject getList(){
    	JSONParser parser = new JSONParser();
    	Object obj;
    	JSONObject jsonObject = null;
		try {
			obj = parser.parse(new FileReader("./database.json"));
			
			jsonObject = (JSONObject) obj;
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
    	
    }
    
    
    
}
