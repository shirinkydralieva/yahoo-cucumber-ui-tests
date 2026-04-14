package service;

import bo.User;
import lombok.extern.slf4j.Slf4j;
import pages.InboxPage;
import pages.LoginPage;

@Slf4j
public class LoginService {

  private LoginPage loginPage = new LoginPage();
  private InboxPage inboxPage = new InboxPage();

  public InboxPage login(User user){
    log.info("Start user login as {}", user.getUsername());
    loginPage
        .typeEmail(user.getUsername())
        .clickNextButton()
        .typePassword(user.getPassword())
        .clickNextButton();
    log.info("User login completed");
    return inboxPage;
  }

  public void logout(){
    inboxPage
        .clickJsAccountMenuOpener()
        .clickJsLogoutButton();
    log.info("User logout completed");
  }

}
