package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.statistic.StatisticService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserSessionAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

@Controller
public class AdminStatisticController {

   private static final String NUMBER_OF_ACTIVE_SELLER_KEY = "numberOfActiveSeller";

   private static final String NUMBER_OF_ACTIVE_BUYER_KEY = "numberOfActiveBuyer";

   private static final String ADMIN_ROLE = Role.ADMIN;

   private UserSessionAuthorizationService userSessionAuthorizationService;

   private StatisticService adminStatisticService;

   @Inject
   public AdminStatisticController(UserSessionAuthorizationService userSessionAuthorizationService,
         StatisticService adminStatisticService) {
      this.userSessionAuthorizationService = userSessionAuthorizationService;
      this.adminStatisticService = adminStatisticService;
   }

   @RequestMapping(path = "/admin/statistic/number_active_buyers", method = RequestMethod.GET)
   public ModelAndView getNumberOfActiveBuyer(HttpServletRequest request)
         throws InvalidAccessException, CouldNotAccessDataException, DocumentException {
      userSessionAuthorizationService.verifySessionIsAllowed(request, ADMIN_ROLE);

      int numberOfActiveBuyer = adminStatisticService.getNumberOfActiveBuyers();

      ModelAndView numberOfActiveUserViewModel = new ModelAndView("buyer_active_statistic");
      numberOfActiveUserViewModel.addObject(NUMBER_OF_ACTIVE_BUYER_KEY, numberOfActiveBuyer);

      return numberOfActiveUserViewModel;
   }

   @RequestMapping(path = "/admin/statistic/number_active_sellers", method = RequestMethod.GET)
   public ModelAndView getNumberOfActiveSellers(HttpServletRequest request)
         throws InvalidAccessException, CouldNotAccessDataException {
      userSessionAuthorizationService.verifySessionIsAllowed(request, ADMIN_ROLE);

      int numberOfActiveSeller = adminStatisticService.getNumberOfActiveSellers();

      ModelAndView modelAndView = new ModelAndView("admin_active_seller");
      modelAndView.addObject(NUMBER_OF_ACTIVE_SELLER_KEY, numberOfActiveSeller);

      return modelAndView;
   }

}
