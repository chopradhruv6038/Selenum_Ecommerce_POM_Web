package Org.Selenium.Pages;

import Org.Selenium.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {


    public HomePage(WebDriver driver){

        super(driver);
    }

    By accountLink = By.cssSelector("li[id='menu-item-1237'] a[class='menu-link']");


    public void loadUrl(){

        url("/");

    }


    public AccountPage clickAccountLink(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(accountLink)).click();

        return new AccountPage(driver);
    }




}
