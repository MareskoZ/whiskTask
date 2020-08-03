package tests.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UITest extends BaseTest{

    private final String email = "saryn@vqsprint.com";
    private final String password = "Aq1sw2de";
    private final String listTitle = "test list";
    private final List<String> itemTitlesList = new ArrayList<String>(Arrays.asList("Milk", "Bread", "Onions", "Carrot", "Garlic"));

    @Test(priority = 1)
    public void createShoppingList(){
        SoftAssert softAssert = new SoftAssert();
        drv.get(firstUrl);
        introPage.signInBtn.click();
        basePage.switchTab(2);
        loginPopup.login(email, password);
        drv.get(appUrl + baseRoute.shoppingListUrl);
        shoppingPage.createNewShoppingList(listTitle);
        shoppingPage.addItemsToList(itemTitlesList);
        shoppingPage.checkThatListContains(itemTitlesList, softAssert, true);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void deleteShoppingList(){
        SoftAssert softAssert = new SoftAssert();
        drv.get(firstUrl);
        introPage.signInBtn.click();
        basePage.switchTab(2);
        loginPopup.login(email, password);
        drv.get(appUrl + baseRoute.shoppingListUrl);
        shoppingPage.deleteShoppingList(listTitle);
        Assert.assertEquals(shoppingPage.countOfShoppingLists(),1);
    }

}
