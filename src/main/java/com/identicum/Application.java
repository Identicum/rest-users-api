package com.identicum;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.identicum.service.UserRepository;
import com.identicum.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Application {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadUsers() throws JsonParseException, JsonMappingException, IOException {
        URL fileUrl = this.getClass().getClassLoader().getResource("users.json");
        if (this.userRepository.count() == 0 && fileUrl != null) {
            File file = new File(fileUrl.getFile());
            log.debug("Loading users from json file");
            userService.loadUsersFromFile(file);
        }
    }
}