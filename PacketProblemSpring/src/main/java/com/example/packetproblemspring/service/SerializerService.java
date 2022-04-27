package com.example.packetproblemspring.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface SerializerService {
    void serialize(String input, String fileName) throws IOException, NoSuchAlgorithmException;
}
