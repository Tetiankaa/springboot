package com.example.springboot.models;

import lombok.*;

@Getter
@Setter
public class ClientUserDTO {
    private String email;
    private String password;

    public ClientUserDTO(ClientUser clientUser) {
        this.email = clientUser.getEmail();
        this.password = clientUser.getPassword();
    }
}
