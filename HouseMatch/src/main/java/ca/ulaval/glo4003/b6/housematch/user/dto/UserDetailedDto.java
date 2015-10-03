package ca.ulaval.glo4003.b6.housematch.user.dto;

public class UserDetailedDto extends BaseUserDto {

   public UserDetailedDto(String username) {
      super(username);
   }

   private String firstName;

   private String lastName;

   private String phoneNumber;

   private String email;

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
