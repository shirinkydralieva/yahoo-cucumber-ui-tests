package framework.ui.driver;

import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {

  private static WebDriver driver;
  private static ThreadLocal<WebDriverSingleton> instance = new ThreadLocal<>();

  private WebDriverSingleton(){
    driver = WebDriverFactory.getWebDriver();
  }

  public static synchronized WebDriverSingleton getInstance() {
    if (instance.get() == null) {
      instance.set(new WebDriverSingleton());
    }
    return instance.get();
  }

  public WebDriver getDriver() {
    return driver;
  }

  public void cleanup() {
    if (instance != null) {
      instance.remove();
    }
  }

}
