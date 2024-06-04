package com.jj.Gradebook.service.timetable;

import com.jj.Gradebook.dao.TimetableRepository;
import com.jj.Gradebook.entity.Timetable;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TimetableServiceImpl implements TimetableService{
    private TimetableRepository timetableRepository;

    @Override
    public List<Timetable> findAll() {
        return timetableRepository.findAll();
    }

    @Override
    public Timetable findById(int id) {
        Optional<Timetable> result = timetableRepository.findById(id);

        Timetable timetable;
        if (result.isPresent()){
            timetable = result.get();
        }
        else { //TODO: FIND BETTER APPROACH
            throw new RuntimeException("No timetable with id - " + id);
        }
        return timetable;
    }

    @Override
    @Transactional
    public Timetable save(Timetable timetable) {
        return timetableRepository.save(timetable);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        timetableRepository.deleteById(id);
    }
}
