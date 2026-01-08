package com.example.newproject.service;

import com.example.newproject.repository.UserEntryRepo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserEntryRepo userEntryRepo;

    @ParameterizedTest
    @CsvSource({
            "Ram",
            "Raaa",
            "Raa"
    })
    public void testFindByUserName(String name){
        assertNotNull(userEntryRepo.findByUsername(name));
    }

}
