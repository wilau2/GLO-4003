package ca.ulaval.glo4003.b6.housematch.dto;

public class ContactInformationDto {

   private String firstName;

   private String lastName;

   private String phoneNumber;

   private String email;

   public ContactInformationDto(String firstName, String lastName, String phoneNumber, String email) {
      this.setFirstName(firstName);
      this.setLastName(lastName);
      this.setPhoneNumber(phoneNumber);
      this.setEmail(email);
   }

   public ContactInformationDto() {

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

   public void setEmail(String email) {
      this.email = email;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

}
