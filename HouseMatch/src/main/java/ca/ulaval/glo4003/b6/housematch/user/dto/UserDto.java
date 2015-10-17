package ca.ulaval.glo4003.b6.housematch.user.dto;

public class UserDto {

   private String username;

   private String password;

   private String role;

   private ContactInformationDto contactInformationDto;

   public UserDto() {
   }

   public ContactInformationDto getContactInformationDto() {
      return contactInformationDto;
   }

   public void setContactInformationDto(ContactInformationDto contactInformationDto) {
      this.contactInformationDto = contactInformationDto;
   }

   public String getUsername() {
      return this.username;
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

   public void setUsername(String username) {
      this.username = username;
   }

}
