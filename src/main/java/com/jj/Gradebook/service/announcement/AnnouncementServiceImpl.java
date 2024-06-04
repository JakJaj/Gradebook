package com.jj.Gradebook.service.announcement;

import com.jj.Gradebook.dao.AnnouncementRepository;
import com.jj.Gradebook.entity.Announcements;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementServiceImpl implements AnnouncementService{

    private final AnnouncementRepository announcementRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public List<Announcements> findAll() {
        return announcementRepository.findAll();
    }

    @Override
    public Announcements findById(int id) {
        Optional<Announcements> result = announcementRepository.findById(id);

        Announcements announcement;
        if(result.isPresent()){
            announcement = result.get();
        }
        else{
            throw new RuntimeException("No announcements with id - " + id);
        }
        return announcement;
    }

    @Override
    @Transactional
    public Announcements save(Announcements announcements) {
        return announcementRepository.save(announcements);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        announcementRepository.deleteById(id);
    }
}
