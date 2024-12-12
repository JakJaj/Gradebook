package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcements, Long> {
    void deleteAllByTeacher_TeacherId(Long teacherID);

}
