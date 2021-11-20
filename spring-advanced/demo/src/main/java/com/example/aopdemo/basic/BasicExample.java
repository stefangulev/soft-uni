package com.example.aopdemo.basic;

import com.example.aopdemo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "examples.basic.enabled", havingValue = "true")

public class BasicExample implements CommandLineRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(BasicExample.class);
    private final Student student;

    public BasicExample(Student student) {
        this.student = student;
    }


    @Override
    public void run(String... args) throws Exception {
        student.sayHello();
        student.echo("Echo something");
        LOGGER.info(student.concat("A", "B"));
        try {
            student.boom();
        } catch (Exception ex) {

        }
    }
}
