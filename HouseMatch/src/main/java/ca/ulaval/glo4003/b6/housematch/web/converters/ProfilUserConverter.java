package ca.ulaval.glo4003.b6.housematch.web.converters;

import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.ProfilUserViewModel;

public class ProfilUserConverter {

   public ProfilUserViewModel convertToViewModel(UserDto user) {
      ProfilUserViewModel viewModel = new ProfilUserViewModel();
      viewModel.setUsername(user.getUsername());
      viewModel.setFirstName(user.getContactInformationDto().getFirstName());
      viewModel.setLastName(user.getContactInformationDto().getLastName());
      viewModel.setPhoneNumber(user.getContactInformationDto().getPhoneNumber());
      viewModel.setEmail(user.getContactInformationDto().getEmail());
      return viewModel;
   }

   public UserDto convertViewModelToDto(ProfilUserViewModel viewModel) {
      UserDto userDto = new UserDto();
      userDto.setUsername(viewModel.getUsername());
      userDto.setContactInformationDto(new ContactInformationDto(viewModel.getFirstName(), viewModel.getLastName(),
            viewModel.getPhoneNumber(), viewModel.getEmail()));

      return userDto;
   }
}
