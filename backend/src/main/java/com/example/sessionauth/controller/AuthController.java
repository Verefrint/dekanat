package com.example.sessionauth.controller;

import com.example.sessionauth.dto.AuthDTO;
import com.example.sessionauth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthDTO authDTO,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        return ResponseEntity
                .status(CREATED)
                .body(this.authService.register(authDTO, request, response));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(
            @Valid @RequestBody AuthDTO authDTO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return new ResponseEntity<>(authService.login(authDTO, request, response), OK);
    }

    @GetMapping(path = "/authenticated")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String getAuthenticated(Authentication authentication) {
        return "Admin name is " + authentication.getName();
    }

    @GetMapping(path = "/anyuser")
    public String testEndpointOnlyUserAndAdminCanHit(Authentication authentication) {
        return "An Admin or user can hit this rout is " + authentication.getName();
    }
}
