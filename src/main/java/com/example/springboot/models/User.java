package com.example.springboot.models;

import com.example.springboot.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(value = Views.Admin.class)
    private int id;

    @NotBlank(message = "Name can't be empty")
    @Size(min = 2,message = "Name too short")
    @Size(max = 10,message = "Name too long")
    @JsonView(value = {Views.Client.class,Views.Admin.class})
    private String name;

    @Min(value = 0,message = "Age can't be negative")
    @Max(value = 123,message = "Too old")
    @JsonView(value = {Views.Client.class,Views.Admin.class})
    private int age;

    private String avatar;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
