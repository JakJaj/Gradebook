package com.jj.Gradebook.service.parents;

import com.jj.Gradebook.controller.response.parents.ParentResponse;
import com.jj.Gradebook.controller.response.parents.ParentsResponse;
import com.jj.Gradebook.dao.ParentRepository;
import com.jj.Gradebook.dto.ParentDTO;
import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.exceptions.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentsService {

    private final ParentRepository parentRepository;

    public ParentsResponse getAllParents(){

        List<Parent> parents = parentRepository.findAll();

        List<ParentDTO> parentDTOList = parents.stream()
                .map(parent -> ParentDTO.builder()
                        .parentID(parent.getParentId())
                        .firstName(parent.getFirstName())
                        .lastName(parent.getLastName())
                        .build())
                .toList();

        return ParentsResponse.builder()
                .status("Success")
                .message("Successfully return list of parents")
                .parents(parentDTOList)
                .build();
    }

    public ParentResponse getParentByID(Long parentID) {

        Parent parent = parentRepository.findById(parentID).orElseThrow(() -> new NoSuchUserException(String.format("No parent with id - %d", parentID)));

        return ParentResponse.builder()
                .status("Success")
                .message(String.format("Successfully return parent with id - %d", parentID))
                .parent(ParentDTO.builder()
                        .parentID(parent.getParentId())
                        .firstName(parent.getFirstName())
                        .lastName(parent.getLastName())
                        .build())
                .build();
    }
}
