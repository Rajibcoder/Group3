package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPageAfterLogin {
	
	@FindBy(linkText = "Log out")
	private WebElement logout;

	@FindBy(xpath = "//strong[contains(@class,'poll-display-text')]")
	private WebElement votedone;
	
	public LandingPageAfterLogin(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String TheLogout() {
		return logout.getText();
	}
	
	public String TheText() {
		return votedone.getText();
	}
}
