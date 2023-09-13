package pagefactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriverWait wait;
	
	@FindBy(id = "pollanswers-1")
	private WebElement Excellent;
	
	@FindBy(id = "vote-poll-1")
	private WebElement vote;
	
	@FindBy(id = "block-poll-vote-error-1")
	private WebElement cannotvote;

	@FindBy(linkText = "Log in")
	private WebElement login;
	
	@FindBy(id="Email")
	private WebElement Email;
	
	@FindBy(id="Password")
	private WebElement Password;
	
	@FindBy(xpath="//input[contains(@class,'button-1 login-button')]")
	private WebElement loginbtn;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}
	
	public void tryVote() {
		Excellent.click();
		vote.click();
	}
	
	public String failedvote() throws InterruptedException {
		Thread.sleep(1000);
//		wait.until(ExpectedConditions.visibilityOf(cannotvote));
		return cannotvote.getText();
	}
	
	public void getlogin() {
		login.click();
	}
	
	public void enter(String email,String password) {
		Email.sendKeys(email);
		Password.sendKeys(password);
	}
	
	public void clicklogin() {
		loginbtn.click();
	}
}
