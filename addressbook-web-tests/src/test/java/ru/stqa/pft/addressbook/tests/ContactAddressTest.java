package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("newGroup"));
      app.goTo().homePage();
    }
    if (app.db().contacts().size()==0) {
      app.contact().create(new ContactData()
              .withFirstname("Fname").withMiddlename("Mname").withLastname("Lname").withNickname("Nname").withCompany("ooo\"company\"")
              .withAddress("Country").withEmail("Email@mail.com").withEmail2("Email2@mail.com").withEmail3("Email3@mail.com")
              .withHomePhone("5-55-55").withMobilePhone("333").withWorkPhone("7(222)")/*.withGroup("newGroup")*/, true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactAddress(){
    app.goTo().homePage();
    ContactData contact = app.db().contacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
  }
  private String mergeAddress(ContactData contact) {
    return Arrays.asList(contact.getAddress())
            .stream().collect(Collectors.joining("\n"));
  }
}


