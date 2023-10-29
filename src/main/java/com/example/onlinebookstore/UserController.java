package com.example.onlinebookstore;

import jakarta.validation.Valid;

import org.bookhaven.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // This should match the name of your HTML file (e.g., "index.html").
    }

    @GetMapping("/css/{fileName:.+}")
    public ResponseEntity<Resource> serveCss(@PathVariable String fileName) {
        Resource resource = new ClassPathResource("static/css/" + fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "text/css")
                .body(resource);
    }

    @GetMapping("/images/{fileName:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String fileName) {
        Resource resource = new ClassPathResource("static/images/" + fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/png") // Change the content type as needed
                .body(resource);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (userRepository.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }


//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@Valid @RequestBody User user) {
//        Logger.debug("MOVED PAST APPLICATION");
//        if (userRepository.existsByUsername(user.getUsername())) {
//            return ResponseEntity.badRequest().body("Username already exists");
//        }
//        if (userRepository.existsByEmail(user.getEmail())) {
//            return ResponseEntity.badRequest().body("Email already exists");
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        userRepository.save(user);
//
//        return ResponseEntity.ok("User registered successfully");
//    }

    // Other methods

}
