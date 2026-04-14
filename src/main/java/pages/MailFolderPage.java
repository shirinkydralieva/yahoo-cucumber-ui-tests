package pages;

import framework.ui.AbstractPage;
import org.openqa.selenium.By;

public class MailFolderPage extends AbstractPage {

  private static final String MAIL_IN_FOLDER_LOCATOR_PATTERN
      = "div[data-test-id='message-list-item'] div[title='%s']";

  public MailDialogPage clickMailInFolderBySubject(String subject){
    element((By.cssSelector(String.format(MAIL_IN_FOLDER_LOCATOR_PATTERN, subject)))).click();
    return new MailDialogPage();
  }

  public boolean isMailDisplayed(String subject){
    return element(By.cssSelector(String.format(MAIL_IN_FOLDER_LOCATOR_PATTERN, subject)))
        .getWrappedWebElement().isDisplayed();
  }

  public boolean isMailDisappeared(String subject){
    return element(By.cssSelector(String.format(MAIL_IN_FOLDER_LOCATOR_PATTERN, subject)))
        .waitInvisibilityOfElement();
  }

}
