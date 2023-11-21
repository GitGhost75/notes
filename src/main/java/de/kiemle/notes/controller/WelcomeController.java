package de.kiemle.notes.controller;

import de.kiemle.notes.model.ProblemDetail;
import de.kiemle.notes.model.User;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api")
public class WelcomeController {

    @GetMapping("/welcome")
    public String hello() {
        log.info("calling welcome()");
        return "Welcome Guest!";
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Resource not found",
                    content = {@Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class))})})
    @GetMapping("/welcome/{name}")
    public ResponseEntity<User> welcome(@PathVariable String name) {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new RuntimeException("Name is blank");
        }
        User user = new User(name);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/encrypt/{pwd}")
    public ResponseEntity<String> encrypt(@PathVariable String pwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return ResponseEntity.ok(encoder.encode(pwd));
    }
}
