package ca.ulaval.glo4003.b6.housematchIT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import ca.ulaval.glo4003.b6.housematch.application.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AppTest {

   @Test
   public void shouldDoThisWhenThat() {
      // Given

      // When
      MockMvc mockMvc = MockMvcBuilders.xmlConfigSetup("classpath:applicationContext.xml").build();

      // Then
   }
}
