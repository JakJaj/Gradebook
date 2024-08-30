package com.jj.Gradebook.service.parent;

import com.jj.Gradebook.dao.ParentRepository;
import com.jj.Gradebook.dto.ParentDTO;
import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParentServiceImpl implements ParentService{

    private final ParentRepository parentRepository;

    @Override
    @Transactional
    public List<ParentDTO> findAll() {
        List<Parent> data = parentRepository.findAll();
        List<ParentDTO> result = new ArrayList<>();

        for (Parent parent : data){
            result.add(
                    new ParentDTO(
                            parent.getParentId(),
                            parent.getFirstName(),
                            parent.getLastName(),
                            parent.getUser().getEmail()
                            )
            );
        }
        return result;
    }// ! ENTITY LIST EMPTY EXCEPTION FOR ALL FIND ALL METHODS!

    @Override
    @Transactional
    public ParentDTO findById(Long id) throws EntityNotFoundException {
        Optional<Parent> result = parentRepository.findById(id);

        Parent parent;
        if(result.isPresent()){
            parent = result.get();
            return new ParentDTO(
                    parent.getParentId(),
                    parent.getFirstName(),
                    parent.getLastName(),
                    parent.getUser().getEmail()
            );
        }
        else{
            throw new EntityNotFoundException("No parent with id - " + id);
        }
    }

    @Override
    public ParentDTO save(Parent parent) throws EntityAlreadyExistException {
        Optional<Parent> existingParent = parentRepository.findParentByUser_Pesel(parent.getUser().getPesel());
        if (existingParent.isEmpty()){
            Parent savedParent = parentRepository.save(parent);
            return new ParentDTO(
                    savedParent.getParentId(),
                    savedParent.getFirstName(),
                    savedParent.getLastName(),
                    savedParent.getUser().getEmail()
            );
        }
        else {
            throw new EntityAlreadyExistException("Parent already exists!");
        }
    }

    @Override
    public void deleteById(Long id) throws EntityNotFoundException {
        Optional<Parent> existingParent = parentRepository.findById(id);
        if (existingParent.isPresent()) {
            parentRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("No parent with id - " + id);
        }
    }
}
