package Org.Selenium.Pages;

import Org.Selenium.Base.BasePage;
import Org.Selenium.Objects.RegistrationDetails;
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


    public String verifyRegisterPageText() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(registerText)).getText();
    }


    public AccountPage enterUserName(String userName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameReg)).clear();

        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameReg)).sendKeys(userName);

        return this;
    }

    public AccountPage enterPassword(String password) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordReg)).clear();

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordReg)).sendKeys(password);

        return this;
    }


    public AccountPage enterEmail(String Email) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailReg)).clear();

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailReg)).sendKeys(Email);

        return this;
    }


    public AccountPage enterAllRegistrationDetails(RegistrationDetails registrationDetails){

        enterUserName(registrationDetails.getEnterUserName())
                .enterPassword(registrationDetails.getEnterPassword())
                .enterEmail(registrationDetails.getEnterEmail());
        return this;


    }

    public AccountPage clickRegisterBtn() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(registerBtn)).click();
        ;

        return this;
    }


    public String verifyUserHelloMessage() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(helloMessage)).getText();

    }

}
