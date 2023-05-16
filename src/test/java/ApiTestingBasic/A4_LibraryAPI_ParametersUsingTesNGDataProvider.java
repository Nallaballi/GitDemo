package ApiTestingBasic;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class A4_LibraryAPI_ParametersUsingTesNGDataProvider
{
	@Test(dataProvider="BooksData")
	public void myLibraryAPI(String isbn,String aisle)
	{
		System.out.println("------------------------------post method---------------------------------");
		RestAssured.baseURI="https://rahulshettyacademy.com";
				
		String res = given().header("Content-Type","application/json")
		.body(A0_MyAPIData.dataLibrary(isbn,aisle)) 
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).body("Msg", equalTo("successfully added"))
		.extract().body().asString();

		System.out.println("--------------------------Post Response print------------------------------");
		System.out.println(res);
		JsonPath jp = new JsonPath(res);
		String id = jp.getString("ID");
		System.out.println(id);
	}
	
	@DataProvider(name="BooksData")
	public Object[][] dataProvider()
	{
		return new Object[][]  {{"opeosse","622093"},{"gdtesdc","788434"},{"gtynmji","425594"}};
	}
}
