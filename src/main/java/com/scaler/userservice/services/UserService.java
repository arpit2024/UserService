package com.scaler.userservice.services;

import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.TokenRepository;
import com.scaler.userservice.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService  {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    public UserService(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder,TokenRepository tokenRepository){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository=tokenRepository;

    }

//------------------------------------------------------------------------------------------------
    public User SignUp(String fullname, String email, String password){
        User u=new User();
        u.setEmail(email);
        u.setName(fullname);
        u.setHashedPassword(bCryptPasswordEncoder.encode(password));
        User user=userRepository.save(u);
        return user;
    }
//-------------------------------------------------------------------------------------------------------------------

/*the question is can i find if the user is present in DB by input email & password-No as PWD will
 be present in hashed form- so use only email to find user in DB-for that right a methos in UserRepository*/
    public Token login(String email, String Password) {

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return null;
            //here throw user not exist exception
        }
        User user = userOptional.get();
/*Q) now should i check the brypt of user.get() and compare the pwd to the input pwd-NO
        String hp=bCryptPasswordEncoder.encode(Password);
        if(hp!=user.getHashedPassword()) return null;
    NO- because the hashed password will be different for the same password */
        if (!bCryptPasswordEncoder.matches(Password, user.getHashedPassword())) {
            return null;
            //here throw password not matching exception
        }
//  Creating a Date object from system date

        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plus(30, ChronoUnit.DAYS);
        // Convert LocalDate to Date
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //create a token and return it
        Token token = new Token();
        token.setUser(user);
        token.setExpiryAt(expiryDate);
    //As of now trying to generate a random string as pwd
        token.setValue(RandomStringUtils.randomAlphanumeric(128));

        Token savedToken=tokenRepository.save(token);
        return token;
    }

    @Transactional
    public void logout(String token){
/*  so instead of deleting the token completely, we can SoftDelete it.
    i.e., we don't delete the data instead we just set isDeleted column as TRUE.(History still exists) */
        Optional<Token> token1=tokenRepository.findByValueAndDeletedEquals(token,false);
        if(token1.isEmpty()){
        //throw Tokennotexists or AlreadyExpired exception
            return;
        }
        Token tkn=token1.get();
        tkn.setDeleted(true);
        tokenRepository.save(tkn);
        return;
    }

    //if token(input) is null it should be validated by controller or ude @NotNull in controller method
    public User validateToken(String token) {
        Optional<Token> tkn = tokenRepository.
                findByValueAndDeletedEqualsAndExpiryAtGreaterThan(token, false, new Date());

        if (tkn.isEmpty()) {
            return null;
        }

        return tkn.get().getUser();
    }
}

//Here we are not using User Interface- if we think we need multiple implementations of User,
// we can use User Interface