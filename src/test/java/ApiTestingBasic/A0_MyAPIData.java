package ApiTestingBasic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class A0_MyAPIData
{
	A7_POJOGoogleInfo locationData = new A7_POJOGoogleInfo();
	
	public String getBasicAPIData()
	{
		String data = "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362},\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}" ;
		return data;
	}
	
	public String dataCourses()
	{
		String data = "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": \r\n"
				+ "{\"purchaseAmount\": 910,\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"},\r\n"
				+ "\r\n"
				+ "\"courses\": \r\n"
				+ "[\r\n"
				+ "\r\n"
				+ "{\"title\": \"Selenium Python\",\r\n"
				+ "\"price\": 50,\r\n"
				+ "\"copies\": 6},\r\n"
				+ "\r\n"
				+ "{\"title\": \"Cypress\",\r\n"
				+ "\"price\": 40,\r\n"
				+ "\"copies\": 4},\r\n"
				+ "\r\n"
				+ "{\"title\": \"RPA\",\r\n"
				+ "\"price\": 45,\r\n"
				+ "\"copies\": 10}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
		return data;
	}
	
	public static String dataLibrary(String isbn,String aisle)
	{
		String data = "{\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"James Jones\"\r\n"
				+ "}\r\n"
				+ "";
		return data;
	}
	
	public String GenerateStringFromResource(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	public A7_POJOGoogleInfo pojoData()
	{
		locationData.setAccuracy(50);
		locationData.setName("Frontline house");
		locationData.setPhone_number("(+91) 983 893 3937");
		locationData.setAddress("29, side layout, cohen 09");
		locationData.setWebsite("https://rahulshettyacademy.com");
		locationData.setLanguage("French-IN");
		
		List<String> types =new ArrayList<String>();
		types.add("shoe park");
		types.add("shop"); locationData.setTypes(types);
		
		A8_POJOLocationInfo location = new A8_POJOLocationInfo();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		locationData.setLocation(location);
		
		return locationData; //Must return Object
	}
}