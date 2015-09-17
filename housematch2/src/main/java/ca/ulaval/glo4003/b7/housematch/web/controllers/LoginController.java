package ca.ulaval.glo4003.b7.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b7.housematch.admin.repository.AdminRepository;
import ca.ulaval.glo4003.b7.housematch.admin.repository.XMLAdminRepository;
import ca.ulaval.glo4003.b7.housematch.user.model.User;
import ca.ulaval.glo4003.b7.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b7.housematch.user.repository.XMLUserRepository;
import ca.ulaval.glo4003.b7.housematch.web.converters.LoginUserConverter;
import ca.ulaval.glo4003.b7.housematch.web.viewModel.LoginUserModel;

@Controller
public class LoginController {

  private UserRepository userRepository;

  private AdminRepository adminRepository;

  private LoginUserConverter converter;

  @Autowired
  public LoginController(XMLUserRepository xmlUserRepository, XMLAdminRepository xmlAminRepository,
      LoginUserConverter converter) {
    this.userRepository = xmlUserRepository;
    this.adminRepository = xmlAminRepository;
    this.converter = converter;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(HttpServletRequest request, LoginUserModel viewModel) {
    String loggedEmail = converter.convert(viewModel).getEmail();
    User user = userRepository.findByEmail(loggedEmail);
    request.getSession().setAttribute("loggedInUserRole", "user");
    if (adminRepository.isAdmin(loggedEmail)) {
      request.getSession().setAttribute("loggedInUserRole", "admin");
    }
    request.getSession().setAttribute("loggedInUserEmail", user.email);
    return "redirect:/";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Model model) {
    model.addAttribute("user", new LoginUserModel());
    return "login";

  }
}
