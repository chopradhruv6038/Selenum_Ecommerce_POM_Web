package Org.Selenium.Pages;

import Org.Selenium.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountDetailsPage extends BasePage {

    public AccountDetailsPage(WebDriver driver){

        super(driver);
    }


By accountDisplayName = By.cssSelector("#account_display_name");
By accountEmail = By.cssSelector("#account_email");

public String getDisplayName(){


    return wait.until(ExpectedConditions.visibilityOfElementLocated(accountDisplayName)).getAttribute("value");
}

    public String getEmailDetails(){

    return wait.until(ExpectedConditions.visibilityOfElementLocated(accountEmail)).getAttribute("value");

}



}

