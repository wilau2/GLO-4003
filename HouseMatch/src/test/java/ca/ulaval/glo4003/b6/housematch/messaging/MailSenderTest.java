package ca.ulaval.glo4003.b6.housematch.messaging;

import javax.mail.Message;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.messaging.exceptions.MessageCantBeSentException;

public class MailSenderTest {

   @Mock
   private Message message;

   private MailSender mailSender;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      mailSender = new MailSender();
   }

   @Test(expected = MessageCantBeSentException.class)
   public void sendingMailWhenTransportThrowMessagingExceptionShouldThrowMessageCantBeSentException()
         throws MessageCantBeSentException {
      // Given no changes

      // When
      mailSender.sendMessage(message);

      // Then a Message can't be sent exception
   }
}
