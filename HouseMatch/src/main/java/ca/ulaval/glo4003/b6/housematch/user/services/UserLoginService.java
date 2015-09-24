package ca.ulaval.glo4003.b6.housematch.user.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserDao;

public class UserLoginService {

   private UserDao userRepository;

   public void setUserRepository(UserDao userRepository) {
      this.userRepository = userRepository;
   }

   @Autowired
   public UserLoginService(UserDao userRepository) {

      this.userRepository = userRepository;

   }

   public User login(HttpServletRequest request, UserLoginDto userLoginDto) {

      User user = userRepository.findByUsername(userLoginDto.getUsername());

      request.getSession().setAttribute("loggedInUserRole", "user");

      // if (adminRepository.isAdmin(user.getEmail())) {
      // request.getSession().setAttribute("loggedInUserRole", "admin");
      // }
      request.getSession().setAttribute("loggedInUserEmail", user.getContactInformation().getEmail());

      return user;
   }

}
