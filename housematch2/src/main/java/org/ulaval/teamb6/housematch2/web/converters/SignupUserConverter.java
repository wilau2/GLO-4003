package org.ulaval.teamb6.housematch2.web.converters;

import org.springframework.stereotype.Component;
import org.ulaval.teamb6.housematch2.model.User;
import org.ulaval.teamb6.housematch2.web.viewmodels.SignupUserViewModel;

@Component
public class SignupUserConverter {

  public SignupUserViewModel convert(User entry) {
    SignupUserViewModel viewModel = new SignupUserViewModel();
    viewModel.email = entry.getEmail();
    viewModel.password = entry.getPassword();
    return viewModel;
  }

  public User convert(SignupUserViewModel viewModel) {
    User entry = new User();
    entry.setEmail(viewModel.email);
    entry.setPassword(viewModel.password);
    return entry;
  }

}
