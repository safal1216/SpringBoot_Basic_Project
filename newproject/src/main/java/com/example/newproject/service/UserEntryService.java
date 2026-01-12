package com.example.newproject.service;
import com.example.newproject.entity.UserEntity;
import com.example.newproject.repository.UserEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class UserEntryService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserEntryRepo userEntryRepo;

    public List<UserEntity> getAllUser() {
        return userEntryRepo.findAll();
    }

    public void saveNewUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("USER"));
        userEntryRepo.save(userEntity);
        log.info("User saved to DB: {}", userEntity.getUsername());
    }

    public void saveAdminUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("USER","ADMIN"));
        userEntryRepo.save(userEntity);
    }

    public void saveUser(UserEntity userEntity) {
        userEntryRepo.save(userEntity);
    }

    public UserEntity getUserByName(String name) {
        return userEntryRepo.findByUsername(name);
    }

}



