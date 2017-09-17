package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class AddContactTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    app.group().addFirstGroup(new GroupData().withName("newGroup"));
    app.goTo().homePage();
  }

  @Test
  public void addNewContact() {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("fName").withMiddlename("mName").withLastname("lNname").withNickname("nName").withCompany("ooo")
            .withEmail("Email@com").withHomeTelephone("8-111-111").withGroup("newGroup");
    app.goTo().homePage();
    app.contact().create(contact, true);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
