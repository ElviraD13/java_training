package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super (wd);
  }

  public void enterNewContact() {

    click(By.name("submit"));
  }

  public void fillNewContactForm(ContactData newContactData, boolean creation) {
    type(By.name("firstname"),  newContactData.getFirstname());
    type(By.name("middlename"), newContactData.getMiddlename());
    type(By.name("lastname"), newContactData.getLastname());
    type(By.name("nickname"), newContactData.getNickname());
    type(By.name("company"), newContactData.getCompany());
    type(By.name("email"), newContactData.getEmail());
    type(By.name("home"), newContactData.getHomePhone());
    type(By.name("mobile"), newContactData.getMobilePhone());
    type(By.name("work"), newContactData.getWorkPhone());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void gotoAddNewPage() {

    click(By.linkText("add new"));
  }

  public void selectById(int id) {

    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }


  public void delete() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void submitContactModification() {

    click(By.name("update"));
  }

  public void selectContactToEditById(int id) {
     wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void create(ContactData Contact, boolean b) {
    gotoAddNewPage();
    fillNewContactForm(Contact, b);
    enterNewContact();
    }

  public void modify(ContactData contact) {
    selectContactToEditById(contact.getId());
    fillNewContactForm(contact, false);
    submitContactModification();
  }

  public void delete(ContactData contact) {
    selectById(contact.getId());
    delete();
  }

  public boolean isThereAnyContact() {
    return isElementPresent(By.xpath(".//tr[@name='entry'][1]/td[@class='center']/input"));
  }

  public void addFirstContact(ContactData contact, boolean b) {
    if (all().size()== 0){
      create(contact, b);
    }

  }

  public int getContactCount() {
   return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath(".//tr[@name='entry']"));
    for (WebElement element : elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones);
      contacts.add(contact);
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value = '%s']",id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }
}



