package ca.ulaval.glo4003.b6.housematch.web.converters;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.SignupUserModel;

@Component
public class SignupUserConverter {

   public SignupUserModel convertSignupDtoToViewModel(UserSignupDto userSignupDto) {
      SignupUserModel viewModel = new SignupUserModel();
      viewModel.setRole(userSignupDto.getRole());
      viewModel.setFirstName(userSignupDto.getContactInformationDto().getFirstName());
      viewModel.setLastName(userSignupDto.getContactInformationDto().getLastName());
      viewModel.setUsername(userSignupDto.getUsername());
      viewModel.setEmail(userSignupDto.getContactInformationDto().getEmail());
      viewModel.setPhoneNumber(userSignupDto.getContactInformationDto().getPhoneNumber());
      viewModel.setPassword(userSignupDto.getPassword());
      return viewModel;
   }

   public UserSignupDto convertViewModelToSignupDto(SignupUserModel viewModel) {
      UserSignupDto user = new UserSignupDto(viewModel.getUsername());
      ContactInformationDto contactInformationDto = new ContactInformationDto(viewModel.getFirstName(),
            viewModel.getLastName(), viewModel.getPhoneNumber(), viewModel.getEmail());
      user.setRole(viewModel.getRole());
      user.setPassword(viewModel.getPassword());
      user.setContactInformationDto(contactInformationDto);
      return user;
   }

   public UserLoginDto convertSignupDtoToLoginDto(UserSignupDto userSignupDto) {
      UserLoginDto user = new UserLoginDto(userSignupDto.getUsername());
      user.setPassword(userSignupDto.getPassword());
      return user;
   }
}
