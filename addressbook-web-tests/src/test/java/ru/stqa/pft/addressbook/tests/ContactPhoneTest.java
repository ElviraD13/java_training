package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {
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
              .withEmail("Email@gmail.com").withHomePhone("5-55-55").withMobilePhone("333").withWorkPhone("7(222)").withGroup("newGroup"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
