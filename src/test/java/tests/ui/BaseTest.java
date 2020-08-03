package tests.ui;

import base.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;
import pages.BaseRoute;
import pages.appPages.LoginPopup;
import pages.appPages.ShoppingPage;
import pages.introPage.IntroPage;

public class BaseTest {

    protected final String firstUrl = "http://whisk.com";
    protected final String appUrl = "https://my.whisk.com";

    public WebDriver drv;
    public Browser browser;
    public BasePage basePage;
    public BaseRoute baseRoute;
    public IntroPage introPage;
    public ShoppingPage shoppingPage;
    public LoginPopup loginPopup;


    @BeforeClass
    public void BeforeClass(){
        browser = new Browser();
    }

    @BeforeMethod
    public void BeforeEachMethod(){
        drv = browser.getDesktopChrome();
        basePage = new BasePage(drv);
        baseRoute = new BaseRoute(drv);
        shoppingPage = new ShoppingPage(drv);
        introPage = new IntroPage(drv);
        loginPopup = new LoginPopup(drv);
    }

    @AfterMethod
    public void AfterEachMethod(){
        drv.quit();
    }
}
