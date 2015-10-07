package ca.ulaval.glo4003.b6.housematch.user.services;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public class UserFetcher {

   private UserRepository userRepository;

   @Autowired
   public UserFetcher(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public UserDetailedDto getUserByUsername(String username)
         throws UserNotFoundException, CouldNotAccessUserDataException {
      User user = userRepository.getUser(username);
      UserDetailedDto userDto = convertUserToDetailedDto(user);
      return userDto;

   }

   private UserDetailedDto convertUserToDetailedDto(User user) {
      UserDetailedDto userDetailedDto = new UserDetailedDto(user.getUsername());
      ContactInformationDto contactInformationDto = new ContactInformationDto(
            user.getContactInformation().getFirstName(), user.getContactInformation().getLastName(),
            user.getContactInformation().getPhoneNumber(), user.getContactInformation().getEmail());
      userDetailedDto.setContactInformationDto(contactInformationDto);

      return userDetailedDto;
   }

}
