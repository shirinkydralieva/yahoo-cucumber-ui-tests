package stepdefinitions;

import static bo.UserFactory.asUserWithSimplePermission;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import bo.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.InboxPage;
import pages.MailDialogPage;
import pages.MailFolderPage;
import service.LoginService;
import org.testng.asserts.SoftAssert;

public class YahooMailSteps {

  private static final User user = asUserWithSimplePermission();
  private static final String RECIPIENT = user.getUsername();
  private static final String SUBJECT = "Autotest check subject";
  private static final String BODY = "Autotest check mail body";
  private final InboxPage inboxPage = new InboxPage();
  private final MailDialogPage mailDialogPage = new MailDialogPage();
  private final MailFolderPage mailFolderPage = new MailFolderPage();

  @Given("I am logged in to Yahoo Mail")
  public void loginToYahooMail(){
    new HomePage()
        .clickSignInButton()
        .switchToLastWindow();
    new LoginService()
        .login(user);
  }

  @Given("I start composing a new email")
  public void startComposingNewEmail(){
    mailDialogPage.clickComposeButton();
  }

  @When("I enter email recipient")
  public void enterRecipient(){
    mailDialogPage.typeRecipient(RECIPIENT);
  }

  @And("I enter email subject")
  public void enterSubject(){
    mailDialogPage.typeSubject(SUBJECT);
  }

  @And("I enter email body")
  public void enterBody(){
    mailDialogPage.typeBody(BODY);
  }

  @And("I close the email editor")
  public void closeEmailEditor(){
    mailDialogPage
        .clickCloseButton();
    mailDialogPage
        .waitMailDialogToBeClosed();
  }

  @Then("the email is saved in Drafts")
  public void verifyEmailIsSavedInDrafts(){
    boolean isMailInDraftFolder = inboxPage
        .openDrafts()
        .isMailDisplayed(SUBJECT);

    mailFolderPage
        .clickMailInFolderBySubject(SUBJECT);
    mailDialogPage
        .waitMailDialogToBeOpened();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(isMailInDraftFolder);
    softAssert.assertEquals(mailDialogPage.getRecipientText(RECIPIENT), RECIPIENT);
    softAssert.assertEquals(mailDialogPage.getSubjectTitleValue(), SUBJECT);
    softAssert.assertEquals(mailDialogPage.getBodyText(), BODY);
    softAssert.assertAll();
  }

 @Given("I have a draft with recipient, subject and body filled")
  public void openDraftWithFilledFields(){
    inboxPage
        .openDrafts();
    mailFolderPage
        .clickMailInFolderBySubject(SUBJECT);
 }

 @When("I send the email")
  public void sendEmail(){
   mailDialogPage
       .clickSendButton();
 }

 @Then("the email appears in Sent folder")
  public void verifyEmailAppearsInSentFolder(){
   boolean isMailDisappearedFromDrafts = mailFolderPage
       .isMailDisappeared(SUBJECT);

   boolean isMailInSentFolder = inboxPage
       .openSent()
       .isMailDisplayed(SUBJECT);

   SoftAssert softAssert = new SoftAssert();
   softAssert.assertTrue(isMailDisappearedFromDrafts);
   softAssert.assertTrue(isMailInSentFolder);
   softAssert.assertAll();
 }

 @When("I enter {string} as recipient")
 public void enterInvalidEmailAsRecipient(String invalidEmail){
   mailDialogPage.typeRecipient(invalidEmail);
 }

 @Then("I see an error message about invalid email address")
  public void verifyInvalidEmailErrorMessageIsShown(){
    assertTrue(mailDialogPage.isIncorrectEmailAlertDisplayed());
 }
}
