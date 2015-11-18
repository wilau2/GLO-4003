package ca.ulaval.glo4003.b6.housematch.messaging;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class MailSenderFactoryTest {

   @InjectMocks
   private MailSenderFactory mailSenderFactory;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void whenMailSenderFactoryNewInstanceShouldReturnMailSender() {
      // Given

      // When
      MailSender newInstance = mailSenderFactory.newInstance();

      // Then
      assertTrue(newInstance instanceof MailSender);
   }

}
