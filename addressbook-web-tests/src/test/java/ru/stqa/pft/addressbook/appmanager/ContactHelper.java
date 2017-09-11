package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super (wd);
  }

  public void enterNewContact() {

    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillNewContactForm(ContactData newContactData, boolean creation) {
    type(By.name("firstname"),  newContactData.getFirstname());
    type(By.name("middlename"), newContactData.getMiddlename());
    type(By.name("lastname"), newContactData.getLastname());
    type(By.name("nickname"), newContactData.getNickname());
    type(By.name("company"), newContactData.getCompany());
    type(By.name("email"), newContactData.getEmail());
    type(By.name("home"), newContactData.getHomeTelephone());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void gotoAddNewPage() {

    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
  wd.findElements(By.xpath("//td[1]/input")).get(index).click();
     }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void submitContactModification() {

    click(By.name("update"));
  }

  public void selectContactToEdit(int index) {
    wd.findElements(By.xpath("//td[8]/a/img")).get(index).click();
     }

  public void createContact(ContactData Contact, boolean b) {
    gotoAddNewPage();
    fillNewContactForm(Contact, b);
    enterNewContact();
    }

  public boolean isThereAnyContact() {
    return isElementPresent(By.xpath(".//tr[@name='entry'][1]/td[@class='center']/input"));
  }

  public void addFirstContact(ContactData contact, boolean b) {
    if (! isThereAnyContact()){
      createContact(contact, b);
    }

  }

  public int getContactCount() {
   return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath(".//tr[@name='entry']"));
          for (WebElement element : elements){
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id,firstname, null,lastname,null, null, null, null, null);
            contacts.add(contact);
    }
    return contacts;
  }
}



