package com.example.aopdemo;

import org.springframework.stereotype.Component;

@Component
public class Student {

    public void sayHello() {
        System.out.println("Hello");
    }
    public void echo(String echo) {
        System.out.println("Echo: " + echo);
    }

    public String concat(String a, String b) {
        return a + b;
    }

    public void boom() {
        throw new IllegalStateException("Boom");
    }
}
