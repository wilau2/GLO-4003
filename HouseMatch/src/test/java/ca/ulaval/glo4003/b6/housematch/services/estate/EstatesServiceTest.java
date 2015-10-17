package ca.ulaval.glo4003.b6.housematch.services.estate;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.DescriptionAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.DescriptionAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.estate.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.services.estate.validators.DescriptionValidator;
import ca.ulaval.glo4003.b6.housematch.services.estate.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.services.estate.validators.factory.DescriptionValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.services.estate.validators.factory.EstateValidatorFactory;

public class EstatesServiceTest {

   private static final String ADDRESS = "address";

   @Mock
   private EstateValidatorFactory estateValidatorFactory;

   @Mock
   private EstateDto estateDto;

   @Mock
   private EstateRepository estateRepository;

   @Mock
   private Estate estate;

   @Mock
   private EstateAssemblerFactory estateAssemblerFactory;

   @Mock
   private EstateAssembler estateAssembler;

   @Mock
   private EstateValidator estateValidator;

   @Mock
   private EstateRepositoryFactory estateRepositoryFactory;

   @Mock
   private EstatePersistenceDtoFactory estatePersistenceDtoFactory;

   @Mock
   private DescriptionValidatorFactory descriptionValidatorFactory;

   @Mock
   private DescriptionAssemblerFactory descriptionAssemblerFactory;

   @Mock
   private DescriptionAssembler descriptionAssembler;

   @Mock
   private DescriptionDto descriptionDto;

   @Mock
   private Description description;

   @Mock
   private DescriptionValidator descriptionValidator;

   @Mock
   private AddressDto addressDto;

   private EstatesService estatesService;

   @Before
   public void setUp() {
      MockitoAnnotations.initMocks(this);

      configureDescriptionTests();

      when(estateValidatorFactory.getValidator()).thenReturn(estateValidator);
      when(estateAssemblerFactory.createEstateAssembler()).thenReturn(estateAssembler);
      when(estateAssembler.assembleEstate(estateDto)).thenReturn(estate);
      when(estateRepositoryFactory.newInstance(estateAssemblerFactory)).thenReturn(estateRepository);

      when(estateDto.getAddress()).thenReturn(addressDto);
      when(estateDto.getDescriptionDto()).thenReturn(descriptionDto);

      estatesService = new EstatesService(estateValidatorFactory, estateAssemblerFactory, estateRepositoryFactory);
   }

   @Test
   public void addingAnEstateWhenEstateIsValidShouldCallAddEstateAtRepository()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesService.addEstate(estateDto);

      // Then
      verify(estateRepositoryFactory, times(1)).newInstance(estateAssemblerFactory);
      verify(estateRepository, times(1)).addEstate(estate);
   }

   @Test
   public void whenAddingAnEstateShouldCallGetAssemblerFromAssemblerFactory()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given no changes

      // When
      estatesService.addEstate(estateDto);

      // Then
      verify(estateAssemblerFactory, times(1)).createEstateAssembler();
   }

   @Test(expected = InvalidEstateException.class)
   public void addingEstateWhenValidatingInvalidEstateShouldThrowException()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given
      doThrow(new InvalidEstateException("")).when(estateValidator).validate(estateDto);

      // When
      estatesService.addEstate(estateDto);

      // Then InvalidEstateException is thrown
   }

   @Test
   public void whenAddingDescriptionShouldAssembleTheDescription()
         throws InvalidDescriptionException, InvalidEstateException, CouldNotAccessDataException {
      // given no changes

      // when
      estatesService.editDescription(ADDRESS, descriptionDto);
      // then
      verify(estateAssembler).assembleDescription(descriptionDto);
   }

   private void configureDescriptionTests() {
      when(descriptionAssemblerFactory.createDescriptionAssembler()).thenReturn(descriptionAssembler);
      when(descriptionAssembler.assembleDescription(descriptionDto)).thenReturn(description);
      when(descriptionValidatorFactory.createValidator()).thenReturn(descriptionValidator);
   }

}
