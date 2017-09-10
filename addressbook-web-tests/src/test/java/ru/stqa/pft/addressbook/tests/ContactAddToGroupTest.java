package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactAddToGroupTest extends TestBase {

  @Test

  public void testContactAddToGroup (){
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupsHelper().addFirstGroup(new GroupData("newGroup", null, null));
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().addFirstContact(new ContactData("Fname", "Mname", "Lname", "Nname", "ooo\"company\"", "Email@gmail.com", "5-555-555", "newGroup"), true);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
   // int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().selectGroupToAdd();
    app.getContactHelper().addContactToGroup();
    app.getContactHelper().goToNewGroupPage();
    app.getContactHelper().selectAllGroupShown();
    List<ContactData> after = app.getContactHelper().getContactList();
   // int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after.size(),before.size());
  }
}
