package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.UserProfilCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserFetcher;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

@Controller
public class ProfilUserController {

   private static final List<String> LIST_OF_EXPECTED_ROLES = new ArrayList<String>();

   private UserAuthorizationService userAuthorizationService;

   private UserProfilCorruptionVerificator userProfilCorruptionVerificator;

   private UserFetcher userFetcher;

   @Autowired
   public ProfilUserController(UserAuthorizationService userAuthorizationService, UserFetcher userFetcher,
         UserProfilCorruptionVerificator userProfilCorruptionVerificator) {
      this.userAuthorizationService = userAuthorizationService;
      this.userFetcher = userFetcher;
      this.userProfilCorruptionVerificator = userProfilCorruptionVerificator;
      configureAcceptedRoles();
   }

   private void configureAcceptedRoles() {
      LIST_OF_EXPECTED_ROLES.add(Role.SELLER);
      LIST_OF_EXPECTED_ROLES.add(Role.BUYER);
   }

   @RequestMapping(value = "/profil", method = RequestMethod.GET)
   public ModelAndView getProfil(HttpServletRequest request)
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessDataException {

      userAuthorizationService.verifySessionIsAllowed(request, LIST_OF_EXPECTED_ROLES);

      UserDto userDto = userFetcher.getUserByUsername(
            request.getSession().getAttribute(UserAuthorizationService.LOGGED_IN_USERNAME).toString());

      ModelAndView profilViewModel = new ModelAndView("profil");
      profilViewModel.addObject("user", userDto);

      return profilViewModel;
   }

   @RequestMapping(value = "/profil", method = RequestMethod.POST)
   public String updateProfil(HttpServletRequest request, UserDto userDetailedDto)
         throws InvalidAccessException, CouldNotAccessDataException, UserNotFoundException,
         InvalidContactInformationFieldException, UserNotifyingException {

      userAuthorizationService.verifySessionIsAllowed(request, LIST_OF_EXPECTED_ROLES);

      userProfilCorruptionVerificator.update(userDetailedDto);

      return "redirect:/profil";
   }

}
