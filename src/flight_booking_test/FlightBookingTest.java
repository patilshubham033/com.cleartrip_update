package flight_booking_test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import POM.FlightsSearchPage;
import POM.HomePage;
import POM.JourneyDetails;

public class FlightBookingTest 
{
	WebDriver driver;
	String path = "C:\\Users\\sai\\git\\ClearTrip\\com.cleartrip\\exel\\cleartrip.xlsx";
	Workbook book;
	Sheet sheet;
	SoftAssert soft = new SoftAssert();
	
	
	@BeforeMethod
	public void setup()
	{
		//open the browser
		driver = new FirefoxDriver();
		
		/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\sai\\git\\ClearTrip\\com.cleartrip\\exe file\\chromedriver.exe");
		driver = new ChromeDriver();*/
		
		/*System.setProperty("webdriver.ie.driver", "C:\\Users\\sai\\git\\ClearTrip\\com.cleartrip\\exe file\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();*/
		
		driver.get("https://www.cleartrip.com/");
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@DataProvider
	public Object[][] dataProvider() throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		// data from excel sheet
		FileInputStream file = new FileInputStream(path);
		book = WorkbookFactory.create(file);
		sheet = book.getSheet("Sheet1");
		Object[][] data = new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i = 0; i< sheet.getLastRowNum(); i++)
		{
			for(int k = 0; k< sheet.getRow(0).getLastCellNum(); k++)
			{
					data[i][k] = sheet.getRow(i+1).getCell(k).toString();
					System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
	public void test()
	{
		//open home page & go to flights page
		HomePage homePage = new HomePage(driver);
		homePage.flight();
		soft.equals(driver.getTitle().contains("Book Cheap Air Tickets"));
		Reporter.log("Flight page display", true);
	}
	
	public void journeyDetails(String from, String to, String departOn, String returnOn, String adults1, String children1, String infants1, String class1) throws InterruptedException
	{
		test();
		// enter journey details
		JourneyDetails journeyDetails = new JourneyDetails(driver);
		journeyDetails.roundTrip();
		Reporter.log("Round trip option selected", true);
		journeyDetails.from(from);
		journeyDetails.suggestions();
		journeyDetails.to(to);
		journeyDetails.suggestions();
		
		//enter journey date
		journeyDetails.departOn(departOn);
		journeyDetails.returnOn(returnOn);
		Select adults = new Select(journeyDetails.adults());
		adults.selectByVisibleText(adults1);
		Select children = new Select(journeyDetails.children());
		children.selectByVisibleText(children1);
		Select infants = new Select(journeyDetails.infants());
		infants.selectByVisibleText(infants1);
		journeyDetails.moreOption();
		Select select = new Select(journeyDetails.classOfTravel());
		
		// select the class
		select.selectByVisibleText(class1);
		journeyDetails.searchFlights();
		Reporter.log("Trip information entered", true);
		Thread.sleep(10000);
	}
	
	@Test(dataProvider= "dataProvider")
	public void booking(String email, String pwd, String title1, String firstName, String lastName, String from, String to, String departOn, String returnOn, String passportNo, String adults1, String children1, String infants1, String class1, String mob,String day1, String month1, String year1) throws InterruptedException
	{
		journeyDetails(from, to, departOn, returnOn, adults1, children1, infants1, class1);
		Thread.sleep(10000);
		//flight search page open
		FlightsSearchPage flightSearchPage = new FlightsSearchPage(driver);
		
		// check flight details International or national
		if(flightSearchPage.flights().get(1).isDisplayed())
		{
			Reporter.log("National Flight list is open", true);
			
			//select the flight
			Reporter.log(flightSearchPage.flights().get(1).getText(), true);
			flightSearchPage.flights().get(1).click();
			flights(from, departOn, email, title1, firstName, lastName, mob);
			
			// continue to payment page
			flightSearchPage.continue_to_payment1();
			Thread.sleep(10000);
		}
		else
		{
			//select the flight
			Reporter.log(flightSearchPage.flights1().get(0).getText(), true);
			flightSearchPage.flights1().get(0).click();
			flights(from, departOn, email, title1, firstName, lastName, mob);
			details(passportNo, day1, month1, year1);
			
			// continue to payment page
			flightSearchPage.continue_to_payment1();
			Thread.sleep(10000);
		}
		payment();
		
	}
	public void flights(String from, String to, String email, String title1, String firstName, String lastName, String mob)
	{
		FlightsSearchPage flightSearchPage = new FlightsSearchPage(driver);
		
		Reporter.log("Journey from "+from+" to "+to+ " selected ", true);
		flightSearchPage.insurance_confirm();
		
		// go to the payment page
		flightSearchPage.continue_to_payment();
		flightSearchPage.userName(email);
		flightSearchPage.continue1();
		
		// enter personal details
		Select title = new Select(flightSearchPage.title());
		title.selectByVisibleText(title1);
		flightSearchPage.firstName(firstName);
		flightSearchPage.lastName(lastName);
		flightSearchPage.mobNumber(mob);
	}
	public void details(String passportNo, String day1, String month1, String year1)
	{
		FlightsSearchPage flightSearchPage = new FlightsSearchPage(driver);
		// enter DOB & passport number
		Select day = new Select(flightSearchPage.day());
		day.selectByValue(day1);
		Select month = new Select(flightSearchPage.month());
		month.selectByValue(month1);
		Select year = new Select(flightSearchPage.year());
		year.selectByValue(year1);
		flightSearchPage.passportNum(passportNo);
		Reporter.log("Passport number is accepted", true);
	}
	
	public void payment()
	{
		// check whether payment page is display or not
		FlightsSearchPage flightSearchPage = new FlightsSearchPage(driver);		
		if(flightSearchPage.makePayment().isDisplayed())
		{
			Reporter.log("payment page display", true);
		}
	}
	
	// close the driver
	@AfterMethod
	public void close()
	{
		driver.close();
	}
}
