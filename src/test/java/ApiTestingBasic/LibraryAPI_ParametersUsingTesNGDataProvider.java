package ApiTestingBasic;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class LibraryAPI_ParametersUsingTesNGDataProvider
{
	@Test
	public void myLibraryAPI()
	{
		System.out.println("------------------------------post method---------------------------------");
		RestAssured.baseURI="https://rahulshettyacademy.com";
				
		String res = given().header("Content-Type","application/json")
		.body(MyAPIData.dataProvider("","")) 
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).body("Msg", equalTo("successfully added"))
		.extract().body().asString();

		System.out.println("--------------------------Post Response print------------------------------");
		System.out.println(res);
		JsonPath jp = new JsonPath(res);
		String id = jp.getString("ID");
		System.out.println(id);
	}
}
