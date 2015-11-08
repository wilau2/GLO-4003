package ca.ulaval.glo4003.b6.housematch.persistance.user.converter;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.domain.user.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;

public class RepositoryUserConverter {

   private static final String DATE_LAST_ACTIVITY = "dateLastActivity";

   private static final String EMPTY_FIELD = "";

   private static final String IS_ACTIVE = "isActive";

   private static final String EMAIL = "email";

   private static final String FIRST_NAME = "firstName";

   private static final String PASSWORD = "password";

   private static final String PHONE_NUMBER = "phoneNumber";

   private static final String LAST_NAME = "lastName";

   private static final String ROLE = "role";

   private static final String USERNAME = "username";

   public User assembleUserFromAttributes(HashMap<String, String> attributes) {
      ContactInformation contactInformation = new ContactInformation(attributes.get(FIRST_NAME),
            attributes.get(LAST_NAME), attributes.get(PHONE_NUMBER), attributes.get(EMAIL));

      User user = new User(attributes.get(USERNAME), attributes.get(PASSWORD), contactInformation,
            new Role(attributes.get(ROLE)));

      String lastActivityDateFromAttributes = attributes.get(DATE_LAST_ACTIVITY);
      user.setDateOfLastActivity(LocalDateTime.parse(lastActivityDateFromAttributes));

      return user;
   }

   public User assembleUserFromElement(Element userElement) {

      ContactInformation contactInformation = new ContactInformation(userElement.elementText(FIRST_NAME),
            userElement.elementText(LAST_NAME), userElement.elementText(PHONE_NUMBER), userElement.elementText(EMAIL));

      User user = new User(userElement.elementText(USERNAME), EMPTY_FIELD, contactInformation,
            new Role(userElement.elementText(ROLE)));

      user.setActive(Boolean.parseBoolean(userElement.elementText(IS_ACTIVE)));

      String dateOfLastActivityFromElement = userElement.elementText(DATE_LAST_ACTIVITY);
      user.setDateOfLastActivity(LocalDateTime.parse(dateOfLastActivityFromElement));

      return user;
   }

}
