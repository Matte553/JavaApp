package api.controller;

import api.model.Message;
import api.model.MessageModelPost;
import api.service.MessageService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class MessageController {

    private final MessageService messageService;
    @Autowired
    public MessageController(MessageService messageService) throws Exception {
        this.messageService = messageService;
    }

    /*
    // Anton vad e detta??
    @GetMapping("/Messages/{id]")
    Message one(@PathVariable Integer id){
        return service.findById(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }*/

    //In Postman, GET localhost:8080/messages/1/2   for messages between person 1 and 2
    @GetMapping("/messages/{id1}")
    List<Message> allBetweenTwoPeople(@PathVariable("id1") Integer id1) throws Exception {
        return messageService.getMessages(id1);
    }

    @GetMapping("/messages/all")
    List<Message> getAllMessages() throws Exception {
        return messageService.getAllMessages();
    }

    @PostMapping("/messages/add")
    private MessageModelPost addMessage(@RequestBody MessageModelPost postMessages) {
        return messageService.addMessage(postMessages);
    }
}
