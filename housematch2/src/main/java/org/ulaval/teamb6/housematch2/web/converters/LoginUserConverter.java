package org.ulaval.teamb6.housematch2.web.converters;

import org.springframework.stereotype.Component;
import org.ulaval.teamb6.housematch2.model.User;
import org.ulaval.teamb6.housematch2.web.viewmodels.LoginUserViewModel;

@Component
public class LoginUserConverter {

  public LoginUserViewModel convert(User entry) {
    LoginUserViewModel viewModel = new LoginUserViewModel();
    viewModel.email = entry.getEmail();
    viewModel.password = entry.getPassword();
    return viewModel;
  }

  public User convert(LoginUserViewModel viewModel) {
    User entry = new User();
    entry.setEmail(viewModel.email);
    entry.setPassword(viewModel.password);
    return entry;
  }
}
