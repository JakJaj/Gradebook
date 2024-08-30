package com.jj.Gradebook.service.message;

import com.jj.Gradebook.dao.MessageRepository;
import com.jj.Gradebook.entity.Message;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MessengerServiceImpl implements MessageService{

    private MessageRepository messageRepository;


    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message findById(Long id) {
        Optional<Message> result = messageRepository.findById(id);

        Message message;
        if(result.isPresent()){
            message = result.get();
        }
        else { //TODO: FIND BETTER APPROACH
            throw new RuntimeException("No message with id - " + id);
        }
        return message;
    }

    @Override
    @Transactional
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }
}
