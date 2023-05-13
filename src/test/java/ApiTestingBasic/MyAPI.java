package ApiTestingBasic;

import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MyAPI extends MyAPIData
{
    @Test
    public void basicAPI()
    {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        
        //Add place API----------------------------------------------
        String postResponse = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
        .body(getBasicAPIData())
        .when().post("/maps/api/place/add/json")
        .then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server","Apache/2.4.41 (Ubuntu)")
        .extract().body().asString();
        System.out.println(postResponse);

        //Creating JsonPath object to hold Json Data
        JsonPath keys = new JsonPath(postResponse );
        String place_id = keys.getString("place_id"); //place_id used as a variable
		
        /*
		 * String status = keys.getString("status");
		 * String scope = keys.getString("scope");
		 * System.out.println(status+" "+ place_id+" "+scope);
		 */
        
        //Finding size of the JsonPath object
        int count = keys.getInt("size()");
        System.out.println("Count of the JasonPath object: " + count);
        
		/*
		 * //Should not Iterate JsonPath object using For-Loop. Its not an Array
		 * //ForLoop gives ith position of an Array. But JsonPath Object is in a MAP structure
		 * //If used will get all NULL values
		 * JsonPath jsonPath = new JsonPath(postBody);
		 * int c = jsonPath.getInt("size()");
		 * for (int i = 0; i < c; i++)
		 * { 
		 * 	String nstatus = jsonPath.getString("[" + i + "].status");
		 * 	String nplace_id = jsonPath.getString("[" + i + "].place_id");
		 * 	String nscope = jsonPath.getString("[" + i + "].scope");
		 * 	String nreference = jsonPath.getString("[" + i + "].reference");
		 * 	String nid = jsonPath.getString("[" + i + "].id");
		 * 	System.out.println(nstatus + " " + nplace_id + " " + nscope + " " + nreference + " " + nid);
		 * }
		 */

        //Iterate JsonPath object using ForEach-Loop
        //JsonPath holds all data in key-value pairing in String format. Add it to a MAP structure
        //JsonPath library has .getMap() method that holds key-value paired data. That's a Map structure
        //JsonObject.getMap loads Json response to variable a. It returns a Map reference
        //Map reference is loaded into entrySet(). Which is iterated to access data
        //entrySet() is a method in the Map interface in Java that returns a Set of all the key-value pairs
        //The entrySet() method is often used to iterate over the key-value pairs of a map using a for-each loop.
        Map<Object, Object> a = keys.getMap("");
        for (Map.Entry<Object, Object> entry : a.entrySet())
        {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        //Update place API----------------------------------------------------------
        String newAddress = "70 Summer walk, USA";
        given().queryParam("key", "qaclick123").header("Content-Type","application/json")
        .body("{\"place_id\":\""+place_id+"\",\r\n"
        		+ "\"address\":\""+newAddress+"\",\r\n"
        		+ "\"key\":\"qaclick123\"\r\n"
        		+ "}\r\n"
        		+ "")
        .when().put("/maps/api/place/update/json")
        .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
 
        //Get place API----------------------------------------------------------
        String getResponse = given().queryParam("key", "qaclick123").queryParam("place_id",place_id)
        .when().get("/maps/api/place/get/json")
        .then().assertThat().statusCode(200).extract().body().asString();
        System.out.println(getResponse);
        
        JsonPath jp = new JsonPath(getResponse);
        String newaddress = jp.getString("address");
        System.out.println(newaddress);
        Assert.assertEquals(newAddress, newaddress);
		Assert.assertTrue(newAddress.equals(newaddress));
		
      //Iterate JsonPath object using ForEach-Loop to print Get Response
		Map<Object, Object> res = jp.getMap("");
		for(Entry<Object, Object> r:res.entrySet())
		{
			System.out.println(r.getKey() + ":" + r.getValue());
		}
    }
}