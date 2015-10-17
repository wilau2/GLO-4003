package ca.ulaval.glo4003.b6.housematch.web.converters;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.web.viewmodels.LoginUserViewModel;

@Component
public class LoginUserConverter {

   public LoginUserViewModel convertToViewModel(UserDto user) {
      LoginUserViewModel viewModel = new LoginUserViewModel();
      viewModel.setUsername(user.getUsername());
      viewModel.setPassword(user.getPassword());
      return viewModel;
   }

   public UserDto convertViewModelToDto(LoginUserViewModel viewModel) {
      UserDto userDto = new UserDto();
      userDto.setUsername(viewModel.getUsername());
      userDto.setPassword(viewModel.getPassword());
      return userDto;
   }
}
