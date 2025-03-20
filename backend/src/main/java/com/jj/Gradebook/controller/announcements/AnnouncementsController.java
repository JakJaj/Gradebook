package com.jj.Gradebook.controller.announcements;

import com.jj.Gradebook.controller.request.announcements.CreateAnnouncementRequest;
import com.jj.Gradebook.controller.request.announcements.UpdateAnnouncementDetailsRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.announcements.AnnouncementResponse;
import com.jj.Gradebook.controller.response.announcements.AnnouncementsResponse;
import com.jj.Gradebook.service.announcement.AnnouncementsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
@Slf4j
public class AnnouncementsController {

    private final AnnouncementsService announcementsService;

    @GetMapping()
    public ResponseEntity<AnnouncementsResponse> getAllAnnouncements(){
        log.info("GET /announcements - Fetching all announcements");
        return ResponseEntity.ok(announcementsService.getAllAnnouncements());
    }

    @PostMapping()
    public ResponseEntity<AnnouncementResponse> createNewAnnouncements(@RequestBody CreateAnnouncementRequest request){
        log.info("POST /announcements - Creating new announcement with request: {}", request);
        return ResponseEntity.ok(announcementsService.createNewAnnouncement(request));
    }

    @PutMapping()
    public ResponseEntity<AnnouncementResponse> updateAnnouncementDetails(@RequestBody UpdateAnnouncementDetailsRequest request){
        log.info("PUT /announcements - Updating announcement details with request: {}", request);
        return ResponseEntity.ok(announcementsService.updateAnnouncementDetail(request));
    }

    @DeleteMapping("/{announcementID}")
    public ResponseEntity<BaseResponse> deleteAnnouncement(@PathVariable Long announcementID){
        log.info("DELETE /announcements/{} - Deleting announcement", announcementID);
        return ResponseEntity.ok(announcementsService.deleteAnnouncement(announcementID));
    }
}
