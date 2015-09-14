package org.ulaval.teamb6.housematch2.web.converters;

import org.springframework.stereotype.Component;
import org.ulaval.teamb6.housematch2.model.User;
import org.ulaval.teamb6.housematch2.web.viewmodels.LoginUserViewModel;

@Component
public class LoginUserConverter {

  public LoginUserViewModel convert(User user) {
    LoginUserViewModel viewModel = new LoginUserViewModel();
    viewModel.email = user.getEmail();
    viewModel.password = user.getPassword();
    return viewModel;
  }

  public User convert(LoginUserViewModel viewModel) {
    User user = new User();
    user.setEmail(viewModel.email);
    user.setPassword(viewModel.password);
    return user;
  }
}
