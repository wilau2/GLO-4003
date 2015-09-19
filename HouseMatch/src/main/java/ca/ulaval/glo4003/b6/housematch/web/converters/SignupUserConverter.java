package ca.ulaval.glo4003.b6.housematch.web.converters;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

@Component
public class SignupUserConverter {

   public SignupUserModel convert(UserDto user) {
      SignupUserModel viewModel = new SignupUserModel();
      viewModel.setFirstName(user.getFirstName());
      viewModel.setLastName(user.getLastName());
      viewModel.setUsername(user.getUsername());
      viewModel.setEmail(user.getEmail());
      viewModel.setPhoneNumber(user.getPhoneNumber());
      viewModel.setPassword(user.getPassword());
      return viewModel;
   }

   public UserDto convert(SignupUserModel viewModel) {
      UserDto user = new UserDto();
      user.setEmail(viewModel.getEmail());
      user.setPassword(viewModel.getPassword());
      return user;
   }

}
