package ca.ulaval.glo4003.b6.housematch.services.user;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.domain.user.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.ContactInformationAssembler;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

public class UserProfilService {

   private UserRepository userRepository;

   private ContactInformationAssembler contactInformationAssembler;

   private List<UserObserver> observers;

   @Inject
   public UserProfilService(UserRepository userRepository, ContactInformationAssembler contactInformationAssembler,
         List<UserObserver> observers) {
      this.userRepository = userRepository;
      this.contactInformationAssembler = contactInformationAssembler;
      this.observers = observers;
   }

   public void update(UserDto userDetailedDto)
         throws CouldNotAccessDataException, UserNotFoundException, UserNotifyingException {

      User existingUser = userRepository.getUser(userDetailedDto.getUsername());

      ContactInformation newInfos = getNewContactInformations(userDetailedDto);
      ContactInformation oldInfos = existingUser.getContactInformation();

      boolean emailIsChanged = emailHasChanged(oldInfos.getEmail(), newInfos.getEmail());

      if (emailIsChanged) {
         existingUser.setActive(false);
      }

      existingUser.updateContactInformation(newInfos);

      userRepository.updateUser(existingUser);

      if (emailIsChanged) {
         notifyAllObservers(existingUser);
      }
   }

   private ContactInformation getNewContactInformations(UserDto userDetailedDto) {
      return contactInformationAssembler.assembleContactInformation(userDetailedDto.getContactInformationDto());
   }

   private boolean emailHasChanged(String oldEmail, String newEmail) {
      return !oldEmail.equals(newEmail);
   }

   private void notifyAllObservers(User user) throws UserNotifyingException {
      for (UserObserver observer : observers) {
         observer.notifyUserChanged(user);
      }
   }
}
