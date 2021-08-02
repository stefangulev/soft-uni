package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.UserSeedDto;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService {
    private final String JSON_FILE_PATH = "src/main/resources/files/users.json";
    private final Gson gson;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public UserImpl(Gson gson, UserRepository userRepository, PictureRepository pictureRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.gson = gson;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(JSON_FILE_PATH));
    }

    @Override
    public String importUsers() throws IOException {
        StringBuilder sb = new StringBuilder();
      Arrays.stream(gson.fromJson(readFromFileContent(), UserSeedDto[].class)).filter(d -> {
            boolean isValid = validationUtil.isValid(d) && pictureRepository.findPictureByPath(d.getProfilePicture()) != null;
            sb.append(isValid ? String.format("Successfully imported User: %s", d.getUsername())
                    : "Invalid User").append(System.lineSeparator());
            return isValid;
        }).map(d -> {
            User user = modelMapper.map(d, User.class);
            user.setPicture(pictureRepository.findPictureByPath(d.getProfilePicture()));
            return user;
        }).forEach(userRepository::save);
        return sb.toString();
    }

    @Override
    public String exportUsersWithTheirPosts() {
        StringBuilder sb = new StringBuilder();
      userRepository.findUsersWithPostsOrderByCountDescAndId().forEach(u -> {
          sb.append(String.format("User: %s%n" +
                  "Post count: %d%n" +
                  "%s", u.getUsername(), u.getPosts().size(), u.getPosts().stream()
                  .sorted(Comparator.comparingDouble(l -> l.getPicture().getSize()))
                  .map(p -> String.format("==Post Details:%n"+ "----Caption: %s%n" +
                          "----Picture Size: %.2f", p.getCaption(), p.getPicture().getSize())).collect(Collectors.joining("\n"))))
                  .append(System.lineSeparator());
      });
        return sb.toString();
    }
}
