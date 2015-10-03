package ca.ulaval.glo4003.b6.housematch.user.dto;

public class BaseUserDto {

   private String username;

   public BaseUserDto(String username) {
      this.username = username;
   }

   public String getUsername() {
      return this.username;
   }
}
