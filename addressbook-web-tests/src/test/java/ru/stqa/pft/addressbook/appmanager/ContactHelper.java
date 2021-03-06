package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
    type(By.name("address"), newContactData.getAddress());
    type(By.name("email"), newContactData.getEmail());
    type(By.name("email2"), newContactData.getEmail2());
    type(By.name("email3"), newContactData.getEmail3());
    type(By.name("home"), newContactData.getHomePhone());
    type(By.name("mobile"), newContactData.getMobilePhone());
    type(By.name("work"), newContactData.getWorkPhone());
//    attech(By.name("photo"), newContactData.getPhoto());

    if (creation) {
      if (newContactData.getGroups().size() > 0) {
        Assert.assertTrue(newContactData.getGroups().size()==1);
        new Select(wd.findElement(By.name("new_group"))).
                selectByVisibleText(newContactData.getGroups().iterator().next().getName());
      }
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

  public void create(ContactData contact, boolean b) {
    gotoAddNewPage();
    fillNewContactForm(contact, b);
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

  public int count() {
   return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath(".//tr[@name='entry']"));
    for (WebElement element : elements){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones);
      contacts.add(contact);
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value = '%s']",id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public void selectContact() {
    click(By.xpath(".//tr[@name='entry'][1]/td[@class='center']/input"));
  }

  public void groupToAdd()  {
    click(By.xpath("//div/div[4]/form[2]/div[4]/select/option[3]"));
  }

  public void addContactToGroup()  {

    click(By.name("add"));
  }

  public ContactData findContact(Contacts ct, Groups gp) {
    for (ContactData contact : ct){
      if (contact.getGroups().size() < gp.size())
        return contact;
    }
    return null;
  }

  public GroupData groupToAddContact(ContactData ctToAdd, Groups gp) {
    for (GroupData group : gp) {
      if (!ctToAdd.getGroups().contains(group)) {
        return group;
      }
    }
  return null;

  }
  public void addToGroup() {
    selectContact();
    groupToAdd();
    addContactToGroup();
  }

  public void remove() {
    click(By.name("remove"));
  }

  public void showAllContact() {
    wd.findElement(By.xpath("//form[@id='right']/select//option[@value='']")).click();
  }

  public void selectGroup(GroupData group) {
    //wd.findElement(By.xpath("//form[@id='right']/select//option[2]")).click();
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
  }

  public void deleteFromGroup(ContactData contact,GroupData group) {
    selectGroup(group);
   // selectContact();
    selectById(contact.getId());
    remove();
  }
  public GroupData groupToDeleteContact(Contacts contacts, Groups groups) {
    for (GroupData group : groups) {
      for (ContactData contact : contacts) {
        if (contact.getGroups().contains(group)) {
          return group;
        }

      }
    }
    return null;
  }
  public ContactData contactToDelete(Contacts contacts, GroupData group) {
    for (ContactData contact : contacts){
      if (contact.getGroups().contains(group))
        return contact;
    } return null;
  }
}



