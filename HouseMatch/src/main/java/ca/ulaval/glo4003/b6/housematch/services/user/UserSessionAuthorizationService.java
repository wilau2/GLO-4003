package ca.ulaval.glo4003.b6.housematch.services.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

public class UserSessionAuthorizationService {

   public static final String LOGGED_IN_USER_ROLE = "loggedInUserRole";

   public static final String LOGGED_IN_USERNAME = "loggedInUsername";

   private static final String INVALID_ACCESS_MESSAGE = "You don't have the access to do that, "
                                                        + "be a gentleman and stay in your field of work";

   HttpServletRequest setSessionUserAuthorisation(HttpServletRequest request, User user) {

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
      throw new InvalidAccessException(INVALID_ACCESS_MESSAGE);

   }

   public void verifySessionIsAllowed(HttpServletRequest request, List<String> listOfExpectedRoles)
         throws InvalidAccessException {
      String sessionRole = request.getSession().getAttribute(LOGGED_IN_USER_ROLE).toString();

      if (listOfExpectedRoles.contains(sessionRole)) {
         return;
      }
      throw new InvalidAccessException(INVALID_ACCESS_MESSAGE);
   }

   public boolean isUserLogged(HttpServletRequest request) {
      String username = (String) request.getSession().getAttribute(LOGGED_IN_USERNAME);
      if (username == null) {
         return false;
      }
      return true;
   }
}
