package com.jj.Gradebook.controller.announcements;

import com.jj.Gradebook.controller.request.announcements.CreateAnnouncementRequest;
import com.jj.Gradebook.controller.request.announcements.UpdateAnnouncementDetailsRequest;
import com.jj.Gradebook.controller.response.announcements.AnnouncementResponse;
import com.jj.Gradebook.controller.response.announcements.AnnouncementsResponse;
import com.jj.Gradebook.service.announcement.AnnouncementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
public class AnnouncementsController {

    private final AnnouncementsService announcementsService;

    @GetMapping()
    public ResponseEntity<AnnouncementsResponse> getAllAnnouncements(){
        return ResponseEntity.ok(announcementsService.getAllAnnouncements());
    }

    @PostMapping()
    public ResponseEntity<AnnouncementResponse> createNewAnnouncements(@RequestBody CreateAnnouncementRequest request){
        return ResponseEntity.ok(announcementsService.createNewAnnouncement(request));
    }

    @PutMapping()
    public ResponseEntity<AnnouncementResponse> updateAnnouncementDetails(@RequestBody UpdateAnnouncementDetailsRequest request){
        return ResponseEntity.ok(announcementsService.updateAnnouncementDetail(request));
    }
}
