package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
