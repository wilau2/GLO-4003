package ca.ulaval.glo4003.b6.housematch.web.viewModel;

public class ProfilUserViewModel {

   private String username;

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

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public void setEmail(String email) {
      this.email = email;
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

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }
}
