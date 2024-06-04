package com.jj.Gradebook.service.announcement;

import com.jj.Gradebook.entity.Announcements;

import java.util.List;

public interface AnnouncementService {
    List<Announcements> findAll();
    Announcements findById(int id);
    Announcements save(Announcements announcements);
    void deleteById(int id);
}
