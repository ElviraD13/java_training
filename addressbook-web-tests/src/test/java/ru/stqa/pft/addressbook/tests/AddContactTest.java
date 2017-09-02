package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.NewContactData;

public class AddContactTest extends TestBase {

  @Test
  public void addNewContact() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupsHelper().addFirstGroup(new GroupData("newGroup", null, null));
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().createContact(new NewContactData("Fname", "Mname", "Lname", "Nname", "ooo\"company\"", "Email@gmail.com", "5-555-555", "newGroup"), true);
    app.getNavigationHelper().returnToHomePage();
  }

}
