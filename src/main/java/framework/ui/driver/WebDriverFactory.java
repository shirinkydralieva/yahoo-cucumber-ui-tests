package framework.ui.driver;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
public class WebDriverFactory {

  public static WebDriver getWebDriver(){
    String browser = System.getProperty("browser", "chrome");
    WebDriver webDriver;
    switch (browser) {
      case "chrome":
        webDriver = new ChromeDriver(getChromeOptions());
        break;
      default:
        throw new RuntimeException("Unsupported browser: " + browser);
    }
    webDriver.manage().window().maximize();
    log.info("Current browser is {}. Screen resolution: {}",
        webDriver, webDriver.manage().window().getSize()
    );
    return webDriver;
  }

  private static ChromeOptions getChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
    options.addArguments(
        "--incognito",
        "--disable-blink-features=AutomationControlled"
    );
    return options;
  }

}
