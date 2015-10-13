package ca.ulaval.glo4003.b6.housematch.user.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.ContactInformationAssembler;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.factory.ContactInformationAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserNotifyingException;
import ca.ulaval.glo4003.b6.housematch.user.services.observer.UserObserver;

public class UserProfilService {

   private UserRepository userRepository;

   private ContactInformationAssemblerFactory contactInformationAssemblerFactory;

   private List<UserObserver> observers;

   @Autowired
   public UserProfilService(UserRepository userRepository,
         ContactInformationAssemblerFactory contactInformationAssemblerFactory, List<UserObserver> observers) {
      this.userRepository = userRepository;
      this.contactInformationAssemblerFactory = contactInformationAssemblerFactory;
      this.observers = observers;
   }

   public void update(UserDetailedDto userDetailedDto)
         throws CouldNotAccessUserDataException, UserNotFoundException, UserNotifyingException {

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

   private ContactInformation getNewContactInformations(UserDetailedDto userDetailedDto) {
      ContactInformationAssembler contactInformationAssembler = contactInformationAssemblerFactory
            .createContactInformationAssembler();
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
