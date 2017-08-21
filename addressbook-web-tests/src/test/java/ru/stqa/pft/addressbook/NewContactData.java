package ru.stqa.pft.addressbook;

public class NewContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String company;
  private final String email;
  private final String homeTelephone;

  public NewContactData(String firstname, String middlename, String lastname, String nickname, String company, String email, String homeTelephone) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.company = company;
    this.email = email;
    this.homeTelephone = homeTelephone;
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
}
