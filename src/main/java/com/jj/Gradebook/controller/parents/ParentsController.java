package com.jj.Gradebook.controller.parents;

import com.jj.Gradebook.controller.response.parents.ParentResponse;
import com.jj.Gradebook.controller.response.parents.ParentsResponse;
import com.jj.Gradebook.service.parents.ParentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{parentID}")
    public ResponseEntity<ParentResponse> getParentByID(@PathVariable Long parentID){
        return ResponseEntity.ok(parentsService.getParentByID(parentID));
    }
}
