package softuni.exam.instagraphlite.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.exam.instagraphlite.models.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
    @Query("SELECT u FROM User u ORDER BY u.posts.size DESC, u.id")
    List<User> findUsersWithPostsOrderByCountDescAndId();
}
