package pages;

import framework.ui.AbstractPage;
import org.openqa.selenium.By;

public class HomePage extends AbstractPage {

  private static final By SIGN_IN_BUTTON =
      By.xpath("//*[contains(@class,'signin') or contains(@aria-label,'Sign')]");

  public LoginPage clickSignInButton(){
    element(SIGN_IN_BUTTON).click();
    return new LoginPage();
  }

}
