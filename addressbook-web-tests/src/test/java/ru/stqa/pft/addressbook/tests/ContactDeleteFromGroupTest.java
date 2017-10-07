package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 1"));
      app.goTo().homePage();
    }
    if (app.db().contacts().size()==0){
      app.contact().create(new ContactData()
              .withFirstname("Fname").withMiddlename("Mname").withLastname("Lname").withNickname("Nname").withCompany("ooo\"company\"")
              .withEmail("Email@gmail.com").withHomePhone("5-555-555"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void deleteContactFromGroupTest() {
    app.goTo().homePage();
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    GroupData group = app.contact().groupToDeleteContact(contacts, groups);
    ContactData contact = app.contact().contactToDelete(contacts,group);
    ContactData contactBefore = app.db().contact(contact.getId());
    app.contact().deleteFromGroup(contact,group);
    app.goTo().homePage();
    app.contact().showAllContact();
    ContactData contactAfter = app.db().contact(contact.getId());
    assertThat(contactBefore.getGroups(), equalTo(contactAfter.getGroups().withAdded(group)));
  }

}
