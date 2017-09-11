package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class AddContactTest extends TestBase {

  @Test
  public void addNewContact() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupsHelper().addFirstGroup(new GroupData("newGroup", null, null));
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("fName", "mName", "lNname", "nName", "ooo", "Email@com", "8-111-111", "newGroup");
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().createContact(contact, true);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() + 1);


    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
