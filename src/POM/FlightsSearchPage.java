package POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightsSearchPage 
{
	@FindBy(xpath="//button[@class='booking fRight']")
	private List<WebElement> flights;
	@FindBy(xpath= "//button[@class='booking']")
	private List<WebElement> flights1;
	@FindBy (xpath ="//input[@id='insurance_confirm']")
	private WebElement insurance_confirm;
	@FindBy(xpath= "//input[@id='itineraryBtn']")
	private WebElement continue_to_payment;
	@FindBy(xpath="//input[@id='username']")
	private WebElement userName;
	@FindBy(xpath="//input[@id='LoginContinueBtn_1']")
	private WebElement continue1;
	@FindBy(id="AdultTitle1")
	private WebElement title;
	@FindBy(id="AdultFname1")
	private WebElement firstName;
	@FindBy(id="AdultLname1")
	private WebElement lastName;
	@FindBy(id="AdultDobDay1")
	private WebElement day;
	@FindBy(id= "AdultDobMonth1")
	private WebElement month;
	@FindBy(id="AdultDobYear1")
	private WebElement year;
	@FindBy(id="AdultPassport1")
	private WebElement passportNum;
	@FindBy(id = "mobileNumber")
	private WebElement mobNumber;
	@FindBy(id="travellerBtn")
	private WebElement continue_to_payment1;
	@FindBy(id="paymentSubmit")
	private WebElement makePayment;
	
	
	public FlightsSearchPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public List<WebElement> flights()
	{
		return (List<WebElement>) flights;
	}
	public List<WebElement> flights1()
	{
		return (List<WebElement>) flights1;
	}
	public void insurance_confirm() 
	{
		insurance_confirm.click();
	}
	public void continue_to_payment() 
	{
		continue_to_payment.click();
	}
	public void userName(String user_Name) 
	{
		userName.sendKeys(user_Name);
	}
	public void continue1() 
	{
		continue1.click();
	}
	public WebElement title() 
	{
		return title;
	}
	public void firstName(String fName) 
	{
		firstName.sendKeys(fName);
	}
	public void lastName(String lName) 
	{
		lastName.sendKeys(lName);
	}
	public WebElement day() 
	{
		return day;
	}
	public WebElement year() 
	{
		return year;
	}
	public WebElement month() 
	{
		return month;
	}
	
	public void passportNum(String passNum)
	{
		passportNum.sendKeys(passNum);
	}
	public void mobNumber(String mobNum)
	{
		mobNumber.sendKeys(mobNum);
	}
	public void continue_to_payment1() 
	{
		continue_to_payment1.click();
	}

	public WebElement makePayment() {
		
		return makePayment;
	}

}
