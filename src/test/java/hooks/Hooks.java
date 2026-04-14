package hooks;

import static framework.properties.EnvironmentProperty.getEnv;

import lombok.extern.slf4j.Slf4j;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import framework.ui.driver.WebDriverSingleton;
import io.cucumber.java.Scenario;
import util.ScreenshotUtil;

@Slf4j
public class Hooks {

  @Before
  public void setUp(){
    log.info("Open browser");
    WebDriverSingleton.getInstance().getDriver().get(getEnv());
  }

  @After
  public void tearDown(Scenario scenario){
    if (scenario.isFailed()){
      attachScreenshot(scenario);
    }
    log.info("Close browser");
    WebDriverSingleton.getInstance().getDriver().quit();
    WebDriverSingleton.getInstance().cleanup();
  }

  private void attachScreenshot(Scenario scenario){
    String testName = scenario.getName();
    log.error("Scenario failed: {}", testName);
    byte[] screenshot =
        ScreenshotUtil.takeScreenshot
            (WebDriverSingleton.getInstance().getDriver(), testName);
    scenario.attach(screenshot, "image/png", "Failed scenario");
  }
}
