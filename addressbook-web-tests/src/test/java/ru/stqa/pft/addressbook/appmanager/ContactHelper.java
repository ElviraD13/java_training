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

  public void selectContact() {

    click(By.xpath(".//tr[@name='entry'][1]/td[@class='center']/input"));
    }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void selectGroupToAdd() {
    click(By.xpath("//div/div[4]/form[2]/div[4]/select/option[3]"));
  }

  public void addContactToGroup() {

    click(By.name("add"));
  }

  public void goToNewGroupPage() {
    click(By.xpath("//div/div[4]/div/i/a"));
  }

  public void selectAllGroupShown() {

    click(By.xpath("//form[@id='right']/select//option[2]"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContactToEdit() {
    click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img"));
  }
}



