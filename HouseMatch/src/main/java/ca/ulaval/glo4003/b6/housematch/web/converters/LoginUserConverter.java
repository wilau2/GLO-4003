package ca.ulaval.glo4003.b6.housematch.web.converters;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.LoginUserViewModel;

@Component
public class LoginUserConverter {

   public LoginUserViewModel convertToViewModel(UserDto user) {
      LoginUserViewModel viewModel = new LoginUserViewModel();
      viewModel.setUsername(user.getUsername());
      viewModel.setPassword(user.getPassword());
      return viewModel;
   }

   public UserDto convertToDto(LoginUserViewModel viewModel) {
      UserDto userDto = new UserDto();
      userDto.setEmail(viewModel.getUsername());
      userDto.setPassword(viewModel.getPassword());
      return userDto;
   }
}
