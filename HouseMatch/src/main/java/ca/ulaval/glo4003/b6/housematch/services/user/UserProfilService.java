package ca.ulaval.glo4003.b6.housematch.services.user;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.ContactInformationCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.ContactInformationAssembler;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

public class UserProfilService {

   private UserRepository userRepository;

   private ContactInformationAssembler contactInformationAssembler;

   private List<UserObserver> observers;

   private ContactInformationCorruptionVerificator contactInformationCorruptionVerificator;

   @Inject
   UserProfilService(ContactInformationCorruptionVerificator contactInformationCorruptionVerificator,
         UserRepository userRepository, ContactInformationAssembler contactInformationAssembler,
         List<UserObserver> observers) {
      this.contactInformationCorruptionVerificator = contactInformationCorruptionVerificator;
      this.userRepository = userRepository;
      this.contactInformationAssembler = contactInformationAssembler;
      this.observers = observers;
   }

   public void update(UserDto userDetailedDto) throws CouldNotAccessDataException, UserNotFoundException,
         UserNotifyingException, InvalidContactInformationFieldException {
      contactInformationCorruptionVerificator
            .validateContactInformationCorruption(userDetailedDto.getContactInformationDto());

      ContactInformation newContactInformation = contactInformationAssembler
            .assembleContactInformation(userDetailedDto.getContactInformationDto());

      User user = userRepository.getUser(userDetailedDto.getUsername());

      notifyAllObserversIfEmailChanged(user, newContactInformation);

      user.updateContactInformation(newContactInformation);

      userRepository.update(user);

   }

   private void notifyAllObserversIfEmailChanged(User user, ContactInformation newContactInformation)
         throws UserNotifyingException {
      if (user.isEmailChanged(newContactInformation)) {
         for (UserObserver observer : observers) {
            observer.notifyUserChanged(user);
         }
      }
   }

}
