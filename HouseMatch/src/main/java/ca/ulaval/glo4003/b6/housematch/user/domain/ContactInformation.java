package ca.ulaval.glo4003.b6.housematch.user.domain;

public class ContactInformation {

   private String firstName;

   private String lastName;

   private String phoneNumber;

   private String email;

   public ContactInformation(String firstName, String lastName, String phoneNumber, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
      this.email = email;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public String getEmail() {
      return email;
   }

   public void update(ContactInformation newContactInformation) {
      this.firstName = newContactInformation.firstName;
      this.lastName = newContactInformation.lastName;
      this.phoneNumber = newContactInformation.phoneNumber;
      this.email = newContactInformation.email;
   }

}
