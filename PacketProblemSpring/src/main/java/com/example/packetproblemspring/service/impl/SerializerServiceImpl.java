package com.example.packetproblemspring.service.impl;

import com.example.packetproblemspring.service.SerializerService;
import com.example.packetproblemspring.service.UtilService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class SerializerServiceImpl implements SerializerService {

    private final UtilService utilService;

    public SerializerServiceImpl(UtilService utilService) {
        this.utilService = utilService;
    }

    @Override
    public void serialize(String input, String fileName) throws IOException, NoSuchAlgorithmException {
        if(input.isBlank()) {
            System.out.println("Invalid input! Can't be blank!");
            return;
        }
        List<String> chunks = utilService.extractChunks(input);
        utilService.writeToFile(fileName, utilService.formatChunks(chunks));
        System.out.printf("File %s has been updated!", fileName);
    }
}
