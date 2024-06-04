package com.jj.Gradebook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jj.Gradebook.entity.Class;
public interface ClassRepository extends JpaRepository<Class,Integer> {
}
