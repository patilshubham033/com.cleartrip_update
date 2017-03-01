package POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage 
{
	@FindBy(id="modal_window")
	private WebElement iframe;
	@FindBy(id="username1")
	private WebElement emailId;
	@FindBy(id="mkt_sbpt")
	private WebElement checkBox;
	@FindBy(id="registerButton")
	private WebElement createAcc;
	@FindBy(id="password")
	private WebElement pwd;
	@FindBy(id="profile_title")
	private WebElement title;
	@FindBy(xpath="//input[@title='First name']")
	private WebElement firstName;
	@FindBy(xpath="//input[@title='Last name']")
	private WebElement lastName;
	@FindBy(id="mobile_number")
	private WebElement mobNumber;
	@FindBy(id="fb_xdm_frame_https")
	private WebElement iframe1;
	@FindBy(xpath="//button[text()='Create account']")
	private List<WebElement> crtAcc;
	@FindBy(xpath="//button[text()='Sign in']")
	private List<WebElement> signIn;
	
	public RegisterPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement iframe()
	{
		return iframe;
	}
	public void emailId(String email)
	{
		emailId.sendKeys(email);
	}
	public void checkBox()
	{
		checkBox.click();
	}
	public void createAcc() 
	{
		createAcc.click();
	}
	public void pwd(String password) 
	{
		pwd.sendKeys(password);
	}
	public WebElement title() 
	{
		return title;
	}
	public void firstName(String fN) 
	{
		firstName.sendKeys(fN);
	}
	public void lastName(String lN) 
	{
		lastName.sendKeys(lN);
	}
	public void mobNumber(String mobNo) 
	{
		mobNumber.sendKeys(mobNo);
	}
	public WebElement iframe1()
	{
		return iframe1;
	}
	public List<WebElement> crtAcc()
	{
		return crtAcc;
	}
	public List<WebElement> signIn()
	{
		return signIn;
	}

	}
