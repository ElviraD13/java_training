package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class AddNewTest extends TestBase {

  @Test
  public void addNewContact() {
    app.getContactHelper().gotoAddNewPage();
    app.getContactHelper().fillNewContactForm(new NewContactData("Fname", "Mname", "Lname", "Nname", "ooo\"company\"", "Email@gmail.com", "5-555-555"));
    app.getContactHelper().enterNewContact();
    app.getNavigationHelper().returnToHomePage();
  }

}
