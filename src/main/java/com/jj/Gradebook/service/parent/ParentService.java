package com.jj.Gradebook.service.parent;

import com.jj.Gradebook.entity.Parent;

import java.util.List;

public interface ParentService {
    List<Parent> findAll();
    Parent findById(int id);
    Parent save(Parent parent);
    void deleteById(int id);
}
