package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pagefactory.LandingPageAfterLogin;
import pagefactory.LoginPage;


public class SharedSteps extends Tools{

	@Before
	public void setUp(Scenario scenario) {
		Driver.init();
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}

class Tools {
	protected static WebDriver driver;
}

class Driver extends Tools{
	protected static LoginPage loginpage;
	protected static LandingPageAfterLogin landingpageafterlogin;
	public static void init() {
		driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.get("https://demowebshop.tricentis.com/");
	    loginpage = new LoginPage(driver);
	    landingpageafterlogin = new LandingPageAfterLogin(driver);
}
}