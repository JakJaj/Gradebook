package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByUser_UserId(Long userID);

}
