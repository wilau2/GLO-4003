package ca.ulaval.glo4003.b6.housematch.web.converters;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.LoginUserViewModel;

@Component
public class LoginUserConverter {

   public LoginUserViewModel convertToViewModel(UserLoginDto user) {
      LoginUserViewModel viewModel = new LoginUserViewModel();
      viewModel.setUsername(user.getUsername());
      viewModel.setPassword(user.getPassword());
      return viewModel;
   }

   public UserLoginDto convertToDto(LoginUserViewModel viewModel) {
      UserLoginDto userDto = new UserLoginDto();
      userDto.setUsername(viewModel.getUsername());
      userDto.setPassword(viewModel.getPassword());
      return userDto;
   }
}
