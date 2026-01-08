package com.example.newproject.service;

import com.example.newproject.entity.UserEntity;
import com.example.newproject.repository.UserEntryRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserEntryRepo userEntryRepo;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUsernameTest(){
        UserEntity userEntity = UserEntity.builder()
                .username("ram")
                .password("inrinrick")
                .roles(new ArrayList<>())
                .build();

        when(userEntryRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn(userEntity);        UserDetails user = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
        Assertions.assertEquals("ram", user.getUsername());
    }

}
