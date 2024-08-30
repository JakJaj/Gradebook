package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
