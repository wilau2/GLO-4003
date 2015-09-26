package ca.ulaval.glo4003.b6.housematch.user.services;

import javax.servlet.http.HttpServletRequest;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;

public class UserAuthorisationService {

   public HttpServletRequest setSessionUserAuthorisation(HttpServletRequest request, User user) {

      if (user.isAdmin()) {
         request.getSession().setAttribute("loggedInUserRole", "admin");
      }
      if (user.isSeller()) {
         request.getSession().setAttribute("loggedInUserRole", "seller");
      }
      if (user.isBuyer()) {
         request.getSession().setAttribute("loggedInUserRole", "buyer");
      }

      request.getSession().setAttribute("loggedInUsername", user.getUsername());

      return request;
   }

   public HttpServletRequest closeSession(HttpServletRequest request) {
      request.getSession().setAttribute("loggedInUsername", null);
      request.getSession().setAttribute("loggedInUserRole", null);

      return request;
   }
}
