package com.example.cloudinarydemo.service.impl;

import com.cloudinary.Cloudinary;
import com.example.cloudinarydemo.model.view.PictureView;
import com.example.cloudinarydemo.repository.PictureRepository;
import com.example.cloudinarydemo.service.CloudinaryImage;
import com.example.cloudinarydemo.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";
    private final Cloudinary cloudinary;
    private final PictureRepository pictureRepository;

    public CloudinaryServiceImpl(Cloudinary cloudinary, PictureRepository pictureRepository) {
        this.cloudinary = cloudinary;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public CloudinaryImage upload(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary.uploader().upload(tempFile, Map.of());

            String url = uploadResult.getOrDefault(URL, "https://bitsofco.de/content/images/2018/12/broken-1.png");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            return new CloudinaryImage().setUrl(url).setPublicId(publicId);
        } finally {
            tempFile.delete();
        }
    }

    @Override
    public boolean delete(String publicId) {
        try{
            cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    @Override
    public List<PictureView> getAllPictures() {
        return pictureRepository.findAll().stream()
                .map(p -> new PictureView()
                        .setPublicId(p.getPublicId()).setUrl(p.getUrl()).setTitle(p.getTitle())).collect(Collectors.toList());
    }
}
