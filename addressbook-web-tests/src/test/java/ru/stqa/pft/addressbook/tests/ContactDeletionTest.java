package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase{

  @Test
  public void deleteContactTest() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupsHelper().addFirstGroup(new GroupData("newGroup", null, null));
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().addFirstContact(new ContactData("Fname", "Mname", "Lname", "Nname", "ooo\"company\"", "Email@gmail.com", "5-555-555", "newGroup"), true);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before,after);
  }
}
