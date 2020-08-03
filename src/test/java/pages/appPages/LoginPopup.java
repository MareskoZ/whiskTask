package pages.appPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class LoginPopup extends BasePage {
    public final String LOGIN_FORM_XPATH = "//div[@data-testid='authentication-form']";
    public final String LOGIN_FORM_INPUT_XPATH = LOGIN_FORM_XPATH + "//input[@data-testid='UI_KIT_INPUT']";
    public final String CONTINUE_BUTTON_XPATH = LOGIN_FORM_XPATH + "//*[@data-testid='auth-continue-button']";
    public final String LOGIN_BUTTON_XPATH = LOGIN_FORM_XPATH + "//*[@data-testid='auth-login-button']";
    public final String POPUP_CONTINUE_BUTTON_XPATH = "//*[@data-testid='community-onboarding-continue']";

    @FindBy(xpath = LOGIN_FORM_INPUT_XPATH)
    public WebElement loginFormInput;

    @FindBy(xpath = CONTINUE_BUTTON_XPATH)
    public WebElement loginContinueButton;

    @FindBy(xpath = LOGIN_BUTTON_XPATH)
    public WebElement loginButton;

    public LoginPopup(WebDriver drv) {
        super(drv);
        PageFactory.initElements(drv, this);
    }

    public void login(String email, String password){
        loginFormInput.sendKeys(email);
        loginContinueButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOGIN_BUTTON_XPATH)));
        loginFormInput.sendKeys(password);
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(POPUP_CONTINUE_BUTTON_XPATH)));
    }
}
