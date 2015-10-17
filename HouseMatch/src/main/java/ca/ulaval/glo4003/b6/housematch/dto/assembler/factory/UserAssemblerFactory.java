package ca.ulaval.glo4003.b6.housematch.dto.assembler.factory;

import ca.ulaval.glo4003.b6.housematch.dto.assembler.ContactInformationAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.UserAssembler;

public class UserAssemblerFactory {

   public UserAssembler createUserAssembler() {
      return new UserAssembler(new ContactInformationAssembler());
   }

}
