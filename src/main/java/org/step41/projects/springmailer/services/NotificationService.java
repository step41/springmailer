package org.step41.projects.springmailer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.step41.projects.springmailer.entities.JwtUser;
import org.step41.projects.springmailer.entities.Message;
import org.step41.projects.springmailer.security.JwtValidator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.step41.projects.springmailer.config.Constants.Strings.APPLICATION_DEFAULT_CHARSET;
import static org.step41.projects.springmailer.config.Constants.Strings.MSG_MAIL_SEND_START;

@Service
public class NotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);
    private Boolean sent;
    @Autowired
    private AmazonMailService amazonMailService;
    @Autowired
    private SendGridMailService sendGridMailService;

    public NotificationService(AmazonMailService amazonMailService, SendGridMailService sendGridMailService) {
        this.amazonMailService = amazonMailService;
        this.sendGridMailService = sendGridMailService;
    }

    public void validateAddress(String address) throws AddressException, UnsupportedEncodingException {
        InternetAddress internetAddress = new InternetAddress( address, null, APPLICATION_DEFAULT_CHARSET );
        internetAddress.validate();
    }

    public Boolean sendMessage(Message message) {
        String messageToName = message.getToName();
        String messageToEmail = message.getToEmail();
        String messageFromName = message.getFromName();
        String messageFromEmail = message.getFromEmail();
        String messageTo = messageToName + " <" + messageToEmail + ">";
        String messageFrom = messageFromName + " <" + messageFromEmail + ">";
        String messageSubject = message.getSubject();
        String messageBody = message.getBody();

        LOG.debug(MSG_MAIL_SEND_START);

        sent = sendGridMailService.sendMessage(messageTo, messageFrom, messageSubject, messageBody);
        if (sent == false) {
            sent = amazonMailService.sendMessage(messageTo, messageFrom, messageSubject, messageBody);
        }
        return sent;

    }

}
