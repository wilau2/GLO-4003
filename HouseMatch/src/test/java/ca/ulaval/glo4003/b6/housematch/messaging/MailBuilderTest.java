package ca.ulaval.glo4003.b6.housematch.messaging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.domain.user.MessageBuilder;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.messaging.MailBuilder;
import ca.ulaval.glo4003.b6.housematch.messaging.exceptions.MessageBuilderException;

public class MailBuilderTest {

   private static final String EMAIL = "m.savoie@live.ca";

   private static final String USERNAME = "username";

   @Mock
   private User user;

   @Mock
   private ContactInformation contactInformation;
   
   @InjectMocks
   private MailBuilder mailBuilder;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureUser();
   }

   private void configureUser() {
      given(user.getContactInformation()).willReturn(contactInformation);
      given(contactInformation.getEmail()).willReturn(EMAIL);
      given(user.getUsername()).willReturn(USERNAME);

   }

   @Test
   public void whenMessageBuilderWithRecipientShouldReturnMailBuilder() {
      // Given

      // When
      MessageBuilder returnedMailBuilder = mailBuilder.withRecipient(user);

      // Then
      assertTrue(returnedMailBuilder.getClass() == MailBuilder.class);
   }

   @Test
   public void whenMessageBuilderWithRecipientWithMessageShouldReturnMailBuilder() throws MessageBuilderException {
      // Given

      // When
      MessageBuilder returnedMailBuilder = mailBuilder.withRecipient(user).withMessage();

      // Then
      assertTrue(returnedMailBuilder.getClass() == MailBuilder.class);
   }

   @Test
   public void whenMessageBuilderWithRecipientWithMessageWithBuildShouldReturnMessage()
         throws MessageBuilderException, MessagingException {
      // Given

      // When
      Message message = mailBuilder.withRecipient(user).withMessage().build();

      // Then
      String emailReceived = message.getAllRecipients()[0].toString();

      assertEquals(emailReceived, EMAIL);
   }
}
