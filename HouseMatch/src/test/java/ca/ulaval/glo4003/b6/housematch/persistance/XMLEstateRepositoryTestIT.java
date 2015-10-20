package ca.ulaval.glo4003.b6.housematch.persistance;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.estate.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.estate.XMLEstateRepository;
import ca.ulaval.glo4003.b6.housematch.persistance.estate.converter.EstateElementConverterFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class XMLEstateRepositoryTestIT {

   XMLEstateRepository xmlEstateRepository;

   Description description;

   String address;

   @Before
   public void setUp() {
      xmlEstateRepository = new XMLEstateRepository(new EstateAssemblerFactory(), new EstatePersistenceDtoFactory(),
            new EstateElementConverterFactory());

      address = "0-1-Test Sans Description-qc-qc-g12022";
      description = new Description(20, 1, 1, 1, 2002, "4x42", 222, 125000, "FRONT");
   }

   @Test
   public void editDescription() throws CouldNotAccessDataException {
      xmlEstateRepository.editDescription(address, description);
   }

}
