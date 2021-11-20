package com.example.aopdemo.modifying;

import com.example.aopdemo.Student;
import com.example.aopdemo.basic.BasicAspectExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "examples.modifying.enabled", havingValue = "true")
public class ModifyingExample implements CommandLineRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(ModifyingExample.class);
    private final Student student;

    public ModifyingExample(Student student) {
        this.student = student;
    }


    @Override
    public void run(String... args) throws Exception {
       LOGGER.info(student.concat("A", "B"));
    }
}
