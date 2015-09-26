package ca.ulaval.glo4003.b6.housematch.user.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.admin.repository.AdminRepository;
import ca.ulaval.glo4003.b6.housematch.admin.repository.exception.CouldNotAccesAdminDataException;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;

public class UserLoginService {

   private UserRepository userRepository;

   private AdminRepository adminRepository;

   @Autowired
   public UserLoginService(UserRepository userRepository, AdminRepository adminRepository) {

      this.userRepository = userRepository;
      this.adminRepository = adminRepository;

   }

   public void login(HttpServletRequest request, UserLoginDto userLoginDto) throws CouldNotAccesAdminDataException {

      User user = userRepository.findByUsername(userLoginDto.getUsername());

      request.getSession().setAttribute("loggedInUserRole", "user");

      if (adminRepository.isAdmin(user.getUsername())) {
         request.getSession().setAttribute("loggedInUserRole", "admin");
      }

      request.getSession().setAttribute("loggedInUserEmail", user.getContactInformation().getEmail());

   }

}
