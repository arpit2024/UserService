package com.scaler.userservice.repositories;

import com.scaler.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);

    Optional<Token> findByValueAndDeletedEquals(String value,boolean isDeleted);

/*Q)can we not find by token id?
->we can,but when a user is going to log out, will it give the token id or will it give the value of the token id is just a long id, but token value is the real token which we will send to the front end

Q)won't it take huge time to search token in string?
->->Yes, that is why in reality, we will store them in cache or create index on the value column in the db;
or we will have an index on the token value column, so it will be very fast*/

    Optional<Token> findByValueAndDeletedEqualsAndExpiryAtGreaterThan(String value, boolean isDeleted, Date expiryGreaterThan);


}
