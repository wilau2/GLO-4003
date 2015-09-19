package ca.ulaval.glo4003.b6.housematch.web.converters;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

@Component
public class SignupUserConverter {

   public SignupUserModel convertToViewModel(UserSignupDto user) {
      SignupUserModel viewModel = new SignupUserModel();
      viewModel.setFirstName(user.getFirstName());
      viewModel.setLastName(user.getLastName());
      viewModel.setUsername(user.getUsername());
      viewModel.setEmail(user.getEmail());
      viewModel.setPhoneNumber(user.getPhoneNumber());
      viewModel.setPassword(user.getPassword());
      return viewModel;
   }

   public UserSignupDto convertToDto(SignupUserModel viewModel) {
      UserSignupDto user = new UserSignupDto();
      user.setEmail(viewModel.getEmail());
      user.setPassword(viewModel.getPassword());
      return user;
   }

}
