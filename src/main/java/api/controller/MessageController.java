package api.controller;

import api.model.Message;
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

    /*@GetMapping("/messages")
    List<Message> allBetweenTwoPeople() throws Exception {
        return messageService.getMessages(1,2);
    }

    @GetMapping("/Messages/{id]")
    Message one(@PathVariable Integer id){
        return service.findById(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }*/

    //In Postman, GET localhost:8080/messages/1/2   for messages between person 1 and 2
    @GetMapping("/messages/{id1}/{id2}")
    List<Message> allBetweenTwoPeople(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2) throws Exception {
        return messageService.getMessages(id1,id2);
    }

    //In Postman, POST localhost:8080/messages/personID/text/picURL
    @PostMapping("/messages/{persId}/{text}/{picUrl}")
    void addMessage(@PathVariable Integer persId, @PathVariable String text, @PathVariable String picUrl) {
        messageService.addMessage(persId, text, picUrl);
    }
}
