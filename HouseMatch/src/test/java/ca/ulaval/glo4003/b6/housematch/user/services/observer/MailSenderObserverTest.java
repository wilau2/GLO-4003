package ca.ulaval.glo4003.b6.housematch.user.services.observer;

import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import javax.mail.Message;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.common.MailBuilder;
import ca.ulaval.glo4003.b6.housematch.user.common.MailSender;
import ca.ulaval.glo4003.b6.housematch.user.common.MessageBuilderException;
import ca.ulaval.glo4003.b6.housematch.user.common.MessageCantBeSentException;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserNotifyingException;

public class MailSenderObserverTest {

   @Mock
   private MessageBuilderFactory messageBuilderFactory;

   @Mock
   private MessageSenderFactory messageSenderFactory;

   @InjectMocks
   private MailSenderObserver mailSenderObserver;

   @Mock
   private Message message;

   @Mock
   private User user;

   @Mock
   private MailSender mailSender;

   @Mock
   private MailBuilder mailBuilder;

   @Before
   public void setup() throws MessageBuilderException {
      MockitoAnnotations.initMocks(this);
      configureFactory();
      configureMessageBuilder();
   }

   private void configureFactory() {
      given(messageBuilderFactory.newInstance()).willReturn(mailBuilder);
      given(messageSenderFactory.newInstance()).willReturn(mailSender);

   }

   private void configureMessageBuilder() throws MessageBuilderException {
      given(mailBuilder.withRecipient(user)).willReturn(mailBuilder);
      given(mailBuilder.withMessage()).willReturn(mailBuilder);
      given(mailBuilder.build()).willReturn(message);

   }

   @Test
   public void whenMailObserverUpdateMessageSenderShouldBeCall()
         throws UserNotifyingException, MessageCantBeSentException {
      // Given

      // When
      mailSenderObserver.notifyUserChanged(user);

      // Then
      verify(mailSender).sendMessage(message);
   }

   @Test(expected = UserNotifyingException.class)
   public void whenMailObserverUpdateShouldThrowUserNotifyingExceptionByMessageBuilder()
         throws MessageBuilderException, UserNotifyingException {
      // Given
      doThrow(new MessageBuilderException(null, null)).when(mailBuilder).withMessage();

      // When
      mailSenderObserver.notifyUserChanged(user);

      // Then handle throw UserNotifyingException
   }

   @Test(expected = UserNotifyingException.class)
   public void whenMailObserverUpdateShouldThrowUserNotifyingExceptionByMessageSender()
         throws MessageBuilderException, UserNotifyingException, MessageCantBeSentException {
      // Given
      doThrow(new MessageCantBeSentException(null, null)).when(mailSender).sendMessage(message);

      // When
      mailSenderObserver.notifyUserChanged(user);

      // Then throw UserNotifyingException
   }
}
