package com.example.hateoasdemo.model.mapping;

import com.example.hateoasdemo.model.Student;
import com.example.hateoasdemo.model.dto.StudentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO mapEntityToDto(Student student);
}
