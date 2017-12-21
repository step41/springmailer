package org.step41.projects.springmailer.services;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.step41.projects.springmailer.config.Constants.Strings.MSG_MAIL_SEND_FAIL;
import static org.step41.projects.springmailer.config.Constants.Strings.MSG_MAIL_SEND_PASS;

@Service
public class SendGridMailService {

    private Boolean sent;
    @Value("${sendgrid.mail.api.secret}")
    private String sendGridApiSecret;
    @Value("${sendgrid.mail.api.endpoint}")
    private String sendGridApiEndpoint;
    @Value("${sendgrid.mail.api.contenttype}")
    private String sendGridApiContenttype;


    public SendGridMailService() throws IOException {}

    public Boolean sendMessage(String messageTo, String messageFrom, String messageSubject, String messageBody) {

        Email from = new Email(messageFrom);
        String subject = messageSubject;
        Email to = new Email(messageTo);
        com.sendgrid.Content content = new com.sendgrid.Content(sendGridApiContenttype, messageBody);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiSecret);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint(sendGridApiEndpoint);
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            System.out.println(MSG_MAIL_SEND_PASS);
            sent = true;
        } catch (IOException ex) {
            System.out.println(MSG_MAIL_SEND_FAIL + "SendGrid. Error: " + ex);
            sent = false;
            throw ex;
        }
        finally {
            return sent;
        }
    }
}
