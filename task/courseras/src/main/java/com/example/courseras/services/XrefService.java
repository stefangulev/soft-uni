package com.example.courseras.services;


import com.example.courseras.models.entities.Student;
import com.example.courseras.models.entities.Xref;

import java.util.List;

public interface XrefService {
   Xref findStudents(String pin);
   Long studentCount();
}
