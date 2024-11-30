package com.jj.Gradebook.controller.parents;

import com.jj.Gradebook.controller.response.parents.ParentsResponse;
import com.jj.Gradebook.service.parents.ParentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parents")
@RequiredArgsConstructor
public class ParentsController {
    private final ParentsService parentsService;

    @GetMapping("")
    public ResponseEntity<ParentsResponse> getAllParents(){
        return ResponseEntity.ok(parentsService.getAllParents());
    }
}
