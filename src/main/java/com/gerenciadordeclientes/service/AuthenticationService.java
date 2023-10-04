package com.gerenciadordeclientes.service;

import com.gerenciadordeclientes.domain.user.AuthenticationDto;
import com.gerenciadordeclientes.domain.user.LoginResponseDto;
import com.gerenciadordeclientes.domain.user.RegisterDto;
import com.gerenciadordeclientes.domain.user.User;
import com.gerenciadordeclientes.infra.security.TokenService;
import com.gerenciadordeclientes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager,
                                 UserRepository userRepository,
                                 TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public ResponseEntity<LoginResponseDto> login(AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    public ResponseEntity register(RegisterDto data) {
        return nonNull(userRepository.findByLogin(data.login()))
                ? ResponseEntity.badRequest().body("Usuário já existe")
                : cadastrar(data);
    }

    private ResponseEntity cadastrar(RegisterDto data){
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
