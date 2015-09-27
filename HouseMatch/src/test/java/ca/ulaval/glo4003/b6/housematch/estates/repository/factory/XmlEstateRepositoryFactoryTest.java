package ca.ulaval.glo4003.b6.housematch.estates.repository.factory;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.EstateElementAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.estates.repository.XMLEstateRepository;

public class XmlEstateRepositoryFactoryTest {

   @Mock
   private EstateElementAssemblerFactory estateElementAssemblerFactory;

   @Mock
   private EstatePersistenceDtoFactory estatePersistencDtoFactory;

   @Mock
   private EstateAssemblerFactory estateAssemblerFactory;

   @InjectMocks
   private XmlEstateRepositoryFactory xmlEstateRepositoryFactory;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void whenCreatingNewInstanceOfXmlEstateRepositoryShouldReturnAFullyInstanceObject() {
      // Given

      // When
      EstateRepository newInstance = xmlEstateRepositoryFactory.newInstance(estateAssemblerFactory);

      // Then
      assertTrue(newInstance instanceof XMLEstateRepository);
   }
}
