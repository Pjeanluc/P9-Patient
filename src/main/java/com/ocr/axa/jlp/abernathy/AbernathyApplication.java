package com.ocr.axa.jlp.abernathy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableEncryptableProperties
@SpringBootApplication
public class AbernathyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbernathyApplication.class, args);
    }

}
