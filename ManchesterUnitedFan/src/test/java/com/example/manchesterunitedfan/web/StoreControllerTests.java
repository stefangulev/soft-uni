package com.example.manchesterunitedfan.web;

import com.example.manchesterunitedfan.model.entities.*;
import com.example.manchesterunitedfan.model.enums.UserRoleEnum;
import com.example.manchesterunitedfan.repository.ProductRepository;
import com.example.manchesterunitedfan.repository.UserRepository;
import com.example.manchesterunitedfan.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StoreControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private final String PRODUCT_NAME = "Test product";
    private final int PRODUCT_QUANTITY = 2;
    private final BigDecimal PRODUCT_PRICE = BigDecimal.valueOf(10);
    private final String UPDATED_PRODUCT_NAME = "Updated Test product";
    private final int UPDATED_PRODUCT_QUANTITY = 3;
    private final BigDecimal UPDATED_PRODUCT_PRICE = BigDecimal.valueOf(11);

    @AfterEach
    void deleteAll() {
        userRepository.deleteAll();
        productRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getStorePageAuthorized() throws Exception {
        mockMvc.
                perform(get("/store"))
                .andExpect(status().isOk())
                .andExpect(view().name("store-page"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void getStorePageAnonymous() throws Exception {
        mockMvc.
                perform(get("/store"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getDetailsPageAuthorized() throws Exception {
        ProductEntity productEntity = initProduct();
        mockMvc.perform(get("/store/details/" + productEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("product-details"))
                .andExpect(model().attributeExists("product"));
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getDetailsPageProductOutOfStock() throws Exception {
        ProductEntity productEntity = initOutOfStockProduct();
        mockMvc.perform(get("/store/details/" + productEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("product-details"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("outOfStock", true));
    }

    @Test
    void getDetailsPageAnonymous() throws Exception {
        ProductEntity productEntity = initProduct();
        mockMvc.perform(get("/store/details/" + productEntity.getId()))
                .andExpect(status().is3xxRedirection());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getProductDetailsNonExistent() throws Exception {
        mockMvc.perform(get("/store/details/" +2))
                .andExpect(status().isNotFound()).andExpect(view().name("product-not-found"));

    }

    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    @Transactional
    void buyProductAuthorized() throws Exception {
        ProductEntity productEntity = initProduct();
        UserRoleEntity userRoleEntity = initUserRole();
        UserEntity userEntity = initUserEntity(userRoleEntity);
        int preBuyQuantityOfProduct = productEntity.getQuantity();
        int preBuyOwnedItemsCount = userEntity.getOwnedItems().size();
        BigDecimal preBuyUserBalance = userEntity.getAccountBalance();
        mockMvc
                .perform(post("/store/buy/" + productEntity.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users/profile"));



        Assertions.assertEquals(preBuyQuantityOfProduct - 1, productEntity.getQuantity() );
        Assertions.assertEquals(preBuyOwnedItemsCount + 1, userEntity.getOwnedItems().size());
        Assertions.assertEquals(userEntity.getAccountBalance(), preBuyUserBalance.subtract(productEntity.getPrice()));
    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    @Transactional
    void buyProductNoQuantity() throws Exception {
        ProductEntity productEntity = initOutOfStockProduct();
        UserRoleEntity userRoleEntity = initUserRole();
        UserEntity userEntity = initUserEntity(userRoleEntity);
        int preBuyQuantityOfProduct = productEntity.getQuantity();
        int preBuyOwnedItemsCount = userEntity.getOwnedItems().size();
        BigDecimal preBuyUserBalance = userEntity.getAccountBalance();
        mockMvc
                .perform(post("/store/buy/" + productEntity.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/store/details/" + productEntity.getId()));




        Assertions.assertEquals(preBuyQuantityOfProduct, productEntity.getQuantity());
        Assertions.assertEquals(preBuyOwnedItemsCount, userEntity.getOwnedItems().size());
        Assertions.assertEquals(userEntity.getAccountBalance(), preBuyUserBalance);
    }
    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    @Transactional
    void buyProductInvalidId() throws Exception {
        ProductEntity productEntity = initOutOfStockProduct();
        UserRoleEntity userRoleEntity = initUserRole();
       initUserEntity(userRoleEntity);
        mockMvc
                .perform(post("/store/buy/" + (productEntity.getId() + 100)).with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(view().name("product-not-found"));

    }

    @WithMockUser(value = "stefan", roles = {"USER"})
    @Test
    @Transactional
    void buyProductInsufficientFunds() throws Exception {
        ProductEntity productEntity = initProduct();
        UserRoleEntity userRoleEntity = initUserRole();
        UserEntity userEntity = initUserEntity(userRoleEntity).setAccountBalance(BigDecimal.valueOf(9));
        int preBuyQuantityOfProduct = productEntity.getQuantity();
        int preBuyOwnedItemsCount = userEntity.getOwnedItems().size();
        BigDecimal preBuyUserBalance = userEntity.getAccountBalance();
        mockMvc
                .perform(post("/store/buy/" + productEntity.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/store/details/" + productEntity.getId()));




        Assertions.assertEquals(preBuyQuantityOfProduct, productEntity.getQuantity());
        Assertions.assertEquals(preBuyOwnedItemsCount, userEntity.getOwnedItems().size());
        Assertions.assertEquals(userEntity.getAccountBalance(), preBuyUserBalance);
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getAddProductPageAuthorized() throws Exception {
        initUserEntity(initAdminRole());
        mockMvc.
                perform(get("/store/add-product"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-product"));
    }
    @WithMockUser(value = "stefan")
    @Test
    void getAddProductPageUnauthorized() throws Exception {
        initUserEntity(initUserRole());
        mockMvc.
                perform(get("/store/add-product"))
                .andExpect(status().isForbidden());
    }

    @Test
    void getAddProductPageAnonymous() throws Exception {
        mockMvc.
                perform(get("/store/add-product"))
                .andExpect(status().is3xxRedirection());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postProductAuthorized() throws Exception {
        initUserEntity(initAdminRole());
        mockMvc.perform(post("/store/add-product")
                .param("name", PRODUCT_NAME)
                .param("quantity", String.valueOf(PRODUCT_QUANTITY))
                .param("price", String.valueOf(PRODUCT_PRICE))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(productRepository.count(), 1);

        Optional<ProductEntity> product = productRepository.findByName(PRODUCT_NAME);

        Assertions.assertTrue(product.isPresent());
        Assertions.assertEquals(product.get().getName(), PRODUCT_NAME);
        Assertions.assertEquals(product.get().getQuantity(), PRODUCT_QUANTITY);
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postProductInvalidInput() throws Exception {
        initUserEntity(initAdminRole());
        mockMvc.perform(post("/store/add-product")
                .param("name", PRODUCT_NAME)
                .param("quantity", String.valueOf(-1))
                .param("price", String.valueOf(PRODUCT_PRICE))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());


        Assertions.assertEquals(productRepository.count(), 0);

        Optional<ProductEntity> product = productRepository.findByName(PRODUCT_NAME);

        Assertions.assertFalse(product.isPresent());
    }
    @WithMockUser(value = "stefan")
    @Test
    void postProductUnauthorized() throws Exception {
        initUserEntity(initUserRole());
        mockMvc.perform(post("/store/add-product")
                .param("name", PRODUCT_NAME)
                .param("quantity", String.valueOf(-1))
                .param("price", String.valueOf(PRODUCT_PRICE))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isForbidden());


        Assertions.assertEquals(productRepository.count(), 0);

        Optional<ProductEntity> product = productRepository.findByName(PRODUCT_NAME);

        Assertions.assertFalse(product.isPresent());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getEditProductPageAuthorized() throws Exception {
        initUserEntity(initAdminRole());
        ProductEntity productEntity = initProduct();
        mockMvc.
                perform(get("/store/edit-product/" + productEntity.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-product"))
                .andExpect(model().attributeExists("product"));
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void getEditProductPageInvalidId() throws Exception {
        initUserEntity(initAdminRole());
        ProductEntity productEntity = initProduct();
        mockMvc.
                perform(get("/store/edit-product/" + (productEntity.getId() + 100)))
                .andExpect(status().isNotFound())
                .andExpect(view().name("product-not-found"));
    }
    @WithMockUser(value = "stefan")
    @Test
    void getEditProductPageUnauthorized() throws Exception {
        initUserEntity(initUserRole());
        ProductEntity productEntity = initProduct();
        mockMvc.
                perform(get("/store/edit-product/" + productEntity.getId()))
                .andExpect(status().isForbidden());
    }

    @Test
    void getEditProductPageAnonymous() throws Exception {
        mockMvc.
                perform(get("/store/edit-product"))
                .andExpect(status().is3xxRedirection());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postEditProductAuthorized() throws Exception {
        initUserEntity(initAdminRole());
        ProductEntity productEntity = initProduct();
        mockMvc.perform(patch("/store/edit-product/" + productEntity.getId())
                .param("name", UPDATED_PRODUCT_NAME)
                .param("quantity", String.valueOf(UPDATED_PRODUCT_QUANTITY))
                .param("price", String.valueOf(UPDATED_PRODUCT_PRICE))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(productRepository.count(), 1);
        Optional<ProductEntity> product = productRepository.findById(productEntity.getId());
        Assertions.assertTrue(product.isPresent());
        Assertions.assertEquals(product.get().getName(),UPDATED_PRODUCT_NAME);
        Assertions.assertEquals(product.get().getQuantity(),UPDATED_PRODUCT_QUANTITY);

    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void postEditProductInvalidId() throws Exception {
        initUserEntity(initAdminRole());
        ProductEntity productEntity = initProduct();
        mockMvc.perform(patch("/store/edit-product/" + (productEntity.getId() + 100))
                .param("name", UPDATED_PRODUCT_NAME)
                .param("quantity", String.valueOf(UPDATED_PRODUCT_QUANTITY))
                .param("price", String.valueOf(UPDATED_PRODUCT_PRICE))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isNotFound()).andExpect(view().name("product-not-found"));

    }
    @WithMockUser(value = "stefan")
    @Test
    void postEditProductUnauthorized() throws Exception {
        initUserEntity(initUserRole());
        ProductEntity productEntity = initProduct();
        mockMvc.perform(patch("/store/edit-product/" + productEntity.getId())
                .param("name", UPDATED_PRODUCT_NAME)
                .param("quantity", String.valueOf(UPDATED_PRODUCT_QUANTITY))
                .param("price", String.valueOf(UPDATED_PRODUCT_PRICE))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isForbidden());

    }

    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void disableProductAuthorized() throws Exception {
        initUserEntity(initAdminRole());
        ProductEntity productEntity = initProduct();
        mockMvc.perform(delete("/store/disable/" + productEntity.getId())
        .with(csrf())).andExpect(status().is3xxRedirection());

        Optional<ProductEntity> disabledProduct = productRepository.findById(productEntity.getId());
        Assertions.assertTrue(disabledProduct.isPresent());
        Assertions.assertTrue(disabledProduct.get().isDisabled());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void disableProductInvalidId() throws Exception {
        initUserEntity(initAdminRole());
        ProductEntity productEntity = initProduct();
        mockMvc.perform(delete("/store/disable/" + (productEntity.getId() + 100))
                .with(csrf())).andExpect(status().isNotFound()).andExpect(view().name("product-not-found"));
    }
    @WithMockUser(value = "stefan")
    @Test
    void disableProductUnauthorized() throws Exception {
        initUserEntity(initUserRole());
        ProductEntity productEntity = initProduct();
        mockMvc.perform(delete("/store/disable/" + productEntity.getId())
                .with(csrf())).andExpect(status().isForbidden());

    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void enableProductAuthorized() throws Exception {
        initUserEntity(initAdminRole());
        ProductEntity productEntity = initProduct().setDisabled(true);
        mockMvc.perform(patch("/store/enable/" + productEntity.getId())
                .with(csrf())).andExpect(status().is3xxRedirection());

        Optional<ProductEntity> enabledProduct = productRepository.findById(productEntity.getId());
        Assertions.assertTrue(enabledProduct.isPresent());
        Assertions.assertFalse(enabledProduct.get().isDisabled());
    }
    @WithMockUser(value = "stefan", roles = {"ADMIN", "USER"})
    @Test
    void enableProductInvalidId() throws Exception {
        initUserEntity(initAdminRole());
        ProductEntity productEntity = initProduct().setDisabled(true);
        mockMvc.perform(patch("/store/enable/" + (productEntity.getId() + 100))
                .with(csrf())).andExpect(status().isNotFound()).andExpect(view().name("product-not-found"));

    }
    @WithMockUser(value = "stefan")
    @Test
    void enableProductUnauthorized() throws Exception {
        initUserEntity(initUserRole());
        ProductEntity productEntity = initProduct().setDisabled(true);
        mockMvc.perform(patch("/store/enable/" + productEntity.getId())
                .with(csrf())).andExpect(status().isForbidden());

    }


        ProductEntity initProduct() {
        ProductEntity productEntity = new ProductEntity()
                .setName(PRODUCT_NAME)
                .setQuantity(PRODUCT_QUANTITY)
                .setPrice(PRODUCT_PRICE)
                .setDisabled(false);

       return productRepository.save(productEntity);
    }
    ProductEntity initOutOfStockProduct() {
        ProductEntity productEntity = new ProductEntity()
                .setName(PRODUCT_NAME)
                .setQuantity(0)
                .setPrice(PRODUCT_PRICE)
                .setDisabled(false);

        return productRepository.save(productEntity);
    }
    UserRoleEntity initUserRole() {
        UserRoleEntity user = new UserRoleEntity().setName(UserRoleEnum.USER);
        return userRoleRepository.save(user);
    }
    UserRoleEntity initAdminRole() {
        UserRoleEntity user = new UserRoleEntity().setName(UserRoleEnum.ADMIN);
        return userRoleRepository.save(user);
    }
    UserEntity initUserEntity(UserRoleEntity userRoleEntity) {
        UserEntity userEntity = new UserEntity()
                .setUsername("stefan")
                .setPassword("12345")
                .setAccountBalance(BigDecimal.valueOf(15))
                .setEmail("stefan@abv.bg");
        userEntity.getRole().add(userRoleEntity);
        return userRepository.save(userEntity);
    }
}
