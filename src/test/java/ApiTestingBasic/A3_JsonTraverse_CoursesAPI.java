package ApiTestingBasic;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class A3_JsonTraverse_CoursesAPI extends A0_MyAPIData
{
	@Test
	public void myCourses()
	{
		//Print No of courses returned by API
		JsonPath js = new JsonPath(dataCourses());
		int count = js.getInt("courses.size");
		System.out.println(count);
		
		//Print Purchase Amount
		int pamt = js.getInt("dashboard.purchaseAmount");
		System.out.println(pamt);
		
		// Print Title of the first course
		String fTitle = js.getString("courses[0].title");
		System.out.println(fTitle );
		
		//Print All course titles and their respective prices
		for(int i=0;i<count;i++)
		{
			String allTitles = js.getString("courses["+i+"].title");
			int allPrices = js.getInt("courses["+i+"].price");
			System.out.println(allTitles +" : "+allPrices);
		}
		
		//Print no of copies sold by RPA Course
		for(int i = 0;i<count;i++)
		{
			String courseTitle = js.getString("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA"))
			{
				String Title = js.getString("courses["+i+"].title");
				String Copies = js.getString("courses["+i+"].copies");
				System.out.println("Copies sold by " + Title + ":" + Copies );
				break;
			}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		int Total = 0;
		for(int i =0;i<count;i++)
		{
			int myPrice = js.getInt("courses["+i+"].price");
			int myCopies = js.getInt("courses["+i+"].copies");
			int sum = myPrice * myCopies;
			Total = Total + sum;
		}
		System.out.println(Total);
		Assert.assertEquals(pamt, Total);
	}
}

