package com.p0rto.psicoffice.register.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.p0rto.psicoffice.register.dto.RegisterRequest;
import com.p0rto.psicoffice.register.service.RegisterService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest requestDto) {
        registerService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
