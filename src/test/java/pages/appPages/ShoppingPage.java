package pages.appPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import pages.BasePage;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ShoppingPage extends BasePage {

    public final String CREATE_NEW_LIST_BUTTON_XPATH = "//a[@data-testid='create-new-shopping-list-button']";
    public final String POPUP_INPUT_XPATH = "//*[@data-testid='UI_KIT_INPUT']";
    public final String POPUP_CREATE_BUTTON_XPATH = "//*[@data-testid='create-new-shopping-list-create-button']";
    public final String ADD_ITEMS_INPUT_XPATH = "//*[@data-testid='desktop-add-item-autocomplete']";
    public final String ITEM_NAMES_LIST_XPATH = "//*[@data-testid='shopping-list-item-name']";
    public final String SHOPPING_LISTS_LIST_XPATH = "//*[@data-testid='shopping-lists-list-name']";
    public final String SHOPPING_LIST_OPTIONS_BUTTON_XPATH = "//*[@data-testid='vertical-dots-shopping-list-button']";
    public final String SHOPPING_LIST_OPTIONS_DELETE_OPTION_XPATH = "//*[@data-testid='shopping-list-delete-menu-button']";
    public final String CONFIRM_DELETE_BUTTON_XPATH = "//*[@data-testid='confirm-delete-button']";



    @FindBy(xpath = CREATE_NEW_LIST_BUTTON_XPATH)
    public WebElement addNewListBtn;

    @FindBy(xpath = POPUP_INPUT_XPATH)
    public WebElement popupInput;

    @FindBy(xpath = POPUP_CREATE_BUTTON_XPATH)
    public WebElement popupCreateBtn;

    @FindBy(xpath = ADD_ITEMS_INPUT_XPATH)
    public WebElement addItemsInput;

    @FindBy(xpath = ITEM_NAMES_LIST_XPATH)
    public List<WebElement> itemNamesList;

    @FindBy(xpath = SHOPPING_LISTS_LIST_XPATH)
    public List<WebElement> shoppingListsList;

    @FindBy(xpath = SHOPPING_LIST_OPTIONS_BUTTON_XPATH)
    public WebElement shoppingListOptionsBtn;

    @FindBy(xpath = SHOPPING_LIST_OPTIONS_DELETE_OPTION_XPATH)
    public WebElement shoppingListOptionsDeleteOption;

    @FindBy(xpath = CONFIRM_DELETE_BUTTON_XPATH)
    public WebElement confirmDeleteButton;


    public ShoppingPage(WebDriver drv) {
        super(drv);
        PageFactory.initElements(drv, this);
    }

    public void createNewShoppingList(String listTitle){
        addNewListBtn.click();
        cleanInput(popupInput);
        popupInput.sendKeys(listTitle);
        popupCreateBtn.click();
    }

    public void addItemsToList(List<String> itemsTitle){
        wait.until(ExpectedConditions.elementToBeClickable(addItemsInput));
        addItemsInput.click();
        itemsTitle.forEach((itemTitle) -> {
            addItemsInput.sendKeys(itemTitle);
            addItemsInput.sendKeys(Keys.ENTER);
        });
    }

    public void checkThatListContains(List<String> itemsTitle, SoftAssert softAssert, Boolean checkSize) {
        itemsTitle.forEach((itemTitle) -> {
            boolean isExist = false;
            int count = 0;
            for (WebElement elem : itemNamesList) {
                if (elem.getText().equals(itemTitle)){
                    isExist = true;
                    count++;
                }
            }
            softAssert.assertTrue(isExist, itemTitle + " was not found in list of items");
        });
        if(checkSize && itemsTitle.size() != itemNamesList.size()){
            softAssert.fail("items list size is not equal of expected list size, expected: "
                    + itemsTitle.size() + " actual: " + itemNamesList.size());
        }
    }

    public void deleteShoppingList(String listTitle){
        By lookingList = By.xpath(SHOPPING_LISTS_LIST_XPATH + "[text()='"+listTitle+"']" );
        drv.findElement(lookingList).click();
        shoppingListOptionsBtn.click();
        shoppingListOptionsDeleteOption.click();
        confirmDeleteButton.click();
        assertTrue(drv.findElements(lookingList).isEmpty());
    }

    public int countOfShoppingLists(){
        return shoppingListsList.size();
    }
}
