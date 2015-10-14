package ca.ulaval.glo4003.b6.housematch.web.converters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.DescriptionModel;

public class DescriptionConverterTest {
   
   private static final int CONST_NUMBER = 22;
   private static final String CONST_STRING = "abcdef";
   @Mock
   DescriptionModel descriptionModel;
   
   @Mock
   DescriptionDto descriptionDto;
   
   @InjectMocks
   DescriptionConverter descriptionConverter;
   
   @Before
   public void setUp(){
      MockitoAnnotations.initMocks(this);
      configureDescriptionDto();
      configureDescriptionModel();
   }
   

   @Test
   public void convertToDtoShouldCopyEachField() {
      //given

      //when
      descriptionConverter.convertToDto(descriptionModel);
      //then
   }
   
   @Test
   public void convertToModelShouldCopyEachField() {
      //given

      //when
      descriptionConverter.convertToModel(descriptionDto);
      //then
   }
   
   private void configureDescriptionDto() {
      when(descriptionDto.getBuildingDimensions()).thenReturn(CONST_STRING);
      when(descriptionDto.getLivingSpaceAreaSquareMeter()).thenReturn(CONST_NUMBER);
      when(descriptionDto.getMunicipalAssessment()).thenReturn(CONST_NUMBER);
      when(descriptionDto.getNumberOfBathrooms()).thenReturn(CONST_NUMBER);
      when(descriptionDto.getNumberOfBedRooms()).thenReturn(CONST_NUMBER);
      when(descriptionDto.getNumberOfLevel()).thenReturn(CONST_NUMBER);
      when(descriptionDto.getNumberOfRooms()).thenReturn(CONST_NUMBER);
      when(descriptionDto.getYearOfConstruction()).thenReturn(CONST_NUMBER);    
   }

   private void configureDescriptionModel() {
      when(descriptionModel.getDimensionsBuilding()).thenReturn(CONST_STRING);
      when(descriptionModel.getLivingSpaceAreaSquareMeter()).thenReturn(CONST_NUMBER);
      when(descriptionModel.getMunicipalValuation()).thenReturn(CONST_NUMBER);
      when(descriptionModel.getNumberOfBathrooms()).thenReturn(CONST_NUMBER);
      when(descriptionModel.getNumberOfBedRooms()).thenReturn(CONST_NUMBER);
      when(descriptionModel.getNumberOfLevel()).thenReturn(CONST_NUMBER);
      when(descriptionModel.getNumberOfRooms()).thenReturn(CONST_NUMBER);
      when(descriptionModel.getYearsOfConstruction()).thenReturn(CONST_NUMBER);   
      
   }

}
