package api.controller;

import api.model.Message;
import api.service.MessageService;
import jakarta.ws.rs.Path;
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
If the URL http://localhost:8080/MyApp/user/1234/invoices?date=12-05-2013 gets the invoices for user 1234 on December 5th, 2013, the controller method would look like:

@RequestMapping(value="/user/{userId}/invoices", method = RequestMethod.GET)
public List<Invoice> listUsersInvoices(
            @PathVariable("userId") int user,
            @RequestParam(value = "date", required = false) Date dateOrNull) {
  ...
}
 */
    @GetMapping("/Messages")
    List<Message> allBetweenTwoPeople() throws Exception {
        return messageService.getMessages(1,2);
    }
/*
    @GetMapping("/Messages/{id]")
    Message one(@PathVariable Integer id){
        return service.findById(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }
*/

    @GetMapping("/Messages/{id1}/{id2}")
    List<Message> allBetweenTwoPeople(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2) throws Exception {
        return messageService.getMessages(id1,id2);
    }


}
