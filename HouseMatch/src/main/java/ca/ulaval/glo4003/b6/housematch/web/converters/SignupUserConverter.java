package ca.ulaval.glo4003.b6.housematch.web.converters;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

@Component
public class SignupUserConverter {

   public SignupUserModel convertSignupDtoToViewModel(UserSignupDto user) {
      SignupUserModel viewModel = new SignupUserModel();
      viewModel.setFirstName(user.getFirstName());
      viewModel.setLastName(user.getLastName());
      viewModel.setUsername(user.getUsername());
      viewModel.setEmail(user.getEmail());
      viewModel.setPhoneNumber(user.getPhoneNumber());
      viewModel.setPassword(user.getPassword());
      return viewModel;
   }

   public UserSignupDto convertViewModelToSignupDto(SignupUserModel viewModel) {
      UserSignupDto user = new UserSignupDto();
      user.setRole(viewModel.getRole());
      user.setFirstName(viewModel.getFirstName());
      user.setLastName(viewModel.getLastName());
      user.setPhoneNumber(viewModel.getPhoneNumber());
      user.setEmail(viewModel.getEmail());
      user.setUsername(viewModel.getUsername());
      user.setPassword(viewModel.getPassword());
      return user;
   }

   public UserLoginDto convertSignupDtoToLoginDto(UserSignupDto userSignupDto) {
      UserLoginDto user = new UserLoginDto();
      user.setUsername(userSignupDto.getUsername());
      user.setPassword(userSignupDto.getPassword());
      return user;
   }
}
