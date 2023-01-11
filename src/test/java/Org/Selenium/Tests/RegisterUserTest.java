package Org.Selenium.Tests;

import Org.Selenium.Base.BaseTest;
import Org.Selenium.Objects.RegistrationDetails;
import Org.Selenium.Pages.AccountPage;
import Org.Selenium.Pages.HomePage;
import Org.Selenium.Utils.JacksonUtils;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.io.InputStream;

public class RegisterUserTest extends BaseTest {


    @Description("This Test case verifies that a new user is able to register successfully and get a welcome message as per the entered usernName")
    @Test
    public void userRegistration() throws IOException {

        //HomePage
        HomePage homePage = new HomePage(getDriver());
        homePage.loadUrl();

        //Jackson for Json object to Java object
        RegistrationDetails registrationDetails = new RegistrationDetails();
        InputStream is = getClass().getClassLoader().getResourceAsStream("RegistrationDetails.json");
        registrationDetails = JacksonUtils.deserializeJsonForRegistration(is, registrationDetails);


        //AccountPage
        AccountPage accountPage = homePage.clickAccountLink();
        Assert.assertEquals(accountPage.verifyRegisterPageText(), "Register");
        accountPage.enterAllRegistrationDetails(registrationDetails);
        accountPage.clickRegisterBtn();
        //Asserting the hello message on welcome.
        Assert.assertEquals(accountPage.verifyUserHelloMessage(),
                "Hello " + registrationDetails.getEnterUserName() + " (not "
                        + registrationDetails.getEnterUserName() + "?" + " Log out)");



    }


}
