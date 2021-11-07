package com.example.cloudinarydemo.service;

import com.example.cloudinarydemo.model.view.PictureView;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CloudinaryService {
    CloudinaryImage upload(MultipartFile file) throws IOException;
    boolean delete(String publicId);
    List<PictureView> getAllPictures();
}
