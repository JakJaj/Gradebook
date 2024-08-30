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

/**
 * Class that is an implementation of a service that is being used to get / set / delete a parents data from a parents database table
 */
@Service
@AllArgsConstructor

public class ParentServiceImpl implements ParentService{

    private final ParentRepository parentRepository;

    /**
     * Business logic used for getting all of existing parents from a database
     * @return list of all instances of ParentDTOs that exists in a database
     * @throws EntityListEmptyException exception that will be thrown when a database table is empty
     */
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

    /**
     *  Business logic used for finding a specific parent by provided parent id
     * @param id id of a parent that will be used to get a parent from a database
     * @return instance of ParentDTO that was received from a database
     * @throws EntityNotFoundException exception that will be thrown when provided id won't match any id in database
     */
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

    /**
     * Business logic used for creating a new parent in the database
     * @param parent parent data that needs to be placed in a database
     * @return data of a created parent instance
     * @throws EntityAlreadyExistException exception that will be thrown when a provided parent's pesel already exists in a database
     */
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

    /**
     * Business logic used for deleting a parent from a database by provided id
     * @param id id of a parent that is meant to be deleted from a database
     * @throws EntityNotFoundException exception that will be thrown when provided id won't match any id in database
    **/
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

    /**
     *  Template generator of parentDTO from Parent
     *  @param parent the parent instance with all the information about parent with parent's personal data included
     *  @return       parentDTO which includes just a data that can be seen by an end users
     **/
    private ParentDTO getParentDTO(Parent parent){
        return new ParentDTO(
                parent.getParentId(),
                parent.getFirstName(),
                parent.getLastName(),
                parent.getUser().getEmail()
        );
    }
}
