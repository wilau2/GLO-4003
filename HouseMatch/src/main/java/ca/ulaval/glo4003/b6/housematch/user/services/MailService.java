package ca.ulaval.glo4003.b6.housematch.user.services;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.MailSender;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.BadEmailException;

public class MailService {

   private MailSender mailSender;
   public MailService()
   {
      this.mailSender = new MailSender();
   }
   
   public void sendMail(User user) throws BadEmailException{
      try {      
         mailSender.sendMail(user);
      } catch (AddressException e) {
         throw new BadEmailException(e.getMessage());
      } catch (MessagingException e) {
         throw new BadEmailException(e.getMessage());
      }
   }
}
