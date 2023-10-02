package com.example.LibraryManagement.Controllers;

import com.example.LibraryManagement.Config.JwtConfig;
import com.example.LibraryManagement.DTO.AuthenticationRequest;
import com.example.LibraryManagement.DTO.AuthenticationResponse;
import com.example.LibraryManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtConfig.generateToken(authentication);
        System.out.println(jwtToken);

        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }
}
