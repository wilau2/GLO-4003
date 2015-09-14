package org.ulaval.teamb6.housematch2.web.converters;

import org.springframework.stereotype.Component;
import org.ulaval.teamb6.housematch2.domain.User;
import org.ulaval.teamb6.housematch2.service.SignupNewUser;

@Component
public class SignupUserConverter {

  public SignupNewUser convert(User user) {
    SignupNewUser viewModel = new SignupNewUser();
    viewModel.email = user.getEmail();
    viewModel.password = user.getPassword();
    return viewModel;
  }

  public User convert(SignupNewUser viewModel) {
    User user = new User();
    user.setEmail(viewModel.email);
    user.setPassword(viewModel.password);
    return user;
  }

}
