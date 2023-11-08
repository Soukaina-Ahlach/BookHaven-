package com.example.onlinebookstore.Controller;

import com.example.onlinebookstore.Repository.UserRepository;

import org.bookhaven.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/js/{fileName:.+}")
    public ResponseEntity<Resource> serveJs(@PathVariable String fileName) {
        Resource resource = new ClassPathResource("static/js/" + fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "text/javascript")
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

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
            @RequestParam("loginUsername") String username,
            @RequestParam("loginPassword") String password
    ) {
        // Retrieve the user from the database by username
        User user = userRepository.findByUsername(username);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

        // You can use Spring Security to set up user authentication and sessions if needed.

        return ResponseEntity.ok("User logged in successfully");
    }

    // Other methods

}
