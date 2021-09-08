package com.example.courseras.repositories;

import com.example.courseras.models.entities.Courses;
import com.example.courseras.models.entities.Student;
import com.example.courseras.models.entities.Xref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface XrefRepository extends JpaRepository<Xref, Student> {
    @Query("SELECT x FROM Xref x WHERE x.student.pin = :pin")
    Xref findAllByStudent(String pin);
}
