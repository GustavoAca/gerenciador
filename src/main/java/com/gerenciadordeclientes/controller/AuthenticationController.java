package com.gerenciadordeclientes.controller;


import com.gerenciadordeclientes.domain.user.AuthenticationDto;
import com.gerenciadordeclientes.domain.user.RegisterDto;
import com.gerenciadordeclientes.domain.user.User;
import com.gerenciadordeclientes.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
            return authenticationService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data){
        return authenticationService.register(data);
    }

    @GetMapping
    public Page<User> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return authenticationService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authenticationService.deletar(id);
    }
}
