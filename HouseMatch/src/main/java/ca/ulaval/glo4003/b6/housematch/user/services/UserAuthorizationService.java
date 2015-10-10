package ca.ulaval.glo4003.b6.housematch.user.services;

import javax.servlet.http.HttpServletRequest;

import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidAccessException;

public class UserAuthorizationService {

   public final static String LOGGED_IN_USER_ROLE = "loggedInUserRole";

   public final static String LOGGED_IN_USERNAME = "loggedInUsername";

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

   public void verifySessionIsAllowed(HttpServletRequest request, String expectedRole) throws InvalidAccessException {
      String sessionRole = request.getSession().getAttribute(LOGGED_IN_USER_ROLE).toString();
      if (sessionRole.equals(expectedRole)) {
         return;
      }
      throw new InvalidAccessException(
            "You don't have the access to do that, be a gentlement and stay in your field of work");

   }
}
