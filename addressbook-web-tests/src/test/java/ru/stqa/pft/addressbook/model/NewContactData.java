package ru.stqa.pft.addressbook.model;

public class NewContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String company;
  private final String email;
  private final String homeTelephone;
  private String group;

  public NewContactData(String firstname, String middlename, String lastname, String nickname, String company, String email, String homeTelephone, String group) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.company = company;
    this.email = email;
    this.homeTelephone = homeTelephone;
    this.group = group;
  }

  public String getFirstname() {

    return firstname;
  }

  public String getMiddlename() {

    return middlename;
  }

  public String getLastname() {

    return lastname;
  }

  public String getNickname() {

    return nickname;
  }

  public String getCompany() {

    return company;
  }

  public String getEmail() {

    return email;
  }

  public String getHomeTelephone() {

    return homeTelephone;
  }

  public String getGroup() {

    return group;
  }
}
