package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class BasePage {

    public static final int DEFAULT_TIMEOUT = 30;
    public WebDriver drv;
    public static WebDriverWait wait;
    public JavascriptExecutor js;

    public BasePage(WebDriver drv){
        this.drv = drv;
        wait = new WebDriverWait(drv, DEFAULT_TIMEOUT);
        this.js = (JavascriptExecutor) drv;
    }

    public void waitUntilPageReady(){
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void switchTab(int pageNumber){
        ArrayList<String> tabs = new ArrayList<String> (drv.getWindowHandles());
        drv.switchTo().window(tabs.get(pageNumber-1));
    }

    public void cleanInput(WebElement elem){
        elem.sendKeys(Keys.CONTROL + "a");
        elem.sendKeys(Keys.DELETE );
    }
}
