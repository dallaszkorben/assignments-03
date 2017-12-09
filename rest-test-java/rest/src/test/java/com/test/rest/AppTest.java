package com.test.rest;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class AppTest extends TestCase{
	private static final String REST_SERVICE_URL = "http://jsonplaceholder.typicode.com";
	private static final String USER_ID = "1";
	private static final String ENDPOINT_USERS = "users";
	private static final String ENDPOINT_POSTS = "posts";

	public AppTest( String testName ){
        super( testName );
    }

    public static Test suite(){
        return new TestSuite( AppTest.class );
    }

    private String getJSONString( String url, String endpoint, String parameters ) {
    	Client client = Client.create();

		WebResource webResource = client.resource( url + "/" + endpoint + parameters);

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		return response.getEntity(String.class);
    }
    
    public JSONObject postJSONObject( String url, String endpoint, JSONObject input ){
    	
    	Client client = Client.create();
    	
    	WebResource webResource = client.resource( url + "/" + endpoint );
    	
    	ClientResponse response = webResource.type("application/json")
    			.accept("application/json")
    			.post(ClientResponse.class, input.toString());

    	if (response.getStatus() != 201) {
    		throw new RuntimeException("Failed : HTTP error code : "
    				+ response.getStatus());
    	}

    	return new JSONObject( response.getEntity(String.class) );
    	
    }
    
    private JSONObject getJSONObject( String url, String endpoint, String parameters ) {
    	JSONObject jsonObj = new JSONObject( getJSONString( url, endpoint, parameters ) );
		return jsonObj;    	
    }

    private JSONArray getJSONArray( String url, String endpoint, String parameters ) {
    	JSONArray jsonArray = new JSONArray( getJSONString( url, endpoint, parameters ) );
		return jsonArray;    	    	
    }

    
    public void test01EmailCheck(){

    	JSONObject jsonRespond = getJSONObject( REST_SERVICE_URL, ENDPOINT_USERS + "/" + USER_ID, "" );
    	
    	JSONObject addressJSON = jsonRespond.getJSONObject( "address" );
    	System.out.println( addressJSON.get("street") );
    	System.out.println( addressJSON.get("suite") );
    	System.out.println( addressJSON.get("city") );
    	System.out.println( addressJSON.get("zipcode") );
    	
		String email = jsonRespond.getString("email");
		
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}		
    	
        assertTrue( result );
    }
    
    public void test02IdCheck(){
    	JSONArray jsonArrayRespond = getJSONArray( REST_SERVICE_URL, ENDPOINT_POSTS, "?userId=" + USER_ID );
    	Iterator<Object> jsonIterator = jsonArrayRespond.iterator();
    	
    	while( jsonIterator.hasNext() ){
            JSONObject jsonObject = (JSONObject) jsonIterator.next();
            	
            assertTrue( jsonObject.getInt( "id") > 0 );
            assertFalse( jsonObject.getString("title").isEmpty() );
            assertFalse( jsonObject.getString("body").isEmpty() );

        }
    }
    
    public void test03IdCheck(){
    	final int RET_ID = 101;
    	final String TITLE = "mytitle";
    	final String BODY = "mybody";    			
    	
    	JSONObject input = new JSONObject();
    	input.put( "userId", USER_ID );
    	input.put( "title", TITLE );
    	input.put( "body", BODY );

    	JSONObject result = postJSONObject(REST_SERVICE_URL, ENDPOINT_POSTS, input);

    	assertTrue( result.getInt( "id") == RET_ID );
    	assertTrue( String.valueOf( result.getInt( "userId") ).equals( USER_ID ) );
    	assertTrue( result.getString( "title").equals( TITLE ) );
    	assertTrue( result.getString( "body").equals( BODY ) );
    	
    	
    }
}
