package com.example.demo.Repository;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy", Locale.ENGLISH);

        User user = new User();
        user.setUsername("testUser");
        user.setBirthdate(formatter.parse("01-01-2000"));
        user.setCountry("France");

        entityManager.persist(user);
        entityManager.flush();
    }

    @Test
    public void testFindByUsernameCaseUserFound() {
        User foundUser = userRepository.findByUsername("testUser").orElse(null);
        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
    }

    @Test
    public void testFindByUsernameCaseUserNotFound() {
        User foundUser = userRepository.findByUsername("testUserNotFound").orElse(null);
        assertNull(foundUser);
    }



}