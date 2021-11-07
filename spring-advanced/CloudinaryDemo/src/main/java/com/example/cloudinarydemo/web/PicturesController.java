package com.example.cloudinarydemo.web;

import com.example.cloudinarydemo.model.binding.PictureBindingModel;
import com.example.cloudinarydemo.model.entity.PictureEntity;
import com.example.cloudinarydemo.repository.PictureRepository;
import com.example.cloudinarydemo.service.CloudinaryImage;
import com.example.cloudinarydemo.service.CloudinaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Controller
@RequestMapping("/pictures")
public class PicturesController {
    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;

    public PicturesController(CloudinaryService cloudinaryService, PictureRepository pictureRepository) {
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
        //obviously not good practice to use repo directly in controller, done for sake of demo
    }

    @GetMapping("/add")
    public String getAdd() {
        return "add";
    }
    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("pictures", cloudinaryService.getAllPictures());
        return "all";
    }

    @Transactional
    @DeleteMapping("/delete")
    public String deletePicture(@RequestParam("public_id") String publicId) {
        if(cloudinaryService.delete(publicId)) {
            pictureRepository.deleteAllByPublicId(publicId);
        }
        return "redirect:all";
    }
    @PostMapping("/add")
    public String postAdd(PictureBindingModel pictureBindingModel) throws IOException {

        PictureEntity pictureEntity = createPictureEntity(pictureBindingModel.getPicture(), pictureBindingModel.getTitle());
        pictureRepository.save(pictureEntity);

        return "redirect:all";
    }

    private PictureEntity createPictureEntity(MultipartFile multipartFile, String title) throws IOException {
        CloudinaryImage upload = cloudinaryService.upload(multipartFile);
        return new PictureEntity().setPublicId(upload.getPublicId()).setUrl(upload.getUrl()).setTitle(title);
    }

}
