package Org.Selenium.Tests;

import Org.Selenium.Base.BaseTest;
import Org.Selenium.Objects.NonRegisteredUserDetails;
import Org.Selenium.Objects.RegistrationAndLoginDetails;
import Org.Selenium.Pages.AccountDetailsPage;
import Org.Selenium.Pages.AccountPage;
import Org.Selenium.Pages.HomePage;
import Org.Selenium.Utils.JacksonUtils;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class RegisterLoginLogoutUserTest extends BaseTest {


    @Description("This Test case verifies that a new user is able to register successfully and get a welcome message as per the entered userName")
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


    @Description("This test case verifies that registered user should be able to login successfully and view and verify account details")
    @Test(priority = 1)
    public void userLoginVerification() throws IOException {


        HomePage homePage = new HomePage(getDriver());
        homePage.loadUrl();

        //jackson using input stream to read the Json.
        RegistrationAndLoginDetails registrationAndLoginDetails = new RegistrationAndLoginDetails();
        InputStream is = getClass().getClassLoader().getResourceAsStream("RegistrationDetails.json");
        registrationAndLoginDetails = JacksonUtils.deserializeJsonForRegistration(is, registrationAndLoginDetails);


        AccountPage accountPage = homePage.clickAccountLink();
        Assert.assertEquals(accountPage.verifyLoginPageText(), "Login");
        accountPage.enterUserNameAndPassword(registrationAndLoginDetails).clickLoginBtn();


        //Asserting the hello message on welcome.
        Assert.assertEquals(accountPage.verifyUserHelloMessage(),
                "Hello " + registrationAndLoginDetails.getEnterUserName() + " (not "
                        + registrationAndLoginDetails.getEnterUserName() + "?" + " Log out)");

        AccountDetailsPage accountDetailsPage = accountPage.clickAccountDetailsLink();
        Assert.assertEquals(accountDetailsPage.getDisplayName(), registrationAndLoginDetails.getEnterUserName());
        Assert.assertEquals(accountDetailsPage.getEmailDetails(), registrationAndLoginDetails.enterEmailReg());


    }

    @Description("This test case verifies that invalid or non registered user should not be able to login and must get an error")
    @Test
    public void invalidUserLoginVerification() throws IOException {

        //HomePage
        HomePage homePage = new HomePage(getDriver());
        homePage.loadUrl();

        NonRegisteredUserDetails nonRegisteredUserDetails = new NonRegisteredUserDetails();
        InputStream is = getClass().getClassLoader().getResourceAsStream("InvalidUser.json");
        nonRegisteredUserDetails = JacksonUtils.deserializeJsonForNonRegisteredUser(is, nonRegisteredUserDetails);

        AccountPage accountPage = homePage.clickAccountLink();
        Assert.assertEquals(accountPage.verifyLoginPageText(), "Login");
        accountPage.enterNonRegisteredUserNameAndPassword(nonRegisteredUserDetails).clickLoginBtn();

        Assert.assertEquals(accountPage.verifyNonRegisteredUserErrorMsg(),
                "Error: " + "The username " + nonRegisteredUserDetails.getEnterUserName() + " is not registered on this site. " + "If you are unsure of your username, " + "try your email address instead.");

    }

    @Description("This test verifies that logged in user is able to logout successfully")
    @Test
    public void logoutVerification() throws IOException {

        HomePage homePage = new HomePage(getDriver());
        homePage.loadUrl();

        //jackson using input stream to read the Json.
        RegistrationAndLoginDetails registrationAndLoginDetails = new RegistrationAndLoginDetails();
        InputStream is = getClass().getClassLoader().getResourceAsStream("RegistrationDetails.json");
        registrationAndLoginDetails = JacksonUtils.deserializeJsonForRegistration(is, registrationAndLoginDetails);


        AccountPage accountPage = homePage.clickAccountLink();
        accountPage.enterUserNameAndPassword(registrationAndLoginDetails).clickLoginBtn();

        //logging out and asserting.
        accountPage.clickLogoutBtn();
        Assert.assertEquals(accountPage.verifyLoginPageText(), "Logins");


    }


}
