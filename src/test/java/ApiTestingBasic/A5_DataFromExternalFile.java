package ApiTestingBasic;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;



public class A5_DataFromExternalFile extends A0_MyAPIData
{
	@Test
	public void loadSimple() throws IOException
	{
		RestAssured.baseURI=("https://rahulshettyacademy.com");

		String rep = given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(GenerateStringFromResource("C:\\Users\\nalla\\Documents\\AddData.json"))
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP")).extract().response().asString();
		System.out.println(rep);
		
		JsonPath js = new JsonPath(rep);
		String placeid = js.get("place_id");
		System.out.println(placeid);

		//--------------------------------------------------------------------------------------------
		
		String res = given().param("key","qaclick123").and().param("place_id", placeid)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println(res);
		
		JsonPath jp = new JsonPath(res);
		String Name = jp.get("name");
		String Address = jp.get("address");
		System.out.println(Name + " "+Address);
	}
}
