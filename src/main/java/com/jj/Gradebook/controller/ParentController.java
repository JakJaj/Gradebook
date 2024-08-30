package com.jj.Gradebook.controller;

import com.jj.Gradebook.dto.ParentDTO;
import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.entity.User;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import com.jj.Gradebook.service.parent.ParentService;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ParentController {

    private ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @CrossOrigin
    @GetMapping("/parents")
    public ResponseEntity<List<ParentDTO>> findAll(){
        List<ParentDTO> output = parentService.findAll();

        return ResponseEntity.ok(output);
    }

    @GetMapping("/parents/{id}")
    public ResponseEntity<ParentDTO> findById(@PathVariable Long id) throws EntityNotFoundException {
        ParentDTO output = parentService.findById(id);
        return ResponseEntity.ok(output);
    }

    @PostMapping("/parents")
    public ResponseEntity<ParentDTO> save(@RequestBody Parent parent) throws EntityAlreadyExistException {
        ParentDTO output = parentService.save(parent);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/parents/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws EntityNotFoundException {
        parentService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
