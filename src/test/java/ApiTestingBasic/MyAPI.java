package ApiTestingBasic;

import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;

public class MyAPI extends MyAPIData
{
    @Test
    public void myPrint()
    {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String postBody = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
        .body(getBasicAPIData())
        .when().post("/maps/api/place/add/json")
        .then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server","Apache/2.4.41 (Ubuntu)")
        .extract().body().asString();
        System.out.println(postBody);

        JsonPath keys = new JsonPath(postBody);
        String status = keys.getString("status");
        String place_id = keys.getString("place_id");
        String scope = keys.getString("scope");
        System.out.println(status+" "+ place_id+" "+scope);
        
        //JsonPath library has .getMap() method that holds key-value data in a map object
        //keys.getMap loads Json response to variable a. It returns a Map
        //Map a is loaded into entrySet(). Which is iterated
        Map<Object, Object> a = keys.getMap("");
        for (Map.Entry<Object, Object> entry : a.entrySet())
        {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        
        //entrySet() is a method in the Map interface in Java that returns a Set of all the key-value pairs in the map.
        //The entrySet() method is often used to iterate over the key-value pairs of a map using a for-each loop.
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 2);
        map.put("orange", 3);
        map.put("banana", 1);

        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}