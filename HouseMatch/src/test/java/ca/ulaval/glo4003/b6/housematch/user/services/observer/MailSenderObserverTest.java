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

import ca.ulaval.glo4003.b6.housematch.user.common.MessageBuilder;
import ca.ulaval.glo4003.b6.housematch.user.common.MessageBuilderException;
import ca.ulaval.glo4003.b6.housematch.user.common.MessageCantBeSentException;
import ca.ulaval.glo4003.b6.housematch.user.common.MessageSender;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.UserNotifyingException;

public class MailSenderObserverTest {

   @Mock
   private MessageBuilder messageBuilder;
   
   @Mock 
   private MessageSender messageSender;
   
   @InjectMocks
   private MailSenderObserver mailSenderObserver;
   
   @Mock
   private Message message;
   
   @Mock
   private User user;
   
   @Before
   public void setup() throws MessageBuilderException{
      MockitoAnnotations.initMocks(this);
      configureMessageBuilder();
   }

   private void configureMessageBuilder() throws MessageBuilderException {
      given(messageBuilder.withRecipient(user)).willReturn(messageBuilder);
      given(messageBuilder.withMessage()).willReturn(messageBuilder);
      given(messageBuilder.build()).willReturn(message);
      
   }
   
   @Test
   public void whenMailObserverUpdateMessageSenderShouldBeCall() throws UserNotifyingException, MessageCantBeSentException{
      // Given

      // When
      mailSenderObserver.update(user);
      
      // Then
      verify(messageSender).sendMessage(message);
   }
   
   @Test(expected = UserNotifyingException.class)
   public void whenMailObserverUpdateShouldThrowUserNotifyingExceptionByMessageBuilder() throws MessageBuilderException, UserNotifyingException {
      // Given
      doThrow(new MessageBuilderException(null, null)).when(messageBuilder).withMessage();
      
      // When
      mailSenderObserver.update(user);
      
      // Then handle by expectedException
   }
   
   @Test(expected = UserNotifyingException.class)
   public void whenMailObserverUpdateShouldThrowUserNotifyingExceptionByMessageSender() throws MessageBuilderException, UserNotifyingException, MessageCantBeSentException {
      // Given
      doThrow(new MessageCantBeSentException(null, null)).when(messageSender).sendMessage(message);
      
      // When
      mailSenderObserver.update(user);
      
      // Then handle by expectedException
   }
}
