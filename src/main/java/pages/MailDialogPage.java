package pages;

import framework.ui.AbstractPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

@Slf4j
public class MailDialogPage extends AbstractPage {

  private static final By COMPOSE_BUTTON_LOCATOR =
      By.cssSelector("button[data-test-id='left-rail-Compose-icon']");
  private static final By RECIPIENT_INPUT_LOCATOR =
      By.cssSelector("input[aria-label='To']");
  private static final By SUBJECT_INPUT_LOCATOR = By.cssSelector(
      "input[id='compose-subject-input'][aria-label='Subject']");
  private static final By MAIL_BODY_INPUT = By.xpath(
      "//div[contains(@aria-label, 'Message body')]");
  private static final By CLOSE_MAIL_BUTTON_LOCATOR = By.xpath(
      "//button[contains(@aria-label, 'Save & close')]");
  private static final By SEND_MAIL_BUTTON_LOCATOR = By.xpath(
      "//button[@data-test-id='compose-send-button']");
  private static final By INCORRECT_EMAIL_ADDRESS_ALERT_LOCATOR = By.xpath(
      "//div[@role='alert' and contains(., 'Please correct these email addresses')]");
  private static final String MAIL_SUBJECT_TEXT_PATTERN
      = "//div[@data-test-id='collapsed-recipient-container']/span[contains(@title, '%s')]";
  private static final By MAIL_DIALOG =
      By.cssSelector("div[data-test-id='compose-styler']");


  public MailDialogPage clickComposeButton() {
    element(COMPOSE_BUTTON_LOCATOR).click();
    return this;
  }

  public MailDialogPage typeRecipient(String recipient) {
    element(RECIPIENT_INPUT_LOCATOR).click();
    element(RECIPIENT_INPUT_LOCATOR).type(recipient);
    element(RECIPIENT_INPUT_LOCATOR).sendKeys(Keys.ENTER);
    return this;
  }

  public MailDialogPage typeSubject(String subject) {
    element(SUBJECT_INPUT_LOCATOR).click();
    element(SUBJECT_INPUT_LOCATOR).type(subject);
    return this;
  }

  public MailDialogPage typeBody(String body) {
    element(MAIL_BODY_INPUT).click();
    element(MAIL_BODY_INPUT).type(body);
    return this;
  }

  public MailDialogPage clickCloseButton() {
    element(CLOSE_MAIL_BUTTON_LOCATOR).clickJs();
    implicitlyWait();
    return this;
  }

  public MailDialogPage clickSendButton(){
    element(SEND_MAIL_BUTTON_LOCATOR).click();
    return this;
  }

  public String getRecipientText(String recipient){
    return element(By.xpath(String.format(MAIL_SUBJECT_TEXT_PATTERN, recipient)))
        .getWrappedWebElement().getText();
  }

  public String getSubjectTitleValue() {
    return element(SUBJECT_INPUT_LOCATOR).getWrappedWebElement().getAttribute("value");
  }

  public String getBodyText() {
    return element(MAIL_BODY_INPUT).getWrappedWebElement().getText();
  }

  public boolean isMailDialogDisplayed(){
    return element(MAIL_DIALOG).getWrappedWebElement().isDisplayed();
  }

  public boolean isIncorrectEmailAlertDisplayed(){
    return element(INCORRECT_EMAIL_ADDRESS_ALERT_LOCATOR).getWrappedWebElement().isDisplayed();
  }

  public void waitMailDialogToBeOpened(){
    element(MAIL_DIALOG).waitVisibilityOfElement();
  }

  public void waitMailDialogToBeClosed(){
    element((MAIL_DIALOG)).waitInvisibilityOfElement();
  }
}
