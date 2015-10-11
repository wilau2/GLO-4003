package ca.ulaval.glo4003.b6.housematch.user.common;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;


public class MailBuilderTest {
   
   private MailBuilder mailBuilder;
   
   private MessageBuilder secondMailBuilder;
   
   @Mock
   private User user;
   
   @Mock
   private ContactInformation contactInformation;

   private String EMAIL = "m.savoie@live.ca";

   private String USERNAME = "username";
   
   @Before
   public void setup(){
      MockitoAnnotations.initMocks(this);
      mailBuilder = new MailBuilder();
      configureUser();
   }


      @Test
      public void whenMessageBuilderWithRecipientShouldReturnMailBuilder() {
         // Given
      
         // When
         secondMailBuilder = mailBuilder.withRecipient(user);
         
         // Then
         assertTrue(secondMailBuilder.getClass() == MailBuilder.class);
      }
      
      @Test
      public void whenMessageBuilderWithRecipientWithMessageShouldReturnMailBuilder() throws MessageBuilderException {
         // Given

         // When
         secondMailBuilder = mailBuilder.withRecipient(user).withMessage();
         
         // Then
         assertTrue(secondMailBuilder.getClass() == MailBuilder.class);
      }
      
      @Test(expected = NullPointerException.class)
      public void whenMessageBuilderWithoutRecipientShouldThrowNullPointerException() throws MessageBuilderException{
         //Given
         
         //When
         secondMailBuilder = mailBuilder.withMessage();
         
         //Then handle by ExpectedException
      }
      
      @Test
      public void whenMessageBuilderWithRecipientWithMessageWithBuildShouldReturnMessage() throws MessageBuilderException, MessagingException {
         // Given
     
         // When
         Message message = mailBuilder.withRecipient(user).withMessage().build();
         
         // Then

         String emailReceived = message.getAllRecipients()[0].toString();

         assertTrue(emailReceived.equals(EMAIL));
      }
      
      
      private void configureUser() {
         given(user.getContactInformation()).willReturn(contactInformation);
         given(contactInformation.getEmail()).willReturn(EMAIL);
         given(user.getUsername()).willReturn(USERNAME);
      
   }

}
