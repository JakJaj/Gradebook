package com.jj.Gradebook.service.classes;

import java.util.List;
import com.jj.Gradebook.entity.Class;
public interface ClassService {
    List<Class> findAll();
    Class findById(int id);
    Class save(Class classes);
    void deleteById(int id);
}
