package ca.ulaval.glo4003.b6.housematch.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.admin.repository.AdminRepository;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.UserValidator;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.UserValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.user.model.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserDao;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public class UserLoginService {

   private UserDao userRepository;

   private AdminRepository adminRepository;

   private UserValidatorFactory userValidatorFactory;

   @Autowired
   public UserLoginService(UserDao userRepository, AdminRepository adminRepository,
         UserValidatorFactory userValidatorFactory) {

      this.userRepository = userRepository;
      this.adminRepository = adminRepository;
      this.userValidatorFactory = userValidatorFactory;
   }

   public void setUserRepository(UserDao userRepository) {
      this.userRepository = userRepository;
   }

   public void setAdminRepository(AdminRepository adminRepository) {
      this.adminRepository = adminRepository;
   }

   public User login(HttpServletRequest request, UserLoginDto userLoginDto)
         throws UserNotFoundException, CouldNotAccessDataException {
      UserValidator userValidator = userValidatorFactory.getValidator();
      userValidator.validate(userLoginDto);
      // TODO CHANGE REPO METHOD TO BE FIND BY USERNAME
      User user = userRepository.findByEmail(userLoginDto.getUsername());
      request.getSession().setAttribute("loggedInUserRole", "user");

      // if (adminRepository.isAdmin(user.getEmail())) {
      // request.getSession().setAttribute("loggedInUserRole", "admin");
      // }
      request.getSession().setAttribute("loggedInUserEmail", user.getEmail());

      return user;
   }

}
