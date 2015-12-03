package ca.ulaval.glo4003.b6.housematch.domain.user;

import java.time.LocalDateTime;

public class User {

   private static final long SIX_MONTHS = 6;

   private String username;

   private String password;

   private ContactInformation contactInformation;

   private Role role;

   private Boolean active = false;

   private LocalDateTime dateOfLastActivity;

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

   public void updateContactInformation(ContactInformation newContactInformation) {

      if (isEmailChanged(newContactInformation)) {
         setActive(false);
      }
      contactInformation.update(newContactInformation);

   }

   public boolean isEmailChanged(ContactInformation newContactInformation) {

      return contactInformation.isEmailChanged(newContactInformation);

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

   public void setDateOfLastActivity(LocalDateTime dateOfLastActivity) {
      this.dateOfLastActivity = dateOfLastActivity;
   }

   public boolean wasActiveInTheLastSixMonths() {
      if (active && isDateOfLastActivityLessThanSixMonthsAgo()) {
         return true;
      }
      return false;
   }

   private boolean isDateOfLastActivityLessThanSixMonthsAgo() {
      LocalDateTime dateSixMonthsFromNow = LocalDateTime.now().minusMonths(SIX_MONTHS);

      boolean isDateOfLastActivityLessThanSixMonthsAgo = dateOfLastActivity.isAfter(dateSixMonthsFromNow);

      return isDateOfLastActivityLessThanSixMonthsAgo;
   }

   public LocalDateTime getDateOfLastActivity() {
      return dateOfLastActivity;
   }

   public void updateLastActivity() {
      dateOfLastActivity = LocalDateTime.now();
   }

   public boolean validateLoginCredential(String passwordToVerify) {
      if (password.equals(passwordToVerify)) {
         return true;
      }
      return false;
   }

}
