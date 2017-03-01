package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	@FindBy(id="userAccountLink")
	private WebElement yourTrip;
	@FindBy(id="SignIn")
	private WebElement signIn;
	@FindBy(xpath="//a[text()='Register']")
	private WebElement register;
	@FindBy(xpath="//a[text()='Flights']")
	private WebElement flight;
	@FindBy(id="global_signout")
	private WebElement signOut;
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void yourTrip()
	{
		yourTrip.click();
	}
	public void signIn()
	{
		signIn.click();
	}
	public void register()
	{
		register.click();
	}
	public void flight()
	{
		flight.click();
	}

	public void signOut() 
	{
		signOut.click();
	}
}
