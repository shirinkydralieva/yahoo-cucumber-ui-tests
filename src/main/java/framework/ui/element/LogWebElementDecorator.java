package framework.ui.element;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

@Slf4j
public class LogWebElementDecorator extends WebElementDecorator {

  public LogWebElementDecorator(By locator) {
    super(locator);
  }

  @Override
  public void click() {
    super.click();
    log.debug("Click element located {}", locator);
  }

  @Override
  public void clickJs() {
    super.clickJs();
    log.debug("Click using javascript executor element located {}", locator);
  }

  @Override
  public void type(String text) {
    super.type(text);
    log.debug("Send text to element located {}", locator);
  }

  @Override
  public void sendKeys(Keys keys) {
    super.sendKeys(keys);
    log.debug("Send keys {} to element located {}", keys.name(), locator);
  }

  @Override
  public WebElement waitVisibilityOfElement() {
    log.debug("Wait until element located {} will be visible", locator);
    return super.waitVisibilityOfElement();
  }

  @Override
  public Boolean waitInvisibilityOfElement() {
    log.debug("Wait until element located {} will be invisible", locator);
    return super.waitInvisibilityOfElement();
  }

  @Override
  public WebElement waitElementToBeClickable() {
    log.debug("Wait until element located {} will be clickable", locator);
    return super.waitElementToBeClickable();
  }

  @Override
  public Boolean waitElementAttributeTobe(String attribute, String value) {
    log.debug("Wait element located {} attribute {} to be {}", locator, attribute, value);
    return super.waitElementAttributeTobe(attribute, value);
  }

}
