package stepDefinition;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.ReadExcel;


public class ValidateCommunityDefs extends Driver{
	
	@Given("a user is in the landing page")
	public void a_user_is_in_the_landing_page() {
		assertTrue(driver.getTitle().equals("Demo Web Shop"));
	}
	
	@When("click on poll and try to vote")
	public void click_on_poll_and_try_to_vote() {
	    loginpage.tryVote();
	}
	
	@When("found register users can vote")
	public void found_register_users_can_vote() throws InterruptedException {
	    String Expected = "Only registered users can vote.";
	    String Actual = loginpage.failedvote();
	    Assert.assertEquals(Expected, Actual);
	}

	@When("he clicks on Login")
	public void he_clicks_on_login() {
	    loginpage.getlogin();
	}

	@When("entes Email and Password")
	public void entes_email_and_password() throws IOException {
		String[][] data = ReadExcel.getData("C:\\Users\\Rajib\\eclipse-workspace\\DemoWebShop\\src\\test\\resources\\TestData.xlsx", "Sheet1");	
		for(int i=1;i<data.length;i++)
		{
			try {
				String username = data[i][0];
				String password = data[i][1];
				loginpage.enter(username, password);
			}
			catch (Exception e) {
				System.out.println("fail");
			}
		}
	}

	@When("click on Login")
	public void click_on_login() {
	    loginpage.clicklogin();
	}

	@When("login success")
	public void login_success() throws IOException {
		String expected = "Log out";
		String actual = landingpageafterlogin.TheLogout();
		Assert.assertEquals(expected, actual);
		String[][] data = ReadExcel.getData("C:\\Users\\Rajib\\eclipse-workspace\\DemoWebShop\\src\\test\\resources\\TestData.xlsx", "Sheet1");	
		for(int i=1; i<data.length; i++) {
		    try {
		    	String loginStatus = "Success";
		    	data[i][2] = loginStatus;
		    }
		    catch (Exception e) {
		        String loginStatus = "Fail";
		        data[i][2] = loginStatus; 
		    }
		    }
	}
	
	@When("click on poll and vote")
	public void click_on_poll_and_vote() {
		try {
			loginpage.tryVote();
		} catch (Exception e) {
			String Expected = "Do you like nopCommerce?";
		    String Actual = landingpageafterlogin.TheText();
		    Assert.assertEquals(Expected, Actual);
		}
	}

	@Then("he should not able to vote again")
	public void he_should_not_able_to_vote_again() {
		try {
			loginpage.tryVote();
		} catch (Exception e) {
			String Expected = "Do you like nopCommerce?";
		    String Actual = landingpageafterlogin.TheText();
		    Assert.assertEquals(Expected, Actual);
		}
		
	}
	
}
