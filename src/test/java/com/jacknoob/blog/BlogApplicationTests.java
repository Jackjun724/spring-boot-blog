package com.jacknoob.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Inject
    PasswordEncoder passwordEncoder;

    @Test
    public void contextLoads() {
        System.out.println(passwordEncoder.encode("123123"));
    }

}
