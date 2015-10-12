package ca.ulaval.glo4003.b6.housematch.estates.services;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.DescriptionAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.DescriptionAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.AddressDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.DescriptionValidator;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.DescriptionValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.AddressValidator;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.factories.AddressValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.factories.EstateValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class EstatesServiceTest {
   
   private final static String ADDRESS = "address";

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
   private DescriptionValidatorFactory descriptionValidatorFactory ;

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
   
   private String estateAddress = "VALID_ESTATE_ADDRESS";

   @Mock
   private AddressDto addressDto;

   @Mock
   private AddressValidator addressValidaor;

   @Mock
   private AddressValidatorFactory addressValidatorFactory;
   


   // @InjectMocks
   private EstatesService estatesService;

   @Before
   public void setUp() {
      MockitoAnnotations.initMocks(this);
      
      configureDescriptionTests();

      when(estateValidatorFactory.getValidator()).thenReturn(estateValidator);
      when(estateAssemblerFactory.createEstateAssembler()).thenReturn(estateAssembler);
      when(estateAssembler.assembleEstate(estateDto)).thenReturn(estate);
      when(estateRepositoryFactory.newInstance(estateAssemblerFactory)).thenReturn(estateRepository);
      when(addressValidatorFactory.getValidator()).thenReturn(addressValidaor);

      when(estateDto.getAddress()).thenReturn(addressDto);
      when(estateDto.getDescriptionDto()).thenReturn(descriptionDto);

      estatesService = new EstatesService( estateValidatorFactory, 
                                             addressValidatorFactory,
                                             estateAssemblerFactory,
                                             estateRepositoryFactory, 
                                             descriptionValidatorFactory, 
                                             descriptionAssemblerFactory);
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
   public void whenAskedAllEstatesShouldCallAssembleEstate() throws CouldNotAccessDataException {
      // given
      List<Estate> dumbEstates = new ArrayList<Estate>();
      dumbEstates.add(estate);
      when(estateRepository.getAllEstates()).thenReturn(dumbEstates);

      // when
      estatesService.getAllEstates();

      // then
      verify(estateRepository, times(1)).getAllEstates();
   }

   @Test
   public void whenAskedAllEstatesShouldCallEstateAssembleDtoWithEstate() throws CouldNotAccessDataException {
      // given
      List<Estate> dumbEstateDtoList = new ArrayList<Estate>();
      dumbEstateDtoList.add(estate);
      when(estateRepository.getAllEstates()).thenReturn(dumbEstateDtoList);
      when(estateAssembler.assembleEstate(estateDto)).thenReturn(estate);

      // when
      estatesService.getAllEstates();

      // then
      verify(estateAssembler, times(1)).assembleEstateDto(estate);
   }
   
   @Test
   public void whenAddingDescriptionShouldAssembleTheDescription() throws InvalidDescriptionException, InvalidEstateException, CouldNotAccessDataException {
      //given

      //when
      estatesService.editDescription(ADDRESS, descriptionDto);
      //then
      verify(estateAssembler).assembleDescription(descriptionDto);
   }

   private void configureDescriptionTests(){
      when(descriptionAssemblerFactory.createDescriptionAssembler()).thenReturn(descriptionAssembler);
      when(descriptionAssembler.assembleDescription(descriptionDto)).thenReturn(description);
      when(descriptionValidatorFactory.createValidator()).thenReturn(descriptionValidator);
   }

   @Test
   public void whenAddingAnEstateShouldValidateEstateAddress()
         throws InvalidEstateException, CouldNotAccessDataException {
      // Given

      // When
      estatesService.addEstate(estateDto);

      // Then
      verify(addressValidaor, times(1)).validate(addressDto);
   }
}
