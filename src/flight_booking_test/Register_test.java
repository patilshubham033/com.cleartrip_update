package flight_booking_test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import POM.HomePage;
import POM.RegisterPage;

public class Register_test 
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
			}
		}
		return data;
	}
	
	public void homepage()
	{
		//open home page & go to register page
		HomePage homepage = new HomePage(driver);
		soft.assertTrue(driver.getTitle().contains("Cleartrip"));
		Reporter.log("Home Page Open", true);
		homepage.yourTrip();
		homepage.register();
	}
	
	//Register for valid data
	@Test(dataProvider= "dataProvider")
	public void register(String email, String pwd, String title1, String firstName, String lastName, String from, String to, String departOn, String returnOn, String passportNo, String adults1, String children1, String infants1, String class1, String mob) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		homepage();
		RegisterPage registerPage = new RegisterPage(driver);
		
		//switch to I_frame
		driver.switchTo().frame(registerPage.iframe());
		Reporter.log("Register Page Open", true);
		
		//fill all the details in registration page
		registerPage.emailId(email);
		registerPage.checkBox();
		registerPage.createAcc();
		
		// Enter the details
		registerPage.pwd(pwd);
		Select title = new Select(registerPage.title());
		title.selectByVisibleText(title1);
		registerPage.firstName(firstName);
		registerPage.lastName(lastName);
		registerPage.mobNumber(mob);
					
		//click on create account button
		registerPage.crtAcc().get(1).click();
		Reporter.log("successfully register", true);
		Thread.sleep(10000);
		driver.switchTo().defaultContent();
		verify();
	}
	
	//verify registration
	public void verify()
	{
		soft.assertEquals(driver.getTitle(), "Your trips");
		Reporter.log("Home Page dislpayed", true);
	}
	
	//close the browser 
	@AfterMethod
	public void logout()
	{
		driver.close();
	}
}
