package org.step41.projects.springmailer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.step41.projects.springmailer.repositories.MessagesRepository;
import org.step41.projects.springmailer.entities.Message;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    public List<Message> listMessages() {
        List<Message> messages = new ArrayList<>();
        messagesRepository.findAll().forEach(messages::add);
        return messages;
    }

    public Message getMessage(Long id) {
        return messagesRepository.findOne(id);
    }

    public void addMessage(Message message) {
        messagesRepository.save(message);
    }

    public void updateMessage(Long id, Message message) {
        messagesRepository.save(message);

    }

    public void deleteMessage(Long id) {
        messagesRepository.delete(id);
    }

}
