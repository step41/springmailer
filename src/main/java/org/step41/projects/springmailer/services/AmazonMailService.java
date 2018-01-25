package org.step41.projects.springmailer.services;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.step41.projects.springmailer.config.Constants.Strings.*;

@Service
public class AmazonMailService implements MailServiceInterface {

    private Boolean sent;

    public AmazonMailService() throws IOException {}

    public Boolean sendMessage(String messageTo, String messageFrom, String messageSubject, String messageBody) {

        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withRegion(Regions.US_WEST_2).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new Destination()
                            .withToAddresses(messageTo))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withText(new Content()
                                            .withCharset(APPLICATION_DEFAULT_CHARSET).withData(messageBody)))
                            .withSubject(new Content()
                                    .withCharset(APPLICATION_DEFAULT_CHARSET).withData(messageSubject)))
                    .withSource(messageFrom);
            client.sendEmail(request);
            System.out.println(MSG_MAIL_SEND_PASS);
            sent = true;
        } catch (Exception ex) {
            System.out.println(MSG_MAIL_SEND_FAIL + "Amazon SES. Error: " + ex.getMessage());
            sent = false;
        }
        finally {
            return sent;
        }
    }
}
