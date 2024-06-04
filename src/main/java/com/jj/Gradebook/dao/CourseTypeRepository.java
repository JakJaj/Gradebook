package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Course_Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseTypeRepository extends JpaRepository<Course_Type,Integer> {
}
