package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactAddToGroupTest extends TestBase {
  public ContactAddToGroupTest() {
  }

  @Test
  public void testContactAddToGroup() {
    app.contact().selectContact();
    app.contact().groupToAdd();
    app.contact().addContactToGroup();
    app.goTo().homePage();

  }
}
