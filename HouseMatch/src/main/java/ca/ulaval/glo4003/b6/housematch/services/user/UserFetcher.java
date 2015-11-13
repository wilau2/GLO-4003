package ca.ulaval.glo4003.b6.housematch.services.user;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class UserFetcher {

   private UserRepository userRepository;

   @Inject
   public UserFetcher(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public UserDto getUserByUsername(String username) throws UserNotFoundException, CouldNotAccessDataException {
      User user = userRepository.getUser(username);
      UserDto userDto = convertUserToDetailedDto(user);
      return userDto;

   }

   private UserDto convertUserToDetailedDto(User user) {
      UserDto userDto = new UserDto();
      userDto.setUsername(user.getUsername());
      ContactInformationDto contactInformationDto = new ContactInformationDto(
            user.getContactInformation().getFirstName(), user.getContactInformation().getLastName(),
            user.getContactInformation().getPhoneNumber(), user.getContactInformation().getEmail());
      userDto.setContactInformationDto(contactInformationDto);

      return userDto;
   }

}
