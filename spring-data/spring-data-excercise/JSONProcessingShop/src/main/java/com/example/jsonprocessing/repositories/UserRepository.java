package com.example.jsonprocessing.repositories;

import com.example.jsonprocessing.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE(SELECT COUNT(p) FROM Product p WHERE p.seller.id = u.id and p.buyer.id is not null ) >= 1 ORDER BY u.lastName, u.firstName")
    List<User> findUsersWithMoreThanOneSoldProductOrderByLastNameThenFirstName();

    @Query("SELECT u FROM User u WHERE u.soldProducts.size > 1 ORDER BY u.soldProducts.size DESC, u.lastName")
    List<User> findUsersWithMoreThanOneSoldProductOrderByProductSoldAndLastName();
}
