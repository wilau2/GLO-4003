package ca.ulaval.glo4003.b6.housematch.user.domain;

public class User {

   private String username;

   private String firstName;

   private String lastName;

   private String phoneNumber;

   private String email;

   private String password;

   public User(String username, String firstName, String lastName, String phoneNum, String email, String password) {
      this.username = username;
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNum;
      this.email = email;
      this.password = password;
   }

   public String getUsername() {
      return username;
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

   public String getPassword() {
      return password;
   }

}
