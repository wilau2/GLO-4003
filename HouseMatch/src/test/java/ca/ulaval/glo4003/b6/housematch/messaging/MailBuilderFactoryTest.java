package ca.ulaval.glo4003.b6.housematch.messaging;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertTrue;

public class MailBuilderFactoryTest {
   
   
   @InjectMocks
   private MailBuilderFactory mailBuilderFactory;
   
   @Before
   public void setup(){
     MockitoAnnotations.initMocks(this);
   }
   
   @Test
   public void whenMailBuiderFactoryNewInstanceShouldReturnInstanceOfMailBoulder() {
      // Given
      
      // When
      MailBuilder newInstance = mailBuilderFactory.newInstance();
      
      // Then
      assertTrue(newInstance instanceof MailBuilder);
   }

}
