package com.example.demo.Controller;

import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserControllerIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegisterUserAndFetchIt() throws Exception {
        final File jsonFile = new ClassPathResource("test/post_request_body.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());


        this.mockMvc.perform(post("/user/")
                        .contentType(APPLICATION_JSON)
                        .content(userToCreate))
                .andDo(print())
                .andExpect(status().isCreated());

        assertTrue(this.userRepository.findByUsername("john_doe").isPresent());

        this.mockMvc.perform(get("/user/")
                        .param("username", "john_doe"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.username", is("john_doe")))
                .andExpect(jsonPath("$.birthdate", is("1990-01-15")))
                .andExpect(jsonPath("$.country", is("France")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.phoneNumber", is("+1234567890")));

    }

    @Test
    public void testAddUserCaseInvalidInput() throws Exception {
        final File jsonFile = new ClassPathResource("test/post_request_body_invalid.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());

        mockMvc.perform(post("/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userToCreate))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", containsInAnyOrder(
                        "User birthdate must be valid (yyyy-MM-dd format)",
                        "User must be French.",
                        "User gender must be valid (Male, Female, Other or Prefer_not_to_say)",
                        "User must have a username.",
                        "User phone number must be valid"
                )));
        ;
    }

    @Test
    public void testAddUserCaseEmptyInput() throws Exception {
        final File jsonFile = new ClassPathResource("test/post_request_body_empty.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());

        mockMvc.perform(post("/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userToCreate))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", containsInAnyOrder(
                        "User must have a birthdate.",
                        "User must have a country.",
                        "User must have a username."
                )));


    }

}