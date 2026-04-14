package framework.ui;

import framework.ui.driver.WebDriverSingleton;
import framework.ui.element.LogWebElementDecorator;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public abstract class AbstractPage {

  private static final int DEFAULT_TIMEOUT_IN_SECONDS = 45;
  private static final int SHORT_TIMEOUT_IN_SECONDS = 15;

  protected final WebDriver driver = WebDriverSingleton.getInstance().getDriver();
  protected final WebDriverWait explicitWait =
      new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_IN_SECONDS));
  protected final JavascriptExecutor javascriptExecutor =
      (JavascriptExecutor) driver;

  protected boolean isPageLoaded(By locator){
    log.info("Wait for page to be loaded");
    return element(locator).getWrappedWebElement().isDisplayed();
  }

  protected void openPage(String url){
    log.info("Open page {}", url);
    driver.get(url);
  }

  public void switchToLastWindow(){
    String current = driver.getWindowHandle();
    log.debug("Switch to the last window from window:{}", current);
    for (String handle : driver.getWindowHandles()) {
      if (!handle.equals(current)) {
        driver.switchTo().window(handle);
        break;
      }
    }
  }

  protected void implicitlyWait(){
    log.debug("Set implicit wait with duration in seconds: {}", SHORT_TIMEOUT_IN_SECONDS);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SHORT_TIMEOUT_IN_SECONDS));
  }

  protected LogWebElementDecorator element(By locator) {
    return new LogWebElementDecorator(locator);
  }

}
