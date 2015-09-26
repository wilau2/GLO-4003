package ca.ulaval.glo4003.b6.housematch.web.converters;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

@Component
public class SignupUserConverter {

   public SignupUserModel convertSignupDtoToViewModel(UserSignupDto userSignupDto) {
      SignupUserModel viewModel = new SignupUserModel();
      viewModel.setRole(userSignupDto.getRole());
      viewModel.setFirstName(userSignupDto.getFirstName());
      viewModel.setLastName(userSignupDto.getLastName());
      viewModel.setUsername(userSignupDto.getUsername());
      viewModel.setEmail(userSignupDto.getEmail());
      viewModel.setPhoneNumber(userSignupDto.getPhoneNumber());
      viewModel.setPassword(userSignupDto.getPassword());
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
