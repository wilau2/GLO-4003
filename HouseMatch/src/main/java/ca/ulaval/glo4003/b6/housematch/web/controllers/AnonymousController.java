package ca.ulaval.glo4003.b6.housematch.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.statistic.StatisticService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;

@Controller
public class AnonymousController {

   private UserAuthorizationService userAuthorizationSerive;

   private StatisticService statisticService;

   public AnonymousController(UserAuthorizationService userAuthorizationSerive,
         StatisticService statisticService) {
      this.userAuthorizationSerive = userAuthorizationSerive;
      this.statisticService = statisticService;
   }

   @RequestMapping(path = "/home", method = RequestMethod.GET)
   public ModelAndView getHomePage(HttpServletRequest request) throws CouldNotAccessDataException, DocumentException {
      userAuthorizationSerive.isUserLogged(request);

      int numberOfActiveBuyers = statisticService.getNumberOfActiveBuyers();

      ModelAndView modelAndView = new ModelAndView("home");
      modelAndView.addObject("numberOfActiveBuyers", numberOfActiveBuyers);

      return modelAndView;
   }

}
