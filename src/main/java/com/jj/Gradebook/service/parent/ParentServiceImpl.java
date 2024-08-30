package com.jj.Gradebook.service.parent;

import com.jj.Gradebook.dao.ParentRepository;
import com.jj.Gradebook.dto.ParentDTO;
import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.exceptions.EntityAlreadyExistException;
import com.jj.Gradebook.exceptions.EntityListEmptyException;
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
    public List<ParentDTO> findAll() throws EntityListEmptyException {
        List<Parent> data = parentRepository.findAll();
        List<ParentDTO> result = new ArrayList<>();

        if (data.isEmpty()){
            throw new EntityListEmptyException("List of parents is empty!");

        }

        for (Parent parent : data){
            result.add(getParentDTO(parent));
        }

        return result;
    }

    @Override
    @Transactional
    public ParentDTO findById(Long id) throws EntityNotFoundException {
        Optional<Parent> result = parentRepository.findById(id);

        if(result.isPresent()){
            return getParentDTO(result.get());
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
            return getParentDTO(savedParent);
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

    private ParentDTO getParentDTO(Parent result){
        return new ParentDTO(
                result.getParentId(),
                result.getFirstName(),
                result.getLastName(),
                result.getUser().getEmail()
        );
    }
}
