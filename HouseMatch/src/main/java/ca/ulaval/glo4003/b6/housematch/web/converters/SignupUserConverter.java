package ca.ulaval.glo4003.b6.housematch.web.converters;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.web.viewmodels.SignupUserModel;

@Component
public class SignupUserConverter {

   public SignupUserModel convertSignupDtoToViewModel(UserDto userSignupDto) {
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

   public UserDto convertViewModelToSignupDto(SignupUserModel viewModel) {
      UserDto userDto = new UserDto();
      userDto.setUsername(viewModel.getUsername());
      userDto.setContactInformationDto(new ContactInformationDto(viewModel.getFirstName(), viewModel.getLastName(),
            viewModel.getPhoneNumber(), viewModel.getEmail()));
      userDto.setRole(viewModel.getRole());
      userDto.setPassword(viewModel.getPassword());
      return userDto;
   }

}
