package ca.ulaval.glo4003.b6.housematch.user.domain;

public class User {

   private String username;

   private String password;

   private ContactInformation contactInformation;

   private Role role;

   public User(String username, String password, ContactInformation contactInformation, Role role) {
      this.username = username;
      this.password = password;
      this.contactInformation = contactInformation;
      this.role = role;
   }

   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }

   public ContactInformation getContactInformation() {
      return contactInformation;
   }

   public Role getRole() {
      return role;
   }
}
