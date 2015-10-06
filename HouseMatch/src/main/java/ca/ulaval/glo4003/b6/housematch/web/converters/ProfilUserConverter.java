package ca.ulaval.glo4003.b6.housematch.web.converters;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.ProfilUserViewModel;

public class ProfilUserConverter {

   public ProfilUserViewModel convertToViewModel(UserDetailedDto user) {
      ProfilUserViewModel viewModel = new ProfilUserViewModel();
      viewModel.setUsername(user.getUsername());
      viewModel.setFirstName(user.getFirstName());
      viewModel.setLastName(user.getLastName());
      viewModel.setPhoneNumber(user.getPhoneNumber());
      viewModel.setEmail(user.getEmail());
      return viewModel;
   }

   public UserDetailedDto convertViewModelToDto(ProfilUserViewModel viewModel) {
      UserDetailedDto userDto = new UserDetailedDto(viewModel.getUsername());
      userDto.setFirstName(viewModel.getFirstName());
      userDto.setLastName(viewModel.getLastName());
      userDto.setPhoneNumber(viewModel.getPhoneNumber());
      userDto.setEmail(viewModel.getEmail());
      return userDto;
   }
}
