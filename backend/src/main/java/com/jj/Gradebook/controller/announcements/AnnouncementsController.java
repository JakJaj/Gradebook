package com.jj.Gradebook.controller.announcements;

import com.jj.Gradebook.controller.response.announcements.AnnouncementsResponse;
import com.jj.Gradebook.service.announcement.AnnouncementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
public class AnnouncementsController {

    private final AnnouncementsService announcementsService;

    @GetMapping()
    public ResponseEntity<AnnouncementsResponse> getAllAnnouncements(){
        return ResponseEntity.ok(announcementsService.getAllAnnouncements());
    }
}
