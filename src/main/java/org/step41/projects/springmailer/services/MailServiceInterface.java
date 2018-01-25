package org.step41.projects.springmailer.services;

public interface MailServiceInterface {

    Boolean sendMessage(String messageTo, String messageFrom, String messageSubject, String messageBody);

}
