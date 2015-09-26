package ca.ulaval.glo4003.b6.housematch.user.dto;

public class UserLoginDto extends BaseUserDto {

   public UserLoginDto(String username) {
      super(username);
   }

   private String password;

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

}
