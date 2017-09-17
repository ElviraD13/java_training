package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    app.group().addFirstGroup(new GroupData().withName("newGroup"));
    app.goTo().homePage();
  }

  @Test
  public void addNewContact() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("fName").withMiddlename("mName").withLastname("lNname").withNickname("nName").withCompany("ooo")
            .withEmail("Email@com").withHomeTelephone("8-111-111").withGroup("newGroup");
    app.goTo().homePage();
    app.contact().create(contact, true);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
