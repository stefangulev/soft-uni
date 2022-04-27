package com.example.packetproblemspring.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UtilService {
    boolean fileInputIsValid(String fileName);
    List<String> extractChunks(String input);
    String convertToBase64String(String chunk) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    String formatChunks(List<String> chunks) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    void writeToFile(String fileName, String content) throws IOException;
}
