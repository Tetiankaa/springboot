package com.example.springboot.models;

import lombok.*;

@Data
//@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientUserDTO {
    private String username;
    private String password;

//    public ClientUserDTO(ClientUser clientUser) {
//        this.email = clientUser.getEmail();
//        this.password = clientUser.getPassword();
//    }
}
