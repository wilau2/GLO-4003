package ca.ulaval.glo4003.b6.housematch.web.converters;

import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.ProfilUserViewModel;

public class ProfilUserConverter {

   public ProfilUserViewModel convertToViewModel(UserDetailedDto user) {
      ProfilUserViewModel viewModel = new ProfilUserViewModel();
      viewModel.setUsername(user.getUsername());
      viewModel.setFirstName(user.getContactInformationDto().getFirstName());
      viewModel.setLastName(user.getContactInformationDto().getLastName());
      viewModel.setPhoneNumber(user.getContactInformationDto().getPhoneNumber());
      viewModel.setEmail(user.getContactInformationDto().getEmail());
      return viewModel;
   }

   public UserDetailedDto convertViewModelToDto(ProfilUserViewModel viewModel) {
      UserDetailedDto userDetailedDto = new UserDetailedDto(viewModel.getUsername());
      ContactInformationDto contactInformationDto = new ContactInformationDto(viewModel.getFirstName(),
            viewModel.getLastName(), viewModel.getPhoneNumber(), viewModel.getEmail());
      userDetailedDto.setContactInformationDto(contactInformationDto);
      return userDetailedDto;
   }
}
