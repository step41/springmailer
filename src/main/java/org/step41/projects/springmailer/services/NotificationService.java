package org.step41.projects.springmailer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.step41.projects.springmailer.entities.Message;

import java.util.List;

import static org.step41.projects.springmailer.config.Constants.Strings.MSG_MAIL_SEND_START;

@Service
public class NotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);
    private List<MailServiceInterface> mailServiceInterfaces;

    public NotificationService() { }

    public Boolean sendMessage(Message message) {

        int i = 0;
        Boolean sent = false;

        String messageToName = message.getToName();
        String messageToEmail = message.getToEmail();
        String messageFromName = message.getFromName();
        String messageFromEmail = message.getFromEmail();
        String messageTo = messageToName + " <" + messageToEmail + ">";
        String messageFrom = messageFromName + " <" + messageFromEmail + ">";
        String messageSubject = message.getSubject();
        String messageBody = message.getBody();

        LOG.debug(MSG_MAIL_SEND_START);

        while (i < mailServiceInterfaces.size() && sent != true) {
            sent = mailServiceInterfaces.get(i).sendMessage(messageTo, messageFrom, messageSubject, messageBody);
            i++;
        }
        return sent;

    }

}
