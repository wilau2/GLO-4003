package ca.ulaval.glo4003.b6.housematch.persistance.estate.factory;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.estate.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.estate.XMLEstateRepository;
import ca.ulaval.glo4003.b6.housematch.persistance.estate.converter.EstateElementConverterFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.estate.factory.XmlEstateRepositoryFactory;

public class XmlEstateRepositoryFactoryTest {

   @Mock
   private EstateElementConverterFactory estateElementAssemblerFactory;

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
