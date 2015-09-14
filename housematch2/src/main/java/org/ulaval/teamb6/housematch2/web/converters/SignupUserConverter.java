package org.ulaval.teamb6.housematch2.web.converters;

import org.springframework.stereotype.Component;
import org.ulaval.teamb6.housematch2.domain.User;
import org.ulaval.teamb6.housematch2.service.SignupUserViewModel;

@Component
public class SignupUserConverter {

  public SignupUserViewModel convert(User user) {
    SignupUserViewModel viewModel = new SignupUserViewModel();
    viewModel.email = user.getEmail();
    viewModel.password = user.getPassword();
    return viewModel;
  }

  public User convert(SignupUserViewModel viewModel) {
    User user = new User();
    user.setEmail(viewModel.email);
    user.setPassword(viewModel.password);
    return user;
  }

}
