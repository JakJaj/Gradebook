package com.jj.Gradebook.service.attendance;

import com.jj.Gradebook.dao.AttendanceRepository;
import com.jj.Gradebook.entity.Attendance;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService{
    private final AttendanceRepository attendanceRepository;

    @Override
    public List<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance findById(int id) {
        Optional<Attendance> result = attendanceRepository.findById(id);

        Attendance attendance;
        if(result.isPresent()){
            attendance = result.get();
        }
        else{
            throw new RuntimeException("No attendance with id - " + id);
        }
        return attendance;
    }

    @Override
    @Transactional
    public Attendance save(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        attendanceRepository.deleteById(id);
    }
}
