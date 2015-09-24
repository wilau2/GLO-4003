package ca.ulaval.glo4003.b6.housematch.user.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.admin.repository.AdminRepository;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.factory.UserAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.factory.UserValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserDao;

public class UserLoginService {

   private UserDao userRepository;

   private AdminRepository adminRepository;

   private UserValidatorFactory userValidatorFactory;

   private UserAssemblerFactory userAssemblerFactory;

   public void setUserRepository(UserDao userRepository) {
      this.userRepository = userRepository;
   }

   public void setAdminRepository(AdminRepository adminRepository) {
      this.adminRepository = adminRepository;
   }

   @Autowired
   public UserLoginService(UserDao userRepository, AdminRepository adminRepository,
         UserValidatorFactory userValidatorFactory) {

      this.userRepository = userRepository;
      this.adminRepository = adminRepository;
      this.userValidatorFactory = userValidatorFactory;
   }

   public User login(HttpServletRequest request, UserLoginDto userLoginDto) {

      // TODO CHANGE REPO METHOD TO BE FIND BY USERNAME
      User user = userRepository.findByUsername(userLoginDto.getUsername());

      request.getSession().setAttribute("loggedInUserRole", "user");

      // if (adminRepository.isAdmin(user.getEmail())) {
      // request.getSession().setAttribute("loggedInUserRole", "admin");
      // }
      request.getSession().setAttribute("loggedInUserEmail", user.getContactInformation().getEmail());

      return user;
   }

}
