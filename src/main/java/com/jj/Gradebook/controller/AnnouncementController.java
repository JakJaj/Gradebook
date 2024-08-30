package com.jj.Gradebook.controller;

import com.jj.Gradebook.entity.Announcements;
import com.jj.Gradebook.service.announcement.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnnouncementController {
    private AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/announcements")
    public List<Announcements> findAll(){
        return announcementService.findAll();
    }

    @GetMapping("/announcements/{id}")
    public Announcements findById(@PathVariable Long id){
        return announcementService.findById(id);
    }

    @PostMapping("/announcements")
    public Announcements save(@RequestBody Announcements announcements){
        return announcementService.save(announcements);
    }

    @DeleteMapping("/announcements/{id}")
    public void deleteById(@PathVariable Long id){
        announcementService.deleteById(id);
    }
}
