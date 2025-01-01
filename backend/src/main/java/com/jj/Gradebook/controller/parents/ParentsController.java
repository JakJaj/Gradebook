package com.jj.Gradebook.controller.parents;

import com.jj.Gradebook.controller.request.parents.AddNewStudentsToParentRequest;
import com.jj.Gradebook.controller.request.parents.DeleteStudentsToParentRequest;
import com.jj.Gradebook.controller.request.parents.UpdateParentDetailsRequest;
import com.jj.Gradebook.controller.response.BaseResponse;
import com.jj.Gradebook.controller.response.parents.ParentResponse;
import com.jj.Gradebook.controller.response.parents.ParentsResponse;
import com.jj.Gradebook.controller.response.students.StudentsResponse;
import com.jj.Gradebook.service.parents.ParentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parents")
@RequiredArgsConstructor
@CrossOrigin
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

    @GetMapping("/{parentID}/students")
    public ResponseEntity<StudentsResponse> getStudentsOfParent(@PathVariable Long parentID){
        return ResponseEntity.ok(parentsService.getStudentsOfParent(parentID));
    }

    @PostMapping("/{parentID}/students")
    public ResponseEntity<StudentsResponse> addNewStudentToAParents(@PathVariable Long parentID, @RequestBody AddNewStudentsToParentRequest request){
        return ResponseEntity.ok(parentsService.addNewStudentsToParentRequest(parentID, request));
    }

    @PutMapping()
    public ResponseEntity<ParentResponse> updateParentDetails(@RequestBody UpdateParentDetailsRequest request){
        return ResponseEntity.ok(parentsService.updateParentDetails(request));
    }

    @DeleteMapping("/{parentID}/students")
    public ResponseEntity<BaseResponse> deleteStudentFromParent(@PathVariable Long parentID, @RequestBody DeleteStudentsToParentRequest request){
        return ResponseEntity.ok(parentsService.deleteStudentFromParent(parentID, request));
    }

    @DeleteMapping("/{parentID}")
    public ResponseEntity<BaseResponse> deleteParent(@PathVariable Long parentID){
        return ResponseEntity.ok(parentsService.deleteParent(parentID));
    }
}
