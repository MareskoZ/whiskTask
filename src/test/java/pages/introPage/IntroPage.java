package pages.introPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class IntroPage extends BasePage {

    public final String LOGIN_BUTTON_XPATH = "//a[@class='signin']";

    @FindBy(xpath = LOGIN_BUTTON_XPATH)
    public WebElement signInBtn;

    public IntroPage(WebDriver drv) {
        super(drv);
        PageFactory.initElements(drv, this);
    }
}