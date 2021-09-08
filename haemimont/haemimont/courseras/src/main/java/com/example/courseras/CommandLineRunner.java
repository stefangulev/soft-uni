package com.example.courseras;

import com.example.courseras.models.entities.Xref;
import com.example.courseras.services.XrefService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final XrefService xrefService;

    public CommandLineRunner(XrefService xrefService) {
        this.xrefService = xrefService;
    }

    @Override
    public void run(String... args) throws Exception {

    }

    public List<Xref> findStudents(String pins, int minimumCredit) {
        String[] ids = pins.split(",");
        long countOfRelevnatUsers = ids.length;
        if(ids.length == 0 ) {
            countOfRelevnatUsers = xrefService.studentCount();
        }
        List<Xref> relevantEntries = new ArrayList<>();
        for (int i = 0; i < countOfRelevnatUsers; i++) {
            relevantEntries.add(xrefService.findStudents(ids[i]));
        }
        //TO DO
        return relevantEntries;
    }
}
