package org.step41.projects.springmailer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.step41.projects.springmailer.entities.Message;
import org.step41.projects.springmailer.security.JwtValidator;
import org.step41.projects.springmailer.services.MessagesService;
import org.step41.projects.springmailer.services.NotificationService;

import java.util.List;

import static org.step41.projects.springmailer.config.Constants.Routing.URI_API_ENDPOINT;

@RestController
public class MessagesController {

    @Autowired
    private MessagesService messagesService;
    @Autowired
    private NotificationService notificationService;
    private static final String PATH = "/messages";

    @GetMapping(URI_API_ENDPOINT + "/" + PATH)
    public List<Message> listMessages() {
        return messagesService.listMessages();
    }

    @GetMapping(URI_API_ENDPOINT + "/" + PATH + "/{id}")
    public Message getMessage(@PathVariable Long id) {
        return messagesService.getMessage(id);
    }

    @PostMapping(URI_API_ENDPOINT + "/" + PATH)
    public List<Message> addMessage(@RequestBody Message message) {
        // call notification service and pass it the message object
        if (notificationService.sendMessage(message)) {
            messagesService.addMessage(message);
            return messagesService.listMessages();
        }
        return null;
    }

    @PutMapping(URI_API_ENDPOINT + "/" + PATH + "/{id}")
    public void updateMessage(@RequestBody Message message, @PathVariable Long id) {
        messagesService.updateMessage(id, message);
    }

    @DeleteMapping(URI_API_ENDPOINT + "/" + PATH + "/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messagesService.deleteMessage(id);
    }

}
