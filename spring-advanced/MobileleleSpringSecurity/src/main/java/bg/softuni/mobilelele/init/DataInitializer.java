package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.model.entities.*;
import bg.softuni.mobilelele.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OfferRepository offerRepository;

    public DataInitializer(BrandRepository brandRepository, ModelRepository modelRepository, UserRoleRepository userRoleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, OfferRepository offerRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.offerRepository = offerRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        initBrandAndModels();
        initRoles();
        initUsers();
        initializeOffers();

    }

    private void initBrandAndModels() {
        if (brandRepository.count() == 0 && modelRepository.count() == 0) {
            BrandEntity mercedes = new BrandEntity()
                    .setName("Mercedes").setCreated(Instant.now());
            BrandEntity skoda = new BrandEntity()
                    .setName("Skoda").setCreated(Instant.now());
            ModelEntity clk = new ModelEntity().setBrand(mercedes)
                    .setCategory(ModelCategory.CAR)
                    .setImageUrl("asdasda")
                    .setStartYear(1997)
                    .setEndYear(2004)
                    .setCreated(Instant.now()).setName("CLK");
            ModelEntity octavia = new ModelEntity()
                    .setBrand(skoda)
                    .setCategory(ModelCategory.CAR)
                    .setImageUrl("asdasda")
                    .setStartYear(1995)
                    .setCreated(Instant.now()).setName("Octavia");
            modelRepository.save(clk);
            modelRepository.save(octavia);
        }
    }

    private void initRoles() {
        if(userRoleRepository.count() == 0) {
            UserRoleEntity admin = new UserRoleEntity();
            admin.setRole(UserRoleEnum.ADMIN);
            UserRoleEntity user = new UserRoleEntity();
            user.setRole(UserRoleEnum.USER);
            userRoleRepository.save(user);
            userRoleRepository.save(admin);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            UserEntity user = new UserEntity();
            user.setFirstName("Test")
                    .setLastName("Testov")
                    .setUsername("Test")
                    .setPassword(passwordEncoder.encode("12345"))
                    .getRoles().add(userRoleRepository.findUserRoleEntityByRole(UserRoleEnum.USER));
            UserEntity user1 = new UserEntity();
            user1.setFirstName("Ivan")
                    .setLastName("Petrov")
                    .setUsername("Purvi")
                    .setPassword(passwordEncoder.encode("asdasd"))
                    .getRoles().add(userRoleRepository.findUserRoleEntityByRole(UserRoleEnum.ADMIN));
            userRepository.save(user);
            userRepository.save(user1);


        }

    }
    public void initializeOffers() {

        if (offerRepository.count() == 0) {
            OfferEntity offer1 = new OfferEntity();
            offer1
                    .setModel(modelRepository.findById(1L).orElse(null))
                    .setEngineType(EngineType.GASOLINE)
                    .setTransmission(TransmissionType.MANUAL)
                    .setMileage(22500)
                    .setPrice(BigDecimal.valueOf(14300))
                    .setYear(2019)
                    .setDescription("Used, but well services and in good condition.")
                    .setSeller(userRepository.findByUsername("Purvi")
                            .orElse(null)) // or currentUser.getUserName()
                    .setImageUrl(
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcXp1KBpDKgYs6VqndkBpX8twjPOZbHV86yg&usqp=CAU");

            OfferEntity offer2 = new OfferEntity();
            offer2
                    .setModel(modelRepository.findById(1L).orElse(null))
                    .setEngineType(EngineType.DIESEL)
                    .setTransmission(TransmissionType.AUTOMATIC)
                    .setMileage(209000)
                    .setPrice(BigDecimal.valueOf(5500))
                    .setYear(2000)
                    .setDescription("After full maintenance, insurance, new tires...")
                    .setSeller(userRepository.findByUsername("Test")
                            .orElse(null)) // or currentUser.getUserName()
                    .setImageUrl(
                            "https://www.picclickimg.com/d/l400/pict/283362908243_/FORD-ESCORT-MK5-16L-DOHC-16v-ZETEC.jpg");

            offerRepository.saveAll(List.of(offer1, offer2));
        }
    }
}
