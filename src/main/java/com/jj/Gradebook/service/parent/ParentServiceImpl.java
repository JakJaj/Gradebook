package com.jj.Gradebook.service.parent;

import com.jj.Gradebook.dao.ParentRepository;
import com.jj.Gradebook.entity.Parent;
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
        return parentRepository.findAll();
    }

    @Override
    @Transactional
    public Parent findById(int id) {
        Optional<Parent> result = parentRepository.findById(id);

        Parent parent = null;
        if(result.isPresent()){
            parent = result.get();
        }
        else{ //TODO: FIND BETTER APPROACH
            throw new RuntimeException("No parent with id - " + id);
        }

        return parent;
    }

    @Override
    public Parent save(Parent parent) {
        return parentRepository.save(parent);
    }

    @Override
    public void deleteById(int id) {
        parentRepository.deleteById(id);
    }
}
