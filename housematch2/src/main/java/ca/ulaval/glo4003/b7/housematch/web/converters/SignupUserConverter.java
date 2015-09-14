package ca.ulaval.glo4003.b7.housematch.web.converters;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.b7.housematch.domain.User;
import ca.ulaval.glo4003.b7.housematch.web.viewModel.SignupUserModel;

@Component
public class SignupUserConverter {

  public SignupUserModel convert(User user) {
    SignupUserModel viewModel = new SignupUserModel();
    viewModel.email = user.getEmail();
    viewModel.password = user.getPassword();
    return viewModel;
  }

  public User convert(SignupUserModel viewModel) {
    User user = new User();
    user.setEmail(viewModel.email);
    user.setPassword(viewModel.password);
    return user;
  }

}
