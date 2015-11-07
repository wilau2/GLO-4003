package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.services.admin.AdminStatisticService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

@Controller
public class AdminStatisticController {

   private static final String ADMIN_ROLE = Role.ADMIN;

   private UserAuthorizationService userAuthorizationService;

   private AdminStatisticService adminStatisticService;

   @Inject
   public AdminStatisticController(UserAuthorizationService userAuthorizationService,
         AdminStatisticService adminStatisticService) {
      this.userAuthorizationService = userAuthorizationService;
      this.adminStatisticService = adminStatisticService;
   }

   @RequestMapping(path = "/admin/{userId}/statistique")
   public ModelAndView getNumberOfActiveUser(HttpServletRequest request) throws InvalidAccessException {
      userAuthorizationService.verifySessionIsAllowed(request, ADMIN_ROLE);

      int numberOfActiveUser = adminStatisticService.getNumberOfActiveUser();

      ModelAndView numberOfActiveUserViewModel = new ModelAndView("userActiveStatistic");
      numberOfActiveUserViewModel.addObject("numberOfActiveUser", numberOfActiveUser);

      return numberOfActiveUserViewModel;
   }

}
