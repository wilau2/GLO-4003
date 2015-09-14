package org.ulaval.teamb6.housematch2.web.converters;

import org.springframework.stereotype.Component;
import org.ulaval.teamb6.housematch2.domain.User;
import org.ulaval.teamb6.housematch2.service.LoginExistingUser;

@Component
public class LoginUserConverter {

  public LoginExistingUser convert(User user) {
    LoginExistingUser viewModel = new LoginExistingUser();
    viewModel.email = user.getEmail();
    viewModel.password = user.getPassword();
    return viewModel;
  }

  public User convert(LoginExistingUser viewModel) {
    User user = new User();
    user.setEmail(viewModel.email);
    user.setPassword(viewModel.password);
    return user;
  }
}
