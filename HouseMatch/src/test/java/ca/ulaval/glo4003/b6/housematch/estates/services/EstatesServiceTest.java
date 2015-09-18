package ca.ulaval.glo4003.b6.housematch.estates.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;

public class EstatesServiceTest {

   private static final String VALID_TYPE_ESTATE = "CONDO";

   private static final String INVALID_TYPE_ESTATE = "invalid";

   private static final String VALID_ADDRESS = "2-128 rue untel, Quebec";

   private static final Integer VALID_PRICE = 125000;

   @InjectMocks
   private EstatesService estatesService;

   @Mock
   private EstateValidator estateValidator;

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

   @Before
   public void setUp() {
      MockitoAnnotations.initMocks(this);

      when(estateAssemblerFactory.createEstateAssembler()).thenReturn(estateAssembler);
      when(estateAssembler.assembleEstate(estateDto)).thenReturn(estate);
   }

   @Test
   public void addingAnEstateWhenEstateIsValidShouldNotThrowException() {
      // Given no changes

      // When
      estatesService.addEstate(estateDto);

      // Then
      verify(estateRepository, times(1)).addEstate(estate);
   }

   @Test
   public void whenAddingAnEstateShouldCallGetAssemblerFromAssemblerFactory() {
      // Given no changes

      // When
      estatesService.addEstate(estateDto);

      // Then
      verify(estateAssemblerFactory, times(1)).createEstateAssembler();
   }
}
