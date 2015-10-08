package ca.ulaval.glo4003.b6.housematch.user.dto;

public class UserDetailedDto extends BaseUserDto {

   public UserDetailedDto(String username) {
      super(username);
   }

   private ContactInformationDto contactInformationDto;

   public ContactInformationDto getContactInformationDto() {
      return contactInformationDto;
   }

   public void setContactInformationDto(ContactInformationDto contactInformationDto) {
      this.contactInformationDto = contactInformationDto;
   }
}
