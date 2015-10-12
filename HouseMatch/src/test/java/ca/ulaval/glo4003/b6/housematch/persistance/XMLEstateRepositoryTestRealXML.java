package ca.ulaval.glo4003.b6.housematch.persistance;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Address;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers.EstateElementAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.repository.XMLEstateRepository;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class XMLEstateRepositoryTestRealXML {
   
   XMLEstateRepository xmlEstateRepository;
   
   Description description;
   
   String address;
   
   @Before
   public void setUp(){
      xmlEstateRepository = new XMLEstateRepository(new EstateAssemblerFactory(), new EstatePersistenceDtoFactory(), new EstateElementAssemblerFactory());
   
      address = "0-1-Test Sans Description-qc-qc-g12022";
      description= new Description(20, 1, 1, 1, 2002, "4x42", 222, 125000, "FRONT");
   }
   
   @Test
   public void editDescription() throws CouldNotAccessDataException{
      xmlEstateRepository.editDescription(address, description);
   }

}
