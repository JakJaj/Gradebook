package com.jj.Gradebook.service.parent;

import com.jj.Gradebook.dao.ParentRepository;
import com.jj.Gradebook.entity.Parent;
import com.jj.Gradebook.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParentServiceImpl implements ParentService{

    private final ParentRepository parentRepository;

    @Override
    @Transactional
    public List<Parent> findAll() {
        return parentRepository.findAll(); // ! ENTITY LIST EMPTY EXCEPTION FOR ALL FIND ALL METHODS!
    }

    @Override
    @Transactional
    public Parent findById(Long id) throws EntityNotFoundException {
        Optional<Parent> result = parentRepository.findById(id);

        Parent parent = null;
        if(result.isPresent()){
            parent = result.get();
        }
        else{ //TODO: FIND BETTER APPROACH
            throw new EntityNotFoundException("No parent with id - " + id);
        }

        return parent;
    }

    @Override
    public Parent save(Parent parent) {


        return parentRepository.save(parent);
    }

    @Override
    public void deleteById(Long id) {
        parentRepository.deleteById(id);
    }
}
