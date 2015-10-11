package ca.ulaval.glo4003.b6.housematch.user.dto;

public class UserDetailedDto extends BaseUserDto {

   private ContactInformationDto contactInformationDto;

   public UserDetailedDto(String username) {
      super(username);
   }

   public ContactInformationDto getContactInformationDto() {
      return contactInformationDto;
   }

   public void setContactInformationDto(ContactInformationDto contactInformationDto) {
      this.contactInformationDto = contactInformationDto;
   }
}
