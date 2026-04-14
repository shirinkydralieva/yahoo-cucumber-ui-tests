package pages;

import framework.ui.AbstractPage;
import org.openqa.selenium.By;

public class InboxPage extends AbstractPage {

  private static final By ACCOUNT_AVATAR_LOCATOR =
      By.cssSelector("button#ybarAccountMenu");
  private static final By ACCOUNT_MENU_OPENER =
      By.cssSelector("label#ybarAccountMenuOpener");
  private static final By ACCOUNT_MENU_BODY =
      By.cssSelector("div#ybarAccountMenuBody");
  private static final By LOGOUT_BUTTON_LOCATOR =
      By.cssSelector("div#ybarAccountMenuBody a#profile-signout-link");
  private static final By DRAFTS_FOLDER_LOCATOR =
      By.cssSelector("a[role='button'][aria-label='Drafts']");
  private static final By SENT_FOLDER_LOCATOR =
      By.cssSelector("a[role='button'][aria-label='Sent']");

  public boolean isPageLoaded() {
    return super.isPageLoaded(ACCOUNT_AVATAR_LOCATOR);
  }

  public MailFolderPage openDrafts(){
    element(DRAFTS_FOLDER_LOCATOR).click();
    implicitlyWait();
    return new MailFolderPage();
  }

  public MailFolderPage openSent(){
    element(SENT_FOLDER_LOCATOR).click();
    implicitlyWait();
    return new MailFolderPage();
  }

  public InboxPage clickJsAccountMenuOpener(){
    element(ACCOUNT_MENU_OPENER).clickJs();
    element(ACCOUNT_MENU_BODY).waitVisibilityOfElement();
    return this;
  }

  public InboxPage clickJsLogoutButton(){
    element(LOGOUT_BUTTON_LOCATOR).clickJs();
    return this;
  }

}
