package com.example.springboot.models;

import com.example.springboot.views.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Superadmin.class)
    private int id;

    @NotBlank(message = "Please provide name")
    @Size(min = 3,message = "provide at least 3 characters")
    @Size(max = 255,message = "too long name")
    @JsonView({View.Superadmin.class,View.Admin.class})
    private String name;

    @Max(value = 120,message = "Too high age")
    @Min(value = 0,message = "Age can't be less than 0")
    @JsonView({View.Superadmin.class,View.Admin.class})
    private int age;

    private String password;
}
