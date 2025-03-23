package com.prueba.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.Security.JwtUtil;
import com.prueba.demo.Service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/token")
    public ResponseEntity<String> generateToken() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("user@example.com");
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }
}