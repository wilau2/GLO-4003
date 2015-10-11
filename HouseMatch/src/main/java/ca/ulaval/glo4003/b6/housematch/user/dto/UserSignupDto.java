package ca.ulaval.glo4003.b6.housematch.user.dto;

public class UserSignupDto extends UserDetailedDto {

   private String role;

   private String password;

   public UserSignupDto(String username) {
      super(username);
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role;
   }
}
