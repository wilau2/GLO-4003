package ca.ulaval.glo4003.b7.housematch.web.converters;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.b7.housematch.user.model.User;
import ca.ulaval.glo4003.b7.housematch.web.viewModel.LoginUserModel;

@Component
public class LoginUserConverter {

  public LoginUserModel convert(User user) {
    LoginUserModel viewModel = new LoginUserModel();
    viewModel.email = user.getEmail();
    viewModel.password = user.getPassword();
    return viewModel;
  }

  public User convert(LoginUserModel viewModel) {
    User user = new User();
    user.setEmail(viewModel.email);
    user.setPassword(viewModel.password);
    return user;
  }
}
