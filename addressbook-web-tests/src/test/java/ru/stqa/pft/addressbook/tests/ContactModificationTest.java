package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    app.group().addFirstGroup(new GroupData().withName("newGroup"));
    app.goTo().HomePage();
    app.contact().addFirstContact(new ContactData("Fname", "Mname", "Lname", "Nname", "ooo\"company\"", "Email@gmail.com", "5-555-555", "newGroup"), true);
    app.goTo().HomePage();
  }

  @Test
  public void testContactModification(){
    List<ContactData> before = app.contact().list();
    app.contact().selectContactToEdit(before.size()-1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Fname", "Mname", "Lname","Nname", "ooo\"company\"", "Email@gmail.com", "8-777-777", null);
    app.contact().fillNewContactForm(contact, false);
    app.contact().submitContactModification();
    app.goTo().HomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
