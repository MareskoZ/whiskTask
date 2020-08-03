package pages;

import org.openqa.selenium.WebDriver;

public class BaseRoute extends BasePage {

    public final String shoppingListUrl = "/shopping-list/";

    public BaseRoute(WebDriver drv) {
        super(drv);
    }

}
