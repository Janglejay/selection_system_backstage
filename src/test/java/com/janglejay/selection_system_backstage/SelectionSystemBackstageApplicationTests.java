package com.janglejay.selection_system_backstage;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

@SpringBootTest
@Slf4j
class SelectionSystemBackstageApplicationTests {

    @Autowired
    private PasswordEncoder encoder;
    @Test
    void test_password(){
        String root = encoder.encode("root");
        boolean two = encoder.matches("root", root);
        System.out.println(two);

    }
    @Test
    void contextLoads() {
    }

}
