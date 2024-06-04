package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
}
