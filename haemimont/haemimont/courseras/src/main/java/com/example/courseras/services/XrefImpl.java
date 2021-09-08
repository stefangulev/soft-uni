package com.example.courseras.services;

import com.example.courseras.models.entities.Student;
import com.example.courseras.models.entities.Xref;
import com.example.courseras.repositories.XrefRepository;
import org.springframework.stereotype.Service;

@Service
public class XrefImpl implements XrefService{

    private final XrefRepository xrefRepository;


    public XrefImpl(XrefRepository xrefRepository) {
        this.xrefRepository = xrefRepository;
    }


    @Override
    public Xref findStudents(String pin) {
        return xrefRepository.findAllByStudent(pin);
    }

    @Override
    public Long studentCount() {
        return xrefRepository.count();
    }
}
