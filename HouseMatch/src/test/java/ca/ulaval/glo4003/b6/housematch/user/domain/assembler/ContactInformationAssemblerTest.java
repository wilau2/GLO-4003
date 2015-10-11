package ca.ulaval.glo4003.b6.housematch.user.domain.assembler;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;

public class ContactInformationAssemblerTest {

   private static final String FIRST_NAME = "firstname";

   private static final String LAST_NAME = "lastname";

   private static final String PHONE_NUMBER = "phonenumber";

   private static final String EMAIL = "email";

   @InjectMocks
   ContactInformationAssembler contactInformationAssembler;

   @Mock
   private ContactInformationDto contactInformationDto;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureContactInformationDto();
   }

   private void configureContactInformationDto() {
      given(contactInformationDto.getFirstName()).willReturn(FIRST_NAME);
      given(contactInformationDto.getLastName()).willReturn(LAST_NAME);
      given(contactInformationDto.getPhoneNumber()).willReturn(PHONE_NUMBER);
      given(contactInformationDto.getEmail()).willReturn(EMAIL);
   }

   @Test
   public void givenValidContactInformationDtoWhenAssembleThenContactInformationFirsNameIsTheSame() {
      // Given

      // When
      ContactInformation contactInformation = contactInformationAssembler
            .assembleContactInformation(contactInformationDto);

      // Then
      assertEquals(FIRST_NAME, contactInformation.getFirstName());
   }

   @Test
   public void givenValidContactInformationDtoWhenAssembleThenContactInformationLastNameIsTheSame() {
      // Given

      // When
      ContactInformation contactInformation = contactInformationAssembler
            .assembleContactInformation(contactInformationDto);

      // Then
      assertEquals(LAST_NAME, contactInformation.getLastName());
   }

   @Test
   public void givenValidContactInformationDtoWhenAssembleThenContactInformationPhoneNumberIsTheSame() {
      // Given

      // When
      ContactInformation contactInformation = contactInformationAssembler
            .assembleContactInformation(contactInformationDto);

      // Then
      assertEquals(PHONE_NUMBER, contactInformation.getPhoneNumber());
   }

   @Test
   public void givenValidContactInformationDtoWhenAssembleThenContactInformationEmailIsTheSame() {
      // Given

      // When
      ContactInformation contactInformation = contactInformationAssembler
            .assembleContactInformation(contactInformationDto);

      // Then
      assertEquals(EMAIL, contactInformation.getEmail());
   }
}
