package ca.ulaval.glo4003.b6.housematch.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

   public static void main(String[] args) {
      Application p = new Application();
      p.start(args);
   }

   private void start(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/.xml");
      // System.out.println("The method of my Bean: " + myBean.getStr());
      System.out.println("test");
   }
}
