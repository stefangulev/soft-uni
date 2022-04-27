package com.example.packetproblemspring.service.impl;

import com.example.packetproblemspring.service.DeserializerService;
import com.example.packetproblemspring.service.UtilService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

@Service
public class DeserializerServiceImpl implements DeserializerService {
    private final String PATH = "src/main/resources/out/";

    private final UtilService utilService;

    public DeserializerServiceImpl(UtilService utilService) {
        this.utilService = utilService;
    }

    @Override
    public void deserialize(String fileName) throws IOException, NoSuchAlgorithmException {
        String[] fileContent = Files.readString(Path.of(PATH + fileName)).split("\\|");
        String result = "";
        for (String s : fileContent) {
            String[] parts = s.split(";");
            int lengthOfPacket = Integer.parseInt(parts[0]);
            String data = parts[1];
            String base64String = parts[2].trim();
            if(data.length() != lengthOfPacket || !utilService.convertToBase64String(data).equals(base64String)) {
                System.out.println("The packet integrity has been compromised");
                return;
            }
            result += data;
        }
        System.out.println(result);
    }
}
