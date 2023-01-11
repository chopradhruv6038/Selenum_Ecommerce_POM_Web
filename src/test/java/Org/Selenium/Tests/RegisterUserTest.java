package Org.Selenium.Tests;

import Org.Selenium.Base.BaseTest;
import Org.Selenium.Objects.RegistrationAndLoginDetails;
import Org.Selenium.Objects.RegistrationAndLoginDetails;
import Org.Selenium.Pages.AccountPage;
import Org.Selenium.Pages.HomePage;
import Org.Selenium.Utils.JacksonUtils;
import io.qameta.allure.Description;
import net.bytebuddy.build.Plugin;
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
        RegistrationAndLoginDetails registrationAndLoginDetails = new RegistrationAndLoginDetails();
        InputStream is = getClass().getClassLoader().getResourceAsStream("RegistrationDetails.json");
        registrationAndLoginDetails = JacksonUtils.deserializeJsonForRegistration(is, registrationAndLoginDetails);


        //AccountPage
        AccountPage accountPage = homePage.clickAccountLink();
        Assert.assertEquals(accountPage.verifyRegisterPageText(), "Register");
        accountPage.enterAllRegistrationDetails(registrationAndLoginDetails);
        accountPage.clickRegisterBtn();

        //Asserting the hello message on welcome.
        Assert.assertEquals(accountPage.verifyUserHelloMessage(),
                "Hello " + registrationAndLoginDetails.getEnterUserName() + " (not "
                        + registrationAndLoginDetails.getEnterUserName() + "?" + " Log out)");


    }


    @Description("This test case verifies that registered user should be able to login")
    @Test (priority = 1, description = "Not running this test case in parallel as user will first register and then login")
    public void userLogin() throws IOException {


        HomePage homePage = new HomePage(getDriver());
        homePage.loadUrl();

        //jackson using input stream to read the Json.
        RegistrationAndLoginDetails registrationAndLoginDetails = new RegistrationAndLoginDetails();
        InputStream is = getClass().getClassLoader().getResourceAsStream("RegistrationDetails.json");
        registrationAndLoginDetails  = JacksonUtils.deserializeJsonForRegistration(is, registrationAndLoginDetails);


        AccountPage accountPage = homePage.clickAccountLink();
        Assert.assertEquals(accountPage.verifyLoginPageText(), "Login");
        accountPage.enterUserNameAndPassword(registrationAndLoginDetails).clickLoginBtn();


        //Asserting the hello message on welcome.
        Assert.assertEquals(accountPage.verifyUserHelloMessage(),
                "Hello " + registrationAndLoginDetails.getEnterUserName() + " (not "
                        + registrationAndLoginDetails.getEnterUserName() + "?" + " Log out)");

    }


}
