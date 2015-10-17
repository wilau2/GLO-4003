package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.UserProfilCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.user.services.UserFetcher;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidAccessException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserNotifyingException;
import ca.ulaval.glo4003.b6.housematch.web.converters.ProfilUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.ProfilUserViewModel;

@Controller
public class ProfilUserController {

   private static final String EXPECTED_ROLE = Role.BUYER;

   private UserAuthorizationService userAuthorizationService;

   private UserProfilCorruptionVerificator userProfilCorruptionVerificator;

   private ProfilUserConverter profilUserConverter;

   private UserFetcher userFetcher;

   @Autowired
   public ProfilUserController(UserAuthorizationService userAuthorizationService,
         ProfilUserConverter profilUserConverter, UserFetcher userFetcher,
         UserProfilCorruptionVerificator userProfilCorruptionVerificator) {
      this.userAuthorizationService = userAuthorizationService;
      this.profilUserConverter = profilUserConverter;
      this.userFetcher = userFetcher;
      this.userProfilCorruptionVerificator = userProfilCorruptionVerificator;
   }

   @RequestMapping(value = "/profil", method = RequestMethod.GET)
   public ModelAndView getProfil(HttpServletRequest request)
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessUserDataException {

      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      UserDto userDto = userFetcher.getUserByUsername(
            request.getSession().getAttribute(UserAuthorizationService.LOGGED_IN_USERNAME).toString());

      ProfilUserViewModel viewModel = profilUserConverter.convertToViewModel(userDto);

      ModelAndView profilViewModel = new ModelAndView("profil");
      profilViewModel.addObject("user", viewModel);

      return profilViewModel;
   }

   @RequestMapping(value = "/profil", method = RequestMethod.POST)
   public String updateProfil(HttpServletRequest request, ProfilUserViewModel viewModel)
         throws InvalidAccessException, CouldNotAccessUserDataException, UserNotFoundException,
         InvalidContactInformationFieldException, UserNotifyingException {

      userAuthorizationService.verifySessionIsAllowed(request, EXPECTED_ROLE);

      UserDto userDetailedDto = profilUserConverter.convertViewModelToDto(viewModel);

      userProfilCorruptionVerificator.update(userDetailedDto);

      return "redirect:/profil";
   }

}
