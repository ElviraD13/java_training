package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
    super (wd);
  }

  public void enterNewContact() {

    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillNewContactForm(NewContactData newContactData) {
    type(By.name("firstname"),  newContactData.getFirstname());
    type(By.name("middlename"), newContactData.getMiddlename());
    type(By.name("lastname"), newContactData.getLastname());
    type(By.name("nickname"), newContactData.getNickname());
    type(By.name("company"), newContactData.getCompany());
    type(By.name("email"), newContactData.getEmail());
    type(By.name("home"), newContactData.getHomeTelephone());
  }

  public void gotoAddNewPage() {
    click(By.linkText("add new"));
  }
}
