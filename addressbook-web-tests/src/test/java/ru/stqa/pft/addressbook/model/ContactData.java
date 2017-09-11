package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int id;
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String company;
  private final String email;
  private final String homeTelephone;
  private String group;

  public ContactData(int id, String firstname, String middlename, String lastname, String nickname, String company, String email, String homeTelephone, String group) {
    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.company = company;
    this.email = email;
    this.homeTelephone = homeTelephone;
    this.group = group;
  }

  public ContactData(String firstname, String middlename, String lastname, String nickname, String company, String email, String homeTelephone, String group) {
    this.id = Integer.MAX_VALUE;
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}
