package com.example.springboot.dto;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
    private String login;
    private String password;
}
