package org.ulaval.teamb6.housematch2.web.converters;

import org.springframework.stereotype.Component;
import org.ulaval.teamb6.housematch2.domain.User;
import org.ulaval.teamb6.housematch2.web.viewModel.SignupUserModel;

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
