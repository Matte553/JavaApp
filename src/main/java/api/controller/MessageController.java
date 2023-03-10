package api.controller;
//API
import api.model.MessageModel;
import api.model.MessageModelPost;
import api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

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


    //In Postman, GET localhost:8080/messages/2   for messages between Anders and person 2
    @GetMapping("/messages/{id1}")
    List<MessageModel> allBetweenTwoPeople(@PathVariable("id1") Integer id1) throws Exception {
        return messageService.getMessages(id1);
    }

    @GetMapping("/messages/all")
    List<MessageModel> getAllMessages() throws Exception {
        return messageService.getAllMessages();
    }
/*
    @PostMapping("/messages/add")
    private MessageModelPost addMessage(@RequestBody MessageModelPost postMessages) {
        return messageService.addMessage(postMessages);
    }*/
}
