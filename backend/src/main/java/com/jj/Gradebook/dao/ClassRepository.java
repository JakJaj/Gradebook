package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
    void deleteAllByClassId(Long classID);
}
