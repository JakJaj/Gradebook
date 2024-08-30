package com.jj.Gradebook.dao;

import com.jj.Gradebook.entity.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcements,Long> {
}
