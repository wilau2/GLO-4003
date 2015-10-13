package ca.ulaval.glo4003.b6.housematch.user.domain;

public class User {

   private String username;

   private String password;

   private ContactInformation contactInformation;

   private Role role;
   
   private Boolean active = false;

   public User(String username, String password, ContactInformation contactInformation, Role role) {
      this.username = username;
      this.password = password;
      this.contactInformation = contactInformation;
      this.role = role;
   }

   public boolean isSeller() {
      return role.hasSeller();
   }

   public boolean isBuyer() {
      return role.hasBuyer();
   }

   public boolean isAdmin() {
      return role.hasAdmin();
   }

   public void updateContactInformation(ContactInformation contactInformation) {
      this.contactInformation.update(contactInformation);
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

   public Boolean isActive() {
      return active;
   }

   public void setActive(Boolean active) {
      this.active = active;
   }
}
