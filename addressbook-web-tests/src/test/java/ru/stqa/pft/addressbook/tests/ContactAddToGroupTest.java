package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactAddToGroupTest extends TestBase {

  @Test

  public void testContactAddToGroup (){
    app.getContactHelper().selectContact();
    app.getContactHelper().selectGroupToAdd();
    app.getContactHelper().addContactToGroup();
    app.getContactHelper().goToNewGroupPage();
    app.getContactHelper().selectAllGroupShown();
  }
}
