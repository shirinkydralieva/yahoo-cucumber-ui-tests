package framework.ui.element;

import framework.ui.driver.WebDriverSingleton;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementDecorator {

  private static final int DEFAULT_TIMEOUT_IN_SECONDS = 45;
  protected final By locator;
  protected final WebDriver driver = WebDriverSingleton.getInstance().getDriver();
  protected final WebDriverWait explicitWait =
      new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_IN_SECONDS));
  protected final JavascriptExecutor javascriptExecutor =
      (JavascriptExecutor) driver;

  public WebElementDecorator(By locator) {
    this.locator = locator;
  }

  public void click() {
    waitElementToBeClickable().click();
  }

  public void clickJs(){
    javascriptExecutor.executeScript("arguments[0].click()", getWrappedWebElement());
  }

  public void clickAndWaitElementAttributeToBe(String attribute, String value){
    click();
    waitElementAttributeTobe(attribute, value);
  }

  public void type(String text) {
    getWrappedWebElement().sendKeys(text);
  }

  public void sendKeys(Keys keys) {
    getWrappedWebElement().sendKeys(keys);
  }

  public WebElement getWrappedWebElement(){
    return waitVisibilityOfElement();
  }

  public WebElement waitVisibilityOfElement() {
    return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public Boolean waitInvisibilityOfElement() {
    return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
  }

  public WebElement waitElementToBeClickable() {
    return explicitWait
        .until(ExpectedConditions.elementToBeClickable(locator));
  }

  public Boolean waitElementAttributeTobe(String attribute, String value){
    return explicitWait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
  }

}
