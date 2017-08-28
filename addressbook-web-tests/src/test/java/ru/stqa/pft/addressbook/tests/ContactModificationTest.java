package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.getContactHelper().selectContactToEdit();
    app.getContactHelper().fillNewContactForm(new NewContactData("Firstname", "Mname", "Lname","Nname", "ooo\"company\"", "Email@gmail.com", "7-777-777"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }

}
