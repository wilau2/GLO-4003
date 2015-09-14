package org.ulaval.teamb6.housematch2.web.converters;

import org.springframework.stereotype.Component;
import org.ulaval.teamb6.housematch2.domain.User;
import org.ulaval.teamb6.housematch2.web.viewModel.LoginUserModel;

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
