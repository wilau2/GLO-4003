package ca.ulaval.glo4003.b6.housematch.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.admin.repository.AdminRepository;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.UserValidator;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.UserValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.user.model.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;

public class UserLoginService {

   private UserRepository userRepository;

   private AdminRepository adminRepository;

   private UserValidatorFactory userValidatorFactory;

   public void setUserRepository(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public void setAdminRepository(AdminRepository adminRepository) {
      this.adminRepository = adminRepository;
   }

   @Autowired
   public UserLoginService(UserRepository userRepository, AdminRepository adminRepository) {
      this.userRepository = userRepository;
      this.adminRepository = adminRepository;
   }

   public User login(HttpServletRequest request, UserDto userDto) {
      UserValidator userValidator = userValidatorFactory.getValidator();
      userValidator.validate(userDto);

      User user = userRepository.findByEmail(userDto.getEmail());
      request.getSession().setAttribute("loggedInUserRole", "user");

      // if (adminRepository.isAdmin(user.getEmail())) {
      // request.getSession().setAttribute("loggedInUserRole", "admin");
      // }
      request.getSession().setAttribute("loggedInUserEmail", user.getEmail());

      return user;
   }

}
