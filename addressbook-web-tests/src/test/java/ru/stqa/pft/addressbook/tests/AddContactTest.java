package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class AddContactTest extends TestBase {

  @Test
  public void addNewContact() {
 //   int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupsHelper().addFirstGroup(new GroupData("newGroup", null, null));
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().createContact(new ContactData("Fname", "Mname", "Lname", "Nname", "ooo\"company\"", "Email@gmail.com", "5-555-555", "newGroup"), true);
    app.getNavigationHelper().returnToHomePage();
 //   int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() + 1);
  }

}
