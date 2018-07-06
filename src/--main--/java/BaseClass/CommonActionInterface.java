package BaseClass;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface CommonActionInterface {
	
	void navigateTo(String url);
	void enterTextIn(WebDriver driver, WebElement pageElement, String text, String TextFieldName);
    void clickButton(WebDriver driver, WebElement pageElement, String buttonName);
    void waitFor(WebDriver driver, long time);
    void close(WebDriver driver);
    void takeScreenShot(String pageTitle);
    String getPageTitleWithPolicyNumber(WebDriver driver, String pageTitle) throws InterruptedException;
    String getPageTitle(WebDriver driver, String expectedPageTitle);
    String getText(WebDriver driver, WebElement pageElement);
    void clearTextBox(WebDriver driver, WebElement pageElement, String textfield);
    String getAttributeValue(WebElement pageElement, String attributeName);
    void click(WebDriver driver, WebElement pageElement, String elementName);
    void switchToFrameUsingId(WebDriver driver, String uniqId);
    void switchToFrameUsingElement(WebDriver driver, WebElement element) throws Exception;
    void switchToParentWindowfromframe(WebDriver driver);
    void visibilityOfElement(WebDriver driver, WebElement pageElement, String text);
    String switchToWindow(WebDriver driver) throws InterruptedException;
    void selectDropdownByValue(WebDriver driver, WebElement Value,String DropDownOption, String name);
    void switchToParentWindowfromotherwindow(WebDriver driver, String parentwindow);
    void policySearch(WebDriver driver, String policyNo, WebElement policySearchTxtBox, WebElement earchBtn);
    void invisibilityOfLoader(WebDriver driver, WebElement element);
}

