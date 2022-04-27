package com.example.packetproblemspring.service.impl;

import com.example.packetproblemspring.service.UtilService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UtilServiceImpl implements UtilService {
    private final String PATH = "src/main/resources/out/";
    @Override
    public boolean fileInputIsValid(String fileName) {
        String regex = "[A-Za-z0-9]+\\.txt";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileName);
        return matcher.find();
    }

    @Override
    public List<String> extractChunks(String input) {
        String regex = "[A-Za-z0-9]{1,5}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> chunks = new ArrayList<>();
        while (matcher.find()) {
            chunks.add(matcher.group());
        }
        return chunks;
    }

    @Override
    public String convertToBase64String(String chunk) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bytes = chunk.getBytes("UTF-8");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digested = md5.digest(bytes);
        return Base64.getEncoder().encodeToString(digested);
    }

    @Override
    public String formatChunks(List<String> chunks) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        List<String> converted = new ArrayList<>();
        for (String chunk : chunks) {
            String encodedChunk = convertToBase64String(chunk);
            String result = String.format("%d;%s;%s", chunk.length(), chunk, encodedChunk);
            converted.add(result);
        }
        return String.join("|", converted);
    }

    @Override
    public void writeToFile(String fileName, String content) throws IOException {
        Files.write(Paths.get(PATH + fileName), Collections.singleton(content));
    }
}
