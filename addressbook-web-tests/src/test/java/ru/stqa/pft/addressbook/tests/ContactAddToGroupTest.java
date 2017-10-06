package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactAddToGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("newGroup"));
      app.goTo().homePage();
    }
    if (app.db().contacts().size()==0){
      app.contact().create(new ContactData()
              .withFirstname("Fname").withMiddlename("Mname").withLastname("Lname").withNickname("Nname").withCompany("ooo\"company\"")
              .withEmail("Email@gmail.com").withHomePhone("5-555-555")/*.withGroup("newGroup")*/, true);
      app.goTo().homePage();
    }
  }
   @BeforeMethod
   public void ensureContactForGroup(){

     Contacts contacts = app.db().contacts();
     Groups groups = app.db().groups();
     int i = 0;
     for (ContactData contact : contacts){
       if (contact.getGroups().size() == groups.size())
         i++;
     }
     if (i == contacts.size()) {
       app.contact().gotoAddNewPage();
       app.contact().create(new ContactData()
               .withFirstname("Fname").withMiddlename("Mname").withLastname("Lname").withNickname("Nname").withCompany("ooo\"company\"")
               .withEmail("Email@gmail.com").withHomePhone("5-555-555")/*.withGroup("newGroup")*/, true);
       app.goTo().homePage();
     }
   }

  @Test
  public void testContactAddToGroup() {

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    ContactData contactToAdd = app.contact().findContact(contacts,groups);
    GroupData group = app.contact().groupToAddContact(contactToAdd,groups);

    ContactData contactBefore = app.db().contact(contactToAdd.getId());

    app.contact().selectContact();
    app.contact().groupToAdd();
    app.contact().addContactToGroup();
    app.goTo().homePage();

    ContactData contactAfter = app.db().contact(contactToAdd.getId());
    assertThat(contactBefore.getGroups(),equalTo(contactAfter.getGroups().without(group)));
  }
}
