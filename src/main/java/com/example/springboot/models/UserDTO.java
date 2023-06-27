package com.example.springboot.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String name;
    private int age;
    private  String avatar;

    public UserDTO(User user) {
        this.name=user.getName();
        this.age= user.getAge();
        this.avatar= user.getAvatar();
    }

    //DTOs are commonly used in web APIs where data needs to be serialized and sent over HTTP.
    // They provide a convenient way to define the structure of the data being transferred and
    // ensure consistency in the communication between the client and server.
}
