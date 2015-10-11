package ca.ulaval.glo4003.b6.housematch.user.dto;

public class UserLoginDto extends BaseUserDto {

   private String password;

   public UserLoginDto(String username) {
      super(username);
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

}
