package Org.Selenium.Pages;

import Org.Selenium.Base.BasePage;
import Org.Selenium.Objects.NonRegisteredUserDetails;
import Org.Selenium.Objects.RegistrationAndLoginDetails;
import Org.Selenium.Objects.RegistrationAndLoginDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    By registerText = By.cssSelector("div[class='u-column2 col-2'] h2");
    By userNameReg = By.cssSelector("#reg_username");
    By emailReg = By.cssSelector("#reg_email");
    By passwordReg = By.cssSelector("#reg_password");
    By registerBtn = By.cssSelector("button[value='Register']");
    By helloMessage = By.xpath("//p[contains(text(),'Hello')]");
    By loginText = By.cssSelector("div[class='u-column1 col-1'] h2");
    By userNameLogin = By.cssSelector("#username");
    By passwordLogin = By.cssSelector("#password");
    By loginBtn =  By.cssSelector("button[value='Log in']");
    By nonRegisteredUserErrTxt = By.cssSelector("div[id='content'] li:nth-child(1)");


    public String verifyRegisterPageText() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(registerText)).getText();
    }

    public String verifyLoginPageText() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginText)).getText();
    }



    public AccountPage enterUserNameReg(String userName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameReg)).clear();

        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameReg)).sendKeys(userName);

        return this;
    }

    public AccountPage enterPasswordReg(String password) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordReg)).clear();

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordReg)).sendKeys(password);

        return this;
    }


    public AccountPage enterEmailReg(String Email) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailReg)).clear();

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailReg)).sendKeys(Email);

        return this;
    }


    public AccountPage enterAllRegistrationDetails(RegistrationAndLoginDetails registrationDetails){

        enterUserNameReg(registrationDetails.getEnterUserName())
                .enterPasswordReg(registrationDetails.enterPassword())
                .enterEmailReg(registrationDetails.enterEmailReg());
        return this;


    }


    public AccountPage enterLoginUserName(String username){

        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLogin)).sendKeys(username);

        return this;
    }

    public AccountPage enterLoginPassword(String password){

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLogin)).sendKeys(password);

        return this;
    }

    public AccountPage enterUserNameAndPassword(RegistrationAndLoginDetails registrationDetails){

       enterLoginUserName(registrationDetails.getEnterUserName())
               .enterLoginPassword(registrationDetails.enterPassword());
        return this;


    }

    public AccountPage enterNonRegisteredUserNameAndPassword(NonRegisteredUserDetails nonRegisteredUserDetails){

        enterLoginUserName(nonRegisteredUserDetails.getEnterUserName())
                .enterLoginPassword(nonRegisteredUserDetails.getEnterPassword());
        return this;


    }

    public AccountPage clickRegisterBtn() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(registerBtn)).click();
        ;

        return this;
    }

    public AccountPage clickLoginBtn() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn)).click();

        return this;
    }


    public String verifyUserHelloMessage() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(helloMessage)).getText();

    }

    public String verifyNonRegisteredUserErrorMsg() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(nonRegisteredUserErrTxt)).getText();

    }


}
