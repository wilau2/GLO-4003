package ca.ulaval.glo4003.b6.housematch.user.services;

import javax.servlet.http.HttpServletRequest;

import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;

public class UserAuthorisationService {

   protected final static String LOGGED_IN_USER_ROLE = "loggedInUserRole";

   protected final static String LOGGED_IN_USERNAME = "loggedInUsername";

   public HttpServletRequest setSessionUserAuthorisation(HttpServletRequest request, User user) {

      if (user.isAdmin()) {
         request.getSession().setAttribute(LOGGED_IN_USER_ROLE, Role.ADMIN);
      }
      if (user.isSeller()) {
         request.getSession().setAttribute(LOGGED_IN_USER_ROLE, Role.SELLER);
      }
      if (user.isBuyer()) {
         request.getSession().setAttribute(LOGGED_IN_USER_ROLE, Role.BUYER);
      }

      request.getSession().setAttribute(LOGGED_IN_USERNAME, user.getUsername());

      return request;
   }

   public HttpServletRequest closeSession(HttpServletRequest request) {
      request.getSession().setAttribute(LOGGED_IN_USERNAME, null);
      request.getSession().setAttribute(LOGGED_IN_USER_ROLE, null);

      return request;
   }
}
