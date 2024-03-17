package com.scaler.userservice.repositories;

import com.scaler.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    Optional<User> findByEmail(String email);
    //use optional, because there might not be any user with this email-optional will return null(good for us).


}
