package ca.ulaval.glo4003.b6.housematch.user.dto;

public class ContactInformationDto {

   public ContactInformationDto(String firstName, String lastName, String phoneNumber, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
      this.email = email;
   }

   private String firstName;

   private String lastName;

   private String phoneNumber;

   private String email;

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

}
