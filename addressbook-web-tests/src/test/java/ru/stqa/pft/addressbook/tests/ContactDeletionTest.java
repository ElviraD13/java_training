package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    app.group().addFirstGroup(new GroupData().withName("newGroup"));
    app.goTo().homePage();
    app.contact().addFirstContact(new ContactData()
            .withFirstname("Fname").withMiddlename("Mname").withLastname("Lname").withNickname("Nname").withCompany("ooo\"company\"")
            .withEmail("Email@gmail.com").withHomeTelephone("5-555-555").withGroup("newGroup"), true);
    app.goTo().homePage();
  }

  @Test
  public void deleteContactTest() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertEquals(after.size(),before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
