package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Message;
import com.jj.Gradebook.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public List<Message> findAll(){
        return messageService.findAll();
    }

    @GetMapping("/messages/{id}")
    public Message findById(@PathVariable int id){
        return messageService.findById(id);
    }

    @PostMapping("/messages")
    public Message save(@RequestBody Message message){
        return messageService.save(message);
    }

    @DeleteMapping("/messages/{id}")
    public void deleteById(@PathVariable int id){
        messageService.deleteById(id);
    }
}
