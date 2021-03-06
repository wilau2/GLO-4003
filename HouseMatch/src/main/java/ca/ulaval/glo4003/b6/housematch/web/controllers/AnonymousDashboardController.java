package ca.ulaval.glo4003.b6.housematch.web.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.statistic.StatisticService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserSessionAuthorizationService;

@Controller
public class AnonymousDashboardController {

   private static final String NUMBER_OF_ESTATES_SOLD_LAST_YEAR_KEY = "numberOfEstatesSoldLastYear";

   private static final String NUMBER_OF_ACTIVE_BUYERS_KEY = "numberOfActiveBuyers";

   private static final String NUMBER_OF_ACTIVE_SELLERS_KEY = "numberOfActiveSellers";
   
   private static final String NUMBER_OF_ESTATE_IN_EACH_TYPE_KEY = "numberEstatesInEachType";

   private UserSessionAuthorizationService userSessionAuthorizationService;

   private StatisticService statisticService;

   public AnonymousDashboardController(UserSessionAuthorizationService userSessionAuthorizationService,
         StatisticService statisticService) {
      this.userSessionAuthorizationService = userSessionAuthorizationService;
      this.statisticService = statisticService;
   }

   @RequestMapping(path = "/home", method = RequestMethod.GET)
   public ModelAndView getHomePage(HttpServletRequest request) throws CouldNotAccessDataException, DocumentException {
      userSessionAuthorizationService.isUserLogged(request);

      int numberOfActiveBuyers = statisticService.getNumberOfActiveBuyers();
      int numberOfActiveSellers = statisticService.getNumberOfActiveSellers();
      int numberOfEstatesSoldLastYear = statisticService.getNumberOfEstatesSoldLastYear();    
      HashMap<String, Integer> numberEstatesInEachType = statisticService.getNumberOfDifferentCategoryOfProperties();
      
      ModelAndView modelAndView = new ModelAndView("home");

      modelAndView.addObject(NUMBER_OF_ACTIVE_BUYERS_KEY, numberOfActiveBuyers);
      modelAndView.addObject(NUMBER_OF_ACTIVE_SELLERS_KEY, numberOfActiveSellers);
      modelAndView.addObject(NUMBER_OF_ESTATES_SOLD_LAST_YEAR_KEY, numberOfEstatesSoldLastYear);  
      modelAndView.addObject(NUMBER_OF_ESTATE_IN_EACH_TYPE_KEY, numberEstatesInEachType);
      
      return modelAndView;
   }

}
