package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.PostSeedDto;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlConvertorUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostImpl implements PostService {
    private final String XML_FILE_PATH = "src/main/resources/files/posts.xml";
    private final XmlConvertorUtil xmlConvertorUtil;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public PostImpl(XmlConvertorUtil xmlConvertorUtil, PostRepository postRepository, UserRepository userRepository, PictureRepository pictureRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.xmlConvertorUtil = xmlConvertorUtil;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(XML_FILE_PATH));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        PostSeedDto postSeedDto = xmlConvertorUtil.fromFile(XML_FILE_PATH, PostSeedDto.class);
    postSeedDto.getPosts().stream().filter(d -> {
            boolean isValid = validationUtil.isValid(d)
                    && pictureRepository.findPictureByPath(d.getPicture().getPath()) != null
                    && userRepository.findUserByUsername(d.getUser().getUsername()) != null;
            sb.append(isValid ? String.format("Successfully imported Post, made by %s", d.getUser().getUsername())
                    : "Invalid Post").append(System.lineSeparator());
            return isValid;
        }).map(d -> {
            Post post = modelMapper.map(d, Post.class);
            post.setPicture(pictureRepository.findPictureByPath(d.getPicture().getPath()));
            post.setUser(userRepository.findUserByUsername(d.getUser().getUsername()));
            return post;
        }).forEach(postRepository::save);
        return sb.toString();
    }
}
