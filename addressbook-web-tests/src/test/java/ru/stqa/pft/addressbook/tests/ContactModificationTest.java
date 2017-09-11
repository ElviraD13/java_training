package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupsHelper().addFirstGroup(new GroupData("newGroup", null, null));
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().addFirstContact(new ContactData("Fname", "Mname", "Lname", "Nname", "ooo\"company\"", "Email@gmail.com", "5-555-555", "newGroup"), true);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContactToEdit(before.size()-1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Fname", "Mname", "Lname","Nname", "ooo\"company\"", "Email@gmail.com", "8-777-777", null);
    app.getContactHelper().fillNewContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
