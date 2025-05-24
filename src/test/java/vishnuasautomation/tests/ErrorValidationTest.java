package vishnuasautomation.tests;

import java.io.IOException;
import vishnuasautomation.TestComponents.Retry;


import org.testng.Assert;
import org.testng.annotations.Test;

import vishnuasautomation.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {
	
	@Test(groups={"errorhandling"})
	public void errorValidation() throws IOException, InterruptedException {

		landingpage.loginApplication("asvishnukaruvannur7@gmail.com", "Vi@123456");
		String toasterMessage=landingpage.getToasterMessage();
		Assert.assertEquals(toasterMessage, "Incorrect email or password.");
	}

}
//,retryAnalyzer=Retry.class