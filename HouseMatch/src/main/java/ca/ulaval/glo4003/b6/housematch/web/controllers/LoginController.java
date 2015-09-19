package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ulaval.glo4003.b6.housematch.admin.repository.AdminRepository;
import ca.ulaval.glo4003.b6.housematch.user.model.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.web.converters.LoginUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.LoginUserModel;

@Controller
public class LoginController {

   private UserRepository userRepository;

   private AdminRepository adminRepository;

   public void setUserRepository(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public void setAdminRepository(AdminRepository adminRepository) {
      this.adminRepository = adminRepository;
   }

   private LoginUserConverter converter;

   @Autowired
   public LoginController(UserRepository userRepository, AdminRepository adminRepository,
         LoginUserConverter converter) {
      this.userRepository = userRepository;
      this.adminRepository = adminRepository;
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
