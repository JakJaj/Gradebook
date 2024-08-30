package com.jj.Gradebook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jj.Gradebook.entity.Class;

import java.time.Year;
import java.util.Calendar;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class,Long> {
    Optional<Class> findClassByClassNameAndStartYear(String className, Year startYear);
}
