package com.jj.Gradebook.service.message;

import com.jj.Gradebook.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll();
    Message findById(Long id);
    Message save(Message message);
    void deleteById(Long id);
}
