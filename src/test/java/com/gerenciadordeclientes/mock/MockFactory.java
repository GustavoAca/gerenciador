package com.gerenciadordeclientes.mock;

import com.gerenciadordeclientes.domain.user.RegisterDto;
import com.gerenciadordeclientes.domain.user.Role;
import org.springframework.stereotype.Component;

@Component
public class MockFactory {

    public RegisterDto registrar(String login){
       return new RegisterDto(login,"12345", Role.USER,"Grimal");
    }
}
