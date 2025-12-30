package com.p0rto.psicoffice.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p0rto.psicoffice.auth.dto.AuthRequest;
import com.p0rto.psicoffice.auth.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping()
    public ResponseEntity<Void> authenticate(@RequestBody @Valid AuthRequest requestDto) {
        authService.authenticate(requestDto);
        
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
