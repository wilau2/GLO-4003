package ca.ulaval.glo4003.b6.housematch.user.services;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.ContactInformationAssembler;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.factory.ContactInformationAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public class UserProfilService {

   private UserRepository userRepository;

   private ContactInformationAssemblerFactory contactInformationAssemblerFactory;

   @Autowired
   public UserProfilService(UserRepository userRepository,
         ContactInformationAssemblerFactory contactInformationAssemblerFactory) {
      this.userRepository = userRepository;
      this.contactInformationAssemblerFactory = contactInformationAssemblerFactory;
   }

   public void update(UserDetailedDto userDetailedDto) throws CouldNotAccessUserDataException, UserNotFoundException {

      ContactInformationAssembler contactInformationAssembler = contactInformationAssemblerFactory
            .createContactInformationAssembler();
      ContactInformation contactInformation = contactInformationAssembler
            .assembleContactInformation(userDetailedDto.getContactInformationDto());

      User user = userRepository.getUser(userDetailedDto.getUsername());
      user.updateContactInformation(contactInformation);
      userRepository.updateUser(user);
   }
}
