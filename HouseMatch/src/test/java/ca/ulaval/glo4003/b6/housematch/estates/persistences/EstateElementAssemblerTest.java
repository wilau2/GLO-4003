package ca.ulaval.glo4003.b6.housematch.estates.persistences;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public class EstateElementAssemblerTest {

   private static final String TYPE = "TYPE";

   private static final String ADDRESS = "ADDRESS";

   private static final Integer PRICE = 1;

   @Mock
   private Element element;

   @InjectMocks
   private EstateElementAssembler estateElementAssembler;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      when(element.attributeValue("type")).thenReturn(TYPE);
      when(element.attributeValue("address")).thenReturn(ADDRESS);
      when(element.attributeValue("price")).thenReturn("1");

   }

   @Test
   public void assemblingEstateDtoWhenElementHasAllElementShouldCreateDtoWithAllFieldsSet() {
      // Given

      // When
      EstateDto estateDto = estateElementAssembler.convertToDto(element);

      // Then
      assertEquals(TYPE, estateDto.getType());
      assertEquals(ADDRESS, estateDto.getAddress());
      assertEquals(PRICE, estateDto.getPrice());
   }
}
