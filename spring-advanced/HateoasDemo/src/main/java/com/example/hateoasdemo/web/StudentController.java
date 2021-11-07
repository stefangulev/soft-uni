package com.example.hateoasdemo.web;

import com.example.hateoasdemo.model.Order;
import com.example.hateoasdemo.model.Student;
import com.example.hateoasdemo.model.dto.OrderDto;
import com.example.hateoasdemo.model.dto.StudentDTO;
import com.example.hateoasdemo.model.mapping.StudentMapper;
import com.example.hateoasdemo.repositories.StudentRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/students")
@RestController
public class StudentController {

    private final StudentRepository studentRepository;
   private final StudentMapper studentMapper;

    public StudentController(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentByID(@PathVariable Long id) {
        StudentDTO studentDTO = studentRepository.findById(id).map(studentMapper::mapEntityToDto).orElseThrow();

        return ResponseEntity.ok(EntityModel.of(studentDTO, createStudentLinks(studentDTO)));


    }
    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDto>>> getOrders(@PathVariable("id") Long id) {
        Student student = studentRepository.
                findById(id).orElseThrow();

        List<EntityModel<OrderDto>> orders = student.
                getOrders().
                stream().
                map(this::map).
                map(EntityModel::of).
                collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(orders));
    }

    private OrderDto map(Order order) {
        return new OrderDto().setId(order.getId()).setStudentId(order.getStudent().getId()).
                setCourseId(order.getCourse().getId());
    }
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudents() {
        List<EntityModel<StudentDTO>> allStudents = studentRepository.findAll()
                .stream().map(studentMapper::mapEntityToDto).map(d -> EntityModel.of(d, createStudentLinks(d))).collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(allStudents));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> update(StudentDTO studentDTO, @PathVariable Long id) {

        //IMPL NOT IMPORTANT FOR DEMO
        return ResponseEntity.ok().build();
    }

    private Link[] createStudentLinks(StudentDTO studentDTO) {
        List<Link> result = new ArrayList<>();
        Link selfLink = linkTo(methodOn(StudentController.class).getStudentByID(studentDTO.getId())).withSelfRel();
        result.add(selfLink);
        Link updateLink = linkTo(methodOn(StudentController.class).update( studentDTO, studentDTO.getId())).withRel("update");
        result.add(updateLink);
        Link orderLink = linkTo(methodOn(StudentController.class).getOrders(studentDTO.getId())).withRel("orders");
        result.add(orderLink);
        return  result.toArray(new Link[0]);
    }
}
