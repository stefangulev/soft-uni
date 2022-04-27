package com.example.packetproblemspring.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface DeserializerService{
    void deserialize(String fileName) throws IOException, NoSuchAlgorithmException;
}
