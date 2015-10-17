package ca.ulaval.glo4003.b6.housematch.messaging;

import javax.mail.Message;

import ca.ulaval.glo4003.b6.housematch.messaging.exceptions.MessageCantBeSentException;

public interface MessageSender {

   void sendMessage(Message message) throws MessageCantBeSentException;

}
