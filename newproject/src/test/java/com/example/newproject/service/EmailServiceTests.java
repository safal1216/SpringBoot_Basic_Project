package com.example.newproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService email;

    @Test
    public void sendMailTest(){
        email.sendMail("safalmehrotra1612@gmail.com","Testing java","Hi, aap kaise ho");
    }
}
