package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JourneyDetails 
{
	@FindBy(id="RoundTrip")
	private WebElement roundTrip;
	@FindBy(id="FromTag")
	private WebElement from;
	@FindBy(xpath="//a[@class='uiSelected']")
	private WebElement suggestions;
	@FindBy(id="ToTag")
	private WebElement to;
	@FindBy(id="DepartDate")
	private WebElement departOn;
	@FindBy(id="ReturnDate")
	private WebElement returnOn;
	@FindBy(id="Adults")
	private WebElement adults;
	@FindBy(id="Childrens")
	private WebElement children;
	@FindBy(id="Infants")
	private WebElement infants;
	@FindBy(id="MoreOptionsLink")
	private WebElement moreOption;
	@FindBy(id="Class")
	private WebElement classOfTravel;
	@FindBy(id="AirlineAutocomplete")
	private WebElement preferredAirline ;
	@FindBy(xpath="//input[@id='SearchBtn']")
	private WebElement searchFlights ;
	
	public JourneyDetails(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void roundTrip()
	{
		roundTrip.click();
	}
	public void from(String From )
	{
		from.sendKeys(From);
	}
	public void suggestions() 
	{
		suggestions.click();
	}
	public void to(String To)
	{
		to.sendKeys(To);
	}
	public void departOn(String Depart)
	{
		departOn.sendKeys(Depart);
	}
	public void returnOn(String Return)
	{
		returnOn.sendKeys(Return);
	}
	public WebElement adults()
	{
		return adults;
	}
	public WebElement children()
	{
		return children;
	}
	public WebElement infants()
	{
		return infants;
	}
	public void moreOption()
	{
		moreOption.click();
	}
	public WebElement classOfTravel()
	{
		return classOfTravel;
	}
	public void preferredAirline(String PreferredAirline)
	{
		preferredAirline.sendKeys(PreferredAirline);
	}
	public void searchFlights()
	{
		searchFlights.click();
	}
}
