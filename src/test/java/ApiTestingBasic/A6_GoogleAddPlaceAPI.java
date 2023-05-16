package ApiTestingBasic;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class A6_GoogleAddPlaceAPI extends A0_MyAPIData
{
	@Test
	public void GoogleAddPlaceAPI()
	{
        //Add place API----------------------------------------------
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String postResponse = given().queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		.body(pojoData())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server","Apache/2.4.41 (Ubuntu)")
		.extract().body().asString();

		//Creating JsonPath object to hold Json Data
		JsonPath keys = new JsonPath(postResponse );
		String place_id = keys.getString("place_id"); //place_id used as a variable
		System.out.println("Place_id used as a variable: " + place_id);
		
        //Get place API----------------------------------------------
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		Response getResponse = given().queryParam("key","qaclick123")
	    .queryParam("place_id", ""+place_id+"").expect().defaultParser(Parser.JSON)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().statusCode(200).header("Server", "Apache/2.4.41 (Ubuntu)")
		.extract().response();
		
		System.out.println("This is the extracted HTTPGet Request Raw Response: "  + getResponse);
		String getres = getResponse.asString(); // Converting asString in the response section
		System.out.println("This is the extracted HTTPGet Request Raw Response converted to String: "  + getres);
		
	}
}
